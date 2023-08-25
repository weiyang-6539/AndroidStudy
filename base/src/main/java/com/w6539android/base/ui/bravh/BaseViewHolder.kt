package com.w6539android.base.ui.bravh

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Yang
 * @since 2023/8/22 9:02
 * @desc
 */
open class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    constructor(
        @LayoutRes resId: Int, parent: ViewGroup
    ) : this(LayoutInflater.from(parent.context).inflate(resId, parent, false))

    /**
     * Views indexed with their IDs
     */
    private val views: SparseArray<View> = SparseArray()

    open fun <T : View> getView(@IdRes viewId: Int): T {
        val view = getViewOrNull<T>(viewId)
        checkNotNull(view) { "No view found with id $viewId" }
        return view
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : View> getViewOrNull(@IdRes viewId: Int): T? {
        val view = views.get(viewId) ?: return itemView.findViewById<T>(viewId)?.apply {
            views.put(viewId, this)
        }
        return view as? T
    }

    fun <T : View> Int.findView(): T? {
        return itemView.findViewById(this)
    }

    fun setText(@IdRes viewId: Int, value: CharSequence?) =
        apply {
            getView<TextView>(viewId).text = value
        }

    fun setText(@IdRes viewId: Int, @StringRes strId: Int) =
        apply {
            getView<TextView>(viewId).setText(strId)
        }

    fun setTextColor(@IdRes viewId: Int, @ColorInt color: Int) =
        apply {
            getView<TextView>(viewId).setTextColor(color)
        }

    fun setTextColorRes(@IdRes viewId: Int, @ColorRes colorRes: Int) =
        apply {
            getView<TextView>(viewId).setTextColor(
                ContextCompat.getColor(itemView.context, colorRes)
            )
        }

    fun setImageResource(@IdRes viewId: Int, @DrawableRes imageResId: Int) =
        apply {
            getView<ImageView>(viewId).setImageResource(imageResId)
        }

    fun setImageDrawable(@IdRes viewId: Int, drawable: Drawable?) =
        apply {
            getView<ImageView>(viewId).setImageDrawable(drawable)
        }

    fun setImageBitmap(@IdRes viewId: Int, bitmap: Bitmap?) =
        apply {
            getView<ImageView>(viewId).setImageBitmap(bitmap)
        }

    fun setBackgroundColor(@IdRes viewId: Int, @ColorInt color: Int) =
        apply {
            getView<View>(viewId).setBackgroundColor(color)
        }

    fun setBackgroundResource(@IdRes viewId: Int, @DrawableRes backgroundRes: Int) =
        apply {
            getView<View>(viewId).setBackgroundResource(backgroundRes)
        }

    fun setVisible(@IdRes viewId: Int, isVisible: Boolean) =
        apply {
            getView<View>(viewId).visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        }

    fun setGone(@IdRes viewId: Int, isGone: Boolean) =
        apply {
            getView<View>(viewId).visibility = if (isGone) View.GONE else View.VISIBLE
        }

    fun setEnabled(@IdRes viewId: Int, isEnabled: Boolean) =
        apply {
            getView<View>(viewId).isSelected
            getView<View>(viewId).isEnabled = isEnabled
        }

    fun isEnabled(@IdRes viewId: Int) = getView<View>(viewId).isEnabled

    fun setSelected(@IdRes viewId: Int, isSelected: Boolean) =
        apply {
            getView<View>(viewId).isSelected = isSelected
        }

    fun isSelected(@IdRes viewId: Int) = getView<View>(viewId).isSelected
}