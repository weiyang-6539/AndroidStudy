package com.wyang.study.ui.fragment.custom

import android.content.Context
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.wyang.study.R
import com.wyang.study.databinding.FragmentNinegridlayoutBinding
import com.wyang.study.ui.base.BaseFragment
import com.wyang.study.ui.util.DataProvider
import com.wyang.study.ui.util.GlideImageLoader
import com.wyang.study.ui.widget.NineGridLayout.ImageLoaderInterface

class NineGridLayoutFragment: BaseFragment<FragmentNinegridlayoutBinding>() {
    override fun getViewBinding(): FragmentNinegridlayoutBinding {
        return FragmentNinegridlayoutBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        mBinding.mSeekBar.max = 100
        mBinding.mSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                mBinding.mNineGridLayout.setSpacing(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        mBinding.tvAdd.setOnClickListener { onClickAdd() }
        mBinding.tvSub.setOnClickListener { onClickSub() }
    }


    private var count = 9

    private fun onClickAdd() {
        count++
        mBinding.mNineGridLayout.setImageUrls(DataProvider.getImageUrls(count), imageLoader)
    }

    private fun onClickSub() {
        if (count == 0) return
        count--
        mBinding.mNineGridLayout.setImageUrls(DataProvider.getImageUrls(count), imageLoader)
    }

    private val imageLoader: ImageLoaderInterface = object : ImageLoaderInterface {
        override fun createImageView(context: Context, count: Int, pos: Int): ImageView {
            val child = ImageView(context)
            child.scaleType = ImageView.ScaleType.CENTER_CROP
            return child
        }

        override fun displayImage(context: Context, imageView: ImageView, url: String) {
            GlideImageLoader.load(
                context,
                imageView,
                url,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background
            )
        }
    }
}