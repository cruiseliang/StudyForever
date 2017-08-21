package com.example.studyforever.adapter;

/**
 * Created by Liang on 2017/7/28 0028.
 */

public interface SectionSupport<T> {
    public int sectionHeaderLayoutId();

    public int sectionTitleTextViewId();

    public String getTitle(T t);
}
