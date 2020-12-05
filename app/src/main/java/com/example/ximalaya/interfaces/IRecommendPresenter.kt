package com.example.ximalaya.interfaces

import com.example.ximalaya.base.IBasePresenter
import com.ximalaya.ting.android.opensdk.model.album.Album

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.interfaces
 * @ClassName:      IRecommendPresenter
 * @Description:    推荐页接口
 * @Author:         MMC
 * @CreateDate:     2020/11/21 13:21
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/11/21 13:21
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
interface IRecommendPresenter:IBasePresenter<IRecommendViewCallback> {
    /**
     * 获取推荐内容
     */
    fun getRecommendList()

    /**
     * 下拉刷新
     */
    fun pull2RefreshMore()

    /**
     * 上拉刷新
     */
    fun loadMore()




}