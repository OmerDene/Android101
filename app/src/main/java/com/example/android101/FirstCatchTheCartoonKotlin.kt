package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import com.example.android101.databinding.ActivityFirstCatchTheCartoonKotlinBinding
import com.example.android101.databinding.ActivityKotlinRunnableBinding
import java.util.*
import kotlin.collections.ArrayList

class FirstCatchTheCartoonKotlin : AppCompatActivity() {
    private lateinit var binding: ActivityFirstCatchTheCartoonKotlinBinding
    private var score = 0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable {}
    var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFirstCatchTheCartoonKotlinBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)


        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        hideImages()
        object : CountDownTimer(15000,1000){
            override fun onTick(p0: Long) {
                binding.timeText.text = "Time :  ${p0/1000}"


            }

            override fun onFinish() {
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
            }

        }.start()
    }

    fun hideImages(){
        runnable = object : Runnable{
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,500)
            }



        }
        handler.post(runnable)

    }
    fun increaseScore(view : View){
        score++
        binding.scoreText.text = "Score : ${score}"

    }
}