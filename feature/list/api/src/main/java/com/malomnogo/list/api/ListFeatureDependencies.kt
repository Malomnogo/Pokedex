package com.malomnogo.list.api

import com.malomnogo.common.AppDispatchers
import com.malomnogo.common.HandleError
import com.malomnogo.data.PokemonCloudDataSource

interface ListFeatureDependencies {
    fun appDispatchers(): AppDispatchers

    fun handleError(): HandleError

    fun pokemonCloudDataSource(): PokemonCloudDataSource
}
