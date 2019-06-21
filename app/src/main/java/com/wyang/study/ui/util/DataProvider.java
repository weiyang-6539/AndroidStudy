package com.wyang.study.ui.util;

import com.wyang.study.bean.Channel;
import com.wyang.study.bean.SectionItem;
import com.wyang.study.bean.Simple;
import com.wyang.study.ui.fragment_second.ContactsFragment;
import com.wyang.study.ui.fragment_second.DragSortFragment;
import com.wyang.study.ui.fragment_second.WeChatFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by weiyang on 2019/6/19.
 */
public class DataProvider {
    private final static HashMap classMap = new HashMap() {{
        //put(OfficialFragment.class.getSimpleName(), OfficialFragment.class);
        put(DragSortFragment.class.getSimpleName(), DragSortFragment.class);
        put(WeChatFragment.class.getSimpleName(), WeChatFragment.class);
        put(ContactsFragment.class.getSimpleName(), ContactsFragment.class);
    }};
    private final static HashMap titleMap = new HashMap() {{
        put(DragSortFragment.class.getSimpleName(), "RecyclerView之仿今日头条拖拽排序");
        put(WeChatFragment.class.getSimpleName(), "RecyclerView之微信发送朋友圈图片九宫格");
        put(ContactsFragment.class.getSimpleName(), "RecyclerView之微信通讯录");
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

    public static List<Simple> getUnofficialData() {
        List<Simple> list = new ArrayList<>();
        list.add(new Simple("RecyclerView用法一", "实现今日头条拖拽排序", DragSortFragment.class.getSimpleName()));
        list.add(new Simple("RecyclerView用法二", "仿微信发送图片朋友圈九宫格效果", WeChatFragment.class.getSimpleName()));
        list.add(new Simple("RecyclerView用法三", "仿微信通讯录效果", ContactsFragment.class.getSimpleName()));
        return list;
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
