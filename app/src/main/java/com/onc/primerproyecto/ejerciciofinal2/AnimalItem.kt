package com.onc.primerproyecto.ejerciciofinal2

import java.io.Serializable

data class AnimalItem (
    val name : String,
    val image : String,
    val tieneEnfermedad : Boolean,
    val sexo : Sexo
): Serializable
