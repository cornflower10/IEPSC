package cn.cornflower.com.huan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.adapter.TaskSendAdapter;
import cn.cornflower.com.huan.entity.Task;

public class SendTaskActivity extends BaseActivity {

    @InjectView(R.id.lv_send_task)
    ListView lvSendTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_task);
        ButterKnife.inject(this);
        setToolbar();
        initData();
    }

    private void initData() {
        List<Task> taskList =new ArrayList<>();
        for (int j = 0; j<9 ;j++){
            Task task = new Task();
            task.setDateTime("2016-04-12 14:2"+j);
            task.setArriveTime("3"+j);
            task.setTitle("昆山市花桥镇"+j);
            task.setContext("昆山市花桥镇XXXX餐厅需要服务XXXXX请尽快处理"+j);
            taskList.add(task);
        }
        lvSendTask.setAdapter(new TaskSendAdapter(this,taskList));

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.task_check));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        if (id == R.id.action_check_people) {
            Intent intent = new Intent(this, PeopleActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
