package com.example.administrator.channeldemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trs.channellib.channel.channel.helper.OnDragVHListener;

/**
 * Created by Administrator on 2017/3/28.
 */

public class Fragment_data extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);
        return view;
    }
}
