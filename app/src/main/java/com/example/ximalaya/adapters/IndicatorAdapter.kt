package com.example.ximalaya.adapters

import android.content.Context
import android.graphics.Color
import android.view.View
import com.example.ximalaya.R
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView

class IndicatorAdapter(context: Context?): CommonNavigatorAdapter() {

    var list:Array<out String>?=null
    lateinit var context: Context

    init {
        this.context=context!!
        list = context?.resources?.getStringArray(R.array.indicator_title)
    }
    override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
        val simplePaperTitleView = ColorTransitionPagerTitleView(context)
        simplePaperTitleView.run{
            normalColor=Color.GRAY
            selectedColor=Color.WHITE
            text=list?.get(index)
            setTextSize(18F)
            setOnClickListener(object :View.OnClickListener{
                override fun onClick(v: View?) {

                }

            })
        }
        return simplePaperTitleView
    }

    override fun getCount(): Int {
        return list?.size?:0
    }

    override fun getIndicator(context: Context?): IPagerIndicator {
        val linePagerIndicator=LinePagerIndicator(context)
        linePagerIndicator.run {
            mode=LinePagerIndicator.MODE_WRAP_CONTENT
            setColors(Color.WHITE)
        }
        return linePagerIndicator
    }
}