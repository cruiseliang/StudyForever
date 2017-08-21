package com.example.studyforever.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Liang on 2017/7/25 0025.
 */

public interface IUserBiz {
    @GET("users")
    Call<List<User>> getUsers();
}
