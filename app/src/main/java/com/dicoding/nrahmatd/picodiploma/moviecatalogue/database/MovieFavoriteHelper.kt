package com.dicoding.nrahmatd.picodiploma.moviecatalogue.database

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dicoding.nrahmatd.picodiploma.core.model.MovieModel
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.DatabaseContract.MovieFavoriteColumns.MOVIE_ID
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.DatabaseContract.MovieFavoriteColumns.MOVIE_OVERVIEW
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.DatabaseContract.MovieFavoriteColumns.MOVIE_PHOTO
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.DatabaseContract.MovieFavoriteColumns.MOVIE_RELEASE_DATE
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.DatabaseContract.MovieFavoriteColumns.MOVIE_TABLE
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.DatabaseContract.MovieFavoriteColumns.MOVIE_TITLE

/**
 *
 * @author Nur Rahmat Dwi Riyanto
 */

class MovieFavoriteHelper(context: Context) {

    private var mContext = context
    private var tableName = MOVIE_TABLE
    private var databaseHelper = DatabaseHelper(mContext)

    private lateinit var database: SQLiteDatabase

    companion object {
        fun getInstance(context: Context) =
            synchronized(SQLiteOpenHelper::class.java) { MovieFavoriteHelper(context) }
    }

    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()
        if (database.isOpen) database.close()
    }

    fun getAllMoviesFavorite(): ArrayList<MovieModel> {
        val arrayList = ArrayList<MovieModel>()
        val cursor = database.query(
            tableName, null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor.moveToFirst()
        if (cursor.count > 0) {
            do {
                arrayList.add(
                    MovieModel(
                    cursor.getInt(cursor.getColumnIndexOrThrow(MOVIE_ID)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(MOVIE_PHOTO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MOVIE_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MOVIE_RELEASE_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MOVIE_OVERVIEW)),
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
                )
                )
                cursor.moveToNext()
            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    fun insertMovieData(movieFavorite: MovieModel): Long {
        val args = ContentValues()
        args.put(MOVIE_ID, movieFavorite.id)
        args.put(MOVIE_TITLE, movieFavorite.titleMovie)
        args.put(MOVIE_OVERVIEW, movieFavorite.descMovie)
        args.put(MOVIE_RELEASE_DATE, movieFavorite.dateMovie)
        args.put(MOVIE_PHOTO, movieFavorite.imgMovie)
        return database.insert(tableName, null, args)
    }

    fun selectMovieData(movieId: Int): Boolean {
        val query = "Select * from $tableName where $MOVIE_ID = $movieId"
        val cursor = database.rawQuery(query, null)
        if (cursor.count <= 0) {
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    fun deleteMovieData(id: Int): Int {
        return database.delete(MOVIE_TABLE, "$MOVIE_ID = '$id'", null)
    }
}
