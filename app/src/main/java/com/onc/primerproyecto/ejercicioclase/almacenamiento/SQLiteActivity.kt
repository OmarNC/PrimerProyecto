package com.onc.primerproyecto.ejercicioclase.almacenamiento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.set
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onc.primerproyecto.R

class SQLiteActivity : AppCompatActivity(),RecyclerItemSQLListener {

    private lateinit var sqlHelper : SqlHelper
    private lateinit var userSQLAdapter: UserSQLAdapter
    //Elementos de la vista
    private lateinit var etId : EditText
    private lateinit var etName : EditText
    private lateinit var  etDescription : EditText
    private lateinit var  btAdd : Button
    private lateinit var  btView : Button
    private lateinit var  btUpdate  : Button
    private lateinit var  btDelete  : Button
    private lateinit var btLimpiar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        sqlHelper = SqlHelper(this)


        etId = findViewById<EditText>(R.id.etId)
        etName = findViewById<EditText>(R.id.etName)
        etDescription = findViewById<EditText>(R.id.etDescription)
        btAdd = findViewById<Button>(R.id.btAgregar)
        btView = findViewById<Button>(R.id.btView)
        btUpdate = findViewById<Button>(R.id.btUptade)
        btDelete = findViewById<Button>(R.id.btDelete)
        btLimpiar = findViewById<Button>(R.id.btLimpiar)



        val lista = findViewById<RecyclerView>(R.id.id_sql_lista)
        userSQLAdapter = UserSQLAdapter(sqlHelper.getAllUsers(), this)

        lista.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        // lista.layoutManager = GridLayoutManager(this, 2)

        lista.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lista.itemAnimator = DefaultItemAnimator()

        lista.adapter = userSQLAdapter




        btAdd.setOnClickListener {
            if (etName.text.toString().isEmpty())
                Toast.makeText(this, "Ingreaa el nombre", Toast.LENGTH_SHORT).show()
            else if ( etDescription.text.toString().isEmpty())
                Toast.makeText(this, "Ingreaa la descripcion", Toast.LENGTH_LONG).show()
            else {
                val user =  UserSqlModel(name =etName.text.toString(), description =etDescription.text.toString())
                val result = sqlHelper.insert(user)
                if (result > -1) {
                    userSQLAdapter.updateItems(sqlHelper.getAllUsers())
                    Toast.makeText(this, "Se ha insertado el registro", Toast.LENGTH_LONG).show()
                }
                else
                    Toast.makeText(this, "ERROR al insertar el registro", Toast.LENGTH_LONG).show()
            }
        }


        btView.setOnClickListener {
            var list = sqlHelper.getAllUsers()

            Log.e("Lista: ", list.toString())
            Toast.makeText(this, "Lista: $list.toString()", Toast.LENGTH_LONG).show()
        }

        btUpdate.setOnClickListener {

            if (etId.text.toString().isNotEmpty())
            {
                val id = etId.text.toString().toInt()
                val nombre = etName.text.toString()
                val descripcion = etDescription.text.toString()

                var userUpdated = UserSqlModel(id, nombre, descripcion)
                val result = sqlHelper.updateUser(userUpdated)

                if (result > -1) {
                    Toast.makeText(this, "Registro actualizado", Toast.LENGTH_SHORT).show()
                    userSQLAdapter.updateItems(sqlHelper.getAllUsers())
                }
                else
                    Toast.makeText(this, "ERROR al actualizar el registro", Toast.LENGTH_LONG).show()
            }
            else Toast.makeText(this, "Es necesario establecer el ID", Toast.LENGTH_LONG).show()

        }

        btDelete.setOnClickListener {
            if (etId.text.toString().isEmpty())
                Toast.makeText(this, "Se debe especificar el ID", Toast.LENGTH_LONG).show()
            else
            {
                val id = etId.text.toString().toInt()
                val result = sqlHelper.deleteUser(id)

                if (result > 0) {
                    Toast.makeText(this, "Registro fue eliminado", Toast.LENGTH_LONG).show()
                    userSQLAdapter.updateItems(sqlHelper.getAllUsers())
                }
                else
                    Toast.makeText(this, "ERROR no se borró el registro", Toast.LENGTH_LONG).show()
            }

        }

        btLimpiar.setOnClickListener {
            etId.text.clear()
            etName.text.clear()
            etDescription.text.clear()
        }





    }

    override fun OnItemSelected(usuario: UserSqlModel) {
        /*(etId as TextView).text = usuario.id.toString()
        (etName as TextView).text = usuario.name
        (etDescription as TextView).text = usuario.description
         */

        etId.setText(usuario.id.toString())
        etName.setText(usuario.name)
        etDescription.setText(usuario.description)
        //userSQLAdapter.updateItems(sqlHelper.getAllUsers())
    }
}