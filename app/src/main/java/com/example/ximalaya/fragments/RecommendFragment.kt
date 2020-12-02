package com.example.ximalaya.fragments


import android.graphics.Rect
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.ximalaya.R
import com.example.ximalaya.adapters.RecommendListAdapter
import com.example.ximalaya.base.BaseFragment
import com.example.ximalaya.interfaces.IRecommendViewCallback
import com.example.ximalaya.presenters.RecommendPresenter
import com.example.ximalaya.utils.LogUtil
import com.example.ximalaya.views.UILoader
import com.ximalaya.ting.android.opensdk.model.album.Album
import net.lucode.hackware.magicindicator.buildins.UIUtil

/**
 * A simple [Fragment] subclass.
 */
class RecommendFragment : BaseFragment() {
    private val TAG: String = "RecommendFragment"

    lateinit var rootView: View
    lateinit var recommendRv: RecyclerView
    lateinit var recommendListAdapter: RecommendListAdapter
    lateinit var mUiLoader: UILoader


    //获取逻辑层对象
    private val recommendPresenter: RecommendPresenter
        get() {
            val recommendPresenter = RecommendPresenter.getInstance()
            return recommendPresenter
        }

    override fun onSubViewLoaded(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        mUiLoader = object : UILoader(context!!) {
            override fun getSuccessView(container: ViewGroup): View {
                return createSuccessView(layoutInflater, container)
            }

        }

        mUiLoader.setOnRetryClickListener(onRetryClickListener)


        //逻辑层对象
        //设置通知接口的注册
        recommendPresenter.registerViewCallback(iRecommendViewCallback)
        recommendPresenter.getRecommendList()

        if (mUiLoader.parent is ViewGroup) {
            ((mUiLoader.parent) as ViewGroup).removeView(mUiLoader)
        }
//        return rootView
        return mUiLoader
    }

    private fun createSuccessView(
        layoutInflater: LayoutInflater,
        container: ViewGroup
    ): View {
        rootView = layoutInflater.inflate(R.layout.fragment_recommend, container, false)
        //使用RecyclerView,设置布局管理器
        recommendRv = rootView.findViewById(R.id.recommend_list)
        recommendRv.layoutManager = LinearLayoutManager(context)
        //设置Item项目的上下间距
        recommendRv.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                UIUtil.dip2px(view.context, 5.0).let {
                    outRect.top = it
                    outRect.bottom = it
                    outRect.left = it
                    outRect.right = it
                }
            }
        })
        //设置适配器
        recommendListAdapter = RecommendListAdapter()
        recommendRv.adapter = recommendListAdapter
        return rootView
    }

    val iRecommendViewCallback = object : IRecommendViewCallback {
        override fun onNetworkError() {
            LogUtil.d(TAG, "NetworkError")
            mUiLoader.updateStatus(UILoader.UIStatus.NETWORK_ERROR)
        }

        override fun onEmpty() {
            LogUtil.d(TAG, "Empty")
            mUiLoader.updateStatus(UILoader.UIStatus.EMPTY)
        }

        override fun onLoading() {
            LogUtil.d(TAG, "Loading")
            mUiLoader.updateStatus(UILoader.UIStatus.LOADING)
        }

        override fun onRecommendListLoaded(result: List<Album>) {
            //当获取推荐内容，该方法就会被调用成功
            //拿到数据，更新UI
            LogUtil.d(TAG, "onRecommendList")
            recommendListAdapter.setData(result)
            mUiLoader.updateStatus(UILoader.UIStatus.SUCCESS)
        }

        override fun onRefreshMore(rusult: List<Album>) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onLoadMore(result: List<Album>) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (recommendPresenter != null) {
            recommendPresenter.unRegisterViewCallback(iRecommendViewCallback)
        }
    }

    val onRetryClickListener = object : UILoader.OnRetryClickListener {
        override fun onRetryClick() {
            //重新获取数据
            recommendPresenter.getRecommendList()
        }

    }

}
