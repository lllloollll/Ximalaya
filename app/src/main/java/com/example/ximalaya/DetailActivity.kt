package com.example.ximalaya

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.ximalaya.base.BaseActivity
import com.example.ximalaya.interfaces.IAlbumDetailViewCallback
import com.example.ximalaya.presenters.AlbumDetailPresenter
import com.ximalaya.ting.android.opensdk.model.album.Album
import com.ximalaya.ting.android.opensdk.model.track.Track
import jp.wasabeef.glide.transformations.BlurTransformation
import net.lucode.hackware.magicindicator.buildins.UIUtil

class DetailActivity : BaseActivity() {
    private lateinit var mAlbumDetailPresenter: AlbumDetailPresenter
    private lateinit var tvTitle: TextView
    private lateinit var tvAuthor: TextView
    private lateinit var ivLargeCover: ImageView
    private lateinit var ivSmallCover: ImageView

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        //设置状态栏为透明
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        window.statusBarColor = Color.TRANSPARENT

        initView()

        mAlbumDetailPresenter = AlbumDetailPresenter.getInstance()
        mAlbumDetailPresenter.registerViewCallback(viewCallback)
    }

    private fun initView() {
        tvTitle = findViewById<TextView>(R.id.tv_album_title)
        tvAuthor = findViewById<TextView>(R.id.tv_album_author)
        ivLargeCover = findViewById(R.id.iv_large_cover)
        ivSmallCover = findViewById(R.id.iv_small_cover)
    }

    private val viewCallback = object : IAlbumDetailViewCallback {
        override fun onAlbumload(album: Album) {
            tvTitle.text = album.albumTitle
            tvAuthor.text = album.announcer.nickname

            //设置图片圆角角度
            val roundedCorners = RoundedCorners(UIUtil.dip2px(applicationContext, 10.0))
            //设置圆角及图片的宽高，压缩图片，减少内存消耗
            val option = RequestOptions.bitmapTransform(roundedCorners).override(
                UIUtil.dip2px(applicationContext, 75.0),
                UIUtil.dip2px(applicationContext, 75.0)
            )
            //加载圆角图片
            ivSmallCover?.let {
                Glide.with(applicationContext)
                    .load(album.coverUrlMiddle)
                    .apply(option)
                    .into(it)
            }

            //通过RequestOptions扩展功能,设置高斯模糊，模糊半径（即模糊程度，最大值为25），缩放比例（值越大，缩放越大）
            val blurOption = RequestOptions.bitmapTransform(BlurTransformation(14, 7))
            ivLargeCover?.let {
                Glide.with(applicationContext)
                    .load(album.coverUrlLarge)
                    .apply(blurOption)
                    .into(it)
            }
        }

        override fun onDetailListLoaded(tracks: List<Track>) {

        }

    }
}
