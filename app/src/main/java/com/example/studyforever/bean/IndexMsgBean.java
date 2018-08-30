package com.example.studyforever.bean;

import android.app.Activity;

/**
 * Created by Liang on 2018/8/30 0030.
 *
 */

public class IndexMsgBean {
    private String  name;
    private Activity activity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public IndexMsgBean(String name, Activity activity) {
        this.name = name;
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "IndexMsgBean{" +
                "name='" + name + '\'' +
                ", activity=" + activity +
                '}';
    }
}
