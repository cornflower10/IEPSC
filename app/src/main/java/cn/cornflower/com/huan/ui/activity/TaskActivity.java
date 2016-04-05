package cn.cornflower.com.huan.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.cornflower.com.huan.R;

public class TaskActivity extends BaseActivity {

    @InjectView(R.id.tb)
    TabLayout tb;
    @InjectView(R.id.vp)
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ButterKnife.inject(this);
         initData();
    }

    private void initData() {
        tb.addTab(tb.newTab().setText("未完成"));
        tb.addTab(tb.newTab().setText("已完成"));
    }
}
