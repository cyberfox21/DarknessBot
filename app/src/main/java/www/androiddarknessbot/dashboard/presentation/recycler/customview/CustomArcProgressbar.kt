package www.androiddarknessbot.dashboard.presentation.recycler.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import www.androiddarknessbot.R

@SuppressLint("CustomViewStyleable")
class CustomArcProgressbar @kotlin.jvm.JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var progressMax: Int = DEFAULT_PROGRESS_MAX

    var progress: Int = DEFAULT_PROGRESS
        set(value) {
            field = when {
                value < 0 -> 0
                value > progressMax -> progressMax
                else -> value
            }
        }

    private var _startAngle: Float = DEFAULT_START_ANGLE
    private var _arcAngle: Float = DEFAULT_ARC_ANGLE
    private var _arcWidth: Float = dpToPx(DEFAULT_ARC_WIDTH)
    private var _arcBackgroundWidth: Float = dpToPx(DEFAULT_ARC_WIDTH_BACKGROUND)

    private var _arcColorUnfinished: Int = DEFAULT_COLOR_UNFINISHED

    private val drawPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()

    companion object {
        private const val DEFAULT_PROGRESS = 0
        private const val DEFAULT_PROGRESS_MAX = 100
        private const val DEFAULT_START_ANGLE = 135F
        private const val DEFAULT_ARC_ANGLE = 270F
        private const val DEFAULT_ARC_WIDTH = 8F
        private const val DEFAULT_ARC_WIDTH_BACKGROUND = 7F

        private const val DEFAULT_COLOR_UNFINISHED = Color.LTGRAY
    }

    init {
        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcProgressbar)
            progressMax =
                typedArray.getInteger(R.styleable.ArcProgressbar_ar_max, DEFAULT_PROGRESS_MAX)
            progress =
                typedArray.getInteger(R.styleable.ArcProgressbar_ar_progress, DEFAULT_PROGRESS)
            _arcWidth = typedArray.getDimension(
                R.styleable.ArcProgressbar_ar_width, dpToPx(
                    DEFAULT_ARC_WIDTH
                )
            )
            _arcBackgroundWidth
            _startAngle =
                typedArray.getFloat(R.styleable.ArcProgressbar_ar_start_angle, DEFAULT_START_ANGLE)
            _arcAngle =
                typedArray.getFloat(R.styleable.ArcProgressbar_ar_arc_angle, DEFAULT_ARC_ANGLE)
            _arcColorUnfinished = typedArray.getColor(
                R.styleable.ArcProgressbar_ar_arc_color_unfinished,
                DEFAULT_COLOR_UNFINISHED
            )
            typedArray.recycle()
        }
        setup()
    }

    private fun setup() {
        with(drawPaint) {
            strokeWidth = _arcWidth
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
    }

    private fun dpToPx(dp: Float): Float = context.resources.displayMetrics.density * dp

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.set(
            (_arcWidth / 2F) + 4,
            (_arcWidth / 2F) + 4,
            w - _arcWidth / 2F - 4,
            h - _arcWidth / 2F
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val arcFinishedAngle = (progress * _arcAngle) / progressMax

        drawPaint.color = _arcColorUnfinished
        canvas.drawArc(rectF, _startAngle, _arcAngle, false, drawPaint)

        drawPaint.color = getColorByAngle(arcFinishedAngle)
        canvas.drawArc(rectF, _startAngle, arcFinishedAngle, false, drawPaint)
    }

    private fun getColorByAngle(arcFinishedAngle: Float): Int = when (arcFinishedAngle) {
        in 0F..91F -> Color.GREEN
        in 91F..180F -> Color.YELLOW
        in 180F..270F -> Color.RED
        else -> {
            println("angle $arcFinishedAngle")
            Color.BLACK
        }
    }

}