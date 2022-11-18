package com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dicoding.nrahmatd.R
import com.dicoding.nrahmatd.picodiploma.core.utils.DummyData
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.MainActivity
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.RecyclerViewItemCountAssertion
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.withCustomConstraints
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun assertGetMovieData() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.swipe_movie)).perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)))
        onView(withId(R.id.rv_movie)).check(RecyclerViewItemCountAssertion(DummyData.getMovieList().size))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(DummyData.getMovieList().size.minus(1)))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(DummyData.getMovieList().size.minus(1), click()))
    }

    @Test
    fun assertGetTVData() {
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.swipe_tv)).perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)))
        onView(withId(R.id.rv_tv)).check(RecyclerViewItemCountAssertion(DummyData.getTVList().size))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(DummyData.getTVList().size.minus(1)))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(DummyData.getTVList().size.minus(1), click()))
    }
}
