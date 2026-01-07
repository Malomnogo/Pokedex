package com.malomnogo.network.di

import com.malomnogo.network.PokemonService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

val networkModule =
    module {
        single {
            Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
        }

        single {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        single {
            OkHttpClient
                .Builder()
                .addInterceptor(get<HttpLoggingInterceptor>())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        single {
            val json = get<Json>()
            Retrofit
                .Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .client(get())
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
        }

        single {
            get<Retrofit>().create(PokemonService::class.java)
        }
    }
