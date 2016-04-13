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
import cn.cornflower.com.huan.entity.RecoveryAmount;
import cn.cornflower.com.huan.view.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by xiejingbao on 2016/4/12.
 */
public class StickyListViewAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private List<RecoveryAmount> listListRa ;
    private LayoutInflater layoutInflater;
    private Context context;

    public StickyListViewAdapter(Context context, List<RecoveryAmount> listListRa) {
        this.listListRa = listListRa;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        ViewHolderHeader viewHolderHeader = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.recovery_amount_item, parent, false);
            viewHolderHeader =new ViewHolderHeader(convertView);
            convertView.setTag(viewHolderHeader);
        }else
            viewHolderHeader = (ViewHolderHeader) convertView.getTag();

        viewHolderHeader.tvTime.setText(listListRa.get(position).getMonthDateTime());
        viewHolderHeader.tvAmount.setText(listListRa.get(position).getMonthAmount());

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return listListRa.size();
    }

    @Override
    public Object getItem(int position) {
        return listListRa.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View childView, ViewGroup parent) {

        ViewHolderChild viewHolderChild =null;

        if (childView == null) {
            childView = layoutInflater.inflate(R.layout.recovery_amount_item, parent, false);
            viewHolderChild =new ViewHolderChild(childView);
            childView.setTag(viewHolderChild);
        }else
            viewHolderChild = (ViewHolderChild) childView.getTag();
        viewHolderChild.tvTimeChild.setText("");

        return childView;
    }


    static class ViewHolderHeader {
        @InjectView(R.id.tv_time)
        TextView tvTime;
        @InjectView(R.id.tv_amount)
        TextView tvAmount;

        ViewHolderHeader(View view) {
            ButterKnife.inject(this, view);
        }
    }

    static class ViewHolderChild {
        @InjectView(R.id.tv_time_child)
        TextView tvTimeChild;
        @InjectView(R.id.tv_amount_child)
        TextView tvAmountChild;

        ViewHolderChild(View view) {
            ButterKnife.inject(this, view);
        }
    }

    class ViewHolder {
        @InjectView(R.id.tv_time_child)
        TextView tvTimeChild;
        @InjectView(R.id.tv_amount_child)
        TextView tvAmountChild;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
