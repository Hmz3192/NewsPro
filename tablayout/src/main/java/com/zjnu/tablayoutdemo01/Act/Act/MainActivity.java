package com.zjnu.tablayoutdemo01.Act.Act;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zjnu.R;
import com.zjnu.tablayoutdemo01.Act.Adapter.MyAdapter;
import com.zjnu.tablayoutdemo01.Act.fragment.FragEconomy;
import com.zjnu.tablayoutdemo01.Act.fragment.FragFun;
import com.zjnu.tablayoutdemo01.Act.fragment.FragInternet;
import com.zjnu.tablayoutdemo01.Act.fragment.FragRecommend;
import com.zjnu.tablayoutdemo01.Act.fragment.FragSport;

import java.util.ArrayList;
import java.util.List;

/**
 * 需求：TabLayout的简单使用
 *  确定布局---找到控件--设置adapter--
 *  1 initView()--
 *  2 initAdapter
 *  3 initTablayout
 *  4 initViewpager*/
public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> tabs;
    private List<Fragment> fragments;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initAdapter();
        initTabLayout();
        initViewPager();
    }

    private void initViewPager() {
        /**注意这2行代码的顺序：viewpaper要先设置adapter，才可以让 tablayout绑定
         *  否则报错：viewpager没有setAdapter()*/
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

    private void initData() {
        tabs = new ArrayList<>();
        tabs.add("推荐");
        tabs.add("娱乐");
        tabs.add("体育");
        tabs.add("互联网");
        tabs.add("经济");

        fragments = new ArrayList<>();
        fragments.add(new FragRecommend());
        fragments.add(new FragFun());
        fragments.add(new FragSport());
        fragments.add(new FragInternet());
        fragments.add(new FragEconomy());
    }

    private void initTabLayout() {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(tabs.get(0)));/**注意创建TAB对象：tabLayout.newTab()*/
        tabLayout.addTab(tabLayout.newTab().setText(tabs.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tabs.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(tabs.get(3)));
        tabLayout.addTab(tabLayout.newTab().setText(tabs.get(4)));
    }

    private void initAdapter() {
        adapter = new MyAdapter(getSupportFragmentManager(),tabs,fragments);
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);/**如果不提示导包可以Clean Project*/
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }
}
