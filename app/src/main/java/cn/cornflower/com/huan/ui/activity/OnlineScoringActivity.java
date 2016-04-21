package cn.cornflower.com.huan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cornflower.com.huan.R;

public class OnlineScoringActivity extends BaseActivity {

    @InjectView(R.id.bt_choose)
    Button btChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_scoring);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.bt_choose)
    public void onClick() {
        Intent intent =new Intent(this,ChooseImageActivity.class);
        startActivity(intent);
    }
}
