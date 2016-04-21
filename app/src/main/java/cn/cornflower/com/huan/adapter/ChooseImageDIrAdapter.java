package cn.cornflower.com.huan.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.abslistview.CommonAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.entity.ImageEntity;

/**
 * Created by Administrator on 2016/4/19.
 */
public class ChooseImageDIrAdapter extends CommonAdapter<ImageEntity> {


    public ChooseImageDIrAdapter(Context context, int layoutId, List<ImageEntity> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, ImageEntity imageEntity) {

     SimpleDraweeView view = holder.getView(R.id.iv);
          view.setImageBitmap(getLoacalBitmap(imageEntity.getUrl()));

        holder.setChecked(R.id.acb,imageEntity.isChecked());

    }

    /**
     * 加载本地图片
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
