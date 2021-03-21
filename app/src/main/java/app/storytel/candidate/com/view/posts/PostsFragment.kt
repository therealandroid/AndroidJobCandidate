package app.storytel.candidate.com.view.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.storytel.candidate.com.R

//Find a more appropriated name and renamed the class (Old name was scrolling activity)
//Also changed from activity to fragment so we can use or cool navigation controller
class PostsFragment : Fragment() {
    //removing static URLs and passed them to the appropriate modue

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}