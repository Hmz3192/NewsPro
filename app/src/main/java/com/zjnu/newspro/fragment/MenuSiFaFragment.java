package com.zjnu.newspro.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zjnu.newspro.R;
import com.zjnu.newspro.adapter.NewsAdapter;
import com.zjnu.newspro.model.Model;
import com.zjnu.newspro.model.bean.ArticleUserPojo;
import com.zjnu.newspro.utils.Constant;

import java.util.List;

/**
 * User--Hu mingzhi on 2018/3/16.
 * Created by ThinKPad
 */

public class MenuSiFaFragment extends BaseFragment {
    private NewsAdapter newsAdapter;
    private PullToRefreshRecyclerView pullToRefreshRV;
    private List<ArticleUserPojo.Article> data;
    private Integer offset = 0;
    private ArticleUserPojo articleUserPojo;

    @Override
    public View initView() {
        View view = View.inflate(mcontext, R.layout.fragment_sifa, null);
        pullToRefreshRV = view.findViewById(R.id.pullToRefreshRV);
        initRecyView();
        return view;
    }

    private void initRecyView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pullToRefreshRV.setLayoutManager(layoutManager);
        //请求数据
        getData();
        //是否开启下拉刷新功能
        pullToRefreshRV.setPullRefreshEnabled(true);
        //是否开启上拉加载功能
        pullToRefreshRV.setLoadingMoreEnabled(true);
        //设置是否显示上次刷新的时间
        pullToRefreshRV.displayLastRefreshTime(true);
        //设置刷新回调
        pullToRefreshRV.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefreshRV.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshRV.setRefreshComplete();
                        //模拟没有数据的情况
//                        adapter.notifyDataSetChanged();
                    }
                }, 2000);

            }

            @Override
            public void onLoadMore() {
                pullToRefreshRV.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshRV.setLoadMoreComplete();
                        //模拟加载数据的情况
//                        offset = offset + 10;
//                        List<ArticleUserPojo.Article> dataNew = Model.getInstance().getDbManager().getArticleDao().getArticlesByKind("司法", String.valueOf(offset));
//                        data.addAll(dataNew);
//                        newsAdapter.notifyDataSetChanged();
                    }
                }, 2000);

            }
        });
        //主动触发下拉刷新操作
//        pullToRefreshRV.onRefresh();
    }

    private void getData() {
        final String url = Constant.SIFA;

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils
                        .post()
                        .url(url)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(okhttp3.Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                articleUserPojo = JSON.parseObject(response, ArticleUserPojo.class);
                                Log.e("Save--articleUser:---", articleUserPojo.toString());
                                //存入数据库
//                                            Model.getInstance().getDbManager().getArticleDao().saveListArticles(articleUserPojo.getArticles());
//                                Model.getInstance().getDbManager().getArticleDao().saveListArticles(articleUserPojo.getArticles());
                                newsAdapter = new NewsAdapter(getActivity(), articleUserPojo.getArticles());
                                pullToRefreshRV.setAdapter(newsAdapter);
                            }
                        });

            }
        });
    }


}
