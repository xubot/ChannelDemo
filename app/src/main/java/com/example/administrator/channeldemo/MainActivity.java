package com.example.administrator.channeldemo;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.administrator.channeldemo.Bean.MyChannel;
import com.trs.channellib.channel.channel.helper.ChannelDataHelepr;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChannelDataHelepr.ChannelDataRefreshListenter{

    private ChannelDataHelepr<MyChannel> dataHelepr;
    private List<MyChannel> myChannelsList=new ArrayList<>();
    private List<MyChannel> myChannelList=new ArrayList<>();
    private ViewPager viewPager;
    private int needShowPosition=-1;
    private TitleFragmentAdapter titleFragmentAdapter;
    private ImageView switch_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout= (TabLayout) findViewById(R.id.tl);
        viewPager = (ViewPager) findViewById(R.id.vp);
        switch_view = (ImageView) findViewById(R.id.iv);
        setData();
        dataHelepr = new ChannelDataHelepr(this, this, findViewById(R.id.iv));
        titleFragmentAdapter = new TitleFragmentAdapter(getSupportFragmentManager(), myChannelList);
        viewPager.setAdapter(titleFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        //设置标题的显示模式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void setData() {
        for(int i=0;i<5;i++)
        {
            Fragment fragment = new Fragment();
            if(i<2)
            {
                MyChannel myChannel = new MyChannel(i+"",1,1,1,fragment);
                myChannelsList.add(myChannel);
            }
            else {
                MyChannel myChannel = new MyChannel(i+"",0,0,1,fragment);
                myChannelsList.add(myChannel);
            }
        }
        dataHelepr.setSwitchView(switch_view);
        loadData();
    }

    //此方法为刷新数据的方法，只有在频道发生变化的时候才会触发
    @Override
    public void updateData() {
        loadData();
    }
    //此方法为，点击频道中的item时触发，可根据是否有更新选择，ViewPager切换的时机
    @Override
    public void onChannelSeleted(boolean update, int posisiton) {
        //如果频道没有改变，则立即调整，否则记录下需要调整的position，在数据更新后调整
        if(!update) {
            viewPager.setCurrentItem(posisiton);
        }else {
            needShowPosition=posisiton;
        }
    }
    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //过滤数据，如果有新的频道会自动订阅并保存到数据库。
                final List<MyChannel> showChannels = dataHelepr.getShowChannels(myChannelsList);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myChannelList.clear();
                        myChannelList.addAll(showChannels);
                        titleFragmentAdapter.notifyDataSetChanged();
                        if(needShowPosition!=-1){
                            viewPager.setCurrentItem(needShowPosition);
                            needShowPosition=-1;
                        }
                    }
                });

            }
        }).start();
    }
}
