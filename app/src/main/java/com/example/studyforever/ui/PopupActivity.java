package com.example.studyforever.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.studyforever.R;
import com.example.studyforever.widget.CustomPopupWindow;

/**
 * Created by Liang on 2018/8/30 0030.
 * https://github.com/PopFisher/SmartPopupWindow  全功能弹窗
 */

public class PopupActivity extends Activity {
    private View rootView;
    private Button btnShowDomn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);
        rootView = findViewById(R.id.root_view);
        btnShowDomn = (Button) findViewById(R.id.btn_show_down);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    CustomPopupWindow popupWindow;
    public void showDownPop(View view) {
         popupWindow = new CustomPopupWindow.Builder()
                .setContext(this) //设置 context
                .setContentView(R.layout.popup_calendar) //设置布局文件
                .setwidth(LinearLayout.LayoutParams.WRAP_CONTENT) //设置宽度，由于我已经在布局写好，这里就用 wrap_content就好了
                .setheight(LinearLayout.LayoutParams.WRAP_CONTENT) //设置高度
                .setFouse(true)  //设置popupwindow 是否可以获取焦点
                .setOutSideCancel(true) //设置点击外部取消
                .setAnimationStyle(R.style.popup_anim_style) //设置popupwindow动画
                  .setBackGroudAlpha(this,0.7f) //是否设置背景色，原理为调节 alpha
                .builder() //
                .showAtLocation(R.layout.activity_popup, Gravity.CENTER,0,0); //设置popupwindow居中显示

        popupWindow.getItemView(R.id.tv_custom_popup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "dddddd", Toast.LENGTH_SHORT).show();
            }
        });

    }
    //向上弹出
    public void showUpPop(View view) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        popupWindow = new CustomPopupWindow.Builder()
                .setContext(this) //设置 context
                .setContentView(R.layout.popup_left_or_right)
                .setwidth(LinearLayout.LayoutParams.WRAP_CONTENT) //设置宽度，由于我已经在布局写好，这里就用 wrap_content就好了
                .setheight(LinearLayout.LayoutParams.WRAP_CONTENT) //设置高度
                .setFouse(true)  //设置popupwindow 是否可以获取焦点
                .setOutSideCancel(true) //设置点击外部取消
                .builder();
        int[] location = new int[2];
        view.getLocationOnScreen(location);
//        popupWindow.showAtLocation(view,Gravity.NO_GRAVITY, location[0],location[1]-popupWindow.getHeight());
        popupWindow.showAsLaction(view, 0,0, -(popupWindow.getHeight() + view.getMeasuredHeight()));
        //得到button的左上角坐标
//        int[] positions = new int[2];
//        view.getLocationOnScreen(positions);
//        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, positions[0], positions[1] - popupWindow.getHeight());
    }

}
