package app.storytel.candidate.com.view.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import app.storytel.candidate.com.BuildConfig
import app.storytel.candidate.com.R
import app.storytel.candidate.com.model.Post
import app.storytel.candidate.com.shared.extensions.loadImageAsync
import app.storytel.candidate.com.view.posts.PostAdapter.PostViewHolder
import app.storytel.candidate.com.view.posts_detail.PostDetailsFragment
import kotlinx.android.synthetic.main.post_item.view.*

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {
    private var posts: MutableList<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    fun setData(posts: MutableList<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) {
            itemView.title.text = post.title
            itemView.body.text = post.body
            itemView.image.loadImageAsync(post.photo?.url)

            itemView.setOnClickListener {
                val directions = PostsFragmentDirections.toPostDetailFragment(post)
                Navigation.findNavController(itemView).navigate(directions)
            }

        }
    }

}