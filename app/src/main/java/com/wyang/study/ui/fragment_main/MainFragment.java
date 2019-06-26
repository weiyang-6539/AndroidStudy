package com.wyang.study.ui.fragment_main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import com.wyang.study.R;
import com.wyang.study.adapter.SimpleAdapter;
import com.wyang.study.bean.Simple;
import com.wyang.study.ui.FragmentActivity;
import com.wyang.study.ui.base.BaseFragment;
import com.wyang.study.ui.util.ActivityUtils;
import com.wyang.study.ui.util.DataProvider;

import java.util.List;

import butterknife.BindView;

/**
 * Created by weiyang on 2019/6/18.
 */
public class MainFragment extends BaseFragment {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private SimpleAdapter mAdapter;

    @SuppressLint("ValidFragment")
    private MainFragment() {
    }

    public static MainFragment newInstance(int type) {
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        mainFragment.setArguments(args);
        return mainFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new SimpleAdapter();
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Simple simple = mAdapter.getData().get(position);

            if (!TextUtils.isEmpty(simple.getClassName())) {
                Bundle bundle = new Bundle();
                bundle.putString("className", simple.getClassName());
                ActivityUtils.startActivity(getContext(), FragmentActivity.class, bundle);
            } else {
                Toast.makeText(getContext(), "请设置对应Fragment!", Toast.LENGTH_LONG).show();
            }

        });

        Bundle arguments = getArguments();
        if (arguments != null) {
            int type = arguments.getInt("type");
            List<Simple> list = null;
            switch (type) {
                case 0:
                    list = DataProvider.getOfficialData();
                    break;
                case 1:
                    list = DataProvider.getWidgetData();
                    break;
                case 2:
                    list = DataProvider.getUnofficialData();
                default:
                    break;
            }
            mAdapter.setNewData(list);
        }
    }
}
