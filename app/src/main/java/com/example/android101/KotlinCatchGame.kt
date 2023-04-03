package com.example.android101

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_kotlin_catch_game.*
import kotlin.random.Random

class KotlinCatchGame : AppCompatActivity() {
    private var score = 0
    val images = ArrayList<ImageView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_catch_game)

        images.add(imageView)
        images.add(imageView2)
        images.add(imageView3)
        images.add(imageView4)
        images.add(imageView5)
        images.add(imageView6)
        images.add(imageView7)
        images.add(imageView8)
        images.add(imageView9)
        ımageHide()


        object :CountDownTimer(5000,1000){
            override fun onTick(p0: Long) {

                timeText.text ="Sure : ${p0/1000}"

            }

            override fun onFinish() {
                Toast.makeText(this@KotlinCatchGame, "Sure doldu", Toast.LENGTH_LONG).show()
                var warning = AlertDialog.Builder(this@KotlinCatchGame)
                warning.setTitle("Restart Game")
                warning.setMessage("Do you want to play one more time ?")
                warning.setPositiveButton("Yes"){dialog,which->
                    finish()
                    startActivity(intent)




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
    fun ımageHide(){
        for(i in images){
           i.visibility =View.INVISIBLE
        }

        var random = java.util.Random()
        var randomIndeks = random.nextInt(9)
        images[randomIndeks].visibility =View.VISIBLE

    }
}
