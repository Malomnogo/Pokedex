package com.malomnogo.lsit.presentation.di

import com.malomnogo.list.api.ListFeatureDependencies
import com.malomnogo.list.data.di.FeatureListDataModule
import com.malomnogo.ui.di.CoreUiModule
import dagger.Component
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureScope

@FeatureScope
@Component(
    dependencies = [ListFeatureDependencies::class],
    modules = [
        FeatureListPresentationModule::class,
        FeatureListDataModule::class,
        CoreUiModule::class,
    ],
)
interface ListComponent {
    fun viewModelFactory(): com.malomnogo.ui.di.DaggerViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(dependencies: ListFeatureDependencies): ListComponent
    }
}
