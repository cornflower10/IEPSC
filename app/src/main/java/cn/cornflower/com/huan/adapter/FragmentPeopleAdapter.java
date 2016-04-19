package cn.cornflower.com.huan.adapter;

import android.content.Context;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.abslistview.CommonAdapter;

import java.util.List;

import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.view.sortlistview.SortModel;

/**
 * Created by Administrator on 2016/4/19.
 */
public class FragmentPeopleAdapter extends CommonAdapter<SortModel> {
    public FragmentPeopleAdapter(Context context, int layoutId, List<SortModel> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, SortModel sortModel) {
      holder.setText(R.id.tv_name,sortModel.getName());
    }
}
