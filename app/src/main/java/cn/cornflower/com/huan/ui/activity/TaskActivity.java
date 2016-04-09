package cn.cornflower.com.huan.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.adapter.FragmentTaskAdatpter;
import cn.cornflower.com.huan.ui.activity.fragment.FinshTaskFragment;

public class TaskActivity extends BaseActivity {

    @InjectView(R.id.tb)
    TabLayout tb;
    @InjectView(R.id.vp)
    ViewPager vp;
    private FragmentTaskAdatpter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ButterKnife.inject(this);
         initData();
    }

    private void initData() {

        List<Fragment> lf = new ArrayList<>();
        for (int i = 0; i<2 ;i++){
            FinshTaskFragment finshTaskFragment = new FinshTaskFragment();
            lf.add(finshTaskFragment);

        }

        tb.addTab(tb.newTab().setText("未完成"));
        tb.addTab(tb.newTab().setText("已完成"));
        fragmentAdapter  = new FragmentTaskAdatpter(getSupportFragmentManager(),lf);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setAdapter(fragmentAdapter);

        tb.setupWithViewPager(vp);

    }
}
