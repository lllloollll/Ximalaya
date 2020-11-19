package com.example.ximalaya.utils

import com.example.ximalaya.base.BaseFragment
import com.example.ximalaya.fragments.HistoryFragment
import com.example.ximalaya.fragments.RecommendFragment
import com.example.ximalaya.fragments.SubscriptionFragment

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.utils
 * @ClassName:      FragmentCreator
 * @Description:     java类作用描述
 * @Author:         MMC
 * @CreateDate:     2020/11/18 1:32
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/11/18 1:32
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class FragmentCreator {
    companion object{
        val INDEX_RECOMMEND=0
        val INDEX_SUBSCRIPTION=1
        val INDEX_HISTORY=2

        val PAGE_COUNT = 3

        val sCache= hashMapOf<Int,BaseFragment>()

        fun getFragment(index:Int):BaseFragment{
            var baseFragment= sCache.get(index)
            if (baseFragment!=null) return baseFragment
            when(index){
                INDEX_RECOMMEND->
                    baseFragment=RecommendFragment()
                INDEX_SUBSCRIPTION->
                    baseFragment=SubscriptionFragment()
                INDEX_HISTORY->
                    baseFragment=HistoryFragment()
            }
            sCache.put(index,baseFragment!!)
            return baseFragment!!
        }
    }
}