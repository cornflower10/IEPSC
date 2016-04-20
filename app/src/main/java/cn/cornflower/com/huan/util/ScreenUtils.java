package cn.cornflower.com.huan.util;



import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

public class ScreenUtils {

	/**
	 * 屏幕高
	 * 
	 * @return
	 */
	public static int getScreenHigh(Activity activity) {
		WindowManager windowManager = activity.getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		int height = display.getHeight();
		return height;
	}

	/**
	 * 屏幕宽
	 * 
	 * @return
	 */
	public static int getScreenWidth(Activity activity) {
		DisplayMetrics metric = new DisplayMetrics(); 
		WindowManager windowManager = activity.getWindowManager();
		 windowManager.getDefaultDisplay().getMetrics(metric);
		int width = metric.widthPixels;
		return width;
	}

	/**
	 * 获得状态栏的高度
	 *
	 * @param context
	 * @return
	 */
	public static int getStatusHeight(Context context)
	{

		int statusHeight = -1;
		try
		{
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return statusHeight;
	}



	/**
	 *  px转化dp
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static float px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().densityDpi;
//		WriteLog.WriteLogCatInfo("densityDpi"+scale);
//		WriteLog.WriteLogCatInfo("density"+context.getResources().getDisplayMetrics().density);
		return ((pxValue * 160) / scale + 0.5f);

	}
	
	/**
	 * px转化为sp
	 * @param context
	 * @param pxValue
	 * @return
	 */
	
	  public static int px2sp(Context context, float pxValue) { 
          final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
          return (int) (pxValue / fontScale + 0.5f); 
      } 
	  
	  /**
	   * 图片根据屏幕适应
	   * @param context
	   * @param activity
	   * @param id The resource id of the image data
	   * @return
	   */
	
     public static Bitmap getChangeImage(Context context,Activity activity,int id){
    	 BitmapFactory.Options options = new BitmapFactory.Options();  
    	 options.inPreferredConfig = Bitmap.Config.RGB_565;   
    	 options.inPurgeable = true;  
    	 options.inInputShareable = true;  
	         //获取资源图片  
	       InputStream is = context.getResources().openRawResource(id);  
	       Bitmap bt= BitmapFactory.decodeStream(is,null,options); 
//    	 InputStream is=context.getResources().openRawResource(id) ;
    	
//			Bitmap bt=BitmapFactory.decodeResource(context.getResources(),id);
		
			options.inJustDecodeBounds=false;

			int screenWidth=getScreenWidth( activity);
    	 int height = options.outHeight * screenWidth / options.outWidth;

    	   options.outWidth = screenWidth;
    	   options.outHeight = height;

 //   	   options.inSampleSize=1;
    	   
			//图片宽高
			int ivWidth=bt.getWidth();
			int ivHeight=bt.getHeight();
			//屏幕的宽高
			int screenHigh=getScreenHigh( activity);
			float scaleX=0;
			float scaleY=0;
			if(screenWidth>=px2dp(context, ivWidth)){
				scaleX=screenWidth/px2dp(context, ivWidth);
				 scaleY=screenHigh/px2dp(context, ivHeight);	
			}else{
				scaleX=px2dp(context, ivWidth)/screenWidth;
				 scaleY=px2dp(context, ivHeight)/screenHigh;
			}
			 
//			options.out=(int) scaleX;
//			options.outHeight=(int) (scaleX*ivWidth);
//			options.outWidth=(int) (scaleX*ivHeight);
			
			Matrix matrix = new Matrix(); 
			matrix.setScale(scaleX, scaleX);
			
			Bitmap btm=BitmapFactory.decodeStream(is, null, options);
			
//			Bitmap newBitmap = Bitmap.createBitmap(bt, 0, 0, ivWidth,ivHeight, matrix, true); 
//			Drawable newBitmapDrawable = new BitmapDrawable(newBitmap); 
			
			
//			 if(!newBitmap.isRecycled() ){
//				 newBitmap.recycle() ;  //回收图片所占的内存
//		         System.gc() ; //提醒系统及时回收
//		}
			return btm;
		} 
	
     /**
      * 根据屏幕得到放大图片的长和宽
      * @param context
      * @param activity
      * @param id
      * @return
      */
      
     public static int[] getChangeSize(Context context,Activity activity,int id){
    	 
    	 BitmapFactory.Options options = new BitmapFactory.Options();  
    	 options.inPreferredConfig = Bitmap.Config.RGB_565;   
    	 options.inPurgeable = true;  
    	 options.inInputShareable = true;  
	         //获取资源图片  
	       InputStream is = context.getResources().openRawResource(id);  
	       Bitmap bt= BitmapFactory.decodeStream(is,null,options); 
//    	 InputStream is=context.getResources().openRawResource(id) ;
    	
//			Bitmap bt=BitmapFactory.decodeResource(context.getResources(),id);
		
			options.inJustDecodeBounds=false;

			int screenWidth=getScreenWidth( activity);
    	 int height = options.outHeight * screenWidth / options.outWidth;
		 
    	 int[] size={screenWidth,height};
    	 
    	 return size;
     }
     
     
     /**
      * 最省内存
      * @param context
      * @param resId
      * @return
      */
     public static  Bitmap readBitMap(Context context, int resId){  
    	 BitmapFactory.Options opt = new BitmapFactory.Options();  
	       opt.inPreferredConfig = Bitmap.Config.RGB_565;   
	       opt.inPurgeable = true;  
	       opt.inInputShareable = true;  
	         //获取资源图片  
	       InputStream is = context.getResources().openRawResource(resId);  
	          return BitmapFactory.decodeStream(is,null,opt);  
    	   }
     
     public static int calculateInSampleSize(BitmapFactory.Options options, 
    	        int reqWidth, int reqHeight) { 
    	    // 源图片的高度和宽度 
    	    final int height = options.outHeight; 
    	    final int width = options.outWidth; 
    	    int inSampleSize = 1; 
    	    if (height > reqHeight || width > reqWidth) { 
    	        // 计算出实际宽高和目标宽高的比率 
    	        final int heightRatio = Math.round((float) height / (float) reqHeight); 
    	        final int widthRatio = Math.round((float) width / (float) reqWidth); 
    	        // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高 
    	        // 一定都会大于等于目标的宽和高。 
    	        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio; 
    	    } 
    	    return inSampleSize; }
     
     
     /**
 	 * 转换成sp
 	 * @param size
 	 * @return
 	 */
 	public static int sizeTsp(int size,Context context){
 		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size, context.getResources().getDisplayMetrics());
 	}
 	
 	/**
 	 * 转换成Dp
 	 * @param size
 	 * @return
 	 */
 	public static int sizeTdp(int size,Context context){
 		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size,context. getResources().getDisplayMetrics());
 	}
}
