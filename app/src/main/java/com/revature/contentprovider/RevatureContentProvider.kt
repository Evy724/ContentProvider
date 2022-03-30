package com.revature.contentprovider

import android.content.*
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri

class RevatureContentProvider: ContentProvider() {

    companion object {
        const val DATABASE_NAME = "UserDatabase"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "Users"

        const val name = "name"
        const val id = "id"

        //defining the authority (content URI)

        private const val PROVIDER_NAME = "com.revature.user.provider"

        //defining the content URI

        private const val URL = "content://$PROVIDER_NAME/users"

        //parsing the content URI

        val CONTENT_URI: Uri = Uri.parse(URL)

        private var uriMatcher: UriMatcher? = null

        //create a table

        const val CREATE_DB_TABLE = (
                "CREATE TABLE"
                        + TABLE_NAME
                        + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "name TEXT NOT NULL);")

        private const val uriCode = 1
        init {
            //to match the content URI
            uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            //to access the entire table

            uriMatcher!!.addURI(
                PROVIDER_NAME,
                "users",
                uriCode
            )

            //to access a particular row

            uriMatcher!!.addURI(
                PROVIDER_NAME,
                "users/*",
                uriCode
            )
        }
    }

    override fun onCreate(): Boolean {
        val context = context
        val dbHelper = context?.let { DatabaseHelper(it) }
            db = dbHelper?.writableDatabase

        return db != null
    }
    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }
    override fun getType(p0: Uri): String? {
        TODO()
    }
    override fun insert(uri: Uri, values: ContentValues?): Uri {
        val rowID = db!!.insert(TABLE_NAME, "", values)
        if(rowID > 0) {
            val _uri = ContentUris.withAppendedId(uri,rowID)
            context!!.contentResolver.notifyChange(_uri, null)
            return _uri
        }

        throw SQLiteException("Failed to add record into $uri")
    }
    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO()
    }
    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO()
    }

    private var db: SQLiteDatabase? = null

    private class DatabaseHelper
    internal constructor(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(CREATE_DB_TABLE)
        }
        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }
}