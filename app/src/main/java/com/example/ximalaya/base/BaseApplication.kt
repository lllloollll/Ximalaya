package com.example.ximalaya.base

import android.app.Application
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Handler
import com.example.ximalaya.utils.LogUtil


class BaseApplication: Application() {

    companion object{
        private lateinit var sHandler: Handler
        fun getHandler():Handler= sHandler
    }
    override fun onCreate() {
        super.onCreate()

        sHandler=Handler()

        //初始化LogUtil
        LogUtil.init(this.packageName,0)

        val mXimalaya = CommonRequest.getInstanse()
        if (DTransferConstants.isRelease) {
            val mAppSecret = "8646d66d6abe2efd14f2891f9fd1c8af"
            mXimalaya.setAppkey("9f9ef8f10bebeaa83e71e62f935bede8")
            mXimalaya.setPackid("com.app.test.android");
            mXimalaya.init(applicationContext, mAppSecret)
        }else{
            val mAppSecret = "0a09d7093bff3d4947a5c4da0125972e"
            mXimalaya.setAppkey("f4d8f65918d9878e1702d49a8cdf0183")
            mXimalaya.setPackid("com.ximalaya.qunfeng");
            mXimalaya.init(applicationContext, mAppSecret)
        }
    }
}