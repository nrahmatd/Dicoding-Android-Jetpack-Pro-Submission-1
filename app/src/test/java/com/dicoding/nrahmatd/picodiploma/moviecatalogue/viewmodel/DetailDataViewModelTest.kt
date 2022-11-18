package com.dicoding.nrahmatd.picodiploma.moviecatalogue.viewmodel

import com.dicoding.nrahmatd.picodiploma.core.utils.DummyData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailDataViewModelTest {

    private lateinit var detailDataViewModel: DetailDataViewModel
    private val dummyMovieId = DummyData.getMovieList()[0].id
    private val dummyMovieImage = DummyData.getMovieList()[0].imgMovie
    private val dummyMovieTitle = DummyData.getMovieList()[0].titleMovie
    private val dummyMovieDate = DummyData.getMovieList()[0].dateMovie
    private val dummyMovieDesc = DummyData.getMovieList()[0].descMovie
    private val dummyMovieLang = DummyData.getMovieList()[0].langMovie
    private val dummyMovieRuntime = DummyData.getMovieList()[0].runtimeMovie
    private val dummyMovieFb = DummyData.getMovieList()[0].fbAccountMovie
    private val dummyMovieTwitter = DummyData.getMovieList()[0].twitterAccountMovie
    private val dummyMovieInsta = DummyData.getMovieList()[0].instaAccountMovie
    private val dummyMovieGenre = DummyData.getMovieList()[0].genresMovie

    private val dummyTVId = DummyData.getTVList()[0].id
    private val dummyTVImage = DummyData.getTVList()[0].imgTV
    private val dummyTVTitle = DummyData.getTVList()[0].titleTV
    private val dummyTVDate = DummyData.getTVList()[0].dateTV
    private val dummyTVDesc = DummyData.getTVList()[0].descTV
    private val dummyTVLang = DummyData.getTVList()[0].langTV
    private val dummyTVRuntime = DummyData.getTVList()[0].runtimeTV
    private val dummyTVFb = DummyData.getTVList()[0].fbAccountTV
    private val dummyTVTwitter = DummyData.getTVList()[0].twitterAccountTV
    private val dummyTVInsta = DummyData.getTVList()[0].instaAccountTV
    private val dummyTVGenre = DummyData.getTVList()[0].genresTV

    @Before
    fun setUp() {
        detailDataViewModel = DetailDataViewModel()
    }

    @Test
    fun getMovieDetail() {
        val movieDetail = detailDataViewModel.getMovieDetail(dummyMovieId)
        assertNotNull(movieDetail)
        assertEquals(dummyMovieId, movieDetail.id)
        assertEquals(dummyMovieImage, movieDetail.imgMovie)
        assertEquals(dummyMovieTitle, movieDetail.titleMovie)
        assertEquals(dummyMovieDate, movieDetail.dateMovie)
        assertEquals(dummyMovieDesc, movieDetail.descMovie)
        assertEquals(dummyMovieLang, movieDetail.langMovie)
        assertEquals(dummyMovieRuntime, movieDetail.runtimeMovie)
        assertEquals(dummyMovieFb, movieDetail.fbAccountMovie)
        assertEquals(dummyMovieTwitter, movieDetail.twitterAccountMovie)
        assertEquals(dummyMovieInsta, movieDetail.instaAccountMovie)
        assertEquals(dummyMovieGenre, movieDetail.genresMovie)
    }

    @Test
    fun getTvDetail() {
        val tvDetail = detailDataViewModel.getTvDetail(dummyTVId)
        assertNotNull(tvDetail)
        assertEquals(dummyTVId, tvDetail.id)
        assertEquals(dummyTVImage, tvDetail.imgTV)
        assertEquals(dummyTVTitle, tvDetail.titleTV)
        assertEquals(dummyTVDate, tvDetail.dateTV)
        assertEquals(dummyTVDesc, tvDetail.descTV)
        assertEquals(dummyTVLang, tvDetail.langTV)
        assertEquals(dummyTVRuntime, tvDetail.runtimeTV)
        assertEquals(dummyTVFb, tvDetail.fbAccountTV)
        assertEquals(dummyTVTwitter, tvDetail.twitterAccountTV)
        assertEquals(dummyTVInsta, tvDetail.instaAccountTV)
        assertEquals(dummyTVGenre, tvDetail.genresTV)
    }
}
