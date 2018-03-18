package com.zjnu.newspro.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.zjnu.newspro.R;
import com.zjnu.newspro.model.Model;
import com.zjnu.newspro.utils.BitmapUtils;
import com.zjnu.newspro.utils.Constant;

public class UserSettingActivity extends Activity implements View.OnClickListener{
    private RelativeLayout title;
    private ImageView laBackUser1;
    private ImageView userHeadAvatar1;
    private ImageView userHeadHeadphotoUpdate;
    private TextView userHxid;
    private TextView teHxid1;
    private RelativeLayout rlNickname;
    private TextView userNickname;
    private TextView teNick;
    private ImageView icRightNick;
    private RelativeLayout rlSex;
    private TextView textView3;
    private TextView tvSex;
    private ImageView icRightSex;
    private RelativeLayout rlSig;
    private TextView textView4;
    private TextView tvSig;
    private ImageView icRightSig;
    private RelativeLayout rlLoc;
    private TextView tvLoc;
    private TextView tvLoc2;
    private ImageView icRightLoc;
    private RelativeLayout rlIntro;
    private TextView tvIntro;
    private TextView tvIntro2;
    private ImageView icRightIntro;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-03-17 23:47:37 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        title = (RelativeLayout)findViewById( R.id.title );
        laBackUser1 = (ImageView)findViewById( R.id.la_back_user1 );
        userHeadAvatar1 = (ImageView)findViewById( R.id.user_head_avatar1 );
        userHeadHeadphotoUpdate = (ImageView)findViewById( R.id.user_head_headphoto_update );
        userHxid = (TextView)findViewById( R.id.user_hxid );
        teHxid1 = (TextView)findViewById( R.id.te_hxid1 );
        rlNickname = (RelativeLayout)findViewById( R.id.rl_nickname );
        userNickname = (TextView)findViewById( R.id.user_nickname );
        teNick = (TextView)findViewById( R.id.te_nick );
        icRightNick = (ImageView)findViewById( R.id.ic_right_nick );
        rlSex = (RelativeLayout)findViewById( R.id.rl_sex );
        textView3 = (TextView)findViewById( R.id.textView3 );
        tvSex = (TextView)findViewById( R.id.tv_sex );
        icRightSex = (ImageView)findViewById( R.id.ic_right_sex );
        rlSig = (RelativeLayout)findViewById( R.id.rl_sig );
        textView4 = (TextView)findViewById( R.id.textView4 );
        tvSig = (TextView)findViewById( R.id.tv_sig );
        icRightSig = (ImageView)findViewById( R.id.ic_right_sig );
        rlLoc = (RelativeLayout)findViewById( R.id.rl_loc );
        tvLoc = (TextView)findViewById( R.id.tv_loc );
        tvLoc2 = (TextView)findViewById( R.id.tv_loc2 );
        icRightLoc = (ImageView)findViewById( R.id.ic_right_loc );
        rlIntro = (RelativeLayout)findViewById( R.id.rl_intro );
        tvIntro = (TextView)findViewById( R.id.tv_intro );
        tvIntro2 = (TextView)findViewById( R.id.tv_intro2 );
        icRightIntro = (ImageView)findViewById( R.id.ic_right_intro );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        findViews();
        initData();
    }

    private void initData() {
        teHxid1.setText(Model.getInstance().getGlobalUser().getUserId() + "");

        teNick.setText(Model.getInstance().getGlobalUser().getUserName());
        tvSex.setText("15558680172");
        tvSig.setText(Model.getInstance().getGlobalUser().getEmail());

        Picasso.with(UserSettingActivity.this)
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
                }).into(userHeadAvatar1);

        tvLoc2.setText(Model.getInstance().getGlobalUser().getLocation());
        tvIntro2.setText(Model.getInstance().getGlobalUser().getIntroduction());

    }

    @Override
    public void onClick(View view) {

    }
}
