package com.example.administrator.channeldemo.Bean;

import android.support.v4.app.Fragment;

import com.trs.channellib.channel.channel.ChannelEntity;

/**
 * Created by Administrator on 2017/3/28.
 */

public class MyChannel implements ChannelEntity.ChannelEntityCreater {

    public int isFix;
    public String title;
    public int channelType;
    public int isSubscrible;
    public Fragment fragment;

    public MyChannel(String title, int isFix, int channelType, int isSubscrible, Fragment fragment) {

        this.title = title;
        this.isFix = isFix;
        this.channelType =channelType;
        this.isSubscrible = isSubscrible;
        this.fragment = fragment;
    }

    @Override
    public String toString() {
        return "MyChannel{" +
                "isFix=" + isFix +
                ", title='" + title + '\'' +
                ", channelType=" + channelType +
                ", isSubscrible=" + isSubscrible +
                ", fragment=" + fragment +
                '}';
    }

    @Override
    public ChannelEntity createChannelEntity() {
        ChannelEntity entity=new ChannelEntity();
        //是否是固定频道
        entity.setFixed(isFix==1);
        //显示的名称
        entity.setName(title);
        return entity;
    }
}
