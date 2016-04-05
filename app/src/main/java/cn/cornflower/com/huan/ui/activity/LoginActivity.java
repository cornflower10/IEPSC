package cn.cornflower.com.huan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.view.ClearEditText;


public class LoginActivity extends BaseActivity {
    @InjectView(R.id.et_name)
    ClearEditText etName;
    @InjectView(R.id.et_passwd)
    ClearEditText etPasswd;
    @InjectView(R.id.btn_login)
    AppCompatButton btnLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_login);
        ButterKnife.inject(this);
        initData();
        setListener();
    }


    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.login));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 可以点击
     */
    private void canClick() {
        btnLogin.setAlpha(1f);
        btnLogin.setClickable(true);
    }

    /**
     * 不可以点击
     */
    private void unClick() {

        btnLogin.setAlpha(0.5f);
        btnLogin.setClickable(false);
    }


    protected void setListener() {
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if ((!TextUtils.isEmpty(editable.toString())) && (!TextUtils.isEmpty(etPasswd.getText()))) {
                    canClick();
                } else
                    unClick();

            }
        });

        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (etName.getText().toString().trim().length() < 15) {
                        etName.setError("手机号码有误");
                    }
                }
            }
        });

        etPasswd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if ((!TextUtils.isEmpty(editable.toString())) && (!TextUtils.isEmpty(etName.getText()))) {
                    canClick();
                } else
                    unClick();

            }
        });

//        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//                PreferencesUtils.putSharePre(LoginActivity.this, Constants.REMEMBER_PASSWD, b);
//            }
//        });


    }

    protected void initData() {
        unClick();
        setToolbar();


//        if (PreferencesUtils.getSharePreBoolean(this, Constants.REMEMBER_PASSWD)) {
//            cb.setChecked(true);
//        } else
//            cb.setChecked(false);
//        String name = PreferencesUtils.getSharePreStr(this, Constants.NAME);
//        String passwd = PreferencesUtils.getSharePreStr(this, Constants.PASSWD);
//        if (!TextUtils.isEmpty(name)) {
//            etName.setText(name);
//        }
//
//        if (!TextUtils.isEmpty(passwd)) {
//            etPasswd.setText(passwd);
//        }
    }

    /**
     * 记住用户名和密码
     */

    private void rememberPasswd() {

        //默认记住用户名
//        PreferencesUtils.putSharePre(LoginActivity.this,
//                Constants.NAME, et_name.getText().toString().trim());
//        if (cb.isChecked()) {
//            PreferencesUtils.putSharePre(LoginActivity.this,
//                    Constants.PASSWD, et_passwd.getText().toString().trim());
//        } else
//            PreferencesUtils.putSharePre(LoginActivity.this,
//                    Constants.PASSWD, "");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick({R.id.tv_forget_passwd, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_passwd:
                Intent intent =new Intent(LoginActivity.this,ForgetPasswdActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                break;
        }
    }
}
