package com.example.ximalaya.fragments


import android.graphics.Rect
import android.os.Bundle
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
import com.example.ximalaya.utils.Constants
import com.example.ximalaya.utils.LogUtil
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack
import com.ximalaya.ting.android.opensdk.model.album.Album
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList
import kotlinx.android.synthetic.main.fragment_recommend.*
import net.lucode.hackware.magicindicator.buildins.UIUtil

/**
 * A simple [Fragment] subclass.
 */
class RecommendFragment : BaseFragment() {
    private val TAG: String = "RecommendFragment"

    lateinit var rootView: View
    lateinit var recommendRv: RecyclerView
    lateinit var recommendListAdapter: RecommendListAdapter

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
        //获取 猜你喜欢专辑 接口的数据
//        getRecommendData()
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

        //逻辑层对象
        //设置通知接口的注册
        recommendPresenter.registerViewCallback(iRecommendViewCallback)
        recommendPresenter.getRecommendList()
        return rootView
    }

    val iRecommendViewCallback = object : IRecommendViewCallback {
        override fun onRecommendListLoaded(result: List<Album>) {
            //当获取推荐内容，该方法就会被调用成功
            //拿到数据，更新UI
            recommendListAdapter.setData(result)
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

}
