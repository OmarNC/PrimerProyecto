package com.onc.primerproyecto.ejercicioclase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.onc.primerproyecto.R

class MainActivity : AppCompatActivity() {
    private var TAG = "Estoy en: "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e(TAG, "onCreate")


      //  val intent = Intent()
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
    }
}