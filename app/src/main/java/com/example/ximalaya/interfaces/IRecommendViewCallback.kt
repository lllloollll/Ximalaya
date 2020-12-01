package com.example.ximalaya.interfaces

import com.ximalaya.ting.android.opensdk.model.album.Album

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.interfaces
 * @ClassName:      IRecommendViewCallback
 * @Description:     java类作用描述
 * @Author:         MMC
 * @CreateDate:     2020/11/21 13:24
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/11/21 13:24
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
interface IRecommendViewCallback {
    /**
     * 获取推荐内容结果
     */
    fun onRecommendListLoaded(result:List<Album>)

    /**
     * 下拉加载更多的结果
     */
    fun onRefreshMore(rusult:List<Album>)

    /**
     * 上拉刷新的结果
     */
    fun onLoadMore(result: List<Album>)


}