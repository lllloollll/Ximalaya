package com.example.ximalaya.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ximalaya.R
import com.ximalaya.ting.android.opensdk.model.track.Track
import java.text.SimpleDateFormat

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.adapters
 * @ClassName:      DetailListAdapter
 * @Description:     java类作用描述
 * @Author:         MMC
 * @CreateDate:     2020/12/4 18:35
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/12/4 18:35
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class DetailListAdapter : RecyclerView.Adapter<DetailListAdapter.ViewHolder>() {

    private val mDetailData = arrayListOf<Track>()
    //格式化时间
    private val mDurationSimpleDateFormat = SimpleDateFormat("mm:ss")
    private val mDateSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailListAdapter.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_detail_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mDetailData.size
    }

    override fun onBindViewHolder(holder: DetailListAdapter.ViewHolder, position: Int) {
        //找到控件
        val itemView = holder.itemView
        val tvOrder = itemView.findViewById<TextView>(R.id.tv_order)
        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        val tvPlayCount = itemView.findViewById<TextView>(R.id.tv_play_count)
        val tvDuration = itemView.findViewById<TextView>(R.id.tv_play_duration)
        val tvUpdeDate = itemView.findViewById<TextView>(R.id.tv_update_date)
        //设置数据
        val track = mDetailData.get(position)
        tvOrder.text = position.toString()
        tvTitle.text = track.trackTitle
        track.playCount?.let {
            if (it<10000) tvPlayCount.text=it.toString()
            else
                tvPlayCount.text="${it/10000}.${it%10000/100}万"
        }
        tvDuration.text = mDurationSimpleDateFormat.format(track.duration*1000)
        tvUpdeDate.text = mDateSimpleDateFormat.format(track.updatedAt)
    }

    fun setData(tracks: List<Track>) {
        //清楚原来的数据
        mDetailData.clear()
        //添加新的数据
        mDetailData.addAll(tracks)
        //更新UI
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}