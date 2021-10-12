package com.example.vrgsoft_test_yevstropov_vsevolod.dto

import com.example.vrgsoft_test_yevstropov_vsevolod.domain.RedditDomain
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.NonCancellable.children

data class RedditApiDto(val data: RedditListingDto?) {

    data class RedditListingDto(val children: List<PostContainerDto>?) {

        data class PostContainerDto(val data: RedditPostDto?) {
            data class RedditPostDto(
                @SerializedName("name")
                val key: String?,

                @SerializedName("author")                      // 1
                val author: String?,

                @SerializedName("created_utc")
                val createdUtc: Long?,                               // 2 дата добавления

                @SerializedName("thumbnail")                    //  3  картинка thumbnail
                val image: String?,
                @SerializedName("num_comments")                 //  4
                val commentCount: Int?

            )
        }
    }
}

fun asDomainModel(dto: RedditApiDto): List<RedditDomain> {
    val answerlist = mutableListOf<RedditDomain>()
    //val base: Base? =null

    dto.data?.children?.forEachIndexed { index, it ->
        answerlist.add(
            RedditDomain(
                id = index,
                key = it.data?.key?:"",
                author = it.data?.author?:"",
                createdUtc = it.data?.createdUtc?:System.currentTimeMillis(),
                image = it.data?.image,
                commentCount = it.data?.commentCount?:0
            )
        )
    }
    return answerlist
}
