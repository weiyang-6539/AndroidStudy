package com.wyang.study.ui.util

import com.wyang.study.bean.Album
import com.wyang.study.bean.AlbumBase
import com.wyang.study.bean.Channel
import com.wyang.study.bean.Discover
import com.wyang.study.bean.SectionItem
import com.wyang.study.bean.Simple
import com.wyang.study.ui.activity.RvActivity
import com.wyang.study.ui.fragment.AddressFilterFragment
import com.wyang.study.ui.fragment.AddressLinkageFragment
import com.wyang.study.ui.fragment.AidlFragment
import com.wyang.study.ui.fragment.AlgorithmFragment
import com.wyang.study.ui.fragment.FlexBoxFragment
import com.wyang.study.ui.fragment.HandlerThreadFragment
import com.wyang.study.ui.fragment.IntentServiceFragment
import com.wyang.study.ui.fragment.NotificationFragment
import com.wyang.study.ui.fragment.OkHttpFragment
import com.wyang.study.ui.fragment.RetrofitFragment
import com.wyang.study.ui.fragment.TestConstraintFragment
import com.wyang.study.ui.fragment.custom.BottomBarFragment
import com.wyang.study.ui.fragment.custom.CustomViewFragment
import com.wyang.study.ui.fragment.custom.NineGridLayoutFragment
import com.wyang.study.ui.fragment.custom.PrivacyFragment
import com.wyang.study.ui.fragment.custom.WeiBoFragment
import com.wyang.study.ui.fragment.official.StringFragment
import com.wyang.study.ui.fragment.rv.AlbumFragment
import com.wyang.study.ui.fragment.rv.ContactsFragment
import com.wyang.study.ui.fragment.rv.DecorationFragment
import com.wyang.study.ui.fragment.rv.DragSortFragment
import com.wyang.study.ui.fragment.rv.WeChatFragment

class DataProvider {
    companion object {
        fun getMainPageData(page: Int): MutableList<Simple> {
            return when (page) {
                0 -> mutableListOf(
                    Simple(
                        "HandlerThread",
                        "HandlerThread的用法",
                        HandlerThreadFragment::class.java
                    ),
                    Simple(
                        "IntentService",
                        "IntentService的用法",
                        IntentServiceFragment::class.java
                    ),
                    Simple(
                        "AIDL用法",
                        "AIDL用法",
                        AidlFragment::class.java
                    ),
                    Simple(
                        "SpannableStringBuilder",
                        "api 详解",
                        StringFragment::class.java
                    )
                )

                1 -> mutableListOf(
                    Simple(
                        "OkHttp的学习",
                        "okhttp",
                        OkHttpFragment::class.java
                    ),
                    Simple(
                        "Retrofit的学习",
                        "retrofit",
                        RetrofitFragment::class.java
                    ),
                    Simple(
                        "常见加密算法",
                        "常见加密算法的使用",
                        AlgorithmFragment::class.java
                    )
                )

                2 -> mutableListOf(
                    Simple(
                        "ViewSwitcher",
                        "暂时不知道用法及效果",
                    ),
                    Simple(
                        "Notification",
                        "通知栏的使用",
                        NotificationFragment::class.java
                    ),
                    Simple(
                        "FlexboxLayout",
                        "谷歌弹性盒子, 与css flex类似",
                        FlexBoxFragment::class.java
                    )
                )

                3 -> mutableListOf(
                    Simple(
                        "自定义控件练习",
                        "五星红旗",
                        CustomViewFragment::class.java
                    ),
                    Simple(
                        "图片九宫格",
                        "仿微信图片九宫格效果",
                        NineGridLayoutFragment::class.java
                    ),
                    Simple(
                        "AppCompatEditText",
                        "仿微博话题效果",
                        WeiBoFragment::class.java
                    ),
                    Simple(
                        "带凸起布局",
                        "仿咸鱼",
                        BottomBarFragment::class.java
                    ),
                    Simple(
                        "隐私协议自定义控件的实现",
                        "",
                        PrivacyFragment::class.java
                    ),
                    Simple(
                        "测试约束布局子view拖动",
                        "",
                        TestConstraintFragment::class.java
                    )
                )

                4 -> mutableListOf(
                    Simple(
                        "App",
                        "RecyclerView的复杂实现",
                        RvActivity::class.java,
                        true
                    ),
                    Simple(
                        "RecyclerView用法一",
                        "实现今日头条拖拽排序",
                        DragSortFragment::class.java
                    ),
                    Simple(
                        "RecyclerView用法二",
                        "仿微信发送图片朋友圈九宫格效果",
                        WeChatFragment::class.java
                    ),
                    Simple(
                        "RecyclerView用法三",
                        "仿微信通讯录效果",
                        ContactsFragment::class.java
                    ),
                    Simple(
                        "RecyclerView用法五",
                        "仿小米云相册",
                        AlbumFragment::class.java
                    ),
                    Simple(
                        "RecyclerView分割线一",
                        "垂直线性",
                        DecorationFragment::class.java
                    )
                )

                5 -> mutableListOf(
                    Simple(
                        "树结构之省市区镇四级联动",
                        "利用TreeNode及NodeSeeker实现的四级联动",
                        AddressLinkageFragment::class.java
                    ),
                    Simple(
                        "树结构之筛选遍历",
                        "利用TreeNode及NodeSeeker实现复杂筛选条件,代码简洁的遍历",
                        AddressFilterFragment::class.java
                    )
                )

                else -> mutableListOf()
            }
        }

        fun getImageUrls(count: Int): List<String> {
            val list: MutableList<String> = ArrayList()
            list.add("https://img2.baidu.com/it/u=2498736458,850355095&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500")
            list.add("https://img2.baidu.com/it/u=2498736458,850355095&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500")
            list.add("https://img2.baidu.com/it/u=2498736458,850355095&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500")
            list.add("https://img2.baidu.com/it/u=2498736458,850355095&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500")
            list.add("https://img2.baidu.com/it/u=2498736458,850355095&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500")
            list.add("https://img2.baidu.com/it/u=2498736458,850355095&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500")
            list.add("https://img2.baidu.com/it/u=2498736458,850355095&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500")
            list.add("https://img2.baidu.com/it/u=2498736458,850355095&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500")
            list.add("https://img2.baidu.com/it/u=2498736458,850355095&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500")
            list.add("https://img2.baidu.com/it/u=2498736458,850355095&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500")
            val data = ArrayList<String>()
            for (i in 0 until count) {
                val position = if (i < list.size) i else i % list.size
                data.add(list[position])
            }
            return data
        }

        fun getDragSortData() = mutableListOf(
            SectionItem(true, "我的频道"),
            SectionItem(Channel("关注", false, true)),
            SectionItem(Channel("推荐", false, true)),
            SectionItem(Channel("热点", true, true)),
            SectionItem(Channel("长沙", true, true)),
            SectionItem(Channel("视频", true, true)),
            SectionItem(Channel("音频", true, true)),
            SectionItem(Channel("直播", true, true)),
            SectionItem(Channel("精选", true, true)),
            SectionItem(Channel("小说", true, true)),
            SectionItem(Channel("动漫", true, true)),
            SectionItem(Channel("科技", true, true)),
            SectionItem(Channel("军事", true, true)),
            SectionItem(Channel("国际", true, true)),
            SectionItem(Channel("时尚", true, true)),
            SectionItem(Channel("电影", true, true)),
            SectionItem(Channel("情感", true, true)),
            SectionItem(Channel("家居", true, true)),
            SectionItem(Channel("三农", true, true)),
            SectionItem(Channel("历史", true, true)),
            SectionItem(Channel("搞笑", true, true)),
            SectionItem(Channel("美食", true, true)),
            SectionItem(Channel("历史", true, true)),
            SectionItem(Channel("娱乐", true, true)),
            SectionItem(false, "频道推荐"),
            SectionItem(Channel("新时代", true, false)),
            SectionItem(Channel("NBA", true, false)),
            SectionItem(Channel("动物", true, false)),
            SectionItem(Channel("音乐", true, false)),
            SectionItem(Channel("生活", true, false)),
            SectionItem(Channel("影视", true, false)),
            SectionItem(Channel("党媒推荐", true, false)),
            SectionItem(Channel("冬奥", true, false)),
            SectionItem(Channel("历史", true, false)),
            SectionItem(Channel("彩票", true, false)),
            SectionItem(Channel("收藏", true, false)),
            SectionItem(Channel("家居", true, false)),
            SectionItem(Channel("孕产", true, false)),
            SectionItem(Channel("钓鱼", true, false)),
            SectionItem(Channel("育儿", true, false)),
            SectionItem(Channel("养生", true, false)),
            SectionItem(Channel("星座", true, false))
        )

        fun getDiscoverData() = mutableListOf(
            Discover("", "", "", getImageUrls(1)),
            Discover("", "", "", getImageUrls(2)),
            Discover("", "", "", getImageUrls(3)),
            Discover("", "", "", getImageUrls(4)),
            Discover("", "", "", getImageUrls(5)),
            Discover("", "", "", getImageUrls(6)),
            Discover("", "", "", getImageUrls(7)),
            Discover("", "", "", getImageUrls(8)),
            Discover("", "", "", getImageUrls(9)),
            Discover("", "", "", getImageUrls(10)),
            Discover("", "", "", getImageUrls(11))
        )

        fun getAlbumList() = mutableListOf(
            AlbumBase("2022-11-03"),
            Album(
                "2022-11-03",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-03",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-03",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-03",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-03",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-03",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-03",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-03",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-03",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-03",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            AlbumBase("2022-11-02"),
            Album(
                "2022-11-02",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-02",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-02",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-02",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-02",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-02",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-02",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-02",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-02",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-02",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-02",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-02",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-02",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-02",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-11-02",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            AlbumBase("2022-10-24"),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            AlbumBase("2022-10-20"),
            Album(
                "2022-10-20",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            AlbumBase("2022-10-07"),
            Album(
                "2022-10-07",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-07",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-07",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-07",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-07",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-07",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-07",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-07",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-07",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-07",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-07",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-07",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            AlbumBase("2022-10-01"),
            Album(
                "2022-10-01",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-01",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-01",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-01",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-01",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-01",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-01",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-01",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-01",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-10-01",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            AlbumBase("2022-09-24"),
            Album(
                "2022-09-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-09-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-09-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-09-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-09-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-09-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-09-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-09-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-09-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-09-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-09-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
            Album(
                "2022-09-24",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Flmg.jj20.com%2Fup%2Fallimg%2F1114%2F0R620120041%2F200R6120041-4-1200.jpg&refer=http%3A%2F%2Flmg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670118040&t=55bce387a77087a9d94f09ce472847f3"
            ),
        )
    }
}