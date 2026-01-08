package com.malomnogo.pokedex.di

import android.app.Application
import com.malomnogo.common.di.CommonModule
import com.malomnogo.data.di.DataModule
import com.malomnogo.list.api.ListFeatureDependencies
import com.malomnogo.network.di.NetworkModule
import com.malomnogo.ui.di.CoreUiModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DataModule::class,
        CommonModule::class,
        CoreUiModule::class
    ]
)
interface AppComponent : ListFeatureDependencies {
    
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}
