package com.cradlecare.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.cradlecare.R

/*

class OTPView(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    //------------------------variables------------------------
    var otpLength: Int = 6
    var borderThickness: Int = 1
    var mPaint: Paint
    var borderColor: Int = Color.BLACK
    var spaceBetween: Float = 24f
    var spaceBetweenWithDensity: Float = 24f
    var lineSpacing: Float = 8f
    var lineSpacingWithDensity: Float = 8f
    private var OtpListener: OTPListener? = null
    private var mClickListener: OnClickListener? = null
    private var otpLayoutColor: Int = this.currentTextColor
    private var otpLayoutType: Int = 0

    //------------------------listeners------------------------
    private var mTextWatcher: TextWatcher? = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (p0?.length == otpLength) {
                OtpListener?.onOTPCompleted(p0.toString())
            }
        }

        override fun afterTextChanged(p0: Editable?) {
            //
        }

    }

    init {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.OTPView
        )
        try {
            otpLength = typedArray.getInteger(R.styleable.OTPView_otpLength, 6)
            borderThickness = typedArray.getInteger(R.styleable.OTPView_borderThickness, 2)
            spaceBetween = typedArray.getFloat(R.styleable.OTPView_spaceBetween, 24f)
            otpLayoutColor =
                typedArray.getInteger(R.styleable.OTPView_otpLayoutColor, this.currentTextColor)
            otpLayoutType = typedArray.getInteger(R.styleable.OTPView_otpLayoutType, 0)

            val multi = context.resources.displayMetrics.density
            mPaint = Paint(paint)
            mPaint.strokeWidth = multi * borderThickness
            mPaint.color = borderColor
            setBackgroundResource(0)
            spaceBetweenWithDensity = multi * spaceBetween //convert to pixels for our density

            lineSpacingWithDensity = multi * lineSpacing //convert to pixels for our density

            this.inputType = InputType.TYPE_CLASS_NUMBER
            this.isCursorVisible = false

            setMaxLength(otpLength)

            super.setOnClickListener { v -> // When tapped, move cursor to end of text.
                setSelection(text!!.length)
                mClickListener?.onClick(v)
            }

            super.addTextChangedListener(mTextWatcher)
        } finally {
            typedArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        val availableWidth = width - paddingRight - paddingLeft
        val mCharSize: Float = if (spaceBetweenWithDensity < 0) {
            availableWidth / (otpLength.toFloat() * 2 - 1)
        } else {
            (availableWidth - spaceBetweenWithDensity * (otpLength.toFloat() - 1)) / otpLength.toFloat()
        }
        var startX: Float = paddingLeft.toFloat()
        val bottom: Float = (height - paddingBottom).toFloat()
        var top: Float = 120f

        // Text Width
        val text = text
        val textLength = text!!.length
        val textWidths = FloatArray(textLength)
        paint.getTextWidths(getText(), 0, textLength, textWidths)
        mPaint.color = otpLayoutColor
        if (otpLayoutType == 0) {
            for (i in 0 until otpLength) {
                canvas.drawLine(startX, bottom, startX + mCharSize, bottom, mPaint)
                if (getText()!!.length > i) {
                    val middle = startX + mCharSize / 2
                    paint.color = this.currentTextColor
                    canvas.drawText(
                        text, i, i + 1, middle - textWidths[0] / 2, bottom - lineSpacingWithDensity,
                        paint
                    )
                }
                if (spaceBetweenWithDensity < 0) {
                    startX += (mCharSize * 2).toInt()
                } else {
                    startX += mCharSize + spaceBetweenWithDensity
                }
            }
        } else {
            mPaint.style = Paint.Style.STROKE
            for (i in 0 until otpLength) {
                canvas.drawRect(startX, top, startX + mCharSize, bottom, mPaint)
                if (getText()!!.length > i) {
                    val middle = startX + mCharSize / 2
                    paint.color = this.currentTextColor
                    canvas.drawText(
                        text, i, i + 1, middle - textWidths[0] / 2, bottom - lineSpacingWithDensity,
                        paint
                    )
                }
                if (spaceBetweenWithDensity < 0) {
                    startX += (mCharSize * 2).toInt()
                } else {
                    startX += mCharSize + spaceBetweenWithDensity
                }
            }
        }

    }

    //------------------------setters------------------------
    fun setOTPListener(listener: OTPListener) {
        OtpListener = listener
    }

    override fun setOnClickListener(l: OnClickListener?) {
        mClickListener = l
    }

    override fun setCustomSelectionActionModeCallback(actionModeCallback: android.view.ActionMode.Callback?) {
        throw RuntimeException("setCustomSelectionActionModeCallback() not supported.")
    }

    private fun setMaxLength(length: Int) {
        val filterArray = arrayOfNulls<InputFilter>(1)
        filterArray[0] = LengthFilter(length)
        super.setFilters(filterArray)
    }

    //------------------------callbacks------------------------
    interface OTPListener {
        fun onOTPCompleted(otp: String)
    }


}*/

class OTPView(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    //------------------------variables------------------------
    var otpLength: Int = 6
    var borderThickness: Int = 1
    var mPaint: Paint
    var borderColor: Int = Color.BLACK
    var spaceBetween: Float = 24f
    var spaceBetweenWithDensity: Float = 24f
    var lineSpacing: Float = 8f
    var lineSpacingWithDensity: Float = 8f
    private var OtpListener: OTPListener? = null
    private var mClickListener: OnClickListener? = null
    private var otpLayoutColor: Int = this.currentTextColor
    private var otpLayoutType: Int = 0
    private var boxBackgroundDrawable: Int = -1

    //------------------------listeners------------------------
    private var mTextWatcher: TextWatcher? = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (p0?.length == otpLength) {
                OtpListener?.onOTPCompleted(p0.toString())
            }
        }

        override fun afterTextChanged(p0: Editable?) {}
    }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OTPView)
        try {
            otpLength = typedArray.getInteger(R.styleable.OTPView_otpLength, 6)
            borderThickness =
                typedArray.getDimensionPixelSize(R.styleable.OTPView_borderThickness, 2)
            spaceBetween = typedArray.getFloat(R.styleable.OTPView_spaceBetween, 24f)
            otpLayoutColor =
                typedArray.getColor(R.styleable.OTPView_otpLayoutColor, this.currentTextColor)
            otpLayoutType = typedArray.getInt(R.styleable.OTPView_otpLayoutType, 0)
            boxBackgroundDrawable =
                typedArray.getResourceId(R.styleable.OTPView_boxBackgroundDrawable, -1)

            val multi = context.resources.displayMetrics.density
            mPaint = Paint(paint)
            mPaint.strokeWidth = multi * borderThickness
            mPaint.color = otpLayoutColor
            setBackgroundResource(0)
            spaceBetweenWithDensity = multi * spaceBetween
            lineSpacingWithDensity = multi * lineSpacing

            this.inputType = InputType.TYPE_CLASS_NUMBER
            this.isCursorVisible = false

            setMaxLength(otpLength)
            super.setOnClickListener { v ->
                setSelection(text!!.length)
                mClickListener?.onClick(v)
            }
            super.addTextChangedListener(mTextWatcher)
        } finally {
            typedArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        val availableWidth = width - paddingRight - paddingLeft
        val mCharSize: Float = if (spaceBetweenWithDensity < 0) {
            availableWidth / (otpLength * 2 - 1).toFloat()
        } else {
            (availableWidth - spaceBetweenWithDensity * (otpLength - 1)) / otpLength.toFloat()
        }
        var startX = paddingLeft.toFloat()
        val top = paddingTop.toFloat()
        val bottom = (height - paddingBottom).toFloat()

        val text = text ?: ""
        val textLength = text.length
        val textWidths = FloatArray(textLength)
        paint.getTextWidths(text, 0, textLength, textWidths)

        for (i in 0 until otpLength) {
            // Draw the box or background drawable
            if (boxBackgroundDrawable != -1) {
                val drawable = context.getDrawable(boxBackgroundDrawable)
                drawable?.setBounds(
                    startX.toInt(),
                    top.toInt(),
                    (startX + mCharSize).toInt(),
                    bottom.toInt()
                )
                drawable?.draw(canvas)
            } else {
                mPaint.style = Paint.Style.STROKE
                canvas.drawRect(startX, top, startX + mCharSize, bottom, mPaint)
            }

            // Center the text within each box
            if (text.length > i) {
                val middle = startX + mCharSize / 2
                val baselineY = (top + bottom) / 2 - (paint.descent() + paint.ascent()) / 2
                paint.color = this.currentTextColor
                canvas.drawText(
                    text, i, i + 1, middle - textWidths[0] / 2, baselineY,
                    paint
                )
            }

            startX += mCharSize + spaceBetweenWithDensity
        }
    }


    //------------------------setters------------------------
    fun setOTPListener(listener: OTPListener) {
        OtpListener = listener
    }

    override fun setOnClickListener(l: OnClickListener?) {
        mClickListener = l
    }

    override fun setCustomSelectionActionModeCallback(actionModeCallback: android.view.ActionMode.Callback?) {
        throw RuntimeException("setCustomSelectionActionModeCallback() not supported.")
    }

    private fun setMaxLength(length: Int) {
        val filterArray = arrayOfNulls<InputFilter>(1)
        filterArray[0] = LengthFilter(length)
        super.setFilters(filterArray)
    }

    //------------------------callbacks------------------------
    interface OTPListener {
        fun onOTPCompleted(otp: String)
    }
}
