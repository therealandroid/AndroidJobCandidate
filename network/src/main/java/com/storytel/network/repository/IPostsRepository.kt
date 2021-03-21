package com.storytel.network.repository


import com.storytel.network.dto.CommentDto
import com.storytel.network.dto.PostAndPhotoDto

interface IPostsRepository {
    suspend fun getPostAndPhoto(): List<PostAndPhotoDto>
    suspend fun getTopComments(postId: Int, size: Int): List<CommentDto>
}