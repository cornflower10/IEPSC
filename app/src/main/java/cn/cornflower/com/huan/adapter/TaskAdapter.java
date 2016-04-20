package cn.cornflower.com.huan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.entity.Task;

/**
 * Created by Administrator on 2016/4/1.
 */
public class TaskAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Task> gridItemList;
    private Context context;

    public void setGridItemList(List<Task> gridItemList) {
        this.gridItemList = gridItemList;
    }

    public TaskAdapter(Context context, List<Task> gridItemList) {
        this.context = context;
        this.gridItemList = gridItemList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return gridItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.task_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        } else
            viewHolder = (ViewHolder) view.getTag();
        viewHolder.tvTime.setText(gridItemList.get(i).getDateTime());
        viewHolder.tvArriveTime.setText(gridItemList.get(i).getArriveTime());
        viewHolder.tvTitle.setText(gridItemList.get(i).getTitle());
        viewHolder.tvContext.setText(gridItemList.get(i).getContext());

        return view;
    }


    static class ViewHolder {
        @InjectView(R.id.tv_time)
        TextView tvTime;
        @InjectView(R.id.tv_arriveTime)
        TextView tvArriveTime;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.tv_context)
        TextView tvContext;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
