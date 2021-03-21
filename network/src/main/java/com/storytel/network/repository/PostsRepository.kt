package com.storytel.network.repository

import com.storytel.network.BuildConfig
import com.storytel.network.api.PostService
import com.storytel.network.dto.CommentDto

import com.storytel.network.dto.PostAndPhotoDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class PostsRepository(private val postService: PostService) : IPostsRepository {

    override suspend fun getPostAndPhoto(): List<PostAndPhotoDto> {
        val postsAndPhotos = arrayListOf<PostAndPhotoDto>()

        if(BuildConfig.DEBUG){
            delay(500)
        }

        flowOf(postService.getPosts())
                .zip(flowOf(postService.getPhotos())) { posts, photos ->
                    val photos = photos.take(posts.size)

                    posts.mapIndexed { index, postDto ->
                        postsAndPhotos.add(PostAndPhotoDto(postDto, photos[index]))
                    }
                }.collect()

        return postsAndPhotos
    }

    override suspend fun getTopComments(postId: Int, size: Int): List<CommentDto> {
        return postService.getComments(postId).take(size).toMutableList()
    }


}