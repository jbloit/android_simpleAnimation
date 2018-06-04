package net.onoffon.androidsimpleanimation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


/**
 * Created by julien on 5/31/18.
 */
class ProgressView(context: Context, attrs: AttributeSet) : View(context, attrs){

    private var progressLine = Line(Color.RED, 30.toFloat())
    private var progress = 0.toFloat()

    private var width = 0.toFloat()

    fun setProgress(progress: Float){
        progressLine.moveTo(0.toFloat(), 0.toFloat())
        progressLine.lineTo(progress*width, 0.toFloat())
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        width = canvas.width.toFloat()
        canvas.drawPath(progressLine.path, progressLine.paint)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        val result = super.onTouchEvent(event)
        if (!result) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    progress += 0.1.toFloat()
                    setProgress(progress)
                }
            }
        }
        return result
    }

}