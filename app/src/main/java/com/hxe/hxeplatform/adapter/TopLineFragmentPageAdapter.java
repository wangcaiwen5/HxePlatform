package com.hxe.hxeplatform.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hxe.hxeplatform.ui.fragment.AttentionFragment;
import com.hxe.hxeplatform.ui.fragment.HotFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wangcaiwen
 * Time:2017/11/27.
 * Description:
 */

public class TopLineFragmentPageAdapter  extends FragmentPagerAdapter{

    private List<String> item;
    private Context context;

    public TopLineFragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.item = new ArrayList<>();
        this.context = context;
        item.add("热门");
        item.add("关注");
    }

    public TopLineFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
            fragment = new HotFragment();
                break;

            case 1:
            fragment = new AttentionFragment();
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
