package com.example.studyforever.dagger.modules;


import com.example.studyforever.dagger.GetUserData;

import dagger.Module;
import dagger.Provides;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@Module
public class MainModule {

    @Provides
    public GetUserData provideUserData(){
        return new GetUserData();
    }

}
