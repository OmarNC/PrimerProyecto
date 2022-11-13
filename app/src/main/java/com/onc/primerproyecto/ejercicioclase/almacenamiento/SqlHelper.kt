package com.onc.primerproyecto.ejercicioclase.almacenamiento

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlHelper (context : Context): SQLiteOpenHelper(context, DATEBASE_NAME, null, DATEBASE_VERSION) {

    companion object {
        private const val DATEBASE_VERSION = 1
        private const val DATEBASE_NAME = "user.db"
        private const val TBL_USER = "tbl_user"
        private const val ID = "id"
        private const val NAME = "name"
        private const val DESCRIPTION = "description"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val strSqlCreate = "CREATE TABLE $TBL_USER ("+
                        "$ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "$NAME TEXT, " +
                        "$DESCRIPTION TEXT)"
        db?.execSQL(strSqlCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val strSqlUpdate = "DROP TABLE IF EXISTS $TBL_USER"
        db?.execSQL(strSqlUpdate)
        onCreate(db)
    }

    fun insert(user: UserSqlModel): Long
    {
        val db = writableDatabase
        val contentValue = ContentValues().apply{
            put(NAME, user.name)
            put(DESCRIPTION, user.description)
        }
        val result = db.insert(TBL_USER, null, contentValue)
        db.close()
        return result
    }

    fun getAllUsers() : ArrayList<UserSqlModel>{
        val userList = arrayListOf<UserSqlModel>()
        var strQuery = "SELECT * FROM $TBL_USER"
        val db = readableDatabase

        val cursor : Cursor?

        try {
            cursor = db.rawQuery(strQuery, null)
        }
        catch (e: Exception)
        {
            e.printStackTrace()
            return  userList
        }

        var id : Int = 0
        var name : String = ""
        var description = ""

        with(cursor) {
            while(moveToNext())
            {
                id = getInt(cursor.getColumnIndexOrThrow(ID))
                name = getString(cursor.getColumnIndexOrThrow(NAME))
                description = getString(cursor.getColumnIndexOrThrow(DESCRIPTION))
                val user = UserSqlModel(id, name, description)
                userList.add(user)
            }
        }
        cursor.close()

        return  userList
    }

    fun updateUser(user: UserSqlModel) : Int
    {
        var db = writableDatabase
        var conentValue = ContentValues().apply {
            put(ID, user.id)
            put(NAME, user.name)
            put(DESCRIPTION, user.description)
        }

        //Para evitar SQL Injection
        //db.update(TBL_USER, conentValue, "id=${user.id}", null)

        //con argumentos
        val result = db.update(TBL_USER, conentValue, "id=?", arrayOf("${user.id}"))
        db.close()
        return result
    }

    fun deleteUser(userId: Int) : Int {
        val db = writableDatabase
        val result = db.delete(TBL_USER, "id=?", arrayOf("$userId"))
        db.close()
        return result
    }

}