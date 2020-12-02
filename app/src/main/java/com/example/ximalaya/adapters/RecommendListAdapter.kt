package com.example.ximalaya.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.ximalaya.R
import com.example.ximalaya.utils.LogUtil
import com.ximalaya.ting.android.opensdk.model.album.Album
import net.lucode.hackware.magicindicator.buildins.UIUtil

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
    private var mItemClickListener: OnRecommendItemClickListener?=null
    var mData: ArrayList<Album>
    private val TAG = "RecommendListAdapter"

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
        //设置itemView点击事件
        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                LogUtil.d(TAG,"item view click position --> ${v?.tag}")
                mItemClickListener?.onItemClick(position,mData.get(position))
            }
        })
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
            //设置图片圆角角度
            val roundedCorners = RoundedCorners(UIUtil.dip2px(itemView.context, 20.0))
            //通过RequestOptions扩展功能，override:采样率，因为ImageView就这么大，可以压缩图片，降低内存消耗
            val requestOption = RequestOptions.bitmapTransform(roundedCorners).override(300, 300)
            //显示圆角图片
            itemView.findViewById<ImageView>(R.id.album_cover_iv).run {
                Glide.with(itemView.context)
                    .load(data.coverUrlMiddle)
                    .apply(requestOption)
                    .into(this)
            }
            itemView.findViewById<TextView>(R.id.album_title_tv).text = data.albumTitle
            itemView.findViewById<TextView>(R.id.album_description_tv).text = data.albumIntro
            itemView.findViewById<TextView>(R.id.album_paly_count_tv).text =
                "播放数：${data.playCount / 10000}.${data.playCount % 10000 / 100}万"
            itemView.findViewById<TextView>(R.id.album_content_size_tv).text =
                "订阅数：${data.subscribeCount}"
        }
    }

    /**
     * 将item点击事件 传递给veiw层处理
     */
    fun setOnItemClickListener(itemClickListener:OnRecommendItemClickListener){
        this.mItemClickListener=itemClickListener
    }
    interface OnRecommendItemClickListener{
        fun onItemClick(
            position: Int,
            data: Album
        )
    }
}