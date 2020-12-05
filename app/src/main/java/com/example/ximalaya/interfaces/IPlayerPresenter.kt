package com.example.ximalaya.interfaces

import com.example.ximalaya.base.IBasePresenter
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.interfaces
 * @ClassName:      IPlayerPresenter
 * @Description:    播放器接口
 * @Author:         MMC
 * @CreateDate:     2020/12/5 16:02
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/12/5 16:02
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
interface IPlayerPresenter :IBasePresenter<IPlayerViewCallback>{

    /**
     * 播放
     */
    fun play()

    /**
     * 暂停
     */
    fun pause()

    /**
     * 停止
     */
    fun stop()

    /**
     * 上一首
     */
    fun playPre()

    /**
     * 下一首
     */
    fun playNext()

    /**
     * 切换播放模式
     */
    fun switchPlayMode(mode:XmPlayListControl.PlayMode)

    /**
     * 获取播放列表
     */
    fun getPlayList()

    /**
     * 根据节目在列表中的位置进行播放
     */
    fun playByIndex(index:Int)

    /**
     * 切换播放进度
     */
    fun seekTo(progress:Int)
}