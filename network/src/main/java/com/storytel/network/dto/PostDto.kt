package com.storytel.network.dto

data class PostDto(
        var userId: Int = 0,
        var id:Int = 0,
        var title: String? = null,
        var body: String? = null
)