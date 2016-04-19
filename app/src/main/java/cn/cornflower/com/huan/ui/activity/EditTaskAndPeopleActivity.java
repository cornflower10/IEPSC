package cn.cornflower.com.huan.ui.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.adapter.FragmentTaskAdatpter;
import cn.cornflower.com.huan.common.Constants;
import cn.cornflower.com.huan.entity.Task;
import cn.cornflower.com.huan.ui.activity.fragment.FinshTaskFragment;
import cn.cornflower.com.huan.ui.activity.fragment.PeolpeFragment;
import cn.cornflower.com.huan.view.sortlistview.SortModel;

public class EditTaskAndPeopleActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tb)
    TabLayout tb;
    @InjectView(R.id.vp)
    ViewPager vp;
    FragmentTaskAdatpter fragmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_and_people);
        ButterKnife.inject(this);
        setToolbar();
        initData();
    }


    @SuppressWarnings("WrongConstant")
    private void initData() {
        List<Fragment> lf = new ArrayList<>();
        FinshTaskFragment finshTaskFragment = new FinshTaskFragment();
        Bundle bundle = new Bundle();
        List<Task> taskList = new ArrayList<>();
        for (int j = 0; j < 9; j++) {
            Task task = new Task();
            task.setDateTime("2016-04-12 14:2" + j);
            task.setArriveTime("3" + j);
            task.setTitle("昆山市花桥镇" + j);
            task.setContext("昆山市花桥镇XXXX餐厅需要服务XXXXX请尽快处理" + j);
            taskList.add(task);
        }
        bundle.putParcelableArrayList(Constants.TASKLIST, (ArrayList<? extends Parcelable>) taskList);
        finshTaskFragment.setArguments(bundle);
        lf.add(finshTaskFragment);

        PeolpeFragment peolpeFragment = new PeolpeFragment();
        Bundle bundlePeople = new Bundle();
        List<SortModel> sortModels = new ArrayList<>();
        for (int j = 0; j < 9; j++) {
            SortModel sortModel = new SortModel();
            sortModel.setName("送哥哥"+j);
            sortModels.add(sortModel);
        }
        bundlePeople.putParcelableArrayList(Constants.PEOPLELIST, (ArrayList<? extends Parcelable>) sortModels);
        peolpeFragment.setArguments(bundlePeople);
        lf.add(peolpeFragment);


        List<String> listTitle = new ArrayList<>();
        listTitle.add("任务");
        listTitle.add("联系人");

        tb.setTabMode(TabLayout.MODE_FIXED);
        tb.addTab(tb.newTab().setText(listTitle.get(0)));
        tb.addTab(tb.newTab().setText(listTitle.get(1)));
        fragmentAdapter = new FragmentTaskAdatpter(getSupportFragmentManager(), lf, listTitle);

        vp.setAdapter(fragmentAdapter);

        tb.setupWithViewPager(vp);

    }

    private void setToolbar() {
        toolbar.setTitle(getResources().getString(R.string.detail));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_people, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
       else if (id == R.id.add) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
