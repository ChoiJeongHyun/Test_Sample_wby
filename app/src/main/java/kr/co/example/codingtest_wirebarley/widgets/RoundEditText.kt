package kr.co.example.codingtest_wirebarley.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.appcompat.widget.AppCompatEditText
import kr.co.example.codingtest_wirebarley.R

class RoundEditText : AppCompatEditText {

    private var width: Float = 0f
    private var height: Float = 0f
    private var cornerRadius: Float = 0f
    private var borderSize: Float = 0f

    private var paintBack: Paint = Paint()
    private var paintBoard: Paint = Paint()


    private var backgroundColorNormal: Int = Color.TRANSPARENT
    private var borderColor: Int = Color.TRANSPARENT
    private var focusBorderColor: Int = Color.TRANSPARENT

    private var isFocus: Boolean = false
    private var vScroll: Float = 0.0f
    private var hScroll: Float = 0.0f

    @JvmOverloads
    constructor(context: Context , attrs: AttributeSet , defStyleAttr: Int = R.attr.editTextStyle) : super(context, attrs, defStyleAttr){
        val typeArray = context.obtainStyledAttributes(attrs , R.styleable.RoundEditText, defStyleAttr , 0)

        cornerRadius = typeArray.getDimensionPixelSize(R.styleable.RoundEditText_cornerRadius , 0).toFloat()
        borderSize = typeArray.getDimensionPixelSize(R.styleable.RoundEditText_borderWidth , 0).toFloat()

        backgroundColorNormal = typeArray.getColor(R.styleable.RoundEditText_backgroundColor , Color.TRANSPARENT)
        borderColor = typeArray.getColor(R.styleable.RoundEditText_borderColor , Color.TRANSPARENT)
        focusBorderColor = typeArray.getColor(R.styleable.RoundEditText_focusBorderColor , Color.TRANSPARENT)

        typeArray.recycle()
        viewInit()
    }


    private fun viewInit(){
        paintBack.apply {
            color = backgroundColorNormal
            style = Paint.Style.FILL
        }

        paintBoard.apply {
            color = borderColor
            style = Paint.Style.STROKE
            strokeWidth = borderSize
        }

        isFocusable = true


        super.setBackgroundColor(Color.TRANSPARENT)
    }

    override fun setBackgroundColor(color: Int) {
//        super.setBackgroundColor(color)
        backgroundColorNormal = color
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w.toFloat()
        height = h.toFloat()
        setMeasuredDimension(
            MeasureSpec.getSize(width.toInt()), MeasureSpec.getSize(height.toInt())
        )
    }

    override fun onDraw(canvas: Canvas?) {
        paintBack.color = backgroundColorNormal
//        paintBoard.color = borderColor
        paintBoard.color = if (isFocus) focusBorderColor else borderColor
        @SuppressLint("DrawAllocation") val rectF = RectF(
            hScroll + borderSize / 2.0f,
            vScroll + borderSize / 2.0f,
            hScroll + width - borderSize / 2.0f,
            vScroll + height - borderSize / 2.0f
        )
        canvas!!.drawRoundRect(rectF, cornerRadius, cornerRadius, paintBack)
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paintBoard)
        super.onDraw(canvas)
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        isFocus = focused
        invalidate()
    }

    override fun onScrollChanged(horiz: Int, vert: Int, oldHoriz: Int, oldVert: Int) {
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert)
        vScroll = vert.toFloat()
        hScroll = horiz.toFloat()
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection? {
        val conn = super.onCreateInputConnection(outAttrs)
        outAttrs.imeOptions = outAttrs.imeOptions and EditorInfo.IME_FLAG_NO_ENTER_ACTION.inv()
        return conn
    }







}