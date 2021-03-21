package app.storytel.candidate.com.shared.extensions

import app.storytel.candidate.com.model.Comment
import app.storytel.candidate.com.model.Photo
import app.storytel.candidate.com.model.Post
import com.storytel.network.dto.CommentDto
import com.storytel.network.dto.PostAndPhotoDto

fun PostAndPhotoDto.toModel(): Post {
    val post = Post()
    post.id = this.post.id
    post.body = this.post.body
    post.title = this.post.title
    post.photo = Photo(this.photo.url, this.photo.thumbnailUrl)
    return post
}

fun CommentDto.toModel(): Comment {
    val comment = Comment()
    comment.id = this.id
    comment.name = this.name
    comment.body = this.body
    comment.postId = this.postId
    comment.email = this.email
    return comment
}
