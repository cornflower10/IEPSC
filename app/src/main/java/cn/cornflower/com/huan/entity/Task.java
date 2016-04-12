package cn.cornflower.com.huan.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiejingbao on 2016/4/11.
 */
public class Task implements Parcelable {

    private String title;
    private String context;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.context);
    }

    public Task() {
    }

    protected Task(Parcel in) {
        this.title = in.readString();
        this.context = in.readString();
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
