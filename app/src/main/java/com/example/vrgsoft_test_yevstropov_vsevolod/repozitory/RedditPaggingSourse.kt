package com.example.vrgsoft_test_yevstropov_vsevolod.repozitory

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.vrgsoft_test_yevstropov_vsevolod.Repozitory.Companion.NETWORK_PAGE_SIZE
import com.example.vrgsoft_test_yevstropov_vsevolod.api.RedditApiService
import com.example.vrgsoft_test_yevstropov_vsevolod.domain.RedditDomain
import com.example.vrgsoft_test_yevstropov_vsevolod.dto.RedditApiDto
import com.example.vrgsoft_test_yevstropov_vsevolod.dto.asDomainModel
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.sign
import kotlin.time.milliseconds

private const val REDDIT_STARTING_PAGE_INDEX = 1

class RedditPagingSource(
    private val service: RedditApiService
) : PagingSource<String, RedditDomain>() {   // RedditApiDto - дата класс из сети
    override suspend fun load(params: LoadParams<String>): LoadResult<String, RedditDomain> {
        val previousKey = params.key
        return try {
            val respons = service.getListRedditApiDto(after = previousKey).body()
            val repos = respons?.let { asDomainModel(it) }
            val nextKey = if (repos == null) {
                null
            } else {
                repos.get(repos.size - 1).key
            }
            LoadResult.Page(
                data = repos!!,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<String, RedditDomain>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.nextKey
        }
    }
}