package com.w6539.demo_jetpack.ui.holder

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.w6539.demo_jetpack.R
import com.w6539.demo_jetpack.ext.getItemView
import com.w6539.demo_jetpack.ui.holder.ItemViewType.FOLLOW_CARD
import com.w6539.demo_jetpack.ui.holder.ItemViewType.TEXT_CARD_HEADER4
import com.w6539.demo_jetpack.ui.holder.ItemViewType.TEXT_CARD_HEADER5

/**
 * @author Yang
 * @since 2022/12/15 10:02
 * @desc RecyclerView-ViewHolder统一管理
 */
class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view)

class TextCardViewHeader4ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}

class TextCardViewHeader5ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle = view.findViewById<AppCompatTextView>(R.id.tv_title)
}

class FollowCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivVideo = view.findViewById<ImageView>(R.id.ivVideo)
    val ivAvatar = view.findViewById<ImageView>(R.id.ivAvatar)
    val tvVideoDuration = view.findViewById<TextView>(R.id.tvVideoDuration)
    val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
    val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
    val ivShare = view.findViewById<ImageView>(R.id.ivShare)
    val tvLabel = view.findViewById<TextView>(R.id.tvLabel)
    val ivChoiceness = view.findViewById<ImageView>(R.id.ivChoiceness)
}

object RecyclerViewHelper {
    fun getViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TEXT_CARD_HEADER4 -> TextCardViewHeader4ViewHolder(parent.getItemView(R.layout.item_text_card_type_header5))
        TEXT_CARD_HEADER5 -> TextCardViewHeader5ViewHolder(parent.getItemView(R.layout.item_text_card_type_header5))
        FOLLOW_CARD -> FollowCardViewHolder(parent.getItemView(R.layout.item_follow_card_type))
        else -> EmptyViewHolder(View(parent.context))
    }
}