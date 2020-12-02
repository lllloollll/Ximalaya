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

class DetailActivity : BaseActivity() {
    private lateinit var mAlbumDetailPresenter:AlbumDetailPresenter
    private lateinit var tvTitle:TextView
    private lateinit var tvAuthor:TextView
    private lateinit var ivLargeCover:ImageView
    private lateinit var ivSmallCover:ImageView

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        //设置状态栏为透明
        window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        window.statusBarColor=Color.TRANSPARENT

        initView()

        mAlbumDetailPresenter=AlbumDetailPresenter.getInstance()
        mAlbumDetailPresenter.registerViewCallback(viewCallback)
    }

    private fun initView() {
        tvTitle=findViewById<TextView>(R.id.tv_album_title)
        tvAuthor=findViewById<TextView>(R.id.tv_album_author)
        ivLargeCover=findViewById(R.id.iv_large_cover)
        ivSmallCover=findViewById(R.id.iv_small_cover)
    }

    private val viewCallback=object :IAlbumDetailViewCallback{
        override fun onAlbumload(album: Album) {
            tvTitle.text=album.albumTitle
            tvAuthor.text=album.announcer.nickname
            //加载圆角图片
            //设置图片圆角角度
            val roundedCorners=RoundedCorners(15)
            val option =RequestOptions.bitmapTransform(roundedCorners).override(75,75)
            Glide.with(applicationContext)
                .load(album.coverUrlSmall)
                .apply(option)
                .into(ivSmallCover)
            Glide.with(applicationContext).load(album.coverUrlLarge).into(ivLargeCover)
        }

        override fun onDetailListLoaded(tracks: List<Track>) {

        }

    }
}
