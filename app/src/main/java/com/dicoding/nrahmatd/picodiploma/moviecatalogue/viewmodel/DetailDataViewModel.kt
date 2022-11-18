package com.dicoding.nrahmatd.picodiploma.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.nrahmatd.picodiploma.core.utils.DummyData
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.model.MovieModel
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.model.TVModel

class DetailDataViewModel : ViewModel() {

    fun getMovieDetail(id: Int?): MovieModel {
        lateinit var result: MovieModel
        for (movie in DummyData.getMovieList()) {
            if (movie.id == id) {
                result = movie
                break
            }
        }
        return result
    }

    fun getTvDetail(id: Int?): TVModel {
        lateinit var result: TVModel
        for (tv in DummyData.getTVList()) {
            if (tv.id == id) {
                result = tv
                break
            }
        }
        return result
    }
}
