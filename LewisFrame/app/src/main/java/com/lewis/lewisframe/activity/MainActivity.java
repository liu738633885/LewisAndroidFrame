package com.lewis.lewisframe.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lewis.lewisframe.R;
import com.lewis.lewisframe.animation.MaterialDesignAnimation;
import com.lewis.lewisframe.widget.MultiStateView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class MainActivity extends BaseActivity {
    private MultiStateView mMultiStateView;
    private View view1, view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        //setMultiSateView();
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
       /* view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(getWindow().get);
                setViewWidth(squareRed, 500);
                setViewWidth(squareBlue, 500);
                setViewWidth(squareGreen, 500);
                setViewWidth(squareYellow, 500);
            }
        });*/
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads()
                .detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());


        Toast.makeText(getApplicationContext(), getResources().getText(R.string.test).toString(),
                Toast.LENGTH_SHORT).show();
        String url = "http://dfms_n1.zhongsou.com/Login/getUid?username=test789789";
        WebView webView = new WebView(this);
        webView.addJavascriptInterface(new Show(), "show");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.show.show(document.body.innerHTML);");
                super.onPageFinished(view, url);
            }
        });

        //String url="http://www.weather.com.cn/adat/cityinfo/101010100.html";
        //String url="http://www.zhongsou.net/space/interface/ydcp_getLeftTreeData
        // .php?type=android&ver=3.8&kw=餐厅大管家_cpsy&new=1";
/*

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                });
                Log.e("sucess", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                Log.e("error", error.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast
                                .LENGTH_LONG).show();
                    }
                });
            }
        });
        rq.add(sr);

        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("wd", "xUtils");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {

            }
        });
*/

    }

    class Show {
        @JavascriptInterface
        public void show(final String data) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
                    //new AlertDialog.Builder(WebViewActivity.this).setMessage(data).create().show();
                }
            });

        }
    }

    private void setMultiSateView() {
        mMultiStateView = (MultiStateView) findViewById(R.id.multiStateView);
        mMultiStateView.getView(MultiStateView.VIEW_STATE_ERROR)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
                    }
                });
        mMultiStateView.getView(MultiStateView.VIEW_STATE_EMPTY)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
                    }
                });
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mMultiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
            }
        }, 3000);
    }

    public void gotoAnimation(View v) {
        int[] location = new int[2];
        v.getLocationInWindow(location);
        MaterialDesignAnimation.jump(this, location[0] + (v.getWidth() / 2), location[1] + (v
                .getHeight() / 2));
    }
}
