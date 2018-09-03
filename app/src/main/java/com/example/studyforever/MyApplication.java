package com.example.studyforever;

import android.app.Application;

import com.example.studyforever.dagger.components.AppComponent;
import com.example.studyforever.dagger.components.DaggerAppComponent;
import com.example.studyforever.dagger.modules.AppModule;

/**
 * Created by Liang on 2018/9/3 0003.
 */

public class MyApplication extends Application {
    private static MyApplication application;
    AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static MyApplication getApplication() {
        return application;
    }
    public AppComponent getAppComponent(){
        return mAppComponent;
    }

}

