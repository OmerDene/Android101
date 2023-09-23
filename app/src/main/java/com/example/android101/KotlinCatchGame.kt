package com.example.android101

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_kotlin_catch_game.*

class KotlinCatchGame : AppCompatActivity() {
    private var score = 0
    private val images = ArrayList<ImageView>()
    private lateinit var runnable: Runnable
    private var handler : Handler = Handler(Looper.getMainLooper())

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
        imageHide()

        object :CountDownTimer(5000,1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) {

                timeText.text = getString(R.string.time) + "${p0/1000}"

            }

            override fun onFinish() {
                handler.removeCallbacks(runnable)
                for(i in images){
                    i.visibility =View.INVISIBLE
                }

                Toast.makeText(this@KotlinCatchGame, "Time is up", Toast.LENGTH_LONG).show()
                val warning = AlertDialog.Builder(this@KotlinCatchGame)
                warning.setTitle("Restart Game")
                warning.setMessage("Do you want to play one more time ?")
                warning.setPositiveButton("Yes"){ _,_->
                    finish()
                    startActivity(intent)
                }

                warning.setNegativeButton("No"){ _,_->
                    Toast.makeText(applicationContext,"Game Over",Toast.LENGTH_LONG).show()
                }
                warning.show()
            }
        }.start()
    }

    @SuppressLint("SetTextI18n")
    fun increaseScore(view: View) {
        score++
        scoreText.text ="score : $score"
    }

    private fun imageHide(){
        runnable = Runnable {
            for(i in images){
                i.visibility =View.INVISIBLE
            }

            val random = java.util.Random()
            val randomIndex = random.nextInt(9)
            images[randomIndex].visibility = View.VISIBLE
            handler.postDelayed(runnable,500)
        }
        handler.post(runnable)
    }
}
