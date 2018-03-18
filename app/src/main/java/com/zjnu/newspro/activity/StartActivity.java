package com.zjnu.newspro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.dk.view.patheffect.PathTextView;
import com.zjnu.newspro.R;
import com.zjnu.newspro.model.Model;
import com.zjnu.newspro.model.bean.ArticleUserPojo;
import com.zjnu.newspro.model.db.ArticleUserDBManager;
import com.zjnu.newspro.utils.SpUtils;

public class StartActivity extends Activity {
    private String time;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            // 如果当前activity已经退出，那么我就不处理handler中的消息
            if(isFinishing()) {
                return;
            }
            Intent intent;
            if (!SpUtils.getInstance().getBoolean("entered", false)) {
                //未进入过
                SpUtils.getInstance().save("entered", true);
                intent = new Intent(StartActivity.this, SplashActivity.class);
            }else {
                if (!SpUtils.getInstance().getBoolean("logined", false)) {
                    //未登陆过
                    intent = new Intent(StartActivity.this, LoginActivity.class);
                } else {
                    //最好只用1-1登录
                    ArticleUserDBManager dbManager = new ArticleUserDBManager(getApplication(), "db");
                    ArticleUserPojo.User user = dbManager.getUserDao().getUser("1-1");
                    Model.getInstance().loginSuccess(user);
                    intent = new Intent(StartActivity.this, MainActivity.class);
                }
            }
            startActivity(intent);
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        time = SpUtils.getInstance().getString("time", "0");
//        if (time.equalsIgnoreCase("0")) {
        SpUtils.getInstance().save("time", "1");
        PathTextView mPathTextView = (PathTextView) findViewById(R.id.path);
        mPathTextView.init("NewsPro");

        mPathTextView.setPaintType(PathTextView.Type.SINGLE);
        mPathTextView.setTextColor(R.color.black_deep);
        mPathTextView.setTextSize(88.0f);
        // 发送2s钟的延时消息
        handler.sendMessageDelayed(Message.obtain(), 5000);
        mPathTextView.setTextWeight(13);
        mPathTextView.setDuration(7000);
        mPathTextView.setShadow(10, 0, 0, R.color.black_deep);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 销毁消息
        handler.removeCallbacksAndMessages(null);
    }

}
