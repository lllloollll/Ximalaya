package com.example.ximalaya.interfaces

import com.ximalaya.ting.android.opensdk.model.album.Album
import com.ximalaya.ting.android.opensdk.model.track.Track

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.interfaces
 * @ClassName:      IAlbumDetailViewCallback
 * @Description:     java类作用描述
 * @Author:         MMC
 * @CreateDate:     2020/12/2 23:29
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/12/2 23:29
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
interface IAlbumDetailViewCallback {
    /**
     * 加载专辑详情内容
     */
    fun onDetailListLoaded(tracks : List<Track>)

    /**
     * 把Album传给UI
     */
    fun onAlbumload(album: Album)

    /**
     * 网络错误
     */
    fun onNetworkError(errorCode: Int, errorMsg: String?)
}