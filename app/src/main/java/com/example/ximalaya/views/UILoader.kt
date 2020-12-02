package com.example.ximalaya.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.FrameLayout
import com.example.ximalaya.R
import com.example.ximalaya.base.BaseApplication

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.views
 * @ClassName:      UILoader
 * @Description:     java类作用描述
 * @Author:         MMC
 * @CreateDate:     2020/12/1 11:41
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/12/1 11:41
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
abstract class UILoader : FrameLayout {
    constructor(context: Context) : super(context, null)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        init()
    }

    var mLoadingView: View? = null
    var mSuccessView: View? = null
    var mErrorView: View? = null
    var mEmptyView: View? = null

    enum class UIStatus {
        LOADING, SUCCESS, NETWORK_ERROR, EMPTY, NONE
    }

    private var mCurrentStatus = UIStatus.NONE

    /**
     * 初始化UI
     */
    fun init() {
        switchUiByCurrentStatus()
    }

    private fun switchUiByCurrentStatus() {
        //加载中
        mLoadingView ?: let {
            mLoadingView = getLoadingView()
            addView(mLoadingView)
        }
        //根据状态设置是否可见
        mLoadingView?.visibility =
            if (mCurrentStatus == UIStatus.LOADING) View.VISIBLE else View.GONE

        //成功
        mSuccessView ?: let {
            mSuccessView = getSuccessView(this)
            addView(mSuccessView)
        }
        //根据状态设置是否可见
        mSuccessView?.visibility =
            if (mCurrentStatus == UIStatus.SUCCESS) View.VISIBLE else View.GONE

        //网络错误
        mErrorView ?: let {
            mErrorView = getNetworkErrorView()
            addView(mErrorView)
        }
        //根据状态设置是否可见
        mErrorView?.visibility =
            if (mCurrentStatus == UIStatus.NETWORK_ERROR) View.VISIBLE else View.GONE

        //数据为空 
        mEmptyView ?: let {
            mEmptyView = getEmptyView()
            addView(mEmptyView)
        }
        //根据状态设置是否可见
        mEmptyView?.visibility =
            if (mCurrentStatus == UIStatus.EMPTY) View.VISIBLE else View.GONE

    }

    fun updateStatus(status: UIStatus) {
        mCurrentStatus = status
        BaseApplication.getHandler().post(object : Runnable {
            override fun run() {
                switchUiByCurrentStatus()
            }

        })
    }

    private fun getEmptyView(): View {
        return LayoutInflater.from(context).inflate(R.layout.fragment_empty_view, this, false)
    }

    private fun getNetworkErrorView(): View {
        return LayoutInflater.from(context).inflate(R.layout.fragment_error_view, this, false)
    }

    abstract fun getSuccessView(container: ViewGroup): View

    private fun getLoadingView(): View {
        return LayoutInflater.from(context).inflate(R.layout.fragment_loading_view, this, false)
    }
}