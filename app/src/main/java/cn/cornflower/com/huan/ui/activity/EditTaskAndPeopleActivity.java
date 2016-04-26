package cn.cornflower.com.huan.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.adapter.AddPeopleAdapter;
import cn.cornflower.com.huan.adapter.TaskAdapter;
import cn.cornflower.com.huan.common.Constants;
import cn.cornflower.com.huan.entity.Task;
import cn.cornflower.com.huan.util.ListViewUtil;
import cn.cornflower.com.huan.util.ScreenUtils;
import cn.cornflower.com.huan.view.sortlistview.SortModel;
import cn.cornflower.com.huan.view.sweeplist.SwipeMenu;
import cn.cornflower.com.huan.view.sweeplist.SwipeMenuCreator;
import cn.cornflower.com.huan.view.sweeplist.SwipeMenuItem;
import cn.cornflower.com.huan.view.sweeplist.SwipeMenuListView;

public class EditTaskAndPeopleActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.iv_add_task)
    ImageView ivAddTask;
    @InjectView(R.id.rl_task)
    RelativeLayout rlTask;
    @InjectView(R.id.slv_task)
    SwipeMenuListView slvTask;
    @InjectView(R.id.iv_add_people)
    ImageView ivAddPeople;
    @InjectView(R.id.rl_people)
    RelativeLayout rlPeople;
    @InjectView(R.id.slv_people)
    SwipeMenuListView slvPeople;
    @InjectView(R.id.line_v)
    View lineV;
    @InjectView(R.id.iv_tag)
    ImageView ivTag;
    @InjectView(R.id.iv_tag_people)
    ImageView ivTagPeople;


    private List<Task> checkTaskList;

    private List<SortModel> checkPeopleList;

    private AddPeopleAdapter addPeopleAdapter;

    private TaskAdapter taskAdatpter;

    private final static int TASKTAG = 0;
    private final static int PEOPLETAG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_and_people);
        ButterKnife.inject(this);
        setToolbar();
        initData();
        initListener();
    }

    private void initListener() {

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // Create different menus depending on the view type
                switch (menu.getViewType()) {
                    case 0:
                        createMenu1(menu);
                        break;

                }
            }

            private void createMenu1(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(255, 0,
                        0)));
                item1.setWidth(ScreenUtils.sizeTdp(90, EditTaskAndPeopleActivity.this));
                item1.setIcon(R.mipmap.delete_icon);
                menu.addMenuItem(item1);
            }
        };
        // set creator
        slvTask.setMenuCreator(creator);

        slvTask.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu,
                                           int index) {
                //	System.out.println("====================>"+index);
                if (index == 0) {
                    checkTaskList.remove(position);
                    taskAdatpter.notifyDataSetChanged();
                    ListViewUtil.setListViewHeight(slvTask);
                }
                return true;
            }
        });

        slvPeople.setMenuCreator(creator);

        slvPeople.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu,
                                           int index) {
                //	System.out.println("====================>"+index);
                if (index == 0) {
                    checkPeopleList.remove(position);
                    addPeopleAdapter.notifyDataSetChanged();
                    ListViewUtil.setListViewHeight(slvPeople);
                }
                return true;
            }
        });
    }


    @SuppressWarnings("WrongConstant")
    private void initData() {

//        checkTaskList = getIntent().getParcelableArrayListExtra(Constants.TASKLIST);
//        checkPeopleList = getIntent().getParcelableArrayListExtra(Constants.PEOPLELIST);
        checkTaskList = new ArrayList<>();
        checkPeopleList = new ArrayList<>();

        taskAdatpter = new TaskAdapter(this, checkTaskList);
        slvTask.setAdapter(taskAdatpter);
//        ListViewUtil.setListViewHeight(slvTask);

        addPeopleAdapter = new AddPeopleAdapter(this, R.layout.people_item, checkPeopleList);
        slvPeople.setAdapter(addPeopleAdapter);
//        ListViewUtil.setListViewHeight(slvPeople);
    }

    private void updateTask(Intent intent) {
        List<Task> listTask = intent.getParcelableArrayListExtra(Constants.TASKLIST);
        if(listTask.size()>0){
            slvTask.setVisibility(View.VISIBLE);
            ivTag.setImageDrawable(getResources().
                    getDrawable(R.drawable.ic_expand_less_green_24dp));
        }
        checkTaskList.addAll(listTask);
        taskAdatpter.notifyDataSetChanged();
        ListViewUtil.setListViewHeight(slvTask);
    }

    private void updatePeople(Intent intent) {
//        checkPeopleList.clear();
        List<SortModel> list = intent.getParcelableArrayListExtra(Constants.PEOPLELIST);
        if(list.size()>0){
            slvPeople.setVisibility(View.VISIBLE);
            ivTagPeople.setImageDrawable(getResources().
                    getDrawable(R.drawable.ic_expand_less_green_24dp));
        }
        checkPeopleList.addAll(list);
        addPeopleAdapter.notifyDataSetChanged();
        ListViewUtil.setListViewHeight(slvPeople);

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
        } else if (id == R.id.send) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.iv_add_task, R.id.rl_task, R.id.iv_add_people, R.id.rl_people})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_task:
                Intent intent = new Intent(this, SendTaskActivity.class);
                startActivityForResult(intent, TASKTAG);
                break;
            case R.id.rl_task:
                if (slvTask.getVisibility() == View.GONE) {
                    slvTask.setVisibility(View.VISIBLE);
                    ivTag.setImageDrawable(getResources().
                            getDrawable(R.drawable.ic_expand_less_green_24dp));
                    if (checkTaskList.size() > 0) {
                        lineV.setVisibility(View.VISIBLE);
                    }

                } else {
                    ivTag.setImageDrawable(getResources()
                            .getDrawable(R.drawable.ic_expand_more_green_24dp));
                    lineV.setVisibility(View.GONE);
                    slvTask.setVisibility(View.GONE);
                }

                break;
            case R.id.iv_add_people:
                Intent intent_people = new Intent(this, PeopleActivity.class);
                startActivityForResult(intent_people, PEOPLETAG);
                break;
            case R.id.rl_people:
                if (slvPeople.getVisibility() == View.GONE) {
                    slvPeople.setVisibility(View.VISIBLE);
                    ivTagPeople.setImageDrawable(getResources().
                            getDrawable(R.drawable.ic_expand_less_green_24dp));
                } else
                {
                    slvPeople.setVisibility(View.GONE);
                    ivTagPeople.setImageDrawable(getResources()
                            .getDrawable(R.drawable.ic_expand_more_green_24dp));
                }

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TASKTAG) {
            if (resultCode == RESULT_OK) {
                updateTask(data);
            }
        } else if (requestCode == PEOPLETAG) {
            updatePeople(data);
        }
    }

}
