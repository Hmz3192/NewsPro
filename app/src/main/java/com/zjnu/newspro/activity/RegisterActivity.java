package com.zjnu.newspro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zjnu.newspro.R;

public class RegisterActivity extends Activity implements View.OnClickListener {

    private EditText etUsername;
    private EditText etPassword;
    private EditText etRepeatpassword;
    private EditText etPhone;
    private EditText validateCode;
    private Button getSec;
    private EditText etEmail;
    private Button btRegister;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-03-17 13:40:22 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        etUsername = (EditText)findViewById( R.id.et_username );
        etPassword = (EditText)findViewById( R.id.et_password );
        etRepeatpassword = (EditText)findViewById( R.id.et_repeatpassword );
        etPhone = (EditText)findViewById( R.id.et_phone );
        validateCode = (EditText)findViewById( R.id.validate_code );
        getSec = (Button)findViewById( R.id.get_sec );
        etEmail = (EditText)findViewById( R.id.et_email );
        btRegister = (Button)findViewById( R.id.bt_register );

        getSec.setOnClickListener( this );
        btRegister.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-03-17 13:40:22 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == getSec ) {
            // Handle clicks for getSec
        } else if ( v == btRegister ) {
            // Handle clicks for btRegister
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

}
