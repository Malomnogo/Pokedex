package com.malomnogo.list.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.malomnogo.data.PokemonCloudDataSource
import com.malomnogo.domain.PokemonDomain

class PokemonPagingSource(
    private val cloudDataSource: PokemonCloudDataSource,
    private val mapper: PokemonCloudMapper<PokemonDomain>,
) : PagingSource<Int, PokemonDomain>() {

    companion object {
        private const val FIRST_GEN_LIMIT = 151
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>) = try {
            val page = params.key ?: 0
            val response = cloudDataSource.fetchPokemonList(page)

            val domainList = response
                .mapNotNull {
                    val id = it.id
                    if (id <= FIRST_GEN_LIMIT) mapper.map(it) else null
                }

            val nextKey = if (domainList.isEmpty() || domainList.last().id >= FIRST_GEN_LIMIT)
                null
            else
                page + 1

            LoadResult.Page(
                data = domainList,
                prevKey = if (page == 0)
                    null
                else
                    page - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
