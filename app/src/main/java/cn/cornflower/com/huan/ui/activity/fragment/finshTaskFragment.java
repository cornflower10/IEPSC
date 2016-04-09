package cn.cornflower.com.huan.ui.activity.fragment;

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
import cn.cornflower.com.huan.adapter.TaskAdapter;

/**
 * Created by Administrator on 2016/4/5.
 */
public class FinshTaskFragment extends Fragment {
    @InjectView(R.id.lv_task)
    ListView lvTask;
    private List<String> list;
//   public FinshTaskFragment (String type,){
//
//   }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finish_task, container, false);
        ButterKnife.inject(this, view);
        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        initData();
        return view;
    }

    private void initData() {

        for (int i=0;i<10;i++){
            list.add(i+"");
        }

        lvTask.setAdapter(new TaskAdapter(getActivity(),list));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
