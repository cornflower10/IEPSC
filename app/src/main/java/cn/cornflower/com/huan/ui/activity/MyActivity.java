package cn.cornflower.com.huan.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cornflower.com.huan.R;

public class MyActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.rl_my)
    RelativeLayout rlMy;
    @InjectView(R.id.rl_passwd)
    RelativeLayout rlPasswd;
    @InjectView(R.id.rl_about)
    RelativeLayout rlAbout;
    @InjectView(R.id.rl_logout)
    RelativeLayout rlLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);
        setToolbar();

    }

    private void setToolbar() {
        toolbar.setTitle(getResources().getString(R.string.my));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @OnClick({R.id.rl_passwd, R.id.rl_about, R.id.rl_logout})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rl_passwd:
                Intent intent1 =new Intent(MyActivity.this,PasswdActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_about:
                Intent intent =new Intent(MyActivity.this,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_logout:

               AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity.this);
                builder.setMessage("确定要退出么？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Intent intent = new Intent(MyActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setCancelable(true);
                builder.create().show();
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
