package com.onc.primerproyecto.ejerciciofinal3

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.onc.primerproyecto.ejerciciofinal3.Sexo

class SqlHelper (context : Context): SQLiteOpenHelper(context, DATEBASE_NAME, null, DATEBASE_VERSION) {

    companion object {
        private const val DATEBASE_VERSION = 1
        private const val DATEBASE_NAME = "animals.db"

        //Nombre de la tabla
        private const val TBL_ANIMALS = "tbl_animals"
        //Nombre de los campos de la tabla de animales
        private const val ID = "id"
        private const val NAME = "name"
        private const val SRC_IMAGE = "src_image"
        private const val ESTA_ENFERMO = "esta_enfermo"
        private const val DESCRIPTION = "description"
        private const val SEXO = "sexo"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val strSqlCreate = "CREATE TABLE $TBL_ANIMALS ("+
                "$ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$NAME TEXT, " +
                "$SRC_IMAGE TEXT, " +
                "$ESTA_ENFERMO INTEGER, " +
                "$DESCRIPTION TEXT," +
                "$SEXO INTEGER)"
        db?.execSQL(strSqlCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val strSqlUpdate = "DROP TABLE IF EXISTS $TBL_ANIMALS"
        db?.execSQL(strSqlUpdate)
        onCreate(db)
    }

    fun insert(animal: AnimalSQLModel): Long
    {
        val db = writableDatabase
        val contentValue = ContentValues().apply{
            put(NAME, animal.name)
            put(SRC_IMAGE, animal.srcImage)

            val estaenfermo = if (animal.estaEnfermo) 1 else 0

            put(ESTA_ENFERMO, estaenfermo)
            put(DESCRIPTION, animal.descripcion)
            put(SEXO, animal.sexo.ordinal)
        }
        val result = db.insert(TBL_ANIMALS, null, contentValue)
        db.close()
        return result
    }

    fun getAllAnimals() : ArrayList<AnimalSQLModel>{
        val animalsList = arrayListOf<AnimalSQLModel>()
        var strQuery = "SELECT * FROM $TBL_ANIMALS"
        val db = readableDatabase

        val cursor : Cursor?

        try {
            cursor = db.rawQuery(strQuery, null)
        }
        catch (e: Exception)
        {
            e.printStackTrace()
            return  animalsList
        }

        var id: Int = 0
        var name : String = ""
        var srcImage : String = ""
        var descripcion : String = ""
        var estaEnfermo : Boolean
        var sexo : Sexo

        with(cursor) {
            while(moveToNext())
            {
                id = getInt(cursor.getColumnIndexOrThrow(ID))
                name = getString(cursor.getColumnIndexOrThrow(NAME))
                srcImage = getString(cursor.getColumnIndexOrThrow(SRC_IMAGE))
                descripcion = getString(cursor.getColumnIndexOrThrow(DESCRIPTION))

                estaEnfermo = if (getInt(cursor.getColumnIndexOrThrow(ESTA_ENFERMO)) == 0) false else true

                val n = getInt(cursor.getColumnIndexOrThrow(SEXO))
                sexo = when(n)
                {
                    Sexo.HEMBRA.ordinal -> Sexo.HEMBRA
                    Sexo.MACHO.ordinal -> Sexo.MACHO
                    else -> Sexo.SIN_DEFINIR
                }

                val animal = AnimalSQLModel(id, name, srcImage, descripcion, estaEnfermo, sexo)
                animalsList.add(animal)
            }
        }
        cursor.close()

        return  animalsList
    }

    fun updateAnimal(animal: AnimalSQLModel) : Int
    {
        var db = writableDatabase
        var conentValue = ContentValues().apply {
            put(NAME, animal.name)
            put(SRC_IMAGE, animal.srcImage)
            put(ESTA_ENFERMO, animal.estaEnfermo)
            put(DESCRIPTION, animal.descripcion)
            put(SEXO, animal.sexo.ordinal)
        }

        //db.update(TBL_AN, conentValue, "id=${user.id}", null)

        //Para evitar SQL Injection
        //con argumentos
        val result = db.update(TBL_ANIMALS, conentValue, "id=?" , arrayOf("${animal.id}"))
        db.close()
        return result
    }

    fun deleteAnimal(animalId: Int) : Int {
        val db = writableDatabase
        val result = db.delete(TBL_ANIMALS, "id=?", arrayOf("$animalId"))
        db.close()
        return result
    }

}