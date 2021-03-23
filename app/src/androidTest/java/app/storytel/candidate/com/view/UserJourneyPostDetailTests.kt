package app.storytel.candidate.com.view


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
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
class UserJourneyPostDetailTests {

    @get:Rule
    var activityRule: ActivityScenarioRule<NavigationActivity> =
            ActivityScenarioRule(NavigationActivity::class.java)

    //Prepare scenario for post detail fragment
    @Before
    fun prepareScenario(){
        waitUntilViewIsDisplayed(withId(R.id.recyclerPosts))
        onView(withId(R.id.recyclerPosts)).check(matches(isDisplayed()))
        val recyclerView = onView(withId(R.id.recyclerPosts))
        recyclerView.perform(actionOnItemAtPosition<PostAdapter.PostViewHolder>(2, click()))
    }

    //Verify if clicking in the second post will open with correct data
    @Test
    fun item_clicked_should_open_correctly() {
        waitUntilViewIsDisplayed(withId(R.id.details))
        onView(withId(R.id.details)).check(matches(isDisplayed()))

        val textView = onView(withId(R.id.details))
        textView.check(matches(withText("ea molestias quasi exercitationem repellat qui ipsa sit aut")))
    }

}
