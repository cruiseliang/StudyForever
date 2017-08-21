package com.example.studyforever.adapter;

/**
 * Created by Liang on 2017/7/28 0028.
 */

public interface MultiItemTypeSupport<T> {
    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}
