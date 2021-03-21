package app.storytel.candidate.com.model

import java.io.Serializable

data class Post(
        var id:Int = 0,
        var title: String? = null,
        var body: String? = null,
        var photo: Photo? = null
): Serializable