package com.w6539.demo_jetpack.ui

import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.w6539.demo_jetpack.R
import com.w6539.demo_jetpack.ext.getItemView

/**
 * @author Yang
 * @since 2022/12/22 10:21
 * @desc
 */
class FooterAdapter(val retry: () -> Unit) : LoadStateAdapter<FooterAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        val retryButton: View = view.findViewById(R.id.retryButton)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.progressBar.isVisible = loadState is LoadState.Loading
        holder.retryButton.isVisible = loadState is LoadState.Error
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(parent.getItemView(R.layout.layout_footer_item)).apply {
            retryButton.setOnClickListener {
                retry()
            }
        }
    }
}