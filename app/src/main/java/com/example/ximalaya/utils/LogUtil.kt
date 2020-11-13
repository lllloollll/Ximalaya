package com.example.ximalaya.utils

import android.util.Log

/**
 * 创建Log工具类
 * verbose:不重要的信息
 * info:用户需要关注的信息
 * warm:警告信息
 * error:错误信息
 * assert:断言，非常严重的错误信息
 * debug:调试信息
 * 不输出调试信息
 *
 * 等级：
 * 0：verbose 及以上
 * 1： info及以上
 * 2： warm及以上
 * 3： debug及以上
 * 4： error及以上
 * 5： assert及以上 【无】
 * 6： 不输出调试信息
 */
class LogUtil {
    companion object{
        var sTag = "LogUtil"

        //控制输出Log的等级
        var level = 0

        fun init(baseTag:String,baseLevel:Int){
            sTag = baseTag
            level=baseLevel
        }

        fun v(TAG: String,content: String){
            if (level<1){
                Log.v("[$sTag]$TAG",content)
            }
        }

        fun i(TAG: String,content: String){
            if (level<2){
                Log.i("[$sTag]$TAG",content)
            }
        }

        fun w(TAG: String,content: String){
            if (level<3){
                Log.w("[$sTag]$TAG",content)
            }
        }

        fun d(TAG:String,content:String){
            if (level<4){
                Log.d("[$sTag]$TAG",content)
            }
        }

        fun e(TAG: String,content: String){
            if (level<6){
                Log.d("[$sTag]$TAG",content)
            }
        }

    }


}