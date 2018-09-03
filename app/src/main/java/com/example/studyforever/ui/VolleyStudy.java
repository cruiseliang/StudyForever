package com.example.studyforever.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.studyforever.R;
import com.example.studyforever.network.volley.BitmapCache;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

/**
 * Created by Liang on 2018/8/31 0031.
 */

public class VolleyStudy extends Activity {
    private static final String TAG = "MainActivity";

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_study);
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.cancelAll(this);

    }

    /**
     * 1. 创建一个RequestQueue对象。
     * 2. 创建一个StringRequest对象。
     * 3. 将StringRequest对象添加到RequestQueue里面。
     */
    //适用于通信频繁 ，但请求量较小的情景下，下载大文件时候需要别的请求方式
    //get请求

    //https://blog.csdn.net/guolin_blog/article/details/17656437

    /**
     * 其中蓝色部分代表主线程，绿色部分代表缓存线程，橙色部分代表网络线程。我们在主线程中调用RequestQueue的add()方法来添加一条网络请求，
     * 这条请求会先被加入到缓存队列当中，如果发现可以找到相应的缓存结果就直接读取缓存并解析，然后回调给主线程。如果在缓存中没有找到结果，
     * 则将这条请求加入到网络请求队列中，然后处理发送HTTP请求，解析响应结果，写入缓存，并回调主线程。
     */
    public void volleyHttp1() {
        StringRequest stringRequest = new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //在这里进行解析
                Log.e(TAG, s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, volleyError.getMessage(), volleyError);
            }
        });
        requestQueue.add(stringRequest);
    }

    //post请求
    public void volleyHttp2() {

        ;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //在这里进行解析
                Log.e(TAG, s);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, volleyError.getMessage(), volleyError);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("params1", "value1");
                map.put("params2", "value2");
                return map;

            }
        };
        requestQueue.add(stringRequest);

    }

    //jsonRequest请求
    public void volleyHttp3() {

        JsonObjectRequest jsonRequest = new JsonObjectRequest("http://m.weather.com.cn/data/101010100.html", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d("TAG", jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("TAG", volleyError.getMessage(), volleyError);
            }
        });
        requestQueue.add(jsonRequest);
    }

    //加载图片ImageRequest的使用
    public void volleyImageLoad() {
        ImageRequest imageRequest = new ImageRequest("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {

            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        requestQueue.add(imageRequest);
    }
    //ImageLoader的使用

    /**
     * 1. 创建一个RequestQueue对象。
     * 2. 创建一个ImageLoader对象。
     * 3. 获取一个ImageListener对象。
     * 4. 调用ImageLoader的get()方法加载网络上的图片。
     */
    public void volleyImageLoader() {
        //1.创建请求队列
        //2.创建ImageLoader 对象
        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());

        imageLoader.get("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png", null);
    }

    //okhttp3.0使用
    public void useOkhttp() {

        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        okhttp3.Request request = new okhttp3.Request.Builder().url("http://www.baidu.com").method("GET", null).build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
            }

            //请求成功执行的方法
            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
            }

        });
    }
}
