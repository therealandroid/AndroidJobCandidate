package com.storytel.network.api


import com.storytel.network.dto.CommentDto
import com.storytel.network.dto.PhotoDto
import com.storytel.network.dto.PostAndPhotoDto
import com.storytel.network.dto.PostDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService{
    @GET("/posts")
    suspend fun getPosts(): List<PostDto>

    @GET("/photos")
    suspend fun getPhotos(): List<PhotoDto>
    @GET("/posts/{id}/comments")
    suspend fun getComments(@Path("id") postId: Int): List<CommentDto>
}