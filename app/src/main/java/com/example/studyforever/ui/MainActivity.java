package com.example.studyforever.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.studyforever.MyAdapter;
import com.example.studyforever.R;
import com.example.studyforever.anim.AnimationDemo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private ArrayList<String> datas = new ArrayList<>();
    private ArrayList<Activity> mActivityList = new ArrayList<>();
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }

    private void initData() {
        mActivityList.add(new AnimationDemo());
        mActivityList.add(new AnimationDemo());
        mActivityList.add(new SmileViewActivity());
    }

    //初始化view
    public void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.yzs_base_list);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置
        // 这个选项可以提高性能
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        datas.add("retrofit//封装各种adapter");
        datas.add("animation");
        datas.add("smileview/笑脸评价");
        mAdapter = new MyAdapter(datas);
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(
//                this, DividerItemDecoration.HORIZONTAL_LIST));
        mAdapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data,int position) {
                //
                Log.e("datass", data);
                Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
                Activity activity=mActivityList.get(position);
                startActivity(new Intent(MainActivity.this, activity.getClass()));
            }
        });
//        startActivity(new Intent(MainActivity.this, AnimationDemo.class));
//        mRecyclerView.setAdapter(new CommonAdapter<String>(this, R.layout.item_demo, datas) {
//            @Override
//            public void convert(BaseViewHolder holder, String s) {
//                holder.setText(R.id.tv_item_demo, s);
//
//            }
//        });
    }

}
