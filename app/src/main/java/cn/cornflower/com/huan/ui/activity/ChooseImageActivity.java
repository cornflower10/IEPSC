package cn.cornflower.com.huan.ui.activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.entity.ImageEntity;
import cn.cornflower.com.huan.view.imageloader.bean.ImageFloder;
import cn.cornflower.com.huan.view.imageloader.imageloader.ListImageDirPopupWindow;
import cn.cornflower.com.huan.view.imageloader.imageloader.MyAdapter;

public class ChooseImageActivity extends BaseActivity {
    @InjectView(R.id.bt_pic)
    Button btPic;
    private HashMap<String, List<ImageEntity>> mGruopMap = new HashMap<String, List<ImageEntity>>();

    @InjectView(R.id.gv)
    GridView gv;
    private MyAdapter chooseImageAdapter;
    private List<ImageEntity> listImage;

    private ProgressDialog mProgressDialog;
    private final static int SCAN_OK = 1;
    /**
     * 存储文件夹中的图片数量
     */
    private int mPicsSize;
    /**
     * 图片数量最多的文件夹
     */
    private File mImgDir;
    /**
     * 所有的图片
     */
    private List<ImageEntity> mImgs = new ArrayList<>();
    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<String>();
    int totalCount = 0;

    int mScreenHeight;
    int mPicWidth;
    int pos;//第一次位置
    /**
     * 扫描拿到所有的图片文件夹
     */
    private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();
    private ListImageDirPopupWindow mListImageDirPopupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);
        ButterKnife.inject(this);
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
        mPicWidth = (outMetrics.widthPixels/3)-4;
        getImages();
    }

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
     */
    private void getImages() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }
        // 显示进度条
        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

        new Thread(new Runnable() {
            @Override
            public void run() {

                String firstImage = null;

                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = ChooseImageActivity.this
                        .getContentResolver();

                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);

                Log.e("TAG", mCursor.getCount() + "");
                while (mCursor.moveToNext()) {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));

                    Log.e("TAG", path);
                    // 拿到第一张图片的路径
                    if (firstImage == null)
                        firstImage = path;
                    // 获取该图片的父路径名
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null)
                        continue;
                    String dirPath = parentFile.getAbsolutePath();
                    ImageFloder imageFloder = null;
                    // 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
                    if (mDirPaths.contains(dirPath)) {
                        continue;
                    } else {
                        mDirPaths.add(dirPath);
                        // 初始化imageFloder
                        imageFloder = new ImageFloder();
                        imageFloder.setDir(dirPath);
                        imageFloder.setFirstImagePath(path);
                    }
                    if(parentFile.list()==null)
                        continue;
                    int picSize = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            if (filename.endsWith(".jpg")
                                    || filename.endsWith(".png")
                                    || filename.endsWith(".jpeg"))
                                return true;
                            return false;
                        }
                    }).length;
                    totalCount += picSize;

                    imageFloder.setCount(picSize);
                    mImageFloders.add(imageFloder);

                    if (picSize > mPicsSize) {
                        mPicsSize = picSize;
                        mImgDir = parentFile;
                    }
                }
                mCursor.close();

                // 扫描完成，辅助的HashSet也就可以释放内存了
                mDirPaths = null;

                // 通知Handler扫描图片完成
                mHandler.sendEmptyMessage(SCAN_OK);

            }
        }).start();

    }


    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SCAN_OK:
                    //关闭进度条
                    mProgressDialog.dismiss();
                    data2View();
                    initListDirPopupWindw();
                    break;
            }
        }

    };


    /**
     * 为View绑定数据
     */
    private void data2View() {
        if (mImgDir == null) {
            Toast.makeText(getApplicationContext(), "擦，一张图片没扫描到",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        List<String> lStr = Arrays.asList(mImgDir.list());


        for (String str : lStr) {
            ImageEntity image = new ImageEntity();
            image.setUrl(str);
            mImgs.add(image);
        }

        /**
         * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
         */
        chooseImageAdapter = new MyAdapter(ChooseImageActivity.this, mImgs,
                R.layout.choose_image_item, mImgDir.getAbsolutePath());
        chooseImageAdapter.setWidth(mPicWidth);
        gv.setAdapter(chooseImageAdapter);
//        mImageCount.setText(totalCount + "张");
        btPic.setText(mImgDir.getName());
    }

    ;

    /**
     * 初始化展示文件夹的popupWindw
     */
    private void initListDirPopupWindw() {
        mListImageDirPopupWindow = new ListImageDirPopupWindow(
                ViewGroup.LayoutParams.MATCH_PARENT, (int) (mScreenHeight * 0.7),
                mImageFloders, LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.popwindow_choose_pic, null),mImgDir.getName());
        mListImageDirPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        // 设置选择文件夹的回调
//        mListImageDirPopupWindow.setIsSelected();
        mListImageDirPopupWindow.setOnImageDirSelected(new ListImageDirPopupWindow.OnImageDirSelected() {
            @Override
            public void selected(ImageFloder floder) {
                mListImageDirPopupWindow.setSelectKey(floder.getName());

                mImgDir = new File(floder.getDir());
                List<String> list = Arrays.asList(mImgDir.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String filename) {
                        if (filename.endsWith(".jpg") || filename.endsWith(".png")
                                || filename.endsWith(".jpeg"))
                            return true;
                        return false;
                    }
                }));
                mImgs.clear();
                for (String str : list) {
                    ImageEntity image = new ImageEntity();
                    image.setUrl(str);
                    mImgs.add(image);
                }

                /**
                 * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
                 */
                chooseImageAdapter = new MyAdapter(ChooseImageActivity.this, mImgs,
                        R.layout.choose_image_item, mImgDir.getAbsolutePath());
                chooseImageAdapter.setWidth(mPicWidth);
                gv.setAdapter(chooseImageAdapter);
                // mAdapter.notifyDataSetChanged();
//                mImageCount.setText(floder.getCount() + "张");
                btPic.setText(mImgDir.getName().replace("/",""));
                mListImageDirPopupWindow.dismiss();
            }
        });
    }


    @OnClick(R.id.bt_pic)
    public void onClick() {

        mListImageDirPopupWindow
                .setAnimationStyle(R.style.anim_popup_dir);
        mListImageDirPopupWindow.showAsDropDown(btPic, 0, 0);

        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = .3f;
        getWindow().setAttributes(lp);
    }
}
