package com.example.studyforever.dagger.components;

import android.content.Context;

import com.example.studyforever.dagger.Navigator;
import com.example.studyforever.dagger.modules.AppModule;
import com.example.studyforever.utils.ToastUtil;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by niuxiaowei on 16/3/19.
 */
@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {

    Context getContext();

    Navigator getNavigator();
    ToastUtil getToastUtil();
//    Test test();

}
