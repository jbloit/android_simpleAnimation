package net.onoffon.androidsimpleanimation

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

class Line(private val mColor: Int, private val mStrokeWidth: Float) : Parcelable {
    private var pointsList = ArrayList<PointF>()
    private var initialPoint: PointF = PointF(0f, 0f)

    val paint: Paint
        get() = object : Paint() {
            init {
                style = Style.STROKE
                strokeCap = Cap.SQUARE
                strokeWidth = mStrokeWidth
                isAntiAlias = true
                color = mColor
            }
        }

    val path: Path
        get() {
            val path = Path()
            path.moveTo(initialPoint.x, initialPoint.y)
            for (point in pointsList) {
                path.lineTo(point.x, point.y)
            }
            return path
        }

    fun moveTo(x: Float, y: Float) {
        initialPoint = PointF(x, y)
        pointsList = ArrayList()
    }

    fun lineTo(x: Float, y: Float) {
        val point = PointF(x, y)
        pointsList.add(point)
    }

    // tag::parcelable[]
    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel, i: Int) {
        val points = arrayOfNulls<PointF>(pointsList.size)
        pointsList.toTypedArray()
        parcel.writeInt(mColor)
        parcel.writeFloat(mStrokeWidth)
        parcel.writeParcelable(initialPoint, 0)
        parcel.writeParcelableArray<PointF>(points, 0)
    }

    companion object CREATOR : Parcelable.Creator<Line> {
        override fun createFromParcel(parcel: Parcel): Line {
            val loader = PointF::class.java.classLoader
            val line = Line(parcel.readInt(), parcel.readFloat())
            val initialPoint = parcel.readParcelable<Parcelable>(loader) as PointF
            line.moveTo(initialPoint.x, initialPoint.y)
            val points = parcel.readParcelableArray(loader) as Array<PointF>
            for (i in points.indices) {
                val point = points[i]
                line.lineTo(point.x, point.y)
            }
            return line
        }

        override fun newArray(i: Int): Array<Line?> = arrayOfNulls(i)
    }
    // end::parcelable[]
}
