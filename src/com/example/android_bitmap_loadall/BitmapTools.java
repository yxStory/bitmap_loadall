package com.example.android_bitmap_loadall;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapTools {

	public BitmapTools() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 解码图片
	 * @param byte[] data  数组资源
	 * @param resId   图片的id
	 * @param reqWidth   输出图片的宽度
	 * @param reqHeight  输出图片的高度
	 * @return
	 */
	public static Bitmap decodeBitmap(byte[] data,
			int resId,int reqWidth,int reqHeight){
		//设置解码时的参数
		BitmapFactory.Options options=new BitmapFactory.Options();
		//设置为true，避免申请存储空间，只返回高和宽
		options.inJustDecodeBounds=true;
		//BitmapFactory.decodeByteArray(data, 0, data.length, options);
		//调用calculateBitmap方法，计算图片的压缩比例
		options.inSampleSize=calculateBitmap(options, reqWidth, reqHeight);
		options.inJustDecodeBounds=false;//开始解码图片
		return BitmapFactory.decodeByteArray(data, 0, data.length, options);
	}
	
	/**
	 * 计算图片的压缩比例
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateBitmap(BitmapFactory.Options options,
			int reqWidth,int reqHeight){
		//得到图片的原始宽、高
		int imageWidth=options.outWidth;
		int imageHeight=options.outHeight;
		int inSimpleSize=1;//压缩比例
		//如果图片的原始宽和高大于输出图片的宽、高，计算压缩比例，并返回该值
		if(imageWidth>reqWidth||imageHeight>reqHeight){
			final int widthRatio=Math.round((float)imageWidth
					/(float)reqWidth);
			final int heightRatio=Math.round((float)imageHeight
					/(float)reqHeight);
			//压缩比例取较小的那个压缩值
			inSimpleSize=widthRatio>heightRatio?heightRatio:widthRatio;
		}
		return inSimpleSize;
	}
}
