package com.wyang.study.ui.fragment_second;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jaredrummler.android.widget.AnimatedSvgView;
import com.wyang.study.R;
import com.wyang.study.ui.base.BaseFragment;
import com.wyang.study.ui.util.ModelSVG;

import butterknife.BindView;
import butterknife.OnClick;
import tyrantgit.explosionfield.ExplosionField;

/**
 * Created by weiyang on 2019-10-08.
 */
public class ViewAnimFragment extends BaseFragment {
    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.asv_1)
    AnimatedSvgView asv_1;
    @BindView(R.id.asv_2)
    AnimatedSvgView asv_2;
    @BindView(R.id.asv_3)
    AnimatedSvgView asv_3;
    @BindView(R.id.asv_4)
    AnimatedSvgView asv_4;
    @BindView(R.id.asv_5)
    AnimatedSvgView asv_5;
    @BindView(R.id.asv_6)
    AnimatedSvgView asv_6;
    @BindView(R.id.asv_7)
    AnimatedSvgView asv_7;
    @BindView(R.id.asv_8)
    AnimatedSvgView asv_8;
    @BindView(R.id.asv_9)
    AnimatedSvgView asv_9;
    @BindView(R.id.asv_10)
    AnimatedSvgView asv_10;

    private ExplosionField mExplosionField;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_view_anim;
    }

    @Override
    protected void initView() {
        //Fragment中onOptionsItemSelected生效与否
        setHasOptionsMenu(true);
        mExplosionField = ExplosionField.attach2Window(getActivity());
    }

    @OnClick({R.id.tv_show1, R.id.tv_show2, R.id.tv_show3, R.id.tv_show4, R.id.tv_show5,
            R.id.tv_show6, R.id.tv_show7, R.id.tv_show8, R.id.tv_show9, R.id.tv_show10})
    public void onClickShow(View v) {
        switch (v.getId()) {
            case R.id.tv_show1:
                setSvg(asv_1, ModelSVG.values()[0]);
                break;
            case R.id.tv_show2:
                setSvg(asv_2, ModelSVG.values()[1]);
                break;
            case R.id.tv_show3:
                setSvg(asv_3, ModelSVG.values()[2]);
                break;
            case R.id.tv_show4:
                setSvg(asv_4, ModelSVG.values()[3]);
                break;
            case R.id.tv_show5:
                setSvg(asv_5, ModelSVG.values()[4]);
                break;
            case R.id.tv_show6:
                setSvg(asv_6, ModelSVG.values()[5]);
                break;
            case R.id.tv_show7:
                setSvg(asv_7, ModelSVG.values()[6]);
                break;
            case R.id.tv_show8:
                setSvg(asv_8, ModelSVG.values()[7]);
                break;
            case R.id.tv_show9:
                setSvg(asv_9, ModelSVG.values()[8]);
                break;
            case R.id.tv_show10:
                setSvg(asv_10, ModelSVG.values()[9]);
                break;
        }
    }

    private void setSvg(AnimatedSvgView asv, ModelSVG modelSvg) {
        asv.setGlyphStrings(modelSvg.glyphs);
        asv.setFillColors(modelSvg.colors);
        asv.setViewportSize(modelSvg.width, modelSvg.height);
        asv.setTraceResidueColor(0x32000000);
        asv.setTraceColors(modelSvg.colors);
        asv.rebuildGlyphData();
        asv.start();
    }

    @OnClick({R.id.tv_boom1, R.id.tv_boom2, R.id.tv_boom3, R.id.tv_boom4, R.id.tv_boom5,
            R.id.tv_boom6, R.id.tv_boom7, R.id.tv_boom8, R.id.tv_boom9, R.id.tv_boom10})
    public void onClickBoom(View v) {
        mExplosionField.explode(v);
        switch (v.getId()) {
            case R.id.tv_boom1:
                mExplosionField.explode(asv_1);
                break;
            case R.id.tv_boom2:
                mExplosionField.explode(asv_2);
                break;
            case R.id.tv_boom3:
                mExplosionField.explode(asv_3);
                break;
            case R.id.tv_boom4:
                mExplosionField.explode(asv_4);
                break;
            case R.id.tv_boom5:
                mExplosionField.explode(asv_5);
                break;
            case R.id.tv_boom6:
                mExplosionField.explode(asv_6);
                break;
            case R.id.tv_boom7:
                mExplosionField.explode(asv_7);
                break;
            case R.id.tv_boom8:
                mExplosionField.explode(asv_8);
                break;
            case R.id.tv_boom9:
                mExplosionField.explode(asv_9);
                break;
            case R.id.tv_boom10:
                mExplosionField.explode(asv_10);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_reset) {
            reset(root);
            mExplosionField.clear();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reset(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++) {
                reset(parent.getChildAt(i));
            }
        } else {
            root.setScaleX(1);
            root.setScaleY(1);
            root.setAlpha(1);
        }
    }
}
