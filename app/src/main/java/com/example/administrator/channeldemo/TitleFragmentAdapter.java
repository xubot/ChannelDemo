package com.example.administrator.channeldemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.channeldemo.Bean.MyChannel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28.
 */

public class TitleFragmentAdapter extends FragmentPagerAdapter{
    List<MyChannel> channels;
    int id=1;
    int p=0;
    Map<String,Integer> IdsMap=new HashMap<>();
    List<String> preIds=new ArrayList<>();
    public TitleFragmentAdapter(FragmentManager fm, List<MyChannel> channels) {
        super(fm);
        this.channels=channels;
    }

    @Override
    public Fragment getItem(int position) {
        p=position;
        return channels.get(position).fragment;
    }

    @Override
    public int getCount() {
        return channels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return channels.get(position).title;
    }

    @Override
    public long getItemId(int position) {
        return IdsMap.get(getPageTitle(position));
    }

    @Override
    public int getItemPosition(Object object) {
        String title = channels.get(p).title;
        int preId = preIds.indexOf(title);
        int newId=-1;
        int i=0;
        int size=getCount();
        for(;i<size;i++){
            if(getPageTitle(i).equals(title)){
                newId=i;
                break;
            }
        }
        if(newId!=-1&&newId==preId){
            return POSITION_UNCHANGED;
        }
        if(newId!=-1){
            return newId;
        }
        return POSITION_NONE;
    }

    @Override
    public void notifyDataSetChanged() {
        for(MyChannel info:channels){
            if(!IdsMap.containsKey(info.title)){
                IdsMap.put(info.title,id++);
            }
        }
        super.notifyDataSetChanged();
        preIds.clear();
        int size=getCount();
        for(int i=0;i<size;i++){
            preIds.add((String) getPageTitle(i));
        }
    }
}
