package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class FirstAlertDialogKotlin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_alert_dialog_kotlin)
    }
    fun save(view : View) {

        val alert = AlertDialog.Builder(this)
        alert.setTitle("Save")
        alert.setMessage("Are You Sure?")

        alert.setPositiveButton("Yes") {dialog, which ->

            //OnClick Listener
            Toast.makeText(this,"Saved", Toast.LENGTH_LONG).show()
        }
        alert.setNegativeButton("No") {dialog, which ->

            //OnClick Listener
            Toast.makeText(applicationContext,"Not Saved", Toast.LENGTH_LONG).show()

        }

        alert.show()

    }
}