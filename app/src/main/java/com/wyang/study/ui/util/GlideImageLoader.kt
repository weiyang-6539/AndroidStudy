package com.wyang.study.ui.util

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class GlideImageLoader {
    companion object{

        /**
         * 通用加载方式
         *
         * @param context    上下文
         * @param view       所显示的控件
         * @param imgUrl     图片url
         * @param defaultImg 占位图
         * @param errorImg   加载出错的图
         */
        fun load(
            context: Context?, view: ImageView?, imgUrl: String?,
            @DrawableRes defaultImg: Int,
            @DrawableRes errorImg: Int
        ) {
            //RequestOptions 设置请求参数，通过apply方法设置
            val options = RequestOptions() // 但不保证所有图片都按序加载
                // 枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW
                // 默认为Priority.NORMAL
                // 如果没设置fallback，model为空时将显示error的Drawable，
                // 如果error的Drawable也没设置，就显示placeholder的Drawable
                .priority(Priority.NORMAL) //指定加载的优先级，优先级越高越优先加载，
                .placeholder(defaultImg)
                .error(errorImg)
                .dontAnimate() // 缓存原始数据
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()

            // 图片加载库采用Glide框架
            Glide.with(context!!)
                .load(imgUrl)
                .apply(options)
                .into(view!!)
        }

    }
}