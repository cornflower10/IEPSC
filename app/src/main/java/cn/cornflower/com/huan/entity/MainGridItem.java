package cn.cornflower.com.huan.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/4/1.
 */
public class MainGridItem {

    private int type;
    private Drawable resouce;
    private String name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Drawable getResouce() {
        return resouce;
    }

    public void setResouce(Drawable resouce) {
        this.resouce = resouce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
