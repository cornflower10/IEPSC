package cn.cornflower.com.huan.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiejingbao on 2016/4/11.
 */
public class Task implements Parcelable {
    private String dateTime;
    private String arriveTime;
    private String title;
    private String context;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

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

    public Task() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dateTime);
        dest.writeString(this.arriveTime);
        dest.writeString(this.title);
        dest.writeString(this.context);
        dest.writeByte(isChecked ? (byte) 1 : (byte) 0);
    }

    protected Task(Parcel in) {
        this.dateTime = in.readString();
        this.arriveTime = in.readString();
        this.title = in.readString();
        this.context = in.readString();
        this.isChecked = in.readByte() != 0;
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
