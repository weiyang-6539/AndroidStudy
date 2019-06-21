package com.wyang.study.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wyang.study.R;
import com.wyang.study.bean.Simple;

/**
 * Created by weiyang on 2019/6/19.
 */
public class SimpleAdapter extends BaseQuickAdapter<Simple, BaseViewHolder> {

    public SimpleAdapter() {
        super(R.layout.item_simple_recycler);
    }

    @Override
    protected void convert(BaseViewHolder helper, Simple item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_description, item.getDescription());
    }
}
