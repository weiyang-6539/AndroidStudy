package com.w6539.demo_jetpack.ui.holder

/**
 * @author Yang
 * @since 2022/12/20 14:12
 * @desc
 */
object ItemViewType {
    const val UNKNOWN = -1              //未知类型，使用EmptyViewHolder容错处理。

    const val CUSTOM_HEADER = 0         //自定义头部类型。

    const val TEXT_CARD_HEADER1 = 1

    const val TEXT_CARD_HEADER2 = 2

    const val TEXT_CARD_HEADER3 = 3

    const val TEXT_CARD_HEADER4 = 4     //type:textCard -> dataType:TextCard,type:header4

    const val TEXT_CARD_HEADER5 = 5     //type:textCard -> dataType:TextCard -> type:header5

    const val TEXT_CARD_HEADER6 = 6

    const val TEXT_CARD_HEADER7 =
        7    //type:textCard -> dataType:TextCardWithRightAndLeftTitle,type:header7

    const val TEXT_CARD_HEADER8 =
        8    //type:textCard -> dataType:TextCardWithRightAndLeftTitle,type:header8

    const val TEXT_CARD_FOOTER1 = 9

    const val TEXT_CARD_FOOTER2 = 10    //type:textCard -> dataType:TextCard,type:footer2

    const val TEXT_CARD_FOOTER3 = 11    //type:textCard -> dataType:TextCardWithTagId,type:footer3

    const val BANNER = 12               //type:banner -> dataType:Banner

    const val BANNER3 = 13              //type:banner3-> dataType:Banner

    //type:followCard -> dataType:FollowCard -> type:video -> dataType:VideoBeanForClient
    const val FOLLOW_CARD = 14

    const val TAG_BRIEFCARD = 15        //type:briefCard -> dataType:TagBriefCard

    const val TOPIC_BRIEFCARD = 16      //type:briefCard -> dataType:TopicBriefCard

    const val COLUMN_CARD_LIST = 17      //type:columnCardList -> dataType:ItemCollection

    const val VIDEO_SMALL_CARD = 18     //type:videoSmallCard -> dataType:VideoBeanForClient

    const val INFORMATION_CARD = 19     //type:informationCard -> dataType:InformationCard

    const val AUTO_PLAY_VIDEO_AD = 20   //type:autoPlayVideoAd -> dataType:AutoPlayVideoAdDetail

    //type:horizontalScrollCard -> dataType:HorizontalScrollCard
    const val HORIZONTAL_SCROLL_CARD = 21

    //type:specialSquareCardCollection -> dataType:ItemCollection
    const val SPECIAL_SQUARE_CARD_COLLECTION = 22

    //type:ugcSelectedCardCollection -> dataType:ItemCollection
    const val UGC_SELECTED_CARD_COLLECTION = 23

    const val MAX = 100   //避免外部其他类型与此处包含的某个类型重复。

    private val itemType = mutableMapOf(
        "header4" to TEXT_CARD_HEADER4,
        "header5" to TEXT_CARD_HEADER5,
        "header7" to TEXT_CARD_HEADER7,
        "header8" to TEXT_CARD_HEADER8,
        "footer2" to TEXT_CARD_FOOTER2,
        "footer3" to TEXT_CARD_FOOTER3,
        "HorizontalScrollCard" to HORIZONTAL_SCROLL_CARD,
        "specialSquareCardCollection" to SPECIAL_SQUARE_CARD_COLLECTION,
        "columnCardList" to COLUMN_CARD_LIST,
        "banner" to BANNER,
        "banner3" to BANNER3,
        "videoSmallCard" to VIDEO_SMALL_CARD,
        "TagBriefCard" to TAG_BRIEFCARD,
        "TopicBriefCard" to TOPIC_BRIEFCARD,
        "FollowCard" to FOLLOW_CARD,
        "InformationCard" to INFORMATION_CARD,
        "ItemCollection" to UGC_SELECTED_CARD_COLLECTION,
        "AutoPlayVideoAdDetail" to AUTO_PLAY_VIDEO_AD,
    )

    fun getItemViewType(typeStr: String): Int {
        return itemType[typeStr] ?: UNKNOWN
    }
}