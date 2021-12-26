package www.androiddarknessbot.presentation.fragments.dashboard

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CustomArcProgressbar @kotlin.jvm.JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var _progress: Int = DEFAULT_PROGRESS
    private var _progressMax: Int = DEFAULT_PROGRESS_MAX
    private var _startAngle: Float = DEFAULT_START_ANGLE
    private var _arcAngle: Float = DEFAULT_ARC_ANGLE
    private var _arcWidth: Float = dpToPx(DEFAULT_ARC_WIDTH)
    private var _arcBackgroundWidth: Float = dpToPx(DEFAULT_ARC_WIDTH_BACKGROUND)
//    private var _arcColorFinished: Int = DEFAULT_COLOR_FINISHED
//    private var _arcColorUnfinished: Int = DEFAULT_COLOR_UNFINISHED
//    private var _arcGradient: Boolean = DEFAULT_ARC_GRADIENT

    private val drawPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()
    private var lGradient: LinearGradient? = null

    companion object {
        private const val DEFAULT_PROGRESS = 0
        private const val DEFAULT_PROGRESS_MAX = 100
        private const val DEFAULT_START_ANGLE = 135F
        private const val DEFAULT_ARC_ANGLE = 270F
        private const val DEFAULT_ARC_WIDTH = 8F
        private const val DEFAULT_ARC_WIDTH_BACKGROUND = 7F
//        private const val DEFAULT_COLOR_FINISHED = Color.GREEN
//        private const val DEFAULT_COLOR_UNFINISHED = Color.LTGRAY
//        private const val DEFAULT_ARC_GRADIENT = false
    }

    init {
        if (attrs != null) {
//            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcProgressbar)
//            _progress = typedArray.getInteger(R.styleable.ArcProgressbar_ar_progress, DEFAULT_PROGRESS)
//            _progressMax = typedArray.getInteger(R.styleable.ArcProgressbar_ar_max, DEFAULT_PROGRESS_MAX)
//            _arcWidth = typedArray.getDimension(
//                R.styleable.ArcProgressbar_ar_width, dpToPx(
//                    DEFAULT_ARC_WIDTH
//                ))
//            _arcBackgroundWidth
//            _startAngle = typedArray.getFloat(R.styleable.ArcProgressbar_ar_start_angle, DEFAULT_START_ANGLE)
//            _arcAngle = typedArray.getFloat(R.styleable.ArcProgressbar_ar_arc_angle, DEFAULT_ARC_ANGLE)
//            _arcColorFinished = typedArray.getColor(R.styleable.ArcProgressbar_ar_arc_color_finished, DEFAULT_COLOR_FINISHED)
//            _arcColorUnfinished = typedArray.getColor(R.styleable.ArcProgressbar_ar_arc_color_unfinished, DEFAULT_COLOR_UNFINISHED)
//            _arcGradient = typedArray.getBoolean(R.styleable.ArcProgressbar_ar_arc_color_gradient, DEFAULT_ARC_GRADIENT)
//            typedArray.recycle()
        }
        setup()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.set(
            (_arcWidth / 2F) + 4,
            (_arcWidth / 2F) + 4,
            w - _arcWidth / 2F - 4,
            h - _arcWidth / 2F
        )
//        if (_arcGradient) {
//            lGradient = LinearGradient(
//                rectF.left,
//                rectF.bottom,
//                rectF.right,
//                rectF.bottom,
//                Color.RED,
//                Color.GREEN,
//                Shader.TileMode.CLAMP
//            )
//        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val arcFinishedAngle = _progress * _arcAngle / _progressMax

//        drawPaint.color = _arcColorUnfinished
        drawPaint.shader = null
        canvas.drawArc(rectF, _startAngle, _arcAngle, false, drawPaint)

//        if (!_arcGradient) {
//            if (_progressMax == 0) throw Exception("ar_progress_max mustn't equal zero")
//            val ratio: Float = _progress.toFloat() / _progressMax.toFloat()
//            val r: Float = 255F * (-ratio * ratio + 1)
//            val g: Float =
//                255F * 3.29F * (-(1 - ratio) * (1 - ratio) * (1 - ratio) + (1 - 0.7 * ratio) * (1 - 0.7 * ratio)).toFloat()
//            val b: Float = 0F
//            drawPaint.color = Color.rgb(r.toInt(), g.toInt(), b.toInt())
//        } else {
//            drawPaint.shader = lGradient
//        }
        canvas.drawArc(rectF, _startAngle, arcFinishedAngle, false, drawPaint)
    }

    private fun dpToPx(dp: Float): Float = context.resources.displayMetrics.density * dp

    private fun setup() {
        with(drawPaint) {
            strokeWidth = _arcWidth
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
    }

    fun setProgress(progress: Int) {
        _progress = if (progress < 0) 0 else if (progress > _progressMax) _progressMax else progress
        invalidate()
    }

    fun setMax(max: Int) {
        _progressMax = max
    }


}