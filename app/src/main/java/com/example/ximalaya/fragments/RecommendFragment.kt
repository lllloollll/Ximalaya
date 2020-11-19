package com.example.ximalaya.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ximalaya.R
import com.example.ximalaya.base.BaseFragment
import com.example.ximalaya.utils.Constants
import com.example.ximalaya.utils.LogUtil
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList

/**
 * A simple [Fragment] subclass.
 */
class RecommendFragment : BaseFragment() {
    private val TAG: String="RecommendFragment"

    override fun onSubViewLoaded(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        //获取 猜你喜欢专辑 接口的数据
        getRecommendData()
        return layoutInflater.inflate(R.layout.fragment_recommend,container,false)
    }

    /**
     * 获取推荐内容
     * 接口：3.10.6 获取猜你喜欢专辑
     */
    private fun getRecommendData() {
        val map = hashMapOf<String,String>()
        map.put(DTransferConstants.LIKE_COUNT,"${Constants.RECOMMEND_COUNT}")
        CommonRequest.getGuessLikeAlbum(map,object : IDataCallBack<GussLikeAlbumList> {
            override fun onSuccess(p0: GussLikeAlbumList?) {
                for(i in p0!!.albumList){
                    LogUtil.d(TAG,"${i.albumTitle} : ${i.albumIntro}")
                }
            }

            override fun onError(p0: Int, p1: String?) {
                LogUtil.d(TAG,"error code is --> $p0,error message is --> $p1")
            }

        })
    }



}
