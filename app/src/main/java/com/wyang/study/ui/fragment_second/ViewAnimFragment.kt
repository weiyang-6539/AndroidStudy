package com.wyang.study.ui.fragment_second

import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.jaredrummler.android.widget.AnimatedSvgView
import com.wyang.study.R
import com.wyang.study.databinding.FragmentViewAnimBinding
import com.wyang.study.ui.base.BaseFragment
import com.wyang.study.ui.util.ModelSVG
import tyrantgit.explosionfield.ExplosionField

class ViewAnimFragment :BaseFragment<FragmentViewAnimBinding>() {
    private var mExplosionField: ExplosionField? = null

    override fun getViewBinding(): FragmentViewAnimBinding {
        return FragmentViewAnimBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        //Fragment中onOptionsItemSelected生效与否
        setHasOptionsMenu(true)
        mExplosionField = ExplosionField.attach2Window(activity)

        mBinding?.tvShow1?.setOnClickListener { v: View -> onClickShow(v) }
        mBinding?.tvShow2?.setOnClickListener { v: View -> onClickShow(v) }
        mBinding?.tvShow3?.setOnClickListener { v: View -> onClickShow(v) }
        mBinding?.tvShow4?.setOnClickListener { v: View -> onClickShow(v) }
        mBinding?.tvShow5?.setOnClickListener { v: View -> onClickShow(v) }
        mBinding?.tvShow6?.setOnClickListener { v: View -> onClickShow(v) }
        mBinding?.tvShow7?.setOnClickListener { v: View -> onClickShow(v) }
        mBinding?.tvShow8?.setOnClickListener { v: View -> onClickShow(v) }
        mBinding?.tvShow9?.setOnClickListener { v: View -> onClickShow(v) }
        mBinding?.tvShow10?.setOnClickListener { v: View -> onClickShow(v) }

        mBinding?.tvBoom1?.setOnClickListener { v: View -> onClickBoom(v) }
        mBinding?.tvBoom2?.setOnClickListener { v: View -> onClickBoom(v) }
        mBinding?.tvBoom3?.setOnClickListener { v: View -> onClickBoom(v) }
        mBinding?.tvBoom4?.setOnClickListener { v: View -> onClickBoom(v) }
        mBinding?.tvBoom5?.setOnClickListener { v: View -> onClickBoom(v) }
        mBinding?.tvBoom6?.setOnClickListener { v: View -> onClickBoom(v) }
        mBinding?.tvBoom7?.setOnClickListener { v: View -> onClickBoom(v) }
        mBinding?.tvBoom8?.setOnClickListener { v: View -> onClickBoom(v) }
        mBinding?.tvBoom9?.setOnClickListener { v: View -> onClickBoom(v) }
        mBinding?.tvBoom10?.setOnClickListener { v: View -> onClickBoom(v) }
    }

    fun onClickShow(v: View) {
        when (v.id) {
            R.id.tv_show1 -> mBinding?.asv1?.let { setSvg(it, ModelSVG.values()[0]) }
            R.id.tv_show2 -> mBinding?.asv2?.let { setSvg(it, ModelSVG.values()[1]) }
            R.id.tv_show3 -> mBinding?.asv3?.let { setSvg(it, ModelSVG.values()[2]) }
            R.id.tv_show4 -> mBinding?.asv4?.let { setSvg(it, ModelSVG.values()[3]) }
            R.id.tv_show5 -> mBinding?.asv5?.let { setSvg(it, ModelSVG.values()[4]) }
            R.id.tv_show6 -> mBinding?.asv6?.let { setSvg(it, ModelSVG.values()[5]) }
            R.id.tv_show7 -> mBinding?.asv7?.let { setSvg(it, ModelSVG.values()[6]) }
            R.id.tv_show8 -> mBinding?.asv8?.let { setSvg(it, ModelSVG.values()[7]) }
            R.id.tv_show9 -> mBinding?.asv9?.let { setSvg(it, ModelSVG.values()[8]) }
            R.id.tv_show10 -> mBinding?.asv10?.let { setSvg(it, ModelSVG.values()[9]) }
        }
    }

    private fun setSvg(asv: AnimatedSvgView, modelSvg: ModelSVG) {
        asv.setGlyphStrings(*modelSvg.glyphs)
        asv.setFillColors(modelSvg.colors)
        asv.setViewportSize(modelSvg.width, modelSvg.height)
        asv.setTraceResidueColor(0x32000000)
        asv.setTraceColors(modelSvg.colors)
        asv.rebuildGlyphData()
        asv.start()
    }

    private fun onClickBoom(v: View) {
        mExplosionField!!.explode(v)
        when (v.id) {
            R.id.tv_boom1 -> mExplosionField!!.explode(mBinding?.asv1)
            R.id.tv_boom2 -> mExplosionField!!.explode(mBinding?.asv2)
            R.id.tv_boom3 -> mExplosionField!!.explode(mBinding?.asv3)
            R.id.tv_boom4 -> mExplosionField!!.explode(mBinding?.asv4)
            R.id.tv_boom5 -> mExplosionField!!.explode(mBinding?.asv5)
            R.id.tv_boom6 -> mExplosionField!!.explode(mBinding?.asv6)
            R.id.tv_boom7 -> mExplosionField!!.explode(mBinding?.asv7)
            R.id.tv_boom8 -> mExplosionField!!.explode(mBinding?.asv8)
            R.id.tv_boom9 -> mExplosionField!!.explode(mBinding?.asv9)
            R.id.tv_boom10 -> mExplosionField!!.explode(mBinding?.asv10)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_reset) {
            mBinding?.root?.let { reset(it) }
            mExplosionField!!.clear()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun reset(root: View) {
        if (root is ViewGroup) {
            for (i in 0 until root.childCount) {
                reset(root.getChildAt(i))
            }
        } else {
            root.scaleX = 1f
            root.scaleY = 1f
            root.alpha = 1f
        }
    }
}