 package com.example.ximalaya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ximalaya.utils.LogUtil
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack
import com.ximalaya.ting.android.opensdk.model.category.CategoryList
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

        var map = hashMapOf<String,String>()
        CommonRequest.getCategories(map,object :IDataCallBack<CategoryList>{
            override fun onSuccess(p0: CategoryList?) {
                val categories = p0?.categories
                categories?.run{
                    val size = size
                    LogUtil.d(TAG,"categories size is ----> $size")
                    forEach{
                        LogUtil.d(TAG,"catogory is ----> ${it.categoryName}")
                    }
                }
            }

            override fun onError(p0: Int, p1: String?) {
                LogUtil.e(TAG,"error code ----> $p0 ,error message ----> $p1")
            }

        })

    }
}
