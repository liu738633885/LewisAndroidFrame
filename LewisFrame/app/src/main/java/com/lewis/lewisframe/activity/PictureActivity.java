package com.lewis.lewisframe.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lewis.lewisframe.R;
import com.lewis.lewisframe.recyclerview.RecyclerViewActivity;
import com.lewis.library.utils.PictureUtil;
import com.lewis.library.utils.PictureUtil2;

import java.io.File;
import java.util.ArrayList;

import me.iwf.photopicker.PhotoPickerActivity;
import me.iwf.photopicker.utils.PhotoPickerIntent;


public class PictureActivity extends BaseActivity {

    /**
     * 调用PhotoPicker框架
     */
    public final static int PHOTO_PICKER = 1;
    /**
     * 调用系统相机
     */
    public static final int OPEN_CAMERA = 2,OPEN_CAMERA2=3;
    private ImageView imv, imv2;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        imv = (ImageView) findViewById(R.id.imv);
        imv2 = (ImageView) findViewById(R.id.imv2);
        textView = (TextView) findViewById(R.id.tv_data);
        textView.setText(Environment.getExternalStorageState() + "-----" + Environment
                .MEDIA_MOUNTED + "-----" + Environment.getExternalStorageDirectory().getAbsolutePath() + "----" + "/mnt/sdcard");
        if (savedInstanceState != null) {
            b = savedInstanceState.getParcelable("bitmap");
            imv.setImageBitmap(b);
        }
        Log.e("生命周期", "onCreate");
        Log.e("getFileDir与getCacheDir", getFilesDir()+"&&"+getCacheDir());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("生命周期", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("生命周期", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("生命周期", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("生命周期", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("生命周期", "onStop");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.e("生命周期", "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        if (b != null) {
            outState.putParcelable("bitmap", b);
        }
        Log.e("生命周期", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    Bitmap b;

    public void myOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_openCamera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, OPEN_CAMERA);
                break;
            case R.id.btn_openCamera2:
                Uri imageUri = Uri.fromFile(new File(PictureUtil2.SAVE_REAL_PATH,"camera2.jpg"));
                Intent camera2Intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                camera2Intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(camera2Intent, OPEN_CAMERA2);
                break;

            case R.id.btn_open_photo_picker:
                PhotoPickerIntent photoPickerIntent = new PhotoPickerIntent(PictureActivity.this);
                photoPickerIntent.setPhotoCount(9);
                photoPickerIntent.setShowCamera(true);
                photoPickerIntent.setShowGif(true);
                startActivityForResult(photoPickerIntent, PHOTO_PICKER);
                break;
            case R.id.btn_openAlbum1:
                Intent intent1 = new Intent();
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent1, 6);
                break;
            case R.id.btn_openAlbum2:
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_PICK);
                intent2.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent2, 6);
                // }

                break;

            default:
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case PHOTO_PICKER:
                if (data == null) {
                    return;
                }

                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                Log.e("返回的照片" +
                        "", photos.toString());
                   /* Glide.with(this)
                            .load(Uri.fromFile(new File(photos.get(0))))
                            .centerCrop()
                            .thumbnail(0.1f)
                            .placeholder(me.iwf.photopicker.R.drawable.ic_photo_black_48dp)
                            .error(me.iwf.photopicker.R.drawable.ic_broken_image_black_48dp)
                            .into(imv);*/

                b = PictureUtil2.getimage(photos.get(0));
                imv.setImageBitmap(b);
                PictureUtil2.compressBmpToFile(b, 500, "temp.jpg");

                break;
            case OPEN_CAMERA:
                if (data == null) {
                    return;
                }

                //下面是获取缩略图的两种方式
               // Bitmap bm = (Bitmap) data.getExtras().get("data");
                Bitmap bm =  data.getParcelableExtra("data");
                PictureUtil2.compressBmpToFile(bm,"temp2.jpg");
                imv.setImageBitmap(bm);
                break;
            case OPEN_CAMERA2:
                //下面是获取缩略图的两种方式
                // Bitmap bm = (Bitmap) data.getExtras().get("data");
                Bitmap bmc2 =  PictureUtil2.getimage(PictureUtil2.SAVE_REAL_PATH+"/camera2.jpg");
                PictureUtil2.compressBmpToFile(bmc2,"temp3.jpg");
                imv.setImageBitmap(bmc2);
                break;
            default:
                break;
        }
        if (data != null) {
            textView.append("\n" + data.toString());
            Log.e("返回的照片" +
                    "", data.toString());
        }

    }

}
