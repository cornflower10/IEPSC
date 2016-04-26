package cn.cornflower.com.huan.view.imageloader.imageloader;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.view.imageloader.bean.ImageFloder;
import cn.cornflower.com.huan.view.imageloader.utils.BasePopupWindowForListView;
import cn.cornflower.com.huan.view.imageloader.utils.ViewHolder;

public class ListImageDirPopupWindow extends BasePopupWindowForListView<ImageFloder>
{
	private ListView mListDir;
	private String  selectKey;

	public String getSelectKey() {
		return selectKey;
	}

	public void setSelectKey(String selectKey) {
		this.selectKey = selectKey;
	}

	public ListImageDirPopupWindow(int width, int height,
								   List<ImageFloder> datas, View convertView,String selectKey)
	{
		super(convertView, width, height, true, datas, selectKey);


	}

	@Override
	public void initViews()
	{

		mListDir = (ListView)findViewById(R.id.lv);
		mListDir.setAdapter(new CommonAdapter<ImageFloder>(context, mDatas,
				R.layout.choose_image_dir_item)

		{
			@Override
			public void convert(ViewHolder helper, ImageFloder item)
			{
				helper.setText(R.id.tv_dir_name, item.getName().replace("/",""));
				helper.setImageByUrl(R.id.iv,
						item.getFirstImagePath());
				helper.setText(R.id.tv_pic_count, item.getCount() + "张");
				ImageView imageView = helper.getView(R.id.iv_check);
				if(item.getName().replace("/","").equals(selectKey.replace("/",""))){
					imageView.setImageResource(R.drawable.ic_radio_button_checked_green_24dp);
				}else
					imageView.setImageResource(0);

			}
		});
	}



	public interface OnImageDirSelected
	{
		void selected(ImageFloder floder);
	}

	private OnImageDirSelected mImageDirSelected;

	public void setOnImageDirSelected(OnImageDirSelected mImageDirSelected)
	{
		this.mImageDirSelected = mImageDirSelected;
	}

	@Override
	public void initEvents()
	{
		mListDir.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{

				if (mImageDirSelected != null)
				{
					mImageDirSelected.selected(mDatas.get(position));
					selectKey =mDatas.get(position).getName();

				}
			}
		});
	}

	@Override
	public void init()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void beforeInitWeNeedSomeParams(Object... params)
	{
		selectKey = params[0].toString();
	}

}
