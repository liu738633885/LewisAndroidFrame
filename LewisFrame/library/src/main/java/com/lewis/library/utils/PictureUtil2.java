package com.lewis.library.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by lewis on 16/2/26.
 */
public class PictureUtil2 {
    /**
     * 默认最大文件的大小(kb)
     */
    private static final int IMG_MAX_SIZE = 1024;
    /**
     * 默认最大文件的大小(kb)
     */
    private static final int IMG_MAX_WIDTH = 1080;
    /**
     * 默认最大文件的大小(kb)
     */
    private static final int IMG_MAX_HEIGHT = 1920;
    /**
     * 默认图片存储路径
     */
    private static final String SAVE_PIC_PATH = Environment.getExternalStorageState().equalsIgnoreCase(Environment
            .MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//保存到SD卡
    public static final String SAVE_REAL_PATH = SAVE_PIC_PATH + "/lewis/savePic";//保存的确切位置

    public static void compressBmpToFile(Bitmap bmp, String fileName) {
        compressBmpToFile(bmp, IMG_MAX_SIZE, fileName);
    }

    /**
     * 将图片存储为file文件
     *
     * @param bmp
     * @param maxSize
     */
    public static boolean compressBmpToFile(Bitmap bmp, int maxSize, String fileName) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 80;//个人喜欢从80开始,
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        Log.e("第一次", "数组大小" + baos.toByteArray().length / 1024);
        while (baos.toByteArray().length / 1024 > maxSize) {
            baos.reset();
            options -= 10;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
            Log.e("第次" + options, "数组大小" + baos.toByteArray().length / 1024);
            if (options == 10) {
                break;
            }
        }
        try {
            boolean isSave = false;
            File dirFile = new File(SAVE_REAL_PATH);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            File myCaptureFile = new File(SAVE_REAL_PATH, fileName);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            out.write(baos.toByteArray());
            out.flush();
            out.close();
            return isSave;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        /*try {
            FileOutputStream fos = new FileOutputStream(SAVE_REAL_PATH+"/"+fileName+".jpg");
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public static Bitmap getimage(String srcPath) {
        return getimage(srcPath, IMG_MAX_WIDTH, IMG_MAX_HEIGHT);
    }

    /**
     * 根据路径获取图片限制大小并压缩）
     *
     * @param srcPath （
     * @param ww
     * @param hh
     * @return
     */
    public static Bitmap getimage(String srcPath, int ww, int hh) {
        //先判断图片的旋转角度
        boolean isRotate = false;
        if (readPictureDegree(srcPath) == 90 || readPictureDegree(srcPath) == 270) {
            isRotate = true;
        }
        //第一次粗略的压缩
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, options);// 此时返回bm为空
        float inSampleSize = calculateInSampleSize(options.outWidth, options.outHeight, ww, hh, isRotate);

        options.inSampleSize = Math.round(inSampleSize);// 设置缩放比例   只能是2的幂数
        options.inJustDecodeBounds = false;
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, options);

        Log.e("bitmap", bitmap.getWidth() + ":" + bitmap.getHeight());
        //第二次精确的压缩  并且旋转
        Matrix matrix = new Matrix();
        float ss = calculateInSampleSize(bitmap.getWidth(), bitmap.getHeight(), ww, hh, isRotate);
        matrix.postScale(1 / ss, 1 / ss);
        matrix.postRotate(readPictureDegree(srcPath));//旋转
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        Log.e("最后的bitmap", bitmap.getWidth() + ":" + bitmap.getHeight());
        return bitmap;
    }

    // 计算 BitmapFactpry 的 inSimpleSize的值的方法 google提供
    public int calculateInSampleSize(BitmapFactory.Options options,
                                     int reqWidth, int reqHeight) {
        if (reqWidth == 0 || reqHeight == 0) {
            return 1;
        }


        // 获取图片原生的宽和高
        final int height = options.outHeight;
        final int width = options.outWidth;
        Log.d("想要压缩的宽高", "origin, w= " + width + " h=" + height);
        int inSampleSize = 1;

        // 如果原生的宽高大于请求的宽高,那么将原生的宽和高都置为原来的一半
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // 主要计算逻辑
            // Calculate the largest inSampleSize value that is a power of 2 and
            // keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        Log.d("sampleSize", "sampleSize:" + inSampleSize);
        return inSampleSize;
    }

    private static float calculateInSampleSize(int w, int h, int ww, int hh, boolean isRotate) {
        float ss = 1f;
        if (isRotate) {
            int temp = ww;
            ww = hh;
            hh = temp;
        }
        if (h > hh || w > ww) {
            float heightRatio = (float) h
                    / (float) hh;
            float widthRatio = (float) w / (float) ww;
            ss = heightRatio > widthRatio ? heightRatio : widthRatio;
        }

        return ss;
    }

    ;


    /**
     * 质量压缩方法
     * (不推荐)
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        Log.e("第一次", "数组大小" + baos.size());
        while (baos.toByteArray().length / 1024 > 200) { // 循环判断如果压缩后图片是否大于200kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
            Log.e("第次" + options, "数组大小" + baos.toByteArray().length / 1024);
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        Log.e("size", bitmap.getByteCount() + "daxiao");
        return bitmap;
    }


    /**
     * 图片按比例大小压缩方法
     *
     * @param image （根据Bitmap图片压缩）
     * @return
     */
    public static Bitmap compressScale(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        // 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
        if (baos.toByteArray().length / 1024 > 1024) {
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 80, baos);// 这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        Log.i("PictureUtil2", w + "---------------" + h);
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        // float hh = 800f;// 这里设置高度为800f
        // float ww = 480f;// 这里设置宽度为480f
        float hh = 512f;
        float ww = 512f;
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) { // 如果高度高的话根据高度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be; // 设置缩放比例
        // newOpts.inPreferredConfig = Config.RGB_565;//降低图片从ARGB888到RGB565

        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);

        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩

        //return bitmap;
    }

    /**
     * 判断照片角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            //ExifInterface获取图片信息的类
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转照片
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int degress) {
        if (bitmap != null) {
            // 根据旋转角度，生成旋转矩阵
            Matrix m = new Matrix();
            m.postRotate(degress);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;
    }
}

