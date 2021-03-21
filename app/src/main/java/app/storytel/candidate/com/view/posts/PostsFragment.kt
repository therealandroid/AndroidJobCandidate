package app.storytel.candidate.com.view.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import app.storytel.candidate.com.R
import app.storytel.candidate.com.model.Post
import app.storytel.candidate.com.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.android.ext.android.inject

class PostsFragment : Fragment() {

    private val postsViewModel: PostsViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveDataObservers()
        setupToolbar()
    }

    private fun setupToolbar(){
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun loadPostsInitial(){
        postsViewModel.getPosts()
    }

    private fun initLiveDataObservers() {
        postsViewModel.getPostsLiveData.observe(viewLifecycleOwner,
                Observer { posts ->
                    setupAdapter(posts)
                })

        postsViewModel.loadingPostsLiveData.observe(viewLifecycleOwner,
                Observer { isLoading ->
                    loading.visibility = if (isLoading) View.VISIBLE else View.GONE
                })

        postsViewModel.errorPostsLoadingLiveData.observe(viewLifecycleOwner,
                Observer { hasError ->

                })

        loadPostsInitial()
    }

    private fun setupAdapter(posts: MutableList<Post>) {
        val adapter = PostAdapter()
        adapter.setData(posts)
        recyclerPosts.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}