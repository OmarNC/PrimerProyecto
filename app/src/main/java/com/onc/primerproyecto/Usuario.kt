package com.onc.primerproyecto
import java.io.Serializable

data class Usuario (
    val name: String,
    val lastName : String,
    val age : Int
):Serializable