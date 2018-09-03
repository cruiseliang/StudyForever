package com.example.studyforever.dagger.components;

import android.app.Activity;

import com.example.studyforever.dagger.modules.ActivityModule;
import com.example.studyforever.dagger.scopes.PerActivity;

import dagger.Component;

/**
 *
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    Activity getActivity();
}
