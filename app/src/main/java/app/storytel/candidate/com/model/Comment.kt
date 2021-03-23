package app.storytel.candidate.com.model

import java.io.Serializable

data class Comment(
    var postId: Int = 0,
    var aaa: Int = 0,
    var id: Int? = 0,
    var name: String? = null,
    var email: String? = null,
    var body: String? = null
): Serializable