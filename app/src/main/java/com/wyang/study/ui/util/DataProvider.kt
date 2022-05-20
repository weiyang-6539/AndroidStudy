package com.wyang.study.ui.util

import androidx.fragment.app.Fragment
import com.wyang.study.bean.*
import com.wyang.study.ui.fragment_second.*

class DataProvider {
    companion object {
        private val classMap: HashMap<String, Class<*>> = object : HashMap<String, Class<*>>() {
            init {
                //put(MainFragment.class.getSimpleName(), MainFragment.class);
                put(HandlerThreadFragment::class.java.simpleName, HandlerThreadFragment::class.java)
                put(IntentServiceFragment::class.java.simpleName, IntentServiceFragment::class.java)
                put(AidlFragment::class.java.simpleName, AidlFragment::class.java)
                put(OkHttpFragment::class.java.simpleName, OkHttpFragment::class.java)
                put(RetrofitFragment::class.java.simpleName, RetrofitFragment::class.java)
                put(ViewAnimFragment::class.java.simpleName, ViewAnimFragment::class.java)
                put(AlgorithmFragment::class.java.simpleName, AlgorithmFragment::class.java)
                put(FlexBoxLayoutFragment::class.java.simpleName, FlexBoxLayoutFragment::class.java)
                put(NotificationFragment::class.java.simpleName, NotificationFragment::class.java)
                put(CustomViewFragment::class.java.simpleName, CustomViewFragment::class.java)
                put(
                    NineGridLayoutFragment::class.java.simpleName,
                    NineGridLayoutFragment::class.java
                )
                put(DragSortFragment::class.java.simpleName, DragSortFragment::class.java)
                put(WeChatFragment::class.java.simpleName, WeChatFragment::class.java)
                put(ContactsFragment::class.java.simpleName, ContactsFragment::class.java)
                put(
                    StickyDecorationFragment::class.java.simpleName,
                    StickyDecorationFragment::class.java
                )
                put(AlbumFragment::class.java.simpleName, AlbumFragment::class.java)
                put(
                    AddressLinkageFragment::class.java.simpleName,
                    AddressLinkageFragment::class.java
                )
                put(AddressFilterFragment::class.java.simpleName, AddressFilterFragment::class.java)
            }
        }

        fun createFragmentByName(className: String?): Fragment? {
            val aClass = classMap[className]
            try {
                if (aClass != null) return aClass.newInstance() as Fragment
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            }
            return NullFragment()
        }

        fun getMainPageData(page: Int): List<Simple>? {
            val data: MutableList<Simple> = ArrayList()
            if (page == 0) {
                data.add(
                    Simple(
                        "HandlerThread", "HandlerThread的用法",
                        HandlerThreadFragment::class.java.simpleName
                    )
                )
                data.add(
                    Simple(
                        "IntentService", "IntentService的用法",
                        IntentServiceFragment::class.java.simpleName
                    )
                )
                data.add(Simple("AIDL用法", "AIDL用法", AidlFragment::class.java.simpleName))
            } else if (page == 1) {
                data.add(Simple("OkHttp的学习", "okhttp", OkHttpFragment::class.java.simpleName))
                data.add(Simple("Retrofit的学习", "retrofit", RetrofitFragment::class.java.simpleName))
                data.add(Simple("Boom瞎卡拉卡", "动画展现-动画粉碎", ViewAnimFragment::class.java.simpleName))
                data.add(Simple("常见加密算法", "常见加密算法的使用", AlgorithmFragment::class.java.simpleName))
            } else if (page == 2) {
                data.add(Simple("ViewSwitcher", "暂时不知道用法及效果", ""))
                data.add(
                    Simple(
                        "FlexBoxLayout", "高级LinearLayout",
                        FlexBoxLayoutFragment::class.java.simpleName
                    )
                )
                data.add(
                    Simple(
                        "Notification",
                        "通知栏的使用",
                        NotificationFragment::class.java.simpleName
                    )
                )
            } else if (page == 3) {
                data.add(Simple("自定义控件练习", "五星红旗", CustomViewFragment::class.java.simpleName))
                data.add(
                    Simple(
                        "图片九宫格",
                        "仿微信图片九宫格效果",
                        NineGridLayoutFragment::class.java.simpleName
                    )
                )
            } else if (page == 4) {
                data.add(
                    Simple(
                        "RecyclerView用法一",
                        "实现今日头条拖拽排序",
                        DragSortFragment::class.java.simpleName
                    )
                )
                data.add(
                    Simple(
                        "RecyclerView用法二",
                        "仿微信发送图片朋友圈九宫格效果",
                        WeChatFragment::class.java.simpleName
                    )
                )
                data.add(
                    Simple(
                        "RecyclerView用法三",
                        "仿微信通讯录效果",
                        ContactsFragment::class.java.simpleName
                    )
                )
                data.add(
                    Simple(
                        "RecyclerView用法四",
                        "悬浮分组展示",
                        StickyDecorationFragment::class.java.simpleName
                    )
                )
                data.add(Simple("RecyclerView用法五", "仿小米云相册", AlbumFragment::class.java.simpleName))
            } else if (page == 5) {
                data.add(
                    Simple(
                        "树结构之省市区镇四级联动", "利用TreeNode及NodeSeeker实现的四级联动",
                        AddressLinkageFragment::class.java.simpleName
                    )
                )
                data.add(
                    Simple(
                        "树结构之筛选遍历", "利用TreeNode及NodeSeeker实现复杂筛选条件,代码简洁的遍历",
                        AddressFilterFragment::class.java.simpleName
                    )
                )
            }
            return data
        }

        fun getImageUrls(count: Int): List<String>? {
            val list: MutableList<String> = ArrayList()
            list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2738234332,2025993528&fm=26&gp=0.jpg")
            list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg")
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=f3baf93e28ada9d1fe5fe57329972788&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201310%2F16%2F224046ups8zp1jg31uz82g.jpg")
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg")
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690")
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=0324c2d44474b8087ab2f9c780f8ecd3&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F9bbc284bgw1f2tzaf85l2j20rs0hx78y.jpg")
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=9d74a822ed852a90204701203bafada2&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140603%2F18347945_133954199143_2.jpg")
            val data: MutableList<String> = ArrayList()
            for (i in 0 until count) {
                val position = if (i < list.size) i else i % list.size
                data.add(list[position])
            }
            return data
        }

        fun getDragSortData(): List<SectionItem>? {
            val list: MutableList<SectionItem> = ArrayList()
            list.add(SectionItem(true, "我的频道"))
            list.add(SectionItem(Channel("关注", false, true)))
            list.add(SectionItem(Channel("推荐", false, true)))
            list.add(SectionItem(Channel("热点", true, true)))
            list.add(SectionItem(Channel("长沙", true, true)))
            list.add(SectionItem(Channel("视频", true, true)))
            list.add(SectionItem(Channel("音频", true, true)))
            list.add(SectionItem(Channel("直播", true, true)))
            list.add(SectionItem(Channel("精选", true, true)))
            list.add(SectionItem(Channel("小说", true, true)))
            list.add(SectionItem(Channel("动漫", true, true)))
            list.add(SectionItem(Channel("科技", true, true)))
            list.add(SectionItem(Channel("军事", true, true)))
            list.add(SectionItem(Channel("国际", true, true)))
            list.add(SectionItem(Channel("时尚", true, true)))
            list.add(SectionItem(Channel("电影", true, true)))
            list.add(SectionItem(Channel("情感", true, true)))
            list.add(SectionItem(Channel("家居", true, true)))
            list.add(SectionItem(Channel("三农", true, true)))
            list.add(SectionItem(Channel("历史", true, true)))
            list.add(SectionItem(Channel("搞笑", true, true)))
            list.add(SectionItem(Channel("美食", true, true)))
            list.add(SectionItem(Channel("历史", true, true)))
            list.add(SectionItem(Channel("娱乐", true, true)))
            list.add(SectionItem(true, "频道推荐"))
            list.add(SectionItem(Channel("新时代", true, false)))
            list.add(SectionItem(Channel("NBA", true, false)))
            list.add(SectionItem(Channel("动物", true, false)))
            list.add(SectionItem(Channel("音乐", true, false)))
            list.add(SectionItem(Channel("生活", true, false)))
            list.add(SectionItem(Channel("影视", true, false)))
            list.add(SectionItem(Channel("党媒推荐", true, false)))
            list.add(SectionItem(Channel("冬奥", true, false)))
            list.add(SectionItem(Channel("历史", true, false)))
            list.add(SectionItem(Channel("彩票", true, false)))
            list.add(SectionItem(Channel("收藏", true, false)))
            list.add(SectionItem(Channel("家居", true, false)))
            list.add(SectionItem(Channel("孕产", true, false)))
            list.add(SectionItem(Channel("钓鱼", true, false)))
            list.add(SectionItem(Channel("育儿", true, false)))
            list.add(SectionItem(Channel("养生", true, false)))
            list.add(SectionItem(Channel("星座", true, false)))
            return list
        }

        fun getDiscoverData(): List<Discover>? {
            val list: MutableList<Discover> = ArrayList()
            list.add(Discover("", "", "", getImageUrls(1)))
            list.add(Discover("", "", "", getImageUrls(2)))
            list.add(Discover("", "", "", getImageUrls(3)))
            list.add(Discover("", "", "", getImageUrls(4)))
            list.add(Discover("", "", "", getImageUrls(5)))
            list.add(Discover("", "", "", getImageUrls(6)))
            list.add(Discover("", "", "", getImageUrls(7)))
            list.add(Discover("", "", "", getImageUrls(8)))
            list.add(Discover("", "", "", getImageUrls(9)))
            list.add(Discover("", "", "", getImageUrls(10)))
            list.add(Discover("", "", "", getImageUrls(11)))
            return list
        }

        fun getAlbumList(): List<AlbumBase>? {
            val list: MutableList<AlbumBase> = ArrayList()
            //2020-01-03
            list.add(AlbumBase("2020-01-03"))
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591782075661&di=eb26eeb40a21fea9753ba837494b25ab&imgtype=0&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D1287527925%2C3407796655%26fm%3D214%26gp%3D0.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2738234332,2025993528&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=f3baf93e28ada9d1fe5fe57329972788&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201310%2F16%2F224046ups8zp1jg31uz82g.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=0324c2d44474b8087ab2f9c780f8ecd3&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F9bbc284bgw1f2tzaf85l2j20rs0hx78y.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=9d74a822ed852a90204701203bafada2&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140603%2F18347945_133954199143_2.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=f3baf93e28ada9d1fe5fe57329972788&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201310%2F16%2F224046ups8zp1jg31uz82g.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=0324c2d44474b8087ab2f9c780f8ecd3&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F9bbc284bgw1f2tzaf85l2j20rs0hx78y.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=9d74a822ed852a90204701203bafada2&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140603%2F18347945_133954199143_2.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=f3baf93e28ada9d1fe5fe57329972788&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201310%2F16%2F224046ups8zp1jg31uz82g.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=0324c2d44474b8087ab2f9c780f8ecd3&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F9bbc284bgw1f2tzaf85l2j20rs0hx78y.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=9d74a822ed852a90204701203bafada2&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140603%2F18347945_133954199143_2.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=f3baf93e28ada9d1fe5fe57329972788&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201310%2F16%2F224046ups8zp1jg31uz82g.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=0324c2d44474b8087ab2f9c780f8ecd3&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F9bbc284bgw1f2tzaf85l2j20rs0hx78y.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-03",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=9d74a822ed852a90204701203bafada2&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140603%2F18347945_133954199143_2.jpg"
                )
            )

            //2020-01-02
            list.add(AlbumBase("2020-01-02"))
            list.add(
                Album(
                    "2020-01-02",
                    "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2738234332,2025993528&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=f3baf93e28ada9d1fe5fe57329972788&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201310%2F16%2F224046ups8zp1jg31uz82g.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=0324c2d44474b8087ab2f9c780f8ecd3&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F9bbc284bgw1f2tzaf85l2j20rs0hx78y.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=9d74a822ed852a90204701203bafada2&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140603%2F18347945_133954199143_2.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2738234332,2025993528&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=f3baf93e28ada9d1fe5fe57329972788&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201310%2F16%2F224046ups8zp1jg31uz82g.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=0324c2d44474b8087ab2f9c780f8ecd3&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F9bbc284bgw1f2tzaf85l2j20rs0hx78y.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=9d74a822ed852a90204701203bafada2&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140603%2F18347945_133954199143_2.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2738234332,2025993528&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=f3baf93e28ada9d1fe5fe57329972788&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201310%2F16%2F224046ups8zp1jg31uz82g.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=0324c2d44474b8087ab2f9c780f8ecd3&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F9bbc284bgw1f2tzaf85l2j20rs0hx78y.jpg"
                )
            )
            list.add(
                Album(
                    "2020-01-02",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=9d74a822ed852a90204701203bafada2&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140603%2F18347945_133954199143_2.jpg"
                )
            )

            //2020-01-01
            list.add(AlbumBase("2020-01-01"))
            list.add(
                Album(
                    "2019-12-29",
                    "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2738234332,2025993528&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-29",
                    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-29",
                    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-29",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=f3baf93e28ada9d1fe5fe57329972788&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201310%2F16%2F224046ups8zp1jg31uz82g.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-29",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=f3baf93e28ada9d1fe5fe57329972788&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201310%2F16%2F224046ups8zp1jg31uz82g.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-29",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-29",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-29",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690"
                )
            )
            list.add(
                Album(
                    "2019-12-29",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690"
                )
            )
            list.add(
                Album(
                    "2019-12-29",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=0324c2d44474b8087ab2f9c780f8ecd3&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F9bbc284bgw1f2tzaf85l2j20rs0hx78y.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-29",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=9d74a822ed852a90204701203bafada2&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140603%2F18347945_133954199143_2.jpg"
                )
            )

            //2019-12-28
            list.add(AlbumBase("2019-12-26"))
            list.add(
                Album(
                    "2019-12-26",
                    "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2738234332,2025993528&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-26",
                    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-26",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=f3baf93e28ada9d1fe5fe57329972788&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201310%2F16%2F224046ups8zp1jg31uz82g.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-26",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-26",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-26",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690"
                )
            )
            list.add(
                Album(
                    "2019-12-26",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690"
                )
            )
            list.add(
                Album(
                    "2019-12-26",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=0324c2d44474b8087ab2f9c780f8ecd3&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F9bbc284bgw1f2tzaf85l2j20rs0hx78y.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-26",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=9d74a822ed852a90204701203bafada2&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140603%2F18347945_133954199143_2.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-26",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=9d74a822ed852a90204701203bafada2&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140603%2F18347945_133954199143_2.jpg"
                )
            )

            //2019-12-25
            list.add(AlbumBase("2019-12-25"))
            list.add(
                Album(
                    "2019-12-25",
                    "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2738234332,2025993528&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-25",
                    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-25",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=f3baf93e28ada9d1fe5fe57329972788&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201310%2F16%2F224046ups8zp1jg31uz82g.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-25",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-25",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-25",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-25",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-25",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690"
                )
            )
            list.add(
                Album(
                    "2019-12-25",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690"
                )
            )
            list.add(
                Album(
                    "2019-12-25",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690"
                )
            )
            list.add(
                Album(
                    "2019-12-25",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=0324c2d44474b8087ab2f9c780f8ecd3&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F9bbc284bgw1f2tzaf85l2j20rs0hx78y.jpg"
                )
            )
            list.add(
                Album(
                    "2019-12-25",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=9d74a822ed852a90204701203bafada2&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140603%2F18347945_133954199143_2.jpg"
                )
            )

            //2019-12-16
            list.add(AlbumBase("2019-12-16"))
            list.add(
                Album(
                    "2019-12-16",
                    "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2738234332,2025993528&fm=26&gp=0.jpg"
                )
            )
            return list
        }
    }
}