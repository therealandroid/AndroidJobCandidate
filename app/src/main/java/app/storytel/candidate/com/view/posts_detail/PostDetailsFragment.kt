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
import app.storytel.candidate.com.model.Post
import app.storytel.candidate.com.shared.extensions.loadImageAsync
import app.storytel.candidate.com.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.empty_view_layout.*
import kotlinx.android.synthetic.main.fragment_post_details.*
import kotlinx.android.synthetic.main.fragment_posts.*

import org.koin.android.ext.android.inject

class PostDetailsFragment : Fragment() {

    private val postsViewModel: PostsViewModel by inject()
    private val args by navArgs<PostDetailsFragmentArgs>()

    //Save the bundle post so we can use later
    private var post: Post? = null

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
        initListeners()
    }

    private fun setupBundleData() {
        post = args.post

        post?.let {
            details.text = it.title
            backdrop.loadImageAsync(it.photo?.url)
            loadComments(it.id)
        }

    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbarPostDetail.setupWithNavController(navController, appBarConfiguration)
    }

    private fun initListeners() {
        empty_view_retry_button.setOnClickListener {
            post?.id?.let { loadComments(it) }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack(R.id.listPostsFragment, false)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadComments(postId: Int) {
        postsViewModel.getComments(postId)

        postsViewModel.loadingCommentsLiveData.observe(requireActivity(), { isLoading ->
            commentsLoading?.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        postsViewModel.getCommentsLiveData.observe(requireActivity(), {
            setupAdapter(it)
        })

        postsViewModel.errorCommentsLoadingLiveData.observe(requireActivity(), { hasError ->
            if (hasError)
                error_view_post_details?.visibility = View.VISIBLE
            else
                error_view_post_details?.visibility = View.GONE
        })
    }

    private fun setupAdapter(comments: MutableList<Comment>) {
        val adapter = CommentsAdapter()
        adapter.setData(comments)
        recyclerPostsDetail?.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}

