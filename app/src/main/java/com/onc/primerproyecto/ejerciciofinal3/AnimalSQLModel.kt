package com.onc.primerproyecto.ejerciciofinal3

import com.onc.primerproyecto.ejerciciofinal3.Sexo
import java.io.Serializable

data class AnimalSQLModel (
    val id: Int = 0,
    val name : String,
    val srcImage : String,
    val descripcion : String,
    val estaEnfermo : Boolean,
    val sexo : Sexo
):Serializable