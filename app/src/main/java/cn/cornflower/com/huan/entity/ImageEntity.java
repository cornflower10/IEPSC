package cn.cornflower.com.huan.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiejingbao on 2016/4/21.
 */
public class ImageEntity implements Parcelable {
    /**
     * 文件夹的第一张图片路径
     */
    private String topImagePath;
    /**
     * 文件夹名
     */
    private String folderName;
    /**
     * 文件夹中的图片数
     */
    private int imageCounts;

    private int id;
    private String url;
    private boolean isChecked;

    public String getTopImagePath() {
        return topImagePath;
    }

    public void setTopImagePath(String topImagePath) {
        this.topImagePath = topImagePath;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getImageCounts() {
        return imageCounts;
    }

    public void setImageCounts(int imageCounts) {
        this.imageCounts = imageCounts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.url);
        dest.writeByte(isChecked ? (byte) 1 : (byte) 0);
    }

    public ImageEntity() {
    }

    protected ImageEntity(Parcel in) {
        this.id = in.readInt();
        this.url = in.readString();
        this.isChecked = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ImageEntity> CREATOR = new Parcelable.Creator<ImageEntity>() {
        @Override
        public ImageEntity createFromParcel(Parcel source) {
            return new ImageEntity(source);
        }

        @Override
        public ImageEntity[] newArray(int size) {
            return new ImageEntity[size];
        }
    };
}
