package com.example.ximalaya.interfaces

import com.ximalaya.ting.android.opensdk.model.track.Track
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.interfaces
 * @ClassName:      IPlayerViewCallback
 * @Description:     java类作用描述
 * @Author:         MMC
 * @CreateDate:     2020/12/5 16:08
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/12/5 16:08
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
interface IPlayerViewCallback {
    /**
     * 开始播放
     */
    fun onPlayStart()

    /**
     * 暂停播放
     */
    fun onPlayPause()

    /**
     * 停止播放
     */
    fun onPlayStop()

    /**
     * 播放错误
     */
    fun onPlayError()

    /**
     * 播放上一首
     */
    fun onPlayPre(track: Track)

    /**
     * 播放下一首
     */
    fun onPlayNext(track: Track)

    /**
     * 播放列表数据加载完成
     */
    fun onListLoaded(list:List<Track>)

    /**
     * 播放模式更改
     */
    fun onPlayModeChage(mode:XmPlayListControl.PlayMode)

    /**
     * 播放进度改变
     */
    fun onProgressChange(currentProgress:Long,total:Long)

    /**
     * 广告正在加载
     */
    fun onAdLoading()

    /**
     * 广告结束
     */
    fun onAdFinshed()
}