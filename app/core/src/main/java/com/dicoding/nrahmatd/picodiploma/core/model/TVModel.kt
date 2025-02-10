package com.dicoding.nrahmatd.picodiploma.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *
 * @author Nur Rahmat Dwi Riyanto
 */

@Parcelize
data class TVModel(
    val id: Int,
    val imgTV: Int,
    val titleTV: String,
    val dateTV: String,
    val descTV: String,
    val langTV: String,
    val runtimeTV: String,
    val fbAccountTV: String,
    val twitterAccountTV: String,
    val instaAccountTV: String,
    val genresTV: String
) : Parcelable