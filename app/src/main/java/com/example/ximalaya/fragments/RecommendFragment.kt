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
import com.example.ximalaya.utils.Constants
import com.example.ximalaya.utils.LogUtil
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack
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

    override fun onSubViewLoaded(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        //获取 猜你喜欢专辑 接口的数据
        getRecommendData()
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
                    outRect.left=it
                    outRect.right=it
                }
            }
        })

        recommendListAdapter = RecommendListAdapter()
        recommendRv.adapter = recommendListAdapter
        return rootView
    }

    /**
     * 获取推荐内容
     * 接口：3.10.6 获取猜你喜欢专辑
     */
    private fun getRecommendData() {
        val map = hashMapOf<String, String>()
        map.put(DTransferConstants.LIKE_COUNT, "${Constants.RECOMMEND_COUNT}")
        CommonRequest.getGuessLikeAlbum(map, object : IDataCallBack<GussLikeAlbumList> {
            override fun onSuccess(p0: GussLikeAlbumList?) {
                for (i in p0!!.albumList) {
                    LogUtil.d(TAG, "${i.albumTitle} : ${i.albumIntro}")
                }
                upRecommendUI(p0)
            }

            override fun onError(p0: Int, p1: String?) {
                LogUtil.d(TAG, "error code is --> $p0,error message is --> $p1")
            }

        })
    }

    private fun upRecommendUI(gussLikeAlbumList: GussLikeAlbumList) {
        recommendListAdapter.setData(gussLikeAlbumList.albumList)
    }


}
