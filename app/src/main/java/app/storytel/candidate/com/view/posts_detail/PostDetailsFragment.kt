package app.storytel.candidate.com.view.posts_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import app.storytel.candidate.com.R
import app.storytel.candidate.com.model.Comment
import app.storytel.candidate.com.shared.extensions.loadImageAsync
import app.storytel.candidate.com.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.fragment_post_details.*

import org.koin.android.ext.android.inject

class PostDetailsFragment : Fragment() {

    private val postsViewModel: PostsViewModel by inject()
    private val args by navArgs<PostDetailsFragmentArgs>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBundleData()
        setupToolbar()
    }

    private fun setupBundleData() {
        val post = args.post
        details.text = post?.title
        backdrop.loadImageAsync(post?.photo?.url)
        post?.id?.let { loadComments(it) }
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbarPostDetail.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack(R.id.listPostsFragment, false)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun loadComments(postId: Int) {
        postsViewModel.getComments(postId)

        postsViewModel.loadingCommentsLiveData.observe(requireActivity(), { isLoading ->
            commentsLoading?.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        postsViewModel.getCommentsLiveData.observe(requireActivity(), {
            setupAdapter(it)
        })
    }

    private fun setupAdapter(comments: MutableList<Comment>) {
        val adapter = CommentsAdapter()
        adapter.setData(comments)
        recyclerPostsDetail.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}

