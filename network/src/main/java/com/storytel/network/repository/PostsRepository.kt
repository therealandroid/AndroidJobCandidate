package com.storytel.network.repository

import com.storytel.network.api.PostService
import com.storytel.network.dto.CommentDto
import com.storytel.network.dto.PostAndPhotoDto
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip

class PostsRepository(private val postService: PostService) : IPostsRepository {

    override suspend fun getPostAndPhoto(): List<PostAndPhotoDto> {
        val postsAndPhotos = arrayListOf<PostAndPhotoDto>()

        //Combine photos and posts requests into one object
        flowOf(postService.getPosts())
                .zip(flowOf(postService.getPhotos())) { posts, photos ->
                    //reduce to the images to same amount of posts
                    //so we don't need to random images inside the adapter like old solution
                    val photos = photos.take(posts.size)

                    posts.mapIndexed { index, postDto ->
                        postsAndPhotos.add(PostAndPhotoDto(postDto, photos[index]))
                    }
                }.collect()

        return postsAndPhotos
    }

    /*
     * Get the comments from the API
     * postID: Id of the post
     * size: maximum items you want to retrieve from the list
     *
     */
    override suspend fun getTopComments(postId: Int, size: Int): List<CommentDto> {
        return postService.getComments(postId).take(size).toMutableList()
    }


}