package com.wyang.study.ui.fragment_second;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gavin.com.library.PowerfulStickyDecoration;
import com.gavin.com.library.listener.PowerGroupListener;
import com.wyang.study.R;
import com.wyang.study.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by weiyang on 2019-10-16.
 */
public class StickyDecorationFragment extends BaseFragment {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private StickyAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sticky_decoration;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        PowerGroupListener listener = new PowerGroupListener() {
            @Override
            public String getGroupName(int position) {
                String item = mAdapter.getData().get(position);
                return item.substring(0, 3);
            }

            @Override
            public View getGroupView(int position) {
                //获取自定定义的组View
                View view = getLayoutInflater().inflate(R.layout.sticky_header, null, false);
                TextView tv_type = view.findViewById(R.id.tv_type);
                tv_type.setText(getGroupName(position));

                return view;
            }
        };

        View v = getLayoutInflater().inflate(R.layout.sticky_header, null, false);
        measureWidthAndHeight(v);

        PowerfulStickyDecoration decoration = PowerfulStickyDecoration.Builder
                .init(listener)
                .setDivideHeight(0)
                .setDivideColor(0xffe2e2e2)
                .setGroupHeight(dp2px(40))
                .setGroupBackground(0x00000000)
                //重置span（注意：使用GridLayoutManager时必须调用）
                //.resetSpan(mRecyclerView, (GridLayoutManager) manager)
                .build();

        mRecyclerView.addItemDecoration(new ItemDecoration());
        mRecyclerView.addItemDecoration(decoration);

        mAdapter = new StickyAdapter();
        mAdapter.bindToRecyclerView(mRecyclerView);

        List<String> list = new ArrayList<>();
        list.add("分组1-AAAAA");
        list.add("分组1-BBBBB");
        list.add("分组1-CCCCC");

        list.add("分组2-AAAAA");
        list.add("分组2-BBBBB");
        list.add("分组2-CCCCC");

        list.add("分组3-AAAAA");
        list.add("分组3-AAAAA");
        list.add("分组3-AAAAA");

        list.add("分组4-DDDDD");
        list.add("分组4-EEEEE");
        list.add("分组4-EEEEE");

        list.add("分组5-FFFFF");
        list.add("分组5-FFFFF");
        list.add("分组5-FFFFF");
        list.add("分组5-GGGGG");
        mAdapter.setNewData(list);
    }

    public void measureWidthAndHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }

    /**
     * 判断是不是分组第一个
     */
    private boolean isFirst(int pos) {
        if (pos == 0)
            return true;

        String c = mAdapter.getData().get(pos);
        String p = mAdapter.getData().get(pos - 1);

        boolean rst = !TextUtils.equals(c.substring(0, 3), p.substring(0, 3));
        return rst;
    }

    /**
     * 判断是不是分组最后一个
     */
    private boolean isFooter(int pos) {
        int size = mAdapter.getData().size();
        if (pos == size - 1)
            return true;
        String c = mAdapter.getData().get(pos);
        String n = mAdapter.getData().get(pos + 1);
        return !TextUtils.equals(c.substring(0, 3), n.substring(0, 3));
    }

    class StickyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public StickyAdapter() {
            super(R.layout.item_sticky_recycler);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_content, item);
        }
    }

    public int dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    class ItemDecoration extends RecyclerView.ItemDecoration {
        private GradientDrawable topCorner;
        private GradientDrawable bottomCorner;

        public ItemDecoration() {
            int radius = dp2px(10);

            topCorner = new GradientDrawable();
            topCorner.setShape(GradientDrawable.RECTANGLE);
            topCorner.setCornerRadii(new float[]{
                    radius, radius,
                    radius, radius,
                    0, 0,
                    0, 0
            });
            topCorner.setColor(Color.WHITE);

            bottomCorner = new GradientDrawable();
            bottomCorner.setShape(GradientDrawable.RECTANGLE);
            bottomCorner.setCornerRadii(new float[]{
                    0, 0,
                    0, 0,
                    radius, radius,
                    radius, radius
            });
            bottomCorner.setColor(Color.WHITE);

        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            if (position == 0) {
                outRect.top = dp2px(20);
            } else if (isFirst(position)) {
                outRect.top = dp2px(30);
            } else {
                outRect.top = dp2px(0);
            }

            if (position == mAdapter.getData().size() - 1) {
                outRect.bottom = dp2px(20);
            } else {
                outRect.bottom = 0;
            }
        }

        @Override
        public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDraw(canvas, parent, state);

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = parent.getChildAt(i);
                int position = parent.getChildLayoutPosition(childAt);
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();
                int top = childAt.getTop() - dp2px(40);
                if (position == 0 || position == mAdapter.getData().size() - 1) {
                    if (position == 0) {
                        top -= dp2px(20);
                        topCorner.setBounds(left, top + dp2px(10), right, top + dp2px(20));
                        topCorner.draw(canvas);
                    } else {
                        top = childAt.getBottom();
                        bottomCorner.setBounds(left, top, right, top + dp2px(10));
                        bottomCorner.draw(canvas);
                    }
                } else {
                    top -= dp2px(30);

                    if (isFirst(position)) {   // 是分组第一个，则绘制
                        bottomCorner.setBounds(left, top, right, top + dp2px(10));
                        bottomCorner.draw(canvas);

                        topCorner.setBounds(left, top + dp2px(20), right, top + dp2px(30));
                        topCorner.draw(canvas);
                    }
                }
            }
        }
    }
}
