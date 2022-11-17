package com.onc.primerproyecto.ejerciciofinal3

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.onc.primerproyecto.R


class AnimalShowDetailActivity : AppCompatActivity() {

    private lateinit var sqlHelper : SqlHelper

    //Elementos de la vista
    private lateinit var etId : EditText
    private lateinit var etSrc : EditText
    private lateinit var ivFoto : ImageView
    private lateinit var etNombre: EditText
    private lateinit var etDescripcion : EditText
    private lateinit var cbEnfermedad : CheckBox
    private lateinit var rgSexo : RadioGroup
    private lateinit var btnActualizar : Button
    private lateinit var btnEliminar : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_show_detail)

        sqlHelper = SqlHelper(this)

        etSrc = findViewById<EditText>(R.id.etSrc)
        ivFoto =findViewById<ImageView>(R.id.imgFoto)
        etId = findViewById<EditText>(R.id.etId)
        etNombre = findViewById<EditText>(R.id.etNombre)
        etDescripcion = findViewById<EditText>(R.id.etDescripcion)
        cbEnfermedad = findViewById<CheckBox>(R.id.cbEnfermo)
        rgSexo = findViewById<RadioGroup>(R.id.rgSexo)
        btnActualizar = findViewById<Button>(R.id.btnActualizar)
        btnEliminar = findViewById<Button>(R.id.btnEliminar)

        intent.extras?.let{
            if (it.containsKey("KEY_ANIMAL")){
                val animal : AnimalSQLModel = it.getSerializable("KEY_ANIMAL") as AnimalSQLModel

                etSrc.setText("")
                etId.setText("")
                etNombre.setText("")
                etDescripcion.setText("")


                etSrc.setText(animal.srcImage)
                etId.setText(animal.id.toString())
                etNombre.setText(animal.name)
                etDescripcion.setText(animal.descripcion)

                //Carga la imagen
                val src = etSrc.text.toString()
                if (src.isNotEmpty())
                    Glide.with(this).load(src).into(ivFoto)
                else
                    ivFoto.setImageResource(R.drawable.sound_detection_dog_barking_48px)


                cbEnfermedad.isChecked = animal.estaEnfermo

                when(animal.sexo)
                {
                    Sexo.MACHO -> rgSexo.check(R.id.rbMacho)
                    Sexo.HEMBRA -> rgSexo.check(R.id.rbHembra)
                    else -> rgSexo.check(R.id.rbHembra)
                }
            }
        }

        btnActualizar.setOnClickListener {

            var id: Int = 0
            var name : String = ""
            var srcImage : String = ""
            var descripcion : String = ""
            var estaEnfermo : Boolean
            var sexo : Sexo
            id = etId.text.toString().toInt()
            name = etNombre.text.toString()
            srcImage = etSrc.text.toString()
            descripcion = etDescripcion.text.toString()

            estaEnfermo = if (cbEnfermedad.isChecked) true else false

            sexo = when(rgSexo.checkedRadioButtonId)
            {
                R.id.rbHembra -> Sexo.HEMBRA
                R.id.rbMacho  -> Sexo.MACHO
                else -> Sexo.SIN_DEFINIR
            }

            val animal = AnimalSQLModel(id, name, srcImage, descripcion, estaEnfermo, sexo)
            val result = sqlHelper.updateAnimal(animal)
            if (result > 0)
                Toast.makeText(this, "Registro actualizado", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "NO se pudo actualizar el registro", Toast.LENGTH_SHORT).show()


        }

        btnEliminar.setOnClickListener {
            val id : Int = etId.text.toString().toInt()
            val result = sqlHelper.deleteAnimal(id)
            if (result > 0)
                Toast.makeText(this, "Registro eliminado", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "NO se elimino con éxitp el registro", Toast.LENGTH_SHORT).show()

            finish()
        }
    }
}