package com.example.android101

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_kotlin_catch_game.*

class KotlinCatchGame : AppCompatActivity() {
    private var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_catch_game)
        object :CountDownTimer(15000,1000){
            override fun onTick(p0: Long) {

                timeText.text ="Sure : ${p0/1000}"

            }

            override fun onFinish() {
                Toast.makeText(this@KotlinCatchGame, "Sure doldu", Toast.LENGTH_LONG).show()
                var warning = AlertDialog.Builder(this@KotlinCatchGame)
                warning.setTitle("Restart Game")
                warning.setMessage("Do you want to play one more time ?")
                warning.setPositiveButton("Yes"){dialog,which->



                }
                warning.setNegativeButton("No"){dialog,which->
                    Toast.makeText(applicationContext,"Game Over",Toast.LENGTH_LONG).show()



                }
                warning.show()



            }


        }.start()


    }
    fun increaseScore(view : View){
        score= score+1
        scoreText.text ="score : ${score}"


    }
}