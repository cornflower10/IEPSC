package cn.cornflower.com.huan.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.entity.MainGridItem;

/**
 * Created by Administrator on 2016/4/1.
 */
public class GridAadapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<MainGridItem> gridItemList;
    private Context context;

    public GridAadapter(Context context, List<MainGridItem> gridItemList) {
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
        ViewHolder viewHolder =null;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.grid_item, viewGroup, false);
            viewHolder= new ViewHolder(view);
            view.setTag(viewHolder);

        }else
        viewHolder =(ViewHolder) view.getTag();

        viewHolder.iv.setImageResource(gridItemList.get(i).getResouce());
        viewHolder.tvTitle.setText(gridItemList.get(i).getName());

        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.iv)
        ImageView iv;
        @InjectView(R.id.tv_title)
        AppCompatTextView tvTitle;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
