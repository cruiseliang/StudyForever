package com.example.studyforever.dagger;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.studyforever.MyApplication;
import com.example.studyforever.R;
import com.example.studyforever.dagger.components.DaggerMainComponent;
import com.example.studyforever.dagger.components.MainComponent;
import com.example.studyforever.dagger.modules.ActivityModule;
import com.example.studyforever.dagger.modules.MainModule;

/**
 * Created by Liang on 2018/9/3 0003.
 * dagger使用
 */

public class DaggerActivity extends Activity {
    private MainComponent mMainComponent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        /***
         * 第一步 添加依赖关系
         */
        //第一种方式
        mMainComponent = DaggerMainComponent.builder().appComponent(((MyApplication)getApplication()).getAppComponent())
                .mainModule(new MainModule())
                .activityModule(new ActivityModule(this)).build();
        mMainComponent.inject(this);
    }
}
