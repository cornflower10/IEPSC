package cn.cornflower.com.huan.adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.abslistview.CommonAdapter;

import java.util.List;

import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.entity.ImageEntity;

/**
 * Created by Administrator on 2016/4/19.
 */
public class ChooseImageAdapter extends CommonAdapter<ImageEntity> {


    public ChooseImageAdapter(Context context, int layoutId, List<ImageEntity> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, ImageEntity imageEntity) {

     SimpleDraweeView view = holder.getView(R.id.iv);
          view.setImageURI(Uri.parse(imageEntity.getUrl()));

        holder.setChecked(R.id.acb,imageEntity.isChecked());

    }
}
