package com.dicoding.nrahmatd.picodiploma.moviecatalogue.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 *
 * @author Nur Rahmat Dwi Riyanto
 */

class DatabaseHelper internal constructor(context: Context?) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_FAVORITE_TABLE)
        sqLiteDatabase.execSQL(SQL_CREATE_TV_SHOW_FAVORITE_TABLE)
    }

    override fun onUpgrade(
        sqLiteDatabase: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ${DatabaseContract.MovieFavoriteColumns.MOVIE_TABLE}")
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ${DatabaseContract.TVFavoriteColumns.TV_SHOW_TABLE}")
        onCreate(sqLiteDatabase)
    }

    companion object {
        private const val DATABASE_NAME = "movie_catalogues"
        private const val DATABASE_VERSION = 1
        private val SQL_CREATE_MOVIE_FAVORITE_TABLE = String.format(
            "CREATE TABLE %s" +
                    " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s INTEGER NOT NULL)",
            DatabaseContract.MovieFavoriteColumns.MOVIE_TABLE,
            DatabaseContract.MovieFavoriteColumns.MOVIE_ID,
            DatabaseContract.MovieFavoriteColumns.MOVIE_TITLE,
            DatabaseContract.MovieFavoriteColumns.MOVIE_OVERVIEW,
            DatabaseContract.MovieFavoriteColumns.MOVIE_RELEASE_DATE,
            DatabaseContract.MovieFavoriteColumns.MOVIE_PHOTO
        )
        private val SQL_CREATE_TV_SHOW_FAVORITE_TABLE = String.format(
            "CREATE TABLE %s" +
                    " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s INTEGER NOT NULL)",
            DatabaseContract.TVFavoriteColumns.TV_SHOW_TABLE,
            DatabaseContract.TVFavoriteColumns.TV_ID,
            DatabaseContract.TVFavoriteColumns.TV_TITLE,
            DatabaseContract.TVFavoriteColumns.TV_OVERVIEW,
            DatabaseContract.TVFavoriteColumns.TV_RELEASE_DATE,
            DatabaseContract.TVFavoriteColumns.TV_PHOTO
        )
    }
}
