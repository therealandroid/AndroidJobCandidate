package app.storytel.candidate.com.shared.extensions

import android.widget.ImageView
import app.storytel.candidate.com.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.loadImageAsync(url: String?){
    Glide.with(this.context)
            .load(url)
            .crossFade()
            .placeholder(R.drawable.ic_baseline_broken_image_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
}