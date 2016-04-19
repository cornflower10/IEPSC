package cn.cornflower.com.huan.view.sortlistview;

import android.os.Parcel;
import android.os.Parcelable;

public class SortModel implements Parcelable {

	private String name;
	private String sortLetters;
	private boolean isChecked;

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean checked) {
		isChecked = checked;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeString(this.sortLetters);
		dest.writeByte(isChecked ? (byte) 1 : (byte) 0);
	}

	public SortModel() {
	}

	protected SortModel(Parcel in) {
		this.name = in.readString();
		this.sortLetters = in.readString();
		this.isChecked = in.readByte() != 0;
	}

	public static final Parcelable.Creator<SortModel> CREATOR = new Parcelable.Creator<SortModel>() {
		@Override
		public SortModel createFromParcel(Parcel source) {
			return new SortModel(source);
		}

		@Override
		public SortModel[] newArray(int size) {
			return new SortModel[size];
		}
	};
}
