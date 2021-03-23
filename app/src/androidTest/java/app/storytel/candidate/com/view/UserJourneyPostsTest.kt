package app.storytel.candidate.com.view


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import app.storytel.candidate.com.R
import app.storytel.candidate.com.view.posts.PostAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class UserJourneyPostsTest {


    @get:Rule
    var activityRule: ActivityScenarioRule<NavigationActivity> =
            ActivityScenarioRule(NavigationActivity::class.java)

    /*
     * Wait until the list of posts is displayed in the screen
     */
    @Before
    fun prepareScenario() {
        waitUntilViewIsDisplayed(withId(R.id.recyclerPosts))
    }

    /*
     * verify if the recycler is displayed in the view correctly
     */
    @Test
    fun posts_list_must_show_successfully() {
        onView(withId(R.id.recyclerPosts)).check(matches(isDisplayed()))
    }

    /*
     * select list items should open the post detail fragment
     */
    @Test
    fun test_click_posts_list_open_post_detail_fragment_successfully() {
        val recyclerView = onView(withId(R.id.recyclerPosts))
        recyclerView.check(matches(isDisplayed()))
        recyclerView.perform(actionOnItemAtPosition<PostAdapter.PostViewHolder>(2, click()))
        onView(withId(R.id.post_detail_fragment_container)).check(matches(isDisplayed()))
    }

    /*
     * select list items should open the post detail fragment and
     * press back
     */
    @Test
    fun test_click_posts_list_open_post_detail_fragment_and_press_back_successfully() {
        val recyclerView = onView(withId(R.id.recyclerPosts))
        recyclerView.check(matches(isDisplayed()))
        recyclerView.perform(actionOnItemAtPosition<PostAdapter.PostViewHolder>(5, click()))
        onView(withId(R.id.post_detail_fragment_container)).check(matches(isDisplayed()))
        pressBack()
    }



}
