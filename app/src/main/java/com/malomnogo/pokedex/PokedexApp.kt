package com.malomnogo.pokedex

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import com.malomnogo.common.AppDispatchers
import com.malomnogo.common.HandleError
import com.malomnogo.data.PokemonCloudDataSource
import com.malomnogo.list.api.ListFeatureDependencies
import com.malomnogo.pokedex.di.AppComponent
import com.malomnogo.pokedex.di.DaggerAppComponent

class PokedexApp :
    Application(),
    ImageLoaderFactory,
    ListFeatureDependencies {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    override fun newImageLoader(): ImageLoader =
        ImageLoader
            .Builder(this)
            .components {
                add(SvgDecoder.Factory())
            }.build()

    override fun appDispatchers(): AppDispatchers = appComponent.appDispatchers()

    override fun handleError(): HandleError = appComponent.handleError()

    override fun pokemonCloudDataSource(): PokemonCloudDataSource = appComponent.pokemonCloudDataSource()
}
