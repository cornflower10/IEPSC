package cn.cornflower.com.huan.ui.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.adapter.TaskListAdapter;
import cn.cornflower.com.huan.common.Constants;
import cn.cornflower.com.huan.entity.Task;
import cn.cornflower.com.huan.ui.activity.OnlineScoringActivity;

/**
 * Created by Administrator on 2016/4/5.
 */
public class TaskFragment extends Fragment {
    @InjectView(R.id.lv_task)
    ListView lvTask;
    private List<Task> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finish_task, container, false);
        ButterKnife.inject(this, view);
        initData();

        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(getActivity(),OnlineScoringActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initData() {
      Bundle bundle  = getArguments();
        if(null!= bundle){
            list = bundle.getParcelableArrayList(Constants.TASKLIST);
        }
        lvTask.setAdapter(new TaskListAdapter(getActivity(),R.layout.task_list_item,list));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
