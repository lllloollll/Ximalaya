package com.example.ximalaya.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.ximalaya.utils.FragmentCreator

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.adapters
 * @ClassName:      MainContentAdapter
 * @Description:     java类作用描述
 * @Author:         MMC
 * @CreateDate:     2020/11/14 2:21
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/11/14 2:21
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class MainContentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return FragmentCreator.getFragment(position)
    }

    override fun getCount(): Int {
        return FragmentCreator.PAGE_COUNT
    }
}