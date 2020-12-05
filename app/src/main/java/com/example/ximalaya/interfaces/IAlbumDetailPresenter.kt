package com.example.ximalaya.interfaces

import com.example.ximalaya.base.IBasePresenter

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.interfaces
 * @ClassName:      IAlbumDetailPresenter
 * @Description:     java类作用描述
 * @Author:         MMC
 * @CreateDate:     2020/12/2 23:16
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/12/2 23:16
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
interface IAlbumDetailPresenter:IBasePresenter<IAlbumDetailViewCallback> {
    /**
     * 下拉刷新
     */
    fun pull2RefreshMore()

    /**
     * 上拉刷新
     */
    fun loadMore()

    /**
     * 获取专辑详情
     */
    fun getAlbumDetail(id:Int,page:Int)


}