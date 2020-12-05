package com.example.ximalaya.base

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.base
 * @ClassName:      IBasePresenter
 * @Description:     java类作用描述
 * @Author:         MMC
 * @CreateDate:     2020/12/5 16:03
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/12/5 16:03
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
interface IBasePresenter<T> {
    /**
     * 注册UI回调
     */
    fun registerViewCallback(t: T)

    /**
     *  取消UI的回调注册
     */
    fun unRegisterViewCallback(t:T)
}