package com.hxe.hxeplatform.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hxe.hxeplatform.ui.fragment.AttentionFragment;
import com.hxe.hxeplatform.ui.fragment.HotFragment;
import com.hxe.hxeplatform.ui.fragment.VideoHotFragment;
import com.hxe.hxeplatform.ui.fragment.VideoNearbyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wangcaiwen
 * Time:2017/11/27.
 * Description:
 */

public class VideoFragmentPageAdapter extends FragmentPagerAdapter{

    private List<String> item;
    private Context context;

    public VideoFragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.item = new ArrayList<>();
        this.context = context;
        item.add("热门");
        item.add("附近");
    }

    public VideoFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
            fragment = new VideoHotFragment();
                break;

            case 1:
            fragment = new VideoNearbyFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return item.get(position);
    }
}
