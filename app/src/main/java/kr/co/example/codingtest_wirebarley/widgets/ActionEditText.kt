package kr.co.example.codingtest_wirebarley.widgets

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.appcompat.widget.AppCompatEditText


class ActionEditText : AppCompatEditText {
    private var paintBackgroundColor: Paint? = null
    private var paintBorderColor: Paint? = null
    private var cornerRadius = 0f
    private var borderWidth = 0f
    private var width = 0f
    private var height = 0f
    private var backgroundColor = 0
    private var borderColor = 0
    private var focusBorderColor = 0
    private var isFocus = false
    private var vScroll = 0.0f
    private var hScroll = 0.0f

    constructor(context: Context?) : super(context!!) {}

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int = R.attr.editTextStyle
    ) : super(context, attrs, defStyle) {
        val a =
            context.obtainStyledAttributes(attrs, kr.co.example.codingtest_wirebarley.R.styleable.RoundEditText, defStyle, 0)
        cornerRadius = a.getDimensionPixelSize(kr.co.example.codingtest_wirebarley.R.styleable.RoundEditText_cornerRadius, 0).toFloat()
        borderWidth = a.getDimensionPixelSize(kr.co.example.codingtest_wirebarley.R.styleable.RoundEditText_borderWidth, 0).toFloat()
        backgroundColor = a.getColor(
            kr.co.example.codingtest_wirebarley.R.styleable.RoundEditText_backgroundColor,
            Color.TRANSPARENT
        )
        borderColor =
            a.getColor(kr.co.example.codingtest_wirebarley.R.styleable.RoundEditText_borderColor, Color.TRANSPARENT)
        focusBorderColor = a.getColor(
            kr.co.example.codingtest_wirebarley.R.styleable.RoundEditText_focusBorderColor,
            Color.TRANSPARENT
        )
        a.recycle()
        viewInit()
    }

    fun viewInit() {
        paintBackgroundColor = Paint(Paint.ANTI_ALIAS_FLAG)
        paintBackgroundColor!!.color = backgroundColor
        paintBackgroundColor!!.style = Paint.Style.FILL
        paintBorderColor = Paint(Paint.ANTI_ALIAS_FLAG)
        paintBorderColor!!.color = borderColor
        paintBorderColor!!.style = Paint.Style.STROKE
        paintBorderColor!!.strokeWidth = borderWidth
        isFocusable = true
        super.setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {
        val conn = super.onCreateInputConnection(outAttrs)
        outAttrs.imeOptions = outAttrs.imeOptions and EditorInfo.IME_FLAG_NO_ENTER_ACTION.inv()
        return conn
    }

    override fun setBackgroundColor(color: Int) {
        backgroundColor = color
        invalidate()
    }

    fun getBorderColor(): Int {
        return borderColor
    }

    fun setBorderColor(borderColor: Int) {
        this.borderColor = borderColor
        invalidate()
    }

    fun getFocusBorderColor(): Int {
        return focusBorderColor
    }

    fun setFocusBorderColor(focusBorderColor: Int) {
        this.focusBorderColor = focusBorderColor
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w.toFloat()
        height = h.toFloat()
        setMeasuredDimension(
            MeasureSpec.getSize(width.toInt()),
            MeasureSpec.getSize(height.toInt())
        )
    }

    override fun onDraw(canvas: Canvas) {
        paintBackgroundColor!!.color = backgroundColor
        paintBorderColor!!.color = if (isFocus) focusBorderColor else borderColor
        @SuppressLint("DrawAllocation") val rectF = RectF(
            hScroll + borderWidth / 2.0f,
            vScroll + borderWidth / 2.0f,
            hScroll + width - borderWidth / 2.0f,
            vScroll + height - borderWidth / 2.0f
        )
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paintBackgroundColor!!)
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paintBorderColor!!)
        super.onDraw(canvas)
    }

    override fun onFocusChanged(
        focused: Boolean,
        direction: Int,
        previouslyFocusedRect: Rect?
    ) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        isFocus = focused
        invalidate()
    }

    override fun onScrollChanged(
        horiz: Int,
        vert: Int,
        oldHoriz: Int,
        oldVert: Int
    ) {
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert)
        vScroll = vert.toFloat()
        hScroll = horiz.toFloat()
    }
}