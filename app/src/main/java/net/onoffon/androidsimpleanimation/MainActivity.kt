package net.onoffon.androidsimpleanimation

import android.animation.TimeAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val animationLoop: TimeAnimator = TimeAnimator()
    private var progress = 0.toFloat()

     var pv: ProgressView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pv = findViewById<ProgressView>(R.id.progressView)
        pv?.setBackgroundColor(resources.getColor(R.color.jblack))
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
        progress += 0.01.toFloat()
        if (progress > 1) {progress = 0.toFloat()}

        pv?.setProgress(progress)
    }

}
