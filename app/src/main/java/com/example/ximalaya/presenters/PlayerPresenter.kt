package com.example.ximalaya.presenters

import com.example.ximalaya.base.BaseApplication
import com.example.ximalaya.interfaces.IPlayerPresenter
import com.example.ximalaya.interfaces.IPlayerViewCallback
import com.example.ximalaya.utils.LogUtil
import com.ximalaya.ting.android.opensdk.model.track.Track
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.presenters
 * @ClassName:      PlayerPresenter
 * @Description:     java类作用描述
 * @Author:         MMC
 * @CreateDate:     2020/12/5 16:23
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/12/5 16:23
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class PlayerPresenter : IPlayerPresenter {
    private var mPlayerManager: XmPlayerManager? = null
    private val TAG = "PlayerPresenter"
    private var isPlayListSet = false

    private constructor() {
        mPlayerManager = XmPlayerManager.getInstance(BaseApplication.getContext())
    }

    companion object {
        private var instance: PlayerPresenter? = null
        fun getInstance(): PlayerPresenter {
            instance ?: let {
                synchronized(PlayerPresenter::class.java) {
                    instance ?: let {
                        instance = PlayerPresenter()
                    }
                }
            }
            return instance!!
        }
    }

    /**
     * 暴露方法传递播放列表
     */
    fun setPlayList(list: List<Track>, index: Int) {
        mPlayerManager?.let {
            it.setPlayList(list, index)
            isPlayListSet = true
        }
        mPlayerManager ?: let {
            LogUtil.d(TAG, "mPlayerManager is null")
        }
    }

    override fun play() {
        if (isPlayListSet){
            mPlayerManager?.play()
        }
    }

    override fun pause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun playPre() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun playNext() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun switchPlayMode(mode: XmPlayListControl.PlayMode) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPlayList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun playByIndex(index: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun seekTo(progress: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerViewCallback(t: IPlayerViewCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unRegisterViewCallback(t: IPlayerViewCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}