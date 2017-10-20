package com.example.studyforever.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.studyforever.R;
import com.example.studyforever.adapter.LvTestAdapter;

import java.util.ArrayList;

/**
 * Created by Liang on 2017/8/29 0029.
 */

public class ListViewActivity extends Activity {
    private ListView mListView;
    private LvTestAdapter mAdapter;
    private ArrayList<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mListView = (ListView) findViewById(R.id.lv_test);
        for (int i = 0; i < 20; i++) {
            mList.add("nihao" + i);

        }
        mAdapter = new LvTestAdapter(this, mList);
        mListView.setAdapter(mAdapter);
        mListView.smoothScrollToPosition(9);
        setAction();
    }

    private void setAction() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                switch (i) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:

                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                Log.e("dddd","dd"+i+"/"+i1+"/"+i2);
            }
        });

    }
}
