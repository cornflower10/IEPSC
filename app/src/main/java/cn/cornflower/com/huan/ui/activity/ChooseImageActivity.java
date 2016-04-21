package cn.cornflower.com.huan.ui.activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.widget.GridView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.adapter.ChooseImageAdapter;
import cn.cornflower.com.huan.entity.ImageEntity;

public class ChooseImageActivity extends BaseActivity {
    private HashMap<String, List<ImageEntity>> mGruopMap = new HashMap<String, List<ImageEntity>>();

    @InjectView(R.id.gv)
    GridView gv;
    private ChooseImageAdapter chooseImageAdapter;
    private List<ImageEntity> listImage;

    private ProgressDialog mProgressDialog;
    private final static int SCAN_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);
        ButterKnife.inject(this);

        initData();
    }

    private void initData() {
//        chooseImageAdapter = new ChooseImageAdapter(this,R.layout.choose_image_item,listImage);
//        gv.setAdapter(chooseImageAdapter);

        getImages();



    }

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中
     */
    private void getImages() {
        //显示进度条
        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

        new Thread(new Runnable() {

            @Override
            public void run() {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = getContentResolver();

                //只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);

                if (mCursor == null) {
                    return;
                }

                while (mCursor.moveToNext()) {
                    //获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));

                    //获取该图片的父路径名
                    String parentName = new File(path).getParentFile().getName();


                    //根据父路径名将图片放入到mGruopMap中
                    if (!mGruopMap.containsKey(parentName)) {
                        List<ImageEntity> chileList = new ArrayList<ImageEntity>();
                        ImageEntity image =new ImageEntity();
                        image.setUrl(path);
                        chileList.add(image);
                        mGruopMap.put(parentName, chileList);
                    } else {
                        ImageEntity image =new ImageEntity();
                        image.setUrl(path);
                        mGruopMap.get(parentName).add(image);
                    }
                }

                //通知Handler扫描图片完成
                mHandler.sendEmptyMessage(SCAN_OK);
                mCursor.close();
            }
        }).start();

    }


    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SCAN_OK:
                    //关闭进度条
                    mProgressDialog.dismiss();
                    chooseImageAdapter = new ChooseImageAdapter(
                            ChooseImageActivity.this,R.layout.choose_image_item,
                            mGruopMap.get(subGroupOfImage(mGruopMap).get(2).getFolderName()));
                    gv.setAdapter(chooseImageAdapter);
                    break;
            }
        }

    };


    /**
     * 组装分组界面GridView的数据源，因为我们扫描手机的时候将图片信息放在HashMap中
     * 所以需要遍历HashMap将数据组装成List
     *
     * @param mGruopMap
     * @return
     */
    private List<ImageEntity> subGroupOfImage(HashMap<String, List<ImageEntity>> mGruopMap){
        if(mGruopMap.size() == 0){
            return null;
        }
        List<ImageEntity> list = new ArrayList<ImageEntity>();

        Iterator<Map.Entry<String, List<ImageEntity>>> it = mGruopMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<ImageEntity>> entry = it.next();
            ImageEntity mImageBean = new ImageEntity();
            String key = entry.getKey();
            List<ImageEntity> value = entry.getValue();

            mImageBean.setFolderName(key);
            mImageBean.setImageCounts(value.size());
            mImageBean.setTopImagePath(value.get(0).getUrl());//获取该组的第一张图片

            list.add(mImageBean);
        }

        return list;

    }
}
