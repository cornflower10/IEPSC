package cn.cornflower.com.huan.adapter;

import android.content.Context;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.abslistview.CommonAdapter;

import java.util.List;

import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.entity.Task;

/**
 * Created by Administrator on 2016/4/19.
 */
public class TaskListAdapter extends CommonAdapter<Task> {


    public TaskListAdapter(Context context, int layoutId, List<Task> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, Task task) {
      holder.setText(R.id.tv_number,task.getTitle())
              .setText(R.id.tv_time,task.getDateTime());
    }
}
