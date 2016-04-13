package cn.cornflower.com.huan.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiejingbao on 2016/4/13.
 */
public class RecoveryAmount implements Parcelable {
    private String monthDateTime;
    private String monthAmount;
    private String dateTime;
    private String amount ;

    public String getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(String monthAmount) {
        this.monthAmount = monthAmount;
    }

    public String getMonthDateTime() {
        return monthDateTime;
    }

    public void setMonthDateTime(String monthDateTime) {
        this.monthDateTime = monthDateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public RecoveryAmount() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.monthDateTime);
        dest.writeString(this.monthAmount);
        dest.writeString(this.dateTime);
        dest.writeString(this.amount);
    }

    protected RecoveryAmount(Parcel in) {
        this.monthDateTime = in.readString();
        this.monthAmount = in.readString();
        this.dateTime = in.readString();
        this.amount = in.readString();
    }

    public static final Creator<RecoveryAmount> CREATOR = new Creator<RecoveryAmount>() {
        public RecoveryAmount createFromParcel(Parcel source) {
            return new RecoveryAmount(source);
        }

        public RecoveryAmount[] newArray(int size) {
            return new RecoveryAmount[size];
        }
    };
}
