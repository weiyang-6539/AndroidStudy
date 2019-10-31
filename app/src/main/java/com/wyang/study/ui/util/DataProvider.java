package com.wyang.study.ui.util;

import android.support.v4.app.Fragment;

import com.wyang.study.bean.Channel;
import com.wyang.study.bean.Discover;
import com.wyang.study.bean.SectionItem;
import com.wyang.study.bean.Simple;
import com.wyang.study.ui.fragment_second.AddressFilterFragment;
import com.wyang.study.ui.fragment_second.AddressLinkageFragment;
import com.wyang.study.ui.fragment_second.ContactsFragment;
import com.wyang.study.ui.fragment_second.CustomViewFragment;
import com.wyang.study.ui.fragment_second.DragSortFragment;
import com.wyang.study.ui.fragment_second.FlexBoxLayoutFragment;
import com.wyang.study.ui.fragment_second.GameEventFragment;
import com.wyang.study.ui.fragment_second.NineGridLayoutFragment;
import com.wyang.study.ui.fragment_second.NullFragment;
import com.wyang.study.ui.fragment_second.StickyDecorationFragment;
import com.wyang.study.ui.fragment_second.ViewAnimFragment;
import com.wyang.study.ui.fragment_second.WeChatFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by weiyang on 2019/6/19.
 */
public class DataProvider {
    private final static HashMap<String, Class<?>> classMap = new HashMap<String, Class<?>>() {{
        //put(MainFragment.class.getSimpleName(), MainFragment.class);
        put(ViewAnimFragment.class.getSimpleName(), ViewAnimFragment.class);
        put(FlexBoxLayoutFragment.class.getSimpleName(), FlexBoxLayoutFragment.class);
        put(DragSortFragment.class.getSimpleName(), DragSortFragment.class);
        put(WeChatFragment.class.getSimpleName(), WeChatFragment.class);
        put(ContactsFragment.class.getSimpleName(), ContactsFragment.class);
        put(StickyDecorationFragment.class.getSimpleName(), StickyDecorationFragment.class);
        put(CustomViewFragment.class.getSimpleName(), CustomViewFragment.class);
        put(NineGridLayoutFragment.class.getSimpleName(), NineGridLayoutFragment.class);
        put(GameEventFragment.class.getSimpleName(), GameEventFragment.class);
        put(AddressLinkageFragment.class.getSimpleName(), AddressLinkageFragment.class);
        put(AddressFilterFragment.class.getSimpleName(), AddressFilterFragment.class);
    }};

    public static Fragment createFragmentByName(String className) {
        Class<?> aClass = classMap.get(className);

        if (aClass == ViewAnimFragment.class) {
            return new ViewAnimFragment();
        } else if (aClass == FlexBoxLayoutFragment.class) {
            return new FlexBoxLayoutFragment();
        } else if (aClass == DragSortFragment.class) {
            return new DragSortFragment();
        } else if (aClass == WeChatFragment.class) {
            return new WeChatFragment();
        } else if (aClass == ContactsFragment.class) {
            return new ContactsFragment();
        } else if (aClass == StickyDecorationFragment.class) {
            return new StickyDecorationFragment();
        } else if (aClass == CustomViewFragment.class) {
            return new CustomViewFragment();
        } else if (aClass == NineGridLayoutFragment.class) {
            return new NineGridLayoutFragment();
        } else if (aClass == GameEventFragment.class) {
            return new GameEventFragment();
        } else if (aClass == AddressLinkageFragment.class) {
            return new AddressLinkageFragment();
        } else if (aClass == AddressFilterFragment.class) {
            return new AddressFilterFragment();
        }
        return new NullFragment();
    }

    public static List<Simple> getGithubData() {
        List<Simple> list = new ArrayList<>();
        list.add(new Simple("Boom瞎卡拉卡", "动画展现-动画粉碎", ViewAnimFragment.class.getSimpleName()));
        return list;
    }

    public static List<Simple> getOfficialData() {
        List<Simple> list = new ArrayList<>();
        list.add(new Simple("ViewSwitcher", "暂时不知道用法及效果", ""));
        list.add(new Simple("FlexBoxLayout", "高级LinearLayout", FlexBoxLayoutFragment.class.getSimpleName()));
        return list;
    }

    public static List<Simple> getWidgetData() {
        List<Simple> list = new ArrayList<>();
        list.add(new Simple("自定义控件练习", "五星红旗", CustomViewFragment.class.getSimpleName()));
        list.add(new Simple("图片九宫格", "仿微信图片九宫格效果", NineGridLayoutFragment.class.getSimpleName()));
        list.add(new Simple("赛事查看控件", "给覃司机写的一个控件", GameEventFragment.class.getSimpleName()));
        return list;
    }

    public static List<Simple> getUnofficialData() {
        List<Simple> list = new ArrayList<>();
        list.add(new Simple("RecyclerView用法一", "实现今日头条拖拽排序", DragSortFragment.class.getSimpleName()));
        list.add(new Simple("RecyclerView用法二", "仿微信发送图片朋友圈九宫格效果", WeChatFragment.class.getSimpleName()));
        list.add(new Simple("RecyclerView用法三", "仿微信通讯录效果", ContactsFragment.class.getSimpleName()));
        list.add(new Simple("RecyclerView用法四", "悬浮分组展示", StickyDecorationFragment.class.getSimpleName()));
        return list;
    }

    public static List<Simple> getIdeaData() {
        List<Simple> list = new ArrayList<>();
        list.add(new Simple("树结构之省市区镇四级联动", "利用TreeNode及NodeSeeker实现的四级联动", AddressLinkageFragment.class.getSimpleName()));
        list.add(new Simple("树结构之筛选遍历", "利用TreeNode及NodeSeeker实现复杂筛选条件,代码简洁的遍历", AddressFilterFragment.class.getSimpleName()));
        return list;
    }

    public static List<String> getImageUrls(int count) {
        List<String> list = new ArrayList<>();
        list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2738234332,2025993528&fm=26&gp=0.jpg");
        list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=f3baf93e28ada9d1fe5fe57329972788&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201310%2F16%2F224046ups8zp1jg31uz82g.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=b1974297cd0a721956b3784fdc00e4a8&imgtype=0&src=http%3A%2F%2Fi3.sinaimg.cn%2Fgm%2Fo%2Fi%2F2008-09-03%2FU1901P115T41D148033F757DT20080903105357.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=466abb19de3081f9782a22e63eb7a53c&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F0061prGBgy6RGOKQuqRf9%26690");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=0324c2d44474b8087ab2f9c780f8ecd3&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F9bbc284bgw1f2tzaf85l2j20rs0hx78y.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560162277510&di=9d74a822ed852a90204701203bafada2&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140603%2F18347945_133954199143_2.jpg");

        List<String> data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int position = i < list.size() ? i : i % list.size();

            data.add(list.get(position));
        }
        return data;
    }

    public static List<SectionItem> getDragSortData() {
        List<SectionItem> list = new ArrayList<>();
        list.add(new SectionItem(true, "我的频道"));
        list.add(new SectionItem(new Channel("关注", false, true)));
        list.add(new SectionItem(new Channel("推荐", false, true)));
        list.add(new SectionItem(new Channel("热点", true, true)));
        list.add(new SectionItem(new Channel("长沙", true, true)));
        list.add(new SectionItem(new Channel("视频", true, true)));
        list.add(new SectionItem(new Channel("音频", true, true)));
        list.add(new SectionItem(new Channel("直播", true, true)));
        list.add(new SectionItem(new Channel("精选", true, true)));
        list.add(new SectionItem(new Channel("小说", true, true)));
        list.add(new SectionItem(new Channel("动漫", true, true)));
        list.add(new SectionItem(new Channel("科技", true, true)));
        list.add(new SectionItem(new Channel("军事", true, true)));
        list.add(new SectionItem(new Channel("国际", true, true)));
        list.add(new SectionItem(new Channel("时尚", true, true)));
        list.add(new SectionItem(new Channel("电影", true, true)));
        list.add(new SectionItem(new Channel("情感", true, true)));
        list.add(new SectionItem(new Channel("家居", true, true)));
        list.add(new SectionItem(new Channel("三农", true, true)));
        list.add(new SectionItem(new Channel("历史", true, true)));
        list.add(new SectionItem(new Channel("搞笑", true, true)));
        list.add(new SectionItem(new Channel("美食", true, true)));
        list.add(new SectionItem(new Channel("历史", true, true)));
        list.add(new SectionItem(new Channel("娱乐", true, true)));

        list.add(new SectionItem(true, "频道推荐"));
        list.add(new SectionItem(new Channel("新时代", true, false)));
        list.add(new SectionItem(new Channel("NBA", true, false)));
        list.add(new SectionItem(new Channel("动物", true, false)));
        list.add(new SectionItem(new Channel("音乐", true, false)));
        list.add(new SectionItem(new Channel("生活", true, false)));
        list.add(new SectionItem(new Channel("影视", true, false)));
        list.add(new SectionItem(new Channel("党媒推荐", true, false)));
        list.add(new SectionItem(new Channel("冬奥", true, false)));
        list.add(new SectionItem(new Channel("历史", true, false)));
        list.add(new SectionItem(new Channel("彩票", true, false)));
        list.add(new SectionItem(new Channel("收藏", true, false)));
        list.add(new SectionItem(new Channel("家居", true, false)));
        list.add(new SectionItem(new Channel("孕产", true, false)));
        list.add(new SectionItem(new Channel("钓鱼", true, false)));
        list.add(new SectionItem(new Channel("育儿", true, false)));
        list.add(new SectionItem(new Channel("养生", true, false)));
        list.add(new SectionItem(new Channel("星座", true, false)));

        return list;
    }

    public static List<Discover> getDiscoverData() {
        List<Discover> list = new ArrayList<>();
        list.add(new Discover("", "", "", getImageUrls(1)));
        list.add(new Discover("", "", "", getImageUrls(2)));
        list.add(new Discover("", "", "", getImageUrls(3)));
        list.add(new Discover("", "", "", getImageUrls(4)));
        list.add(new Discover("", "", "", getImageUrls(5)));
        list.add(new Discover("", "", "", getImageUrls(6)));
        list.add(new Discover("", "", "", getImageUrls(7)));
        list.add(new Discover("", "", "", getImageUrls(8)));
        list.add(new Discover("", "", "", getImageUrls(7)));
        return list;
    }
}
