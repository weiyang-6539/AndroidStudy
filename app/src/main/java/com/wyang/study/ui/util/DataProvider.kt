package com.wyang.study.ui.util

import androidx.fragment.app.Fragment
import com.wyang.study.bean.*
import com.wyang.study.ui.fragment_second.*

class DataProvider {
    companion object {
        private val classMap = mutableMapOf(
            HandlerThreadFragment::class.java.simpleName to HandlerThreadFragment::class.java,
            IntentServiceFragment::class.java.simpleName to IntentServiceFragment::class.java,
            AidlFragment::class.java.simpleName to AidlFragment::class.java,
            OkHttpFragment::class.java.simpleName to OkHttpFragment::class.java,
            RetrofitFragment::class.java.simpleName to RetrofitFragment::class.java,
            AlgorithmFragment::class.java.simpleName to AlgorithmFragment::class.java,
            NotificationFragment::class.java.simpleName to NotificationFragment::class.java,
            TestLaunchModeFragment::class.java.simpleName to TestLaunchModeFragment::class.java,
            CustomViewFragment::class.java.simpleName to CustomViewFragment::class.java,
            NineGridLayoutFragment::class.java.simpleName to NineGridLayoutFragment::class.java,
            WeiBoFragment::class.java.simpleName to WeiBoFragment::class.java,
            BottomBarFragment::class.java.simpleName to BottomBarFragment::class.java,
            DragSortFragment::class.java.simpleName to DragSortFragment::class.java,
            WeChatFragment::class.java.simpleName to WeChatFragment::class.java,
            ContactsFragment::class.java.simpleName to ContactsFragment::class.java,
            StickyDecorationFragment::class.java.simpleName to StickyDecorationFragment::class.java,
            AlbumFragment::class.java.simpleName to AlbumFragment::class.java,
            Decoration01Fragment::class.java.simpleName to Decoration01Fragment::class.java,
            Decoration02Fragment::class.java.simpleName to Decoration02Fragment::class.java,
            Decoration03Fragment::class.java.simpleName to Decoration03Fragment::class.java,
            Decoration04Fragment::class.java.simpleName to Decoration04Fragment::class.java,
            Decoration05Fragment::class.java.simpleName to Decoration05Fragment::class.java,
            Decoration06Fragment::class.java.simpleName to Decoration06Fragment::class.java,
            AddressLinkageFragment::class.java.simpleName to AddressLinkageFragment::class.java,
            AddressFilterFragment::class.java.simpleName to AddressFilterFragment::class.java,
        )

        fun createFragmentByName(className: String?): Fragment {
            return classMap[className]?.newInstance() ?: NullFragment()
        }

        fun getMainPageData(page: Int): List<Simple> {
            return when (page) {
                0 -> mutableListOf(
                    Simple(
                        "HandlerThread",
                        "HandlerThread的用法",
                        HandlerThreadFragment::class.java.simpleName
                    ),
                    Simple(
                        "IntentService",
                        "IntentService的用法",
                        IntentServiceFragment::class.java.simpleName
                    ),
                    Simple(
                        "AIDL用法",
                        "AIDL用法",
                        AidlFragment::class.java.simpleName
                    )
                )
                1 -> mutableListOf(
                    Simple(
                        "OkHttp的学习",
                        "okhttp",
                        OkHttpFragment::class.java.simpleName
                    ),
                    Simple(
                        "Retrofit的学习",
                        "retrofit",
                        RetrofitFragment::class.java.simpleName
                    ),
                    Simple(
                        "常见加密算法",
                        "常见加密算法的使用",
                        AlgorithmFragment::class.java.simpleName
                    )
                )
                2 -> mutableListOf(
                    Simple(
                        "ViewSwitcher",
                        "暂时不知道用法及效果",
                        ""
                    ),
                    Simple(
                        "Notification",
                        "通知栏的使用",
                        NotificationFragment::class.java.simpleName
                    ),
                    Simple(
                        "Activity LaunchMode",
                        "",
                        TestLaunchModeFragment::class.java.simpleName
                    )
                )
                3 -> mutableListOf(
                    Simple(
                        "自定义控件练习",
                        "五星红旗",
                        CustomViewFragment::class.java.simpleName
                    ),
                    Simple(
                        "图片九宫格",
                        "仿微信图片九宫格效果",
                        NineGridLayoutFragment::class.java.simpleName
                    ),
                    Simple(
                        "AppCompatEditText",
                        "仿微博话题效果",
                        WeiBoFragment::class.java.simpleName
                    ),
                    Simple(
                        "带凸起布局",
                        "仿咸鱼",
                        BottomBarFragment::class.java.simpleName
                    )
                )
                4 -> mutableListOf(
                    Simple(
                        "RecyclerView用法一",
                        "实现今日头条拖拽排序",
                        DragSortFragment::class.java.simpleName
                    ),
                    Simple(
                        "RecyclerView用法二",
                        "仿微信发送图片朋友圈九宫格效果",
                        WeChatFragment::class.java.simpleName
                    ),
                    Simple(
                        "RecyclerView用法三",
                        "仿微信通讯录效果",
                        ContactsFragment::class.java.simpleName
                    ),
                    Simple(
                        "RecyclerView用法四",
                        "悬浮分组展示",
                        StickyDecorationFragment::class.java.simpleName
                    ),
                    Simple(
                        "RecyclerView用法五",
                        "仿小米云相册",
                        AlbumFragment::class.java.simpleName
                    ),
                    Simple(
                        "RecyclerView分割线一",
                        "垂直线性",
                        Decoration01Fragment::class.java.simpleName
                    ),
                    Simple(
                        "RecyclerView分割线二",
                        "水平线性",
                        Decoration02Fragment::class.java.simpleName
                    ),
                    Simple(
                        "RecyclerView分割线三",
                        "Grid垂直",
                        Decoration03Fragment::class.java.simpleName
                    ),
                    Simple(
                        "RecyclerView分割线四",
                        "Grid水平",
                        Decoration04Fragment::class.java.simpleName
                    ),
                    Simple(
                        "RecyclerView分割线五",
                        "瀑布流垂直",
                        Decoration05Fragment::class.java.simpleName
                    ),
                    Simple(
                        "RecyclerView分割线六",
                        "瀑布流水平",
                        Decoration06Fragment::class.java.simpleName
                    )
                )
                5 -> mutableListOf(
                    Simple(
                        "树结构之省市区镇四级联动",
                        "利用TreeNode及NodeSeeker实现的四级联动",
                        AddressLinkageFragment::class.java.simpleName
                    ),
                    Simple(
                        "树结构之筛选遍历",
                        "利用TreeNode及NodeSeeker实现复杂筛选条件,代码简洁的遍历",
                        AddressFilterFragment::class.java.simpleName
                    )
                )
                else -> mutableListOf()
            }
        }

        fun getImageUrls(count: Int): List<String> {
            val list: MutableList<String> = ArrayList()
            list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2738234332,2025993528&fm=26&gp=0.jpg")
            list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg")
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=f3baf93e28ada9d1fe5fe57329972788&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201310%2F16%2F224046ups8zp1jg31uz82g.jpg")
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg")
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690")
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=0324c2d44474b8087ab2f9c780f8ecd3&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F9bbc284bgw1f2tzaf85l2j20rs0hx78y.jpg")
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=9d74a822ed852a90204701203bafada2&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140603%2F18347945_133954199143_2.jpg")
            val data = ArrayList<String>()
            for (i in 0 until count) {
                val position = if (i < list.size) i else i % list.size
                data.add(list[position])
            }
            return data
        }

        fun getDragSortData(): List<SectionItem> {
            return mutableListOf(
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
                SectionItem(true, "频道推荐"),
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
        }

        fun getDiscoverData(): List<Discover> {
            return mutableListOf(
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
        }

        fun getAlbumList(): List<AlbumBase> {
            return mutableListOf(
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
}