package com.zjnu.newspro.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * User--Hu mingzhi on 2018/3/16.
 * Created by ThinKPad
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    List<String> tabs;
    List<Fragment> fragments;

    public FragmentAdapter(FragmentManager fm, List<String> tabs, List<Fragment> fragments) {
        super(fm);
        this.tabs=tabs;
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}

