package com.dicoding.nrahmatd.picodiploma.moviecatalogue.database

import android.provider.BaseColumns

/**
 *
 * @author Nur Rahmat Dwi Riyanto
 */

internal class DatabaseContract {
    internal object MovieFavoriteColumns : BaseColumns {
        const val MOVIE_TABLE = "movie_favorites"
        const val MOVIE_ID = "_movie_id"
        const val MOVIE_TITLE = "movie_title"
        const val MOVIE_OVERVIEW = "movie_overview"
        const val MOVIE_RELEASE_DATE = "movie_release_date"
        const val MOVIE_PHOTO = "movie_photo"
    }

    internal object TVFavoriteColumns : BaseColumns {
        const val TV_SHOW_TABLE = "tv_show_favorites"
        const val TV_ID = "_tv_id"
        const val TV_TITLE = "tv_title"
        const val TV_OVERVIEW = "tv_overview"
        const val TV_RELEASE_DATE = "tv_release_date"
        const val TV_PHOTO = "tv_photo"
    }
}
