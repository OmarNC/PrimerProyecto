package com.onc.primerproyecto

import java.io.Serializable

data class Persona (
    var nombre:String,
    var apellido:String,
    var peso:Double,
    var estatura:Double
): Serializable