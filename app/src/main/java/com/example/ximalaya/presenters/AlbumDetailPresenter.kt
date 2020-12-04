package com.example.ximalaya.presenters

import com.example.ximalaya.interfaces.IAlbumDetailPresenter
import com.example.ximalaya.interfaces.IAlbumDetailViewCallback
import com.example.ximalaya.interfaces.IRecommendViewCallback
import com.example.ximalaya.utils.Constants
import com.example.ximalaya.utils.LogUtil
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack
import com.ximalaya.ting.android.opensdk.model.album.Album
import com.ximalaya.ting.android.opensdk.model.track.Track
import com.ximalaya.ting.android.opensdk.model.track.TrackList

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.presenters
 * @ClassName:      AlbumDetailPresenter
 * @Description:     java类作用描述
 * @Author:         MMC
 * @CreateDate:     2020/12/2 23:19
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/12/2 23:19
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class AlbumDetailPresenter : IAlbumDetailPresenter {
    override fun registerViewCallback(callback: IAlbumDetailViewCallback) {
        if (mCallbacks != null && !mCallbacks.contains(callback)) mCallbacks.add(callback)
        mTargetAlbum?.let {
            callback.onAlbumload(mTargetAlbum!!)
        }
    }

    override fun unRegisterViewCallback(callback: IAlbumDetailViewCallback) {
        if (mCallbacks.contains(callback)) {
            mCallbacks.remove(callback)

        }
    }

    private val TAG: String = "AlbumDetailPresenter"
    private var mTargetAlbum: Album? = null
    private val mCallbacks = arrayListOf<IAlbumDetailViewCallback>()

    /**
     * 懒汉式单例
     * 1.构造方法私有化
     * 2.单例对象私有化+静态化
     */
    private constructor()

    companion object {
        private var instance: AlbumDetailPresenter? = null
        fun getInstance(): AlbumDetailPresenter {
            instance ?: let {
                synchronized(AlbumDetailPresenter::class.java) {
                    instance ?: let {
                        instance = AlbumDetailPresenter()
                    }
                }
            }
            return instance!!
        }
    }

    override fun pull2RefreshMore() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadMore() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumDetail(id: Int, page: Int) {
        //根据id和页码获取专辑
        val map = hashMapOf<String, String>()
        map.put(DTransferConstants.ALBUM_ID, id.toString())
        map.put(DTransferConstants.SORT, "asc")
        map.put(DTransferConstants.PAGE, page.toString())
        map.put(DTransferConstants.PAGE_SIZE,Constants.COUNT_DEFAULT.toString())
        CommonRequest.getTracks(map, object : IDataCallBack<TrackList> {
            override fun onSuccess(p0: TrackList?) {
                p0?.run {
                    LogUtil.d(TAG, "tracklist size --> ${tracks.size}")
                    //成功获取数据，通知view更新UI
                    handlerAlbumDetailResult(tracks)
                }
            }

            override fun onError(p0: Int, p1: String?) {
                LogUtil.d(TAG, "error code --> $p0")
                p1?.let { LogUtil.d(TAG, "error content --> $it") }
            }

        })
    }

    private fun handlerAlbumDetailResult(tracks:List<Track>) {
        for (mCallback in mCallbacks)
            mCallback.onDetailListLoaded(tracks)
    }

    fun setTargetAlbum(targetAlbum: Album) {
        this.mTargetAlbum = targetAlbum
    }
}