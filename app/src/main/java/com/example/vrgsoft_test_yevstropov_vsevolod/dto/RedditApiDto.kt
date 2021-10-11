package com.example.vrgsoft_test_yevstropov_vsevolod.dto

import com.google.gson.annotations.SerializedName

data class RedditApiDto(val data: RedditListingDto) {

    data class RedditListingDto(val children: List<PostContainerDto>) {

        data class PostContainerDto(val data: RedditPostDto) {
            data class RedditPostDto(
                @SerializedName("name")
                val key: String,
                @SerializedName("title")
                val title: String,
                @SerializedName("score")
                val score: Int,
                @SerializedName("author")                       // 1
                val author: String,

                                                                      // 2 дата добавления

                @SerializedName("thumbnail")                    //  3  картинка thumbnail
                val image: String,
                @SerializedName("num_comments")                 //  4
                val commentCount: Int

            )
        }
    }
}
