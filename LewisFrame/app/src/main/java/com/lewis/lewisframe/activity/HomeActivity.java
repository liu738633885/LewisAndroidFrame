package com.lewis.lewisframe.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lewis.lewisframe.R;
import com.lewis.lewisframe.recyclerview.RecyclerViewActivity;
import com.lewis.library.utils.PictureUtil;


import java.io.File;
import java.util.ArrayList;

import me.iwf.photopicker.PhotoPickerActivity;
import me.iwf.photopicker.utils.PhotoPickerIntent;


public class HomeActivity extends BaseActivity implements View.OnClickListener {


    private Button btn_recyclerView;
    public final static int REQUEST_CODE = 1;
    private ImageView imv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_recyclerView = (Button) findViewById(R.id.btn_recyclerView);
        btn_recyclerView.setOnClickListener(this);
        findViewById(R.id.btn_test).setOnClickListener(this);
        imv = (ImageView) findViewById(R.id.imv);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_recyclerView:
                startActivity(new Intent(HomeActivity.this, RecyclerViewActivity.class));
                break;
            case R.id.btn_test:
                /*PhotoPickerIntent intent = new PhotoPickerIntent(HomeActivity.this);
                intent.setPhotoCount(9);
                intent.setShowCamera(true);
                intent.setShowGif(true);
                startActivityForResult(intent, REQUEST_CODE);*/



            /*    //拍照
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 6);*/

                //选择相册
              /*  if (Build.VERSION.SDK_INT < 19) {*/
                 /*   Intent intent = new Intent();
                    intent.setType("image*//*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,6);*/
             /*   } else {*/
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 6);
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
            case REQUEST_CODE:
                if (data != null) {
                    ArrayList<String> photos =
                            data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                    Log.e("返回的照片" +
                            "", photos.toString());
                /*Glide.with(this)
                        .load(Uri.fromFile(new File(photos.get(0))))
                        .centerCrop()
                        .thumbnail(0.1f)
                        .placeholder(me.iwf.photopicker.R.drawable.ic_photo_black_48dp)
                        .error(me.iwf.photopicker.R.drawable.ic_broken_image_black_48dp)
                        .into(imv);*/
                    // crop(Uri.fromFile(new File(photos.get(0))));
                    Bitmap b = PictureUtil.imageZoom(BitmapFactory.decodeFile(photos.get(0)));
                    //Bitmap b=BitmapFactory.decodeFile(photos.get(0));
                    imv.setImageBitmap(b);
                }
                break;
            case 6:
                if (data != null) {
                    Log.e("data", data.toString());

                }
                break;
            default:
                break;
        }

    }

    int cropcode = 4;

    private void crop(Uri imageUri) {
        /*Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePhotoIntent .putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(takePhotoIntent , cropcode);*/
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        intent.putExtra("crop", "false");
        //裁剪框比例
        intent.putExtra("aspectX", 2);
        intent.putExtra("aspectY", 1);
        //图片输出大小
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //不启用人脸识别
        intent.putExtra("noFaceDetection", false);
        startActivityForResult(intent, cropcode);
    }
}
