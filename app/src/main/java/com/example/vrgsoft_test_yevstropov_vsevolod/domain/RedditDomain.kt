package com.example.vrgsoft_test_yevstropov_vsevolod.domain

data class RedditDomain(
    val id: Int,

    val key: String,

    val author: String,

    val createdUtc: Long,

    val image: String?,

    val commentCount: Int
)