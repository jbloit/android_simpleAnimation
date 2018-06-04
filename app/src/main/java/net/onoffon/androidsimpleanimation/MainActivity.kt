package net.onoffon.androidsimpleanimation

import android.animation.TimeAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val animationLoop: TimeAnimator = TimeAnimator()

    lateinit var pv: ProgressView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pv = findViewById(R.id.progressView)
        startAnimationLoop()
    }

    override fun onPause() {
        super.onPause()
        stopAnimationLoop()
    }


    fun startAnimationLoop(){
        animationLoop.setTimeListener(TimeAnimator.TimeListener({ animation, totalTime, deltaTime ->  performAnimation()}))
        animationLoop.start()
    }

    fun stopAnimationLoop(){
        animationLoop.cancel()
        animationLoop.setTimeListener(null)
        animationLoop.removeAllListeners()
    }

    fun performAnimation(){
        val tv = findViewById<TextView>(R.id.textView)

    }

}
