package com.example.ximalaya.presenters

import com.example.ximalaya.interfaces.IAlbumDetailPresenter
import com.example.ximalaya.interfaces.IAlbumDetailViewCallback
import com.example.ximalaya.interfaces.IRecommendViewCallback
import com.ximalaya.ting.android.opensdk.model.album.Album

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setTargetAlbum(targetAlbum: Album) {
        this.mTargetAlbum = targetAlbum
    }
}