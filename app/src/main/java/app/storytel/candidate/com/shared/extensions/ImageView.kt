package app.storytel.candidate.com.shared.extensions

import android.widget.ImageView
import app.storytel.candidate.com.R
import com.squareup.picasso.Picasso

//Implementing a image load extension to avoid
//library dependency
fun ImageView.loadImageAsync(url: String?){
    Picasso.get().load(url)
            .placeholder(R.drawable.ic_baseline_broken_image_24)
            .into(this)
}