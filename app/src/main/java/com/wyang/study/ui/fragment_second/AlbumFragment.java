package com.wyang.study.ui.fragment_second;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wyang.study.R;
import com.wyang.study.bean.Album;
import com.wyang.study.bean.AlbumBase;
import com.wyang.study.ui.base.BaseFragment;
import com.wyang.study.ui.util.DataProvider;
import com.wyang.study.ui.util.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.OnTouch;

/**
 * Created by fxb on 2020-01-03.
 */
public class AlbumFragment extends BaseFragment {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.cl_date_parent)
    ConstraintLayout cl_date_parent;
    @BindView(R.id.tv_date)
    TextView tv_date;

    private AlbumAdapter mAdapter;

    /**
     * 三个关键值
     */
    private int mHeight;//RecyclerView的高

    private float scrollY;//纵向滑动距离
    private float maxScrollY;//纵向最大滑动距离

    private float maxDateOffsetY;

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            if (maxScrollY == 0)
                return;
            scrollY += dy;

            float percent = scrollY * 1f / maxScrollY;

            setClDateParentBias(percent);

            Log.e(TAG, "scroll监听回调");

            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
                AlbumBase base = mAdapter.getData().get(firstVisibleItemPosition);
                tv_date.setText(base.getDate());
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_album;
    }

    @Override
    protected void initView() {
        mRecyclerView.addOnScrollListener(onScrollListener);
        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mHeight = mRecyclerView.getHeight();

                //计算RecyclerView 纵向最大滑动距离, 在适配器设置数据之后
                calculateMaxScrollY(mHeight, 4);

                mRecyclerView.scrollTo(0, 400);

                mRecyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        mAdapter = new AlbumAdapter();
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setSpanSizeLookup((gridLayoutManager, position) -> {
            AlbumBase item = mAdapter.getItem(position);
            if (item instanceof Album) {
                return 1;
            } else
                return 4;
        });
        mAdapter.setNewData(DataProvider.getAlbumList());

        tv_date.setText("01月03日");


        tv_date.getParent().requestDisallowInterceptTouchEvent(true);
    }

    private void calculateMaxScrollY(int height, int spanCount) {
        maxScrollY = 0;

        //计算每行图片所占高度，即单个图片的宽 （屏幕宽 - RecyclerView左右padding）/ spanCount
        float mItemHeight = (getScreenWidth(getContext()) - dp2px(7) * 2f) / 4;

        List<AlbumBase> data = mAdapter.getData();
        int count = 0;//同一个日期组计数
        for (int i = 0; i < data.size(); i++) {
            AlbumBase base = data.get(i);
            if (base instanceof Album) {
                count++;

                if (count % spanCount == 0) {
                    maxScrollY += mItemHeight;

                    count = 0;
                }
            } else {
                //日期分组item
                maxScrollY += dp2px(40);

                if (count != 0) {
                    maxScrollY += mItemHeight;

                    count = 0;
                }
            }
        }

        if (count != 0) {
            maxScrollY += mItemHeight;
        }

        //最后减去RecyclerView的高度
        maxScrollY -= height;

        //滑块最大拖动的位置
        maxDateOffsetY = height - cl_date_parent.getHeight();
    }

    private float downY;

    @OnTouch(R.id.cl_date_parent)
    public boolean onTouchDateParent(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = event.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                int dy = (int) ((event.getY() - downY) / maxDateOffsetY * maxScrollY);
                cl_date_parent.post(() -> smoothScrollBy(dy));
                break;
            default:
                break;
        }
        return true;
    }

    private void smoothScrollBy(int dy) {
        mRecyclerView.smoothScrollBy(0, dy);
    }

    private void setClDateParentBias(float bias) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) cl_date_parent.getLayoutParams();
        params.verticalBias = bias;
        cl_date_parent.setLayoutParams(params);
    }

    public int dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    private class AlbumAdapter extends BaseMultiItemQuickAdapter<AlbumBase, BaseViewHolder> {

        AlbumAdapter() {
            super(null);
            addItemType(AlbumBase.ALBUM, R.layout.item_album_recycler);
            addItemType(AlbumBase.DATE, R.layout.item_album_date);
        }

        @Override
        protected void convert(BaseViewHolder helper, AlbumBase item) {
            ImageView iv_album = helper.getView(R.id.iv_album);
            if (item instanceof Album) {
                Album album = (Album) item;
                GlideImageLoader.load(mContext, iv_album, album.getUrl(), R.drawable.ic_album_default, R.drawable.ic_album_default);
            } else {
                helper.setText(R.id.tv_date, item.getDate());
            }
        }
    }
}
