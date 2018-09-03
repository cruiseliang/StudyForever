package com.example.studyforever.network.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.studyforever.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Liang on 2018/8/31 0031.
 */

public class OkhttpActivity extends Activity {

    private OkHttpHelper mHttpHelper=OkHttpHelper.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        requestSync();//同步
        requestYibu();//异步
    }

    private void requestYibu() {
        OkHttpClient client = new OkHttpClient();
        //构造Request对象
        //采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
        Request request = new Request.Builder().get().url("http://www.jianshu.com/u/9df45b87cfdf").build();
        Call call = client.newCall(request);
        //异步调用并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }


        });

    }

    private void requestSync() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.baidu.com")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void  goRequest(){
        Map<String,Object> params = new HashMap<>(1);

        params.put("zip_code","000000");

        mHttpHelper.post("", params, new SimpleCallback<String>(this) {



            @Override
            public void onSuccess(Response response, String s) {

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }
}
