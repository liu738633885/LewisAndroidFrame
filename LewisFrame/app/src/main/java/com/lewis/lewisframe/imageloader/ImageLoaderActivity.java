package com.lewis.lewisframe.imageloader;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.lewis.lewisframe.BuildConfig;
import com.lewis.lewisframe.R;
import com.lewis.lewisframe.activity.HomeActivity;
import com.squareup.picasso.Picasso;

import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ImageLoaderActivity extends AppCompatActivity {
    public static final String TAG = ImageLoaderActivity.class.getSimpleName();
    private ImageView imvGlide, imvPicasso, imvImageLoader, imvFresco;
    String url_jpg = "http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg";
    int disk_jpg = R.mipmap.big;
    //String url_gif = "http://img4.imgtn.bdimg.com/it/u=1971774567,3919821869&fm=21&gp=0.jpg";
    String url_gif = "http://img0m.pconline.com.cn/pconline/1303/20/3221727_7f49c60615bfa8e1853834a2ef9bb9f2fdf85c067dccd_275_200.gif";
    String disk_gif = "http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg";
    Object img;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);
        initView();


        try {
            ApplicationInfo appInfo = this.getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String msg = appInfo.metaData.getString("UMENG_CHANNEL");
            Toast.makeText(getApplicationContext(), " msg == " + msg, Toast.LENGTH_SHORT).show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void initView() {
        imvGlide = (ImageView) findViewById(R.id.imvGlide);
        imvPicasso = (ImageView) findViewById(R.id.imvPicasso);
        imvImageLoader = (ImageView) findViewById(R.id.imvImageLoader);
        imvFresco = (ImageView) findViewById(R.id.imvFresco);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case 0:
                        img = url_jpg;
                        break;
                    case 1:
                        img = disk_jpg;
                        break;
                    case 2:
                        img = url_gif;
                        break;
                    case 3:
                        img = disk_gif;
                        break;
                }
            }
        });
    }

    public void Glide(View view) {
        /*DrawableRequestBuilder<Integer> thumbnailRequest = Glide
                .with(this)
                .load(R.mipmap.big);
        Glide.with(ImageLoaderActivity.this)
                .load(url_jpg).bitmapTransform(new RoundedCornersTransformation(this, 30, 0, RoundedCornersTransformation.CornerType.ALL)).thumbnail(thumbnailRequest)
                .bitmapTransform(new GrayscaleTransformation(this)).into(imvGlide);*/
        // HomeActivity.start(this,"dd",90);

        Toast.makeText(getApplicationContext(),"boolean"+BuildConfig.LOG_DEBUG,Toast.LENGTH_LONG).show();

        Glide.with(ImageLoaderActivity.this)
                .load("file:///android_asset/run.gif").placeholder(R.mipmap.ic_launcher).into(imvGlide);
    }

    public void Picasso(View view) {
        Picasso.with(this)
                .load((String) img)
                .into(imvPicasso);
    }

    public void ImageLoader(View view) {

    }

    public void Fresco(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
