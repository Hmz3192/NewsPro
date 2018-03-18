package com.zjnu.newspro.activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.zjnu.newspro.R;
import com.zjnu.newspro.adapter.FragmentAdapter;
import com.zjnu.newspro.fragment.MenuGuoJiFrgment;
import com.zjnu.newspro.fragment.MenuSheHuiFrgment;
import com.zjnu.newspro.fragment.MenuSiFaFragment;
import com.zjnu.newspro.fragment.MenuYingShiFragment;
import com.zjnu.newspro.model.Model;
import com.zjnu.newspro.utils.BitmapUtils;
import com.zjnu.newspro.utils.Constant;
import com.zjnu.newspro.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> tabs;
    private List<Fragment> fragments;
    private FragmentAdapter adapter;
    private Toolbar toolbar;
    private NavigationView left;
    private View menu;
    private DrawerLayout drawer;
    private boolean isDrawer = false;
    private LinearLayout right;
    private ImageView icon;
    private TextView username, textView;
    private Intent intent = null;
    private EditText etQuery;
    private TextView tvAdd;
    private TextView tvSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        initData();
        initAdapter();
        initTabLayout();
        initViewPager();
    }

    private void initListener() {
        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                isDrawer = true;
                //获取屏幕的宽高
                WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                //设置右面的布局位置  根据左面菜单的right作为右面布局的left   左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
                right.layout(left.getRight(), 0, left.getRight() + display.getWidth(), display.getHeight());
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isDrawer = false;
                if (intent != null) {
                    startActivity(intent);
                    intent = null;
                }

            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });


        etQuery.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("query", etQuery.getText().toString());
                    startActivity(intent);
                    tvAdd.setVisibility(View.VISIBLE);
                    etQuery.setVisibility(View.GONE);
                }
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
        }
        int id = item.getItemId();
        //了解社区
        if (id == R.id.nav_camera) {

//            账号管理
        } else if (id == R.id.nav_gallery) {

            intent = new Intent(this, UserSettingActivity.class);


//            设置
        } else if (id == R.id.nav_slideshow) {
            //           Toast.makeText(this, "nav_slideshow", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, MainActivity.class);


//          退出
        } else if (id == R.id.nav_exit) {
            SpUtils.getInstance().save("logined", false);
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        }
        return true;
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
        tabs.add("社会");
        tabs.add("国际");
        tabs.add("影视");
        tabs.add("司法");

        fragments = new ArrayList<>();
        fragments.add(new MenuSheHuiFrgment());
        fragments.add(new MenuGuoJiFrgment());
        fragments.add(new MenuYingShiFragment());
        fragments.add(new MenuSiFaFragment());
    }

    private void initTabLayout() {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(tabs.get(0)));/**注意创建TAB对象：tabLayout.newTab()*/
        tabLayout.addTab(tabLayout.newTab().setText(tabs.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tabs.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(tabs.get(3)));
    }

    private void initAdapter() {
        adapter = new FragmentAdapter(getSupportFragmentManager(),tabs,fragments);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("头条资讯");
        setSupportActionBar(toolbar);
        tvAdd = (TextView) findViewById(R.id.tv_add);
        etQuery = (EditText) findViewById(R.id.et_query);
        tvSearch = (TextView) findViewById(R.id.tv_search);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAdd.setVisibility(View.INVISIBLE);
                etQuery.setVisibility(View.VISIBLE);
            }
        });



        tabLayout = (TabLayout) findViewById(R.id.tabLayout);/**如果不提示导包可以Clean Project*/
        viewPager = (ViewPager) findViewById(R.id.viewpager);


        left = (NavigationView) findViewById(R.id.nav_view);
        left.setItemTextColor(null);
        left.setItemIconTintList(null);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        right = (LinearLayout) findViewById(R.id.right);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        initMydata(user);
        menu = (View) navigationView.getHeaderView(0);
//      初始化左边的上面三个
        icon = (ImageView) menu.findViewById(R.id.imageView_email);
        username = (TextView) menu.findViewById(R.id.username_email);
        textView = (TextView) menu.findViewById(R.id.textView_email);

        Picasso.with(MainActivity.this)
                .load(Constant.PREURL + "/" + Model.getInstance().getGlobalUser().getAvatar())
                .error(R.drawable.shop_icon)
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap bitmap) {
                        //先对图片进行压缩
//                Bitmap zoom = BitmapUtils.zoom(bitmap, DensityUtil.dip2px(mContext, 62), DensityUtil.dip2px(mContext, 62));
                        Bitmap zoom = BitmapUtils.zoom(bitmap, 70, 70);
                        //对请求回来的Bitmap进行圆形处理
                        Bitmap ciceBitMap = BitmapUtils.circleBitmap(zoom);
                        bitmap.recycle();//必须队更改之前的进行回收
                        return ciceBitMap;
                    }

                    @Override
                    public String key() {
                        return "";
                    }
                }).into(icon);

        username.setText(Model.getInstance().getGlobalUser().getUserName());
        textView.setText(Model.getInstance().getGlobalUser().getEmail());

    }
}
