package cn.cornflower.com.huan.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.List;

import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.entity.ImageEntity;


/**
 * Created by Administrator on 2016/4/22.
 */
public class IEPSCPopwindow extends PopupWindow {

    private Context mContext;

   public  IEPSCPopwindow(Context mContext, List<ImageEntity> imageEntityList){
       this.mContext = mContext;
        initView();
   }

    private void initView() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view =  layoutInflater.inflate(R.layout.popwindow_choose_pic,null);
        ListView listView = (ListView) view.findViewById(R.id.lv);
    }

}
