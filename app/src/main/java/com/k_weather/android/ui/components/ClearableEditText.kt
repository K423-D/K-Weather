package com.k_weather.android.ui.components

import android.content.Context
import android.graphics.LightingColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import com.k_weather.android.R

class ClearableEditText(context: Context, attr: AttributeSet) :
    androidx.appcompat.widget.AppCompatEditText(context, attr) {

    private var rightClearDrawable: Drawable? = null
    private var drawable: Drawable? = null

    init {
        /*获取删除按钮图片的Drawable对象*/
        drawable = ContextCompat.getDrawable(context, android.R.drawable.ic_menu_close_clear_cancel)

        /*设置图片的范围*/
        drawable!!.setBounds(0, 0, drawable!!.minimumWidth , drawable!!.minimumHeight )

        /*设置颜色*/
        val filter = LightingColorFilter(0x000000,0xff0000)
        drawable!!.colorFilter = filter

        /*设置EditText和删除按钮图片的间距*/
        compoundDrawablePadding = context.resources.getDimensionPixelSize(R.dimen.dp5)

        /*输入框内容监听*/
        addTextChangedListener(TextWatchImpl())

        /*设置是否显示删除按钮*/
        setHideRightClearDrawable(false)
    }

    private fun setHideRightClearDrawable(isVisible: Boolean) {
        /*是否显示删除按钮*/
        rightClearDrawable = if (isVisible) {
            drawable
        } else {
            null
        }

        /*给EditText左，上，右，下设置图片*/
        this.setCompoundDrawables(
            compoundDrawables[0],
            compoundDrawables[1],
            rightClearDrawable,
            compoundDrawables[3]
        )
    }

    /*这里使用inner表示内部类*/
    private inner class TextWatchImpl : TextWatcher {

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

        }

        override fun afterTextChanged(editable: Editable) {
            /*判断输入框有没有内容，设置是否显示删除按钮*/
            if ("" != text.toString().trim { it <= ' ' } && text.toString().trim { it <= ' ' }
                    .isNotEmpty()) {
                setHideRightClearDrawable(true)
            } else {
                setHideRightClearDrawable(false)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        /*判断手指按下的x座标*/
        val x = event.x

        /*获取自定义EditText宽度*/
        val width = this@ClearableEditText.width.toFloat()

        /*获取EditText右Padding值*/
        val totalPaddingRight = this@ClearableEditText.totalPaddingRight.toFloat()

        /*判断手指按下的区域是否在删除按钮宽高范围内*/
        if (event.action == MotionEvent.ACTION_UP) {
            if (x > width - totalPaddingRight && x < width && event.y < this@ClearableEditText.height) {
                this@ClearableEditText.setText("")
            }
        }
        return super.onTouchEvent(event)
    }
}