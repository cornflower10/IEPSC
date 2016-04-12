package cn.cornflower.com.huan.ui.activity;

import android.os.Bundle;
import android.os.Parcelable;
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
import cn.cornflower.com.huan.common.Constants;
import cn.cornflower.com.huan.entity.Task;
import cn.cornflower.com.huan.ui.activity.fragment.FinshTaskFragment;

public class TaskActivity extends BaseActivity {
    @InjectView(R.id.tb)
    TabLayout tb;
    @InjectView(R.id.vp)
    ViewPager vp;
    private FragmentTaskAdatpter fragmentAdapter;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ButterKnife.inject(this);
         initData();
    }

    @SuppressWarnings("WrongConstant")
    private void initData() {


        List<Fragment> lf = new ArrayList<>();

        for (int i = 0; i<2 ;i++){
            FinshTaskFragment finshTaskFragment = new FinshTaskFragment();
            Bundle bundle =new Bundle();
            List<Task> taskList =new ArrayList<>();
            for (int j = 0; j<9 ;j++){
                Task task = new Task();
                task.setDateTime("2016-04-12 14:2"+j);
                task.setArriveTime("3"+j);
                task.setTitle("昆山市花桥镇"+j);
                task.setContext("昆山市花桥镇XXXX餐厅需要服务XXXXX请尽快处理"+j);
                taskList.add(task);
            }
            bundle.putParcelableArrayList(Constants.TASKLIST, (ArrayList<? extends Parcelable>) taskList);
            finshTaskFragment.setArguments(bundle);
            lf.add(finshTaskFragment);

        }
        List<String> listTitle = new ArrayList<>();
        listTitle.add("未完成");
        listTitle.add("已完成");

        tb.setTabMode(TabLayout.MODE_FIXED);
        tb.addTab(tb.newTab().setText(listTitle.get(0)));
        tb.addTab(tb.newTab().setText(listTitle.get(1)));
        fragmentAdapter  = new FragmentTaskAdatpter(getSupportFragmentManager(),lf,listTitle);
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
