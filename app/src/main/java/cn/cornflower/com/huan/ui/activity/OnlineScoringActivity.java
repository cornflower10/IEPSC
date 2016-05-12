package cn.cornflower.com.huan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cornflower.com.huan.R;

public class OnlineScoringActivity extends BaseActivity {

    @InjectView(R.id.bt_choose)
    Button btChoose;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_scoring);
        ButterKnife.inject(this);
        setToolbar();
    }

    @OnClick(R.id.bt_choose)
    public void onClick() {
        Intent intent = new Intent(this, ChooseImageActivity.class);
        startActivity(intent);
    }


    private void setToolbar() {
        toolbar.setTitle(getResources().getString(R.string.task_my));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
