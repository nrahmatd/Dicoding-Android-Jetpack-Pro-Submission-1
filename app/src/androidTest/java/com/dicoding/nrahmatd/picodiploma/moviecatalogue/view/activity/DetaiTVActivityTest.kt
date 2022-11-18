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
class DetaiTVActivityTest {

    private val dummyTVId = DummyData.getTVList()[0].id
    private val dummyTVTitle = DummyData.getTVList()[0].titleTV
    private val dummyTVDate = DummyData.getTVList()[0].dateTV
    private val dummyTVDesc = DummyData.getTVList()[0].descTV
    private val dummyTVLang = DummyData.getTVList()[0].langTV
    private val dummyTVRuntime = DummyData.getTVList()[0].runtimeTV
    private val dummyTVGenre = DummyData.getTVList()[0].genresTV
    private val intentTVDetail =
        Intent(ApplicationProvider.getApplicationContext(), DetailActivity::class.java)
            .putExtra("loadFrom", Constant.TVTYPE)
            .putExtra("tv_id", dummyTVId)

    @get:Rule
    val tvDetailRule = ActivityScenarioRule<DetailActivity>(intentTVDetail)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun assertGetDetailTV() {
        tvDetailRule.scenario
        onView(withId(R.id.imgdetail)).check(matches(isDisplayed()))
        onView(withId(R.id.txtdetail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.txtdetail_title)).check(matches(withText(dummyTVTitle)))
        onView(withId(R.id.txtdetail_released)).check(matches(isDisplayed()))
        onView(withId(R.id.txtdetail_released)).check(matches(withText(dummyTVDate)))
        onView(withId(R.id.txtdetail_language)).check(matches(isDisplayed()))
        onView(withId(R.id.txtdetail_language)).check(matches(withText(dummyTVLang)))
        onView(withId(R.id.txtdetail_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.txtdetail_genres)).check(matches(withText(dummyTVGenre)))
        onView(withId(R.id.txtdetail_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.txtdetail_runtime)).check(matches(withText(dummyTVRuntime)))
        onView(withId(R.id.txtdetail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.txtdetail_overview)).check(matches(withText(dummyTVDesc)))
        onView(withId(R.id.imgdetail_fb)).perform(click())
        onView(withId(R.id.imgdetail_twt)).perform(click())
        onView(withId(R.id.imgdetail_insta)).perform(click())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }
}
