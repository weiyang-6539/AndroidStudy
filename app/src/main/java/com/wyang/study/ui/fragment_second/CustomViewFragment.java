package com.wyang.study.ui.fragment_second;

import com.wyang.study.R;
import com.wyang.study.ui.base.BaseFragment;
import com.wyang.study.ui.widget.ChineseFlag;

import butterknife.BindView;

/**
 * Created by weiyang on 2019-10-31.
 */
public class CustomViewFragment extends BaseFragment {
    @BindView(R.id.cf1)
    ChineseFlag cf1;
    @BindView(R.id.cf2)
    ChineseFlag cf2;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_custom_view;
    }

    @Override
    protected void initView() {
        cf1.setDrawLine(false);
        cf2.setDrawLine(true);
    }
}
