package com.wyang.study.ui.util;

import com.wyang.study.bean.Channel;
import com.wyang.study.bean.SectionItem;
import com.wyang.study.bean.Simple;
import com.wyang.study.ui.fragment_second.ContactsFragment;
import com.wyang.study.ui.fragment_second.DragSortFragment;
import com.wyang.study.ui.fragment_second.NineGridLayoutFragment;
import com.wyang.study.ui.fragment_second.WeChatFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by weiyang on 2019/6/19.
 */
public class DataProvider {
    private final static HashMap classMap = new HashMap() {{
        //put(MainFragment.class.getSimpleName(), MainFragment.class);
        put(DragSortFragment.class.getSimpleName(), DragSortFragment.class);
        put(WeChatFragment.class.getSimpleName(), WeChatFragment.class);
        put(ContactsFragment.class.getSimpleName(), ContactsFragment.class);
        put(NineGridLayoutFragment.class.getSimpleName(), NineGridLayoutFragment.class);
    }};
    private final static HashMap titleMap = new HashMap() {{
        put(DragSortFragment.class.getSimpleName(), "RecyclerView之仿今日头条拖拽排序");
        put(WeChatFragment.class.getSimpleName(), "RecyclerView之微信发送朋友圈图片九宫格");
        put(ContactsFragment.class.getSimpleName(), "RecyclerView之微信通讯录");
        put(NineGridLayoutFragment.class.getSimpleName(), "图片九宫格显示控件");
    }};

    public static Class<?> getFragmentClass(String className) {
        return (Class<?>) classMap.get(className);
    }

    public static String getTitle(String className) {
        return (String) titleMap.get(className);
    }

    public static List<Simple> getOfficialData() {
        List<Simple> list = new ArrayList<>();
        list.add(new Simple("ViewSwitcher", "暂时不知道用法及效果", ""));
        return list;
    }

    public static List<Simple> getWidgetData() {
        List<Simple> list = new ArrayList<>();
        list.add(new Simple("图片九宫格", "仿微信图片九宫格效果", NineGridLayoutFragment.class.getSimpleName()));
        return list;
    }

    public static List<Simple> getUnofficialData() {
        List<Simple> list = new ArrayList<>();
        list.add(new Simple("RecyclerView用法一", "实现今日头条拖拽排序", DragSortFragment.class.getSimpleName()));
        list.add(new Simple("RecyclerView用法二", "仿微信发送图片朋友圈九宫格效果", WeChatFragment.class.getSimpleName()));
        list.add(new Simple("RecyclerView用法三", "仿微信通讯录效果", ContactsFragment.class.getSimpleName()));
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
}
