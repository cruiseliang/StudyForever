package com.example.studyforever.Retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.studyforever.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Liang on 2017/7/24 0024.
 * Retrofit2.0使用
 */

public class RetrofitActivity extends Activity {
    private static final String TAG = "RetrofitActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_retrofit);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.31.242:8080/springmvc_users/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IUserBiz userBiz = retrofit.create(IUserBiz.class);
        Call<List<User>> call = userBiz.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.e(TAG, "normalGet:" + response.body() + "");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}
