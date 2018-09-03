package com.example.studyforever.dagger.modules;

import android.content.Context;

import com.example.studyforever.dagger.Navigator;
import com.example.studyforever.utils.ToastUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by niuxiaowei on 16/3/19.
 */
@Module
public class AppModule {

    Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    public Navigator provideNavigator(){
        return new Navigator();
    }

    @Provides
    @Singleton
    public ToastUtil provideToastUtil(){
        return new ToastUtil(context);
    }

//    @Provides @Singleton
//    public Test provideTest(){
//        return new Test();
//    }
}
