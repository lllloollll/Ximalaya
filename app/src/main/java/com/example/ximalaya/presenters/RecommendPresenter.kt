package com.example.ximalaya.presenters

import com.example.ximalaya.interfaces.IRecommendPresenter
import com.example.ximalaya.interfaces.IRecommendViewCallback
import com.example.ximalaya.utils.Constants
import com.example.ximalaya.utils.LogUtil
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack
import com.ximalaya.ting.android.opensdk.model.album.Album
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.presenters
 * @ClassName:      RecommendPresenter
 * @Description:     java类作用描述
 * @Author:         MMC
 * @CreateDate:     2020/11/21 16:18
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/11/21 16:18
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class RecommendPresenter : IRecommendPresenter {


    private val TAG: String = "RecommendPresenter"
    private val mCallbacks = arrayListOf<IRecommendViewCallback>()

    companion object {
        private var instance: RecommendPresenter? = null

        /**
         * 懒汉式单例
         */
        public fun getInstance(): RecommendPresenter {
            if (instance == null) {
                synchronized(RecommendPresenter.javaClass) {
                    if (instance == null) {
                        instance = RecommendPresenter()
                    }
                }
            }
            return instance!!
        }
    }

    override fun registerViewCallback(callback: IRecommendViewCallback) {
        if (mCallbacks != null && !mCallbacks.contains(callback)) {
            mCallbacks.add(callback)
        }
    }

    override fun unRegisterViewCallback(callback: IRecommendViewCallback) {
        if (mCallbacks.contains(callback)) {
            mCallbacks.remove(callback)
        }
    }

    override fun getRecommendList() {
        //获取推荐内容
        getRecommendData()
    }

    override fun pull2RefreshMore() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadMore() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
                handlerRecommendResult(p0!!.albumList)
            }

            override fun onError(p0: Int, p1: String?) {
                LogUtil.d(TAG, "error code is --> $p0,error message is --> $p1")
            }

        })
    }

    private fun handlerRecommendResult(i: MutableList<Album>) {
        //通知UI更新
        for (j in mCallbacks) {
            j.onRecommendListLoaded(i)
        }
    }
}