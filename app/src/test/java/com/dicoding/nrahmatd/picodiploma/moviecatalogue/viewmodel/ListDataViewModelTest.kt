package com.dicoding.nrahmatd.picodiploma.moviecatalogue.viewmodel

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ListDataViewModelTest {
    private lateinit var listDataViewModel: ListDataViewModel
    private var totalMovieData = 12
    private var totalTVData = 11

    @Before
    fun setUp() {
        listDataViewModel = ListDataViewModel()
    }

    @Test
    fun getMovieList() {
        val movieDataList = listDataViewModel.getMovieList()
        assertTrue(!movieDataList.isNullOrEmpty())
        assertEquals(totalMovieData, movieDataList.size)
    }

    @Test
    fun getTvShowList() {
        val tvDataList = listDataViewModel.getTvShowList()
        assertTrue(!tvDataList.isNullOrEmpty())
        assertEquals(totalTVData, tvDataList.size)
    }
}
