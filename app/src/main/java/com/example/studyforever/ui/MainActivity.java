package com.example.studyforever.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.example.studyforever.widget.DividerItemDecoration;
import com.example.studyforever.adapter.MyAdapter;
import com.example.studyforever.R;
import com.example.studyforever.anim.AnimationDemo;
import com.example.studyforever.bean.IndexMsgBean;
import com.example.studyforever.network.okhttp.OkhttpActivity;
import com.example.studyforever.network.retrofit.RetrofitActivity;

import java.util.ArrayList;

/**
 * 学习过程中的积累
 * https://github.com/shixinzhang/LearnAndroid
 * https://github.com/crazyqiang/AndroidStudy
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private ArrayList<IndexMsgBean> datas ;

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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private void initData() {
        datas = new ArrayList<>();
        datas.add(new IndexMsgBean("retrofit//封装各种adapter",new RetrofitActivity()));
        datas.add(new IndexMsgBean("animation",new AnimationDemo()));
        datas.add(new IndexMsgBean("smileview/笑脸评价",new SmileViewActivity()));
        datas.add(new IndexMsgBean("富文本编辑器",new MRichTextActivity()));
        datas.add(new IndexMsgBean("surfaceView",new TestSurfaceView()));
        datas.add(new IndexMsgBean("设备信息",new DeviceInfoActivity()));
        datas.add(new IndexMsgBean("listview",new ListViewActivity()));
        datas.add(new IndexMsgBean("弹窗",new PopupActivity()));
        datas.add(new IndexMsgBean("volley",new VolleyStudy()));
        datas.add(new IndexMsgBean("okhttp",new OkhttpActivity()));
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
        mAdapter = new MyAdapter(datas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.HORIZONTAL_LIST));
        mAdapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, IndexMsgBean data,int position) {
                startActivity(new Intent(MainActivity.this, data.getActivity().getClass()));
            }
        });
//        startActivity(new Intent(MainActivity.this, AnimationDemo.class));
//        mRecyclerView.setAdapter(new CommonAdapter<String>(this, R.layout.item_demo, datas) {
//            @Override
//            public void convert(BaseViewHolder holder, String s) {
//                holder.setText(R.id.tv_item_demo, s);
//            }
//        });
    }


}
