package com.dicoding.nrahmatd.picodiploma.moviecatalogue.view.activity

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dicoding.nrahmatd.R
import com.dicoding.nrahmatd.picodiploma.core.utils.DummyData
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.constant.Constant
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DetaiMovieActivityTest {

    private val dummyMovieId = DummyData.getMovieList()[0].id
    private val dummyMovieTitle = DummyData.getMovieList()[0].titleMovie
    private val dummyMovieDate = DummyData.getMovieList()[0].dateMovie
    private val dummyMovieDesc = DummyData.getMovieList()[0].descMovie
    private val dummyMovieLang = DummyData.getMovieList()[0].langMovie
    private val dummyMovieRuntime = DummyData.getMovieList()[0].runtimeMovie
    private val dummyMovieGenre = DummyData.getMovieList()[0].genresMovie
    private val intentMovieDetail =
        Intent(ApplicationProvider.getApplicationContext(), DetailActivity::class.java)
            .putExtra("loadFrom", Constant.MOVIETYPE)
            .putExtra("movie_id", dummyMovieId)

    @get:Rule
    val movieDetailRule = ActivityScenarioRule<DetailActivity>(intentMovieDetail)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun assertGetDetailMovie() {
        movieDetailRule.scenario
        onView(withId(R.id.imgdetail)).check(matches(isDisplayed()))
        onView(withId(R.id.txtdetail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.txtdetail_title)).check(matches(withText(dummyMovieTitle)))
        onView(withId(R.id.txtdetail_released)).check(matches(isDisplayed()))
        onView(withId(R.id.txtdetail_released)).check(matches(withText(dummyMovieDate)))
        onView(withId(R.id.txtdetail_language)).check(matches(isDisplayed()))
        onView(withId(R.id.txtdetail_language)).check(matches(withText(dummyMovieLang)))
        onView(withId(R.id.txtdetail_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.txtdetail_genres)).check(matches(withText(dummyMovieGenre)))
        onView(withId(R.id.txtdetail_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.txtdetail_runtime)).check(matches(withText(dummyMovieRuntime)))
        onView(withId(R.id.txtdetail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.txtdetail_overview)).check(matches(withText(dummyMovieDesc)))
        onView(withId(R.id.imgdetail_fb)).perform(click())
        onView(withId(R.id.imgdetail_twt)).perform(click())
        onView(withId(R.id.imgdetail_insta)).perform(click())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }
}
