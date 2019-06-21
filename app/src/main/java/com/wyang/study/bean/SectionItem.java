package com.wyang.study.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by weiyang on 2019/6/20.
 */
public class SectionItem extends SectionEntity<Channel> {
    public SectionItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionItem(Channel channel) {
        super(channel);
    }
}
