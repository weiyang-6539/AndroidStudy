package com.w6539.demo_jetpack.bean

import com.google.gson.annotations.SerializedName

data class PushMessage(
    @SerializedName("messageList")
    val itemList: List<Message>,
    val nextPageUrl: String?,
    val updateTime: Long
) : Model() {

    data class Message(
        val actionUrl: String,
        val content: String,
        val date: Long,
        val icon: String?,
        val id: Int,
        val ifPush: Boolean,
        val pushStatus: Int,
        val title: String?,
        val uid: Any,
        val viewed: Boolean
    )
}

