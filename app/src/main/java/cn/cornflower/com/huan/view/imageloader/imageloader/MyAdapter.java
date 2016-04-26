package cn.cornflower.com.huan.view.imageloader.imageloader;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.entity.ImageEntity;
import cn.cornflower.com.huan.view.imageloader.utils.ViewHolder;

public class MyAdapter extends CommonAdapter<ImageEntity>
{

	/**
	 * 文件夹路径
	 */
	private String mDirPath;
	private int width;//图片宽

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public MyAdapter(Context context, List<ImageEntity> mDatas, int itemLayoutId,
					 String dirPath)
	{
		super(context, mDatas, itemLayoutId);
		this.mDirPath = dirPath;
	}


	@Override
	public void convert(ViewHolder helper, final ImageEntity item) {
		//设置no_pic
		helper.setImageBackground(R.id.iv, Color.parseColor("#bb000000"));

		helper.setImageByUrl(R.id.iv, mDirPath + "/" + item.getUrl());
		AppCompatCheckBox checkBox = helper.getView(R.id.acb);

		final ImageView mImageView = helper.getView(R.id.iv);

		RelativeLayout.LayoutParams rl = (RelativeLayout.LayoutParams) mImageView.getLayoutParams();
		rl.width = getWidth();
		rl.height = getWidth();
		mImageView.setLayoutParams(rl);
//		Log.e("mImageViewWidth.....", getWidth()+"....");
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				item.setChecked(b);
				if(b){
					mImageView.setColorFilter(Color.parseColor("#99000000"));
				}else
					mImageView.setColorFilter(Color.parseColor("#44000000"));

			}
		});

		mImageView.setColorFilter(Color.parseColor("#44000000"));
			checkBox.setChecked(item.isChecked());
		if(checkBox.isChecked()){
			mImageView.setColorFilter(Color.parseColor("#99000000"));
		}else
			mImageView.setColorFilter(Color.parseColor("#44000000"));

	}
}
