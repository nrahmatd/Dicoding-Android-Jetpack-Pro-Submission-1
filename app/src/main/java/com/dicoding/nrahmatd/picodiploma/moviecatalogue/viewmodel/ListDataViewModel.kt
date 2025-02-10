package com.dicoding.nrahmatd.picodiploma.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.nrahmatd.picodiploma.core.model.MovieModel
import com.dicoding.nrahmatd.picodiploma.core.model.TVModel
import com.dicoding.nrahmatd.picodiploma.core.utils.DummyData

class ListDataViewModel : ViewModel() {
    fun getMovieList(): ArrayList<MovieModel> = DummyData.getMovieList()
    fun getTvShowList(): ArrayList<TVModel> = DummyData.getTVList()
}
