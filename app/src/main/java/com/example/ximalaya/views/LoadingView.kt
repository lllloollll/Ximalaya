package com.example.ximalaya.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.ImageView
import com.example.ximalaya.R

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.views
 * @ClassName:      LoadingView
 * @Description:     自定义VIew，加载页面的转圈圈
 * @Author:         MMC
 * @CreateDate:     2020/12/2 15:53
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/12/2 15:53
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class LoadingView : ImageView {

    private var rotateDegree = 0.0f
    private var mNeedRotate = false

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defSytleAttr: Int) : super(
        context,
        attributeSet,
        defSytleAttr
    ) {
        //设置图标
        setImageResource(R.mipmap.loading)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        //绑定的window的时候
        mNeedRotate = true
        post(object : Runnable {
            override fun run() {
                rotateDegree = if (rotateDegree < 360) rotateDegree + 5 else 0.0f
                invalidate()    //循环调用
                //判断是否继续旋转
                if (mNeedRotate)
                    postDelayed(this, 15)
            }
        })
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        //从window解绑的时候
        mNeedRotate = false
    }

    override fun onDraw(canvas: Canvas?) {
        /**
         * 第一个参数：旋转角度
         * 第二个参数：旋转的X坐标
         * 第三个参数：旋转的Y坐标
         */
        canvas?.rotate(rotateDegree, (width/2).toFloat(), (height/2).toFloat())
        super.onDraw(canvas)
    }
}