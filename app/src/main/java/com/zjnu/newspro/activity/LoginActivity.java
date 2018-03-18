package com.zjnu.newspro.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zjnu.newspro.R;
import com.zjnu.newspro.model.Model;
import com.zjnu.newspro.model.bean.ArticleUserPojo;
import com.zjnu.newspro.utils.Constant;
import com.zjnu.newspro.utils.SpUtils;

public class LoginActivity extends Activity implements View.OnClickListener {

    private TextView tvMore;
    private EditText etLoginName;
    private EditText etLoginPwd;
    private Button btGo;
    private Dialog mCameraDialog;
    private int choice = 1;
    private ArticleUserPojo articleUserPojo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 初始化控件
        findViews();
        // 初始化监听
        initListener();
    }

    private void initListener() {

    }

    private void login() {
        // 1 获取输入的用户名和密码
        final String loginName = etLoginName.getText().toString();
        final String loginPwd = etLoginPwd.getText().toString();

        // 2 校验输入的用户名和密码
        if(TextUtils.isEmpty(loginName) || TextUtils.isEmpty(loginPwd)) {
            Toast.makeText(LoginActivity.this, "输入的用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        final String url = Constant.LOGINURL;
        // 3 登录逻辑处理
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    OkHttpUtils
                            .post()
                            .url(url)
                            .addParams("name", loginName)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(okhttp3.Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    articleUserPojo = JSON.parseObject(response, ArticleUserPojo.class);
//                                    Log.e("Save--articleUser:---", articleUserPojo.toString());
                                    // 保存自己信息到本地数据库
                                            //全局变量
                                    Model.getInstance().loginSuccess(articleUserPojo.getUser());
                                            //存入数据库
//                                            Model.getInstance().getDbManager().getArticleDao().saveListArticles(articleUserPojo.getArticles());
                                    Model.getInstance().getDbManager().getUserDao().saveUser(articleUserPojo.getUser());
                                    SpUtils.getInstance().save("logined", true);
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                            });

                }
        });
    }


    private void findViews() {
        tvMore = (TextView) findViewById(R.id.tv_more);
        etLoginName = (EditText) findViewById(R.id.et_login_name);
        etLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        btGo = (Button) findViewById(R.id.bt_go);

        tvMore.setOnClickListener(this);
        btGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btGo) {
            // Handle clicks for btGo
            login();
        } else if (v == tvMore) {
            showBottomDia();
        }
    }

    private void showBottomDia() {
        mCameraDialog = new Dialog(LoginActivity.this, R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(LoginActivity.this).inflate(
                R.layout.bottom_dialog_login, null);
        root.findViewById(R.id.btn_register).setOnClickListener(btnlistener);
        root.findViewById(R.id.btn_forgetPass).setOnClickListener(btnlistener);
        root.findViewById(R.id.btn_cancel).setOnClickListener(btnlistener);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//      lp.alpha = 9f; // 透明度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    private View.OnClickListener btnlistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // 注册
                case R.id.btn_register:
                    if (mCameraDialog != null) {
                        mCameraDialog.dismiss();
                    }

                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    break;
//                忘记密码
                case R.id.btn_forgetPass:
                    if (mCameraDialog != null) {
                        mCameraDialog.dismiss();
                    }
                    break;
                // 取消
                case R.id.btn_cancel:
                    if (mCameraDialog != null) {
                        mCameraDialog.dismiss();
                    }
                    break;
            }
        }
    };

}
