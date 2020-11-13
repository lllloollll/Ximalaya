 package com.example.ximalaya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.example.ximalaya.adapters.IndicatorAdapter
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

 class MainActivity : AppCompatActivity() {

     companion object{
         val TAG="MainActivity"
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CommonRequest.getInstanse().useHttps=true

        initView()

//        var map = hashMapOf<String,String>()
//        CommonRequest.getCategories(map,object :IDataCallBack<CategoryList>{
//            override fun onSuccess(p0: CategoryList?) {
//                val categories = p0?.categories
//                categories?.run{
//                    val size = size
//                    LogUtil.d(TAG,"categories size is ----> $size")
//                    forEach{
//                        LogUtil.d(TAG,"catogory is ----> ${it.categoryName}")
//                    }
//                }
//            }
//
//            override fun onError(p0: Int, p1: String?) {
//                LogUtil.e(TAG,"error code ----> $p0 ,error message ----> $p1")
//            }
//
//        })

    }

     private fun initView() {
         val magicIndicator = main_indicator as MagicIndicator
         magicIndicator.setBackgroundColor(this.resources.getColor(R.color.main_color))

         //设置indicator的适配器
         val commonNavigator=CommonNavigator(this)
         commonNavigator.adapter=IndicatorAdapter(applicationContext)

         //绑定ViewPage和indicator
         val contentPager = main_content as ViewPager
         magicIndicator.navigator=commonNavigator
         ViewPagerHelper.bind(magicIndicator,contentPager)
     }
 }
