package com.dicoding.nrahmatd.picodiploma.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    var page: Int? = null,
    var results: ArrayList<Result>? = null,
    var total_pages: Int? = null,
    var total_results: Int? = null
) : Parcelable {
    @Parcelize
    data class Result(
        var adult: Boolean? = null,
        var backdrop_path: String? = null,
        var genre_ids: List<Int?>? = null,
        var id: Int? = null,
        var original_language: String? = null,
        var original_title: String? = null,
        var overview: String? = null,
        var popularity: Double? = null,
        var poster_path: String? = null,
        var release_date: String? = null,
        var first_air_date: String? = null,
        var title: String? = null,
        var name: String? = null,
        var video: Boolean? = null,
        var vote_average: Double? = null,
        var vote_count: Int? = null
    ) : Parcelable
}
