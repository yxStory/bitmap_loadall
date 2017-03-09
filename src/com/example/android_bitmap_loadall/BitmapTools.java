package com.example.android_bitmap_loadall;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapTools {

	public BitmapTools() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ����ͼƬ
	 * @param byte[] data  ������Դ
	 * @param resId   ͼƬ��id
	 * @param reqWidth   ���ͼƬ�Ŀ��
	 * @param reqHeight  ���ͼƬ�ĸ߶�
	 * @return
	 */
	public static Bitmap decodeBitmap(byte[] data,
			int resId,int reqWidth,int reqHeight){
		//���ý���ʱ�Ĳ���
		BitmapFactory.Options options=new BitmapFactory.Options();
		//����Ϊtrue����������洢�ռ䣬ֻ���ظߺͿ�
		options.inJustDecodeBounds=true;
		//BitmapFactory.decodeByteArray(data, 0, data.length, options);
		//����calculateBitmap����������ͼƬ��ѹ������
		options.inSampleSize=calculateBitmap(options, reqWidth, reqHeight);
		options.inJustDecodeBounds=false;//��ʼ����ͼƬ
		return BitmapFactory.decodeByteArray(data, 0, data.length, options);
	}
	
	/**
	 * ����ͼƬ��ѹ������
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateBitmap(BitmapFactory.Options options,
			int reqWidth,int reqHeight){
		//�õ�ͼƬ��ԭʼ����
		int imageWidth=options.outWidth;
		int imageHeight=options.outHeight;
		int inSimpleSize=1;//ѹ������
		//���ͼƬ��ԭʼ��͸ߴ������ͼƬ�Ŀ��ߣ�����ѹ�������������ظ�ֵ
		if(imageWidth>reqWidth||imageHeight>reqHeight){
			final int widthRatio=Math.round((float)imageWidth
					/(float)reqWidth);
			final int heightRatio=Math.round((float)imageHeight
					/(float)reqHeight);
			//ѹ������ȡ��С���Ǹ�ѹ��ֵ
			inSimpleSize=widthRatio>heightRatio?heightRatio:widthRatio;
		}
		return inSimpleSize;
	}
}
