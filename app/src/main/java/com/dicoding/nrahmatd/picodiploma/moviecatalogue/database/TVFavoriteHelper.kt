package com.dicoding.nrahmatd.picodiploma.moviecatalogue.database

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.DatabaseContract.TVFavoriteColumns.TV_ID
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.DatabaseContract.TVFavoriteColumns.TV_OVERVIEW
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.DatabaseContract.TVFavoriteColumns.TV_PHOTO
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.DatabaseContract.TVFavoriteColumns.TV_RELEASE_DATE
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.DatabaseContract.TVFavoriteColumns.TV_SHOW_TABLE
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.database.DatabaseContract.TVFavoriteColumns.TV_TITLE
import com.dicoding.nrahmatd.picodiploma.moviecatalogue.model.TVModel

/**
 *
 * @author Nur Rahmat Dwi Riyanto
 */

class TVFavoriteHelper(context: Context) {

    private var mContext = context
    private var tableName = TV_SHOW_TABLE
    private var databaseHelper = DatabaseHelper(mContext)

    private lateinit var database: SQLiteDatabase

    companion object {
        fun getInstance(context: Context) =
            synchronized(SQLiteOpenHelper::class.java) { TVFavoriteHelper(context) }
    }

    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()
        if (database.isOpen) database.close()
    }

    fun getAllTVFavorite(): ArrayList<TVModel> {
        val arrayList: ArrayList<TVModel> = ArrayList()
        val cursor = database.query(
            tableName, null,
            null,
            null,
            null,
            null,
            "$TV_ID ASC",
            null
        )
        cursor.moveToFirst()
        if (cursor.count > 0) {
            do {
                arrayList.add(TVModel(
                    cursor.getInt(cursor.getColumnIndexOrThrow(TV_ID)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(TV_PHOTO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(TV_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(TV_RELEASE_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(TV_OVERVIEW)),
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
                ))
                cursor.moveToNext()
            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    fun insertTVData(tvFavorite: TVModel): Long {
        val args = ContentValues()
        args.put(TV_ID, tvFavorite.id)
        args.put(TV_TITLE, tvFavorite.titleTV)
        args.put(TV_OVERVIEW, tvFavorite.descTV)
        args.put(TV_RELEASE_DATE, tvFavorite.dateTV)
        args.put(TV_PHOTO, tvFavorite.imgTV)
        return database.insert(tableName, null, args)
    }

    fun selectTVData(tvId: Int): Boolean {
        val query = "Select * from $tableName where $TV_ID = $tvId"
        val cursor = database.rawQuery(query, null)
        if (cursor.count <= 0) {
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    fun deleteTVData(id: Int): Int {
        return database.delete(TV_SHOW_TABLE, "$TV_ID = '$id'", null)
    }
}
