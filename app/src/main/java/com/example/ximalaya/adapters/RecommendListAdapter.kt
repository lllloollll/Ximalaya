package com.example.ximalaya.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ximalaya.R
import com.ximalaya.ting.android.opensdk.model.album.Album
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList

/**
 *
 * @ProjectName:    Ximalaya
 * @Package:        com.example.ximalaya.adapters
 * @ClassName:      RecommendRVAdapter
 * @Description:     java类作用描述
 * @Author:         MMC
 * @CreateDate:     2020/11/20 0:45
 * @UpdateUser:     更新者
 * @UpdateDate:     2020/11/20 0:45
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class RecommendListAdapter : RecyclerView.Adapter<RecommendListAdapter.InnerHolder>() {
    var mData: ArrayList<Album>

    init {
        mData = arrayListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        //加载View
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recommend, parent, false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        //设置数据
        holder.itemView.setTag(position)
        holder.setData(mData.get(position))
    }

    fun setData(data: List<Album>) {
        mData.run {
            clear()
            addAll(data)
        }
        //更新UI
        notifyDataSetChanged()
    }


    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(data: Album) {
            itemView.findViewById<ImageView>(R.id.album_cover_iv).run {
                Glide.with(itemView.context)
                    .load(data.coverUrlSmall)
                    .into(this)
            }
            itemView.findViewById<TextView>(R.id.album_title_tv).text = data.albumTitle
            itemView.findViewById<TextView>(R.id.album_description_tv).text = data.albumIntro
            itemView.findViewById<TextView>(R.id.album_paly_count_tv).text =
                data.playCount.toString()
            itemView.findViewById<TextView>(R.id.album_content_size_tv).text =
                data.subscribeCount.toString()
        }
    }


}