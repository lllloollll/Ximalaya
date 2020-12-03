package com.example.ximalaya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.example.ximalaya.adapters.IndicatorAdapter
import com.example.ximalaya.adapters.MainContentAdapter
import com.example.ximalaya.base.BaseActivity
import com.example.ximalaya.utils.FragmentCreator
import com.example.ximalaya.utils.LogUtil
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack
import com.ximalaya.ting.android.opensdk.model.category.CategoryList
import kotlinx.android.synthetic.main.activity_main.*
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import java.util.*
import kotlin.math.log

class MainActivity : BaseActivity() {

    val TAG = "MainActivity"

    lateinit var indicatorAdapter: IndicatorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CommonRequest.getInstanse().useHttps = true

        initView()
        initEvent()

    }

    fun initEvent() {
        indicatorAdapter.setOnIndicatorTabClickListener(object :
            IndicatorAdapter.OnIndicatorTabClickListener {
            override fun onTabClick(index: Int) {
//                FragmentCreator.getFragment(index)
                contentPager.setCurrentItem(index)
            }

        })
    }

    private val contentPager: ViewPager
        get() {
            val contentPager = main_content as ViewPager
            return contentPager
        }

    private fun initView() {
        val magicIndicator = main_indicator as MagicIndicator
//        magicIndicator.setBackgroundColor(this.resources.getColor(R.color.main_color))

        //设置indicator的适配器
        val commonNavigator = CommonNavigator(this)
        indicatorAdapter = IndicatorAdapter(applicationContext)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = indicatorAdapter

        //设置ViewPager适配器
        val viewPagerAdapter = MainContentAdapter(supportFragmentManager)
        contentPager.adapter = viewPagerAdapter

        //绑定ViewPage和indicator
        magicIndicator.navigator = commonNavigator
        ViewPagerHelper.bind(magicIndicator, contentPager)
    }
}
