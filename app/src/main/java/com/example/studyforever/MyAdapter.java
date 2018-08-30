package com.example.studyforever;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studyforever.bean.IndexMsgBean;

import java.util.ArrayList;

/**
 * Created by Liang on 2017/7/24 0024.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {
    public ArrayList<IndexMsgBean> datas = null;
    private  int pos;
    public MyAdapter(ArrayList datas) {
        this.datas = datas;

    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_demo,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return vh;
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.mTextView.setText(datas.get(position).getName());
        // 如果设置了回调，则设置点击事件
        //将数据保存在itemView的Tag中，以便点击时进行获取
        viewHolder.itemView.setTag(datas.get(position));
        pos=position;
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(view,(IndexMsgBean) view.getTag(),pos);
        }
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_item_demo);
        }
    }

    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , IndexMsgBean data,int position);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void addItem(IndexMsgBean content, int position) {
        datas.add(position, content);
        notifyItemInserted(position); //Attention!
    }
    public void removeItem( int position) {
//        int position = datas.indexOf(model);
        datas.remove(position);
        notifyItemRemoved(position);//Attention!
    }
}
