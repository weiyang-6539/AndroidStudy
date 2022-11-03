package com.wyang.study.ui.helper;

import android.content.Context;
import android.graphics.Canvas;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.util.Log;

/**
 * Created by weiyang on 2018/8/10.
 */
public class ItemDragHelperCallback extends ItemTouchHelper.Callback {
    private Context context;

    public ItemDragHelperCallback(Context context) {
        this.context = context;
    }

    /**
     * 设置滑动类型标记
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags;
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager || manager instanceof StaggeredGridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }

        // 如果想支持滑动(删除)操作, swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END
        int swipeFlags = 0;

        //返回一个整数类型的标识，用于判断Item那种移动行为是允许的
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * 拖拽切换Item的回调
     *
     * @return 如果Item切换了位置，返回true；反之，返回false
     * onMove()是在拖动到新位置时候的回调方法，我们在这里做数组集合的交换操作，在这里我们把它暴露出去，交给Adapter自己处理；
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        //instanceof 这个对象是否是这个特定类或者是它的子类的一个实例。
        if (recyclerView.getAdapter() instanceof IDragDelegate) {
            IDragDelegate listener = (IDragDelegate) recyclerView.getAdapter();
            listener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return true;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (dragListener == null) {
            return;
        }

        //Log.e("+++++++++++++", "dy:" + dY);


        /*if (dY >= (recyclerView.getHeight()
                - viewHolder.itemView.getBottom()//item底部距离recyclerView顶部高度
                - DensityUtils.dip2px(context, 48))) {//拖到删除处
            dragListener.deleteState(true);
            if (isTouch) {//在删除处放手，则删除item
                viewHolder.itemView.setVisibility(View.INVISIBLE);//先设置不可见，如果不设置的话，会看到viewHolder返回到原位置时才消失，因为remove会在viewHolder动画执行完成后才将viewHolder删除
                if (recyclerView.getAdapter() instanceof IPictureDelegate) {
                    IPictureDelegate listener = (IPictureDelegate) recyclerView.getAdapter();
                    listener.onItemDelete(viewHolder.getAdapterPosition());
                }
                reset();
                return;
            }
        } else {//没有到删除处
            if (View.INVISIBLE == viewHolder.itemView.getVisibility()) {//如果viewHolder不可见，则表示用户放手，重置删除区域状态
                dragListener.dragState(false);
            }
            dragListener.deleteState(false);
        }
*/
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    /**
     * 滑动删除Item的操作 这里我们暂时永不着
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    /**
     * item被选中时改变item的背景
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        Log.e("State", "actionState:" + actionState);
        //不在闲置的状态
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG && dragListener != null) {
                dragListener.dragState(true);
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 用户操作完毕或者动画完毕后调用，恢复item的背景和透明度
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        reset();
        super.clearView(recyclerView, viewHolder);
    }

    /**
     * isLongPressDragEnabled()如果返回true，则支持长按拖拽，
     * 这里“其他频道”等不需要拖拽，所以返回false，手动调用ItemTouchHelper的startDrag方法启动拖拽。
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    /**
     * 不支持滑动
     *
     * @return false
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    private boolean isTouch;

    @Override
    public long getAnimationDuration(RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
        isTouch = true;
        return super.getAnimationDuration(recyclerView, animationType, animateDx, animateDy);
    }

    private void reset() {
        if (dragListener != null) {
            dragListener.deleteState(false);
            dragListener.dragState(false);
        }

        isTouch = false;
    }

    private DragListener dragListener;

    public void setDragListener(DragListener dragListener) {
        this.dragListener = dragListener;
    }

    public interface DragListener {
        void deleteState(boolean isDelete);

        void dragState(boolean isDrag);
    }
}
