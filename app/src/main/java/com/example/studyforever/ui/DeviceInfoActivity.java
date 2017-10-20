package com.example.studyforever.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.studyforever.R;

/**
 * Created by Liang on 2017/8/25 0025.
 */

public class DeviceInfoActivity extends Activity {
    private TextView mTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_deviceinfo);
        mTextView = (TextView) findViewById(R.id.tv_deviceinfo);
        mTextView.setText(getDeviceInfo());
    }
    /**
     * 获取设备信息
     *
     * @return
     */
    private String getDeviceInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("主板： " + Build.BOARD);
        sb.append(
                "\n系统启动程序版本号： " + Build.BOOTLOADER);
        sb.append(
                "\n系统定制商： " + Build.BRAND);
        sb.append(
                "\ncpu指令集： " + Build.CPU_ABI);
        sb.append(
                "\ncpu指令集2 " + Build.CPU_ABI2);
        sb.append(
                "\n设置参数： " + Build.DEVICE);
        sb.append(
                "\n显示屏参数：" + Build.DISPLAY);
        sb.append("\n无线电固件版本：" + Build.getRadioVersion());
        sb.append("\n硬件识别码： " + Build.FINGERPRINT);
        sb.append("\n硬件名称： " + Build.HARDWARE);
        sb.append("\nHOST: " + Build.HOST);
        sb.append("\nBuild.ID);" + Build.ID);
        sb.append("\n硬件制造商： " + Build.MANUFACTURER);
        sb.append("\n版本： " + Build.MODEL);
        sb.append("\n硬件序列号： " + Build.SERIAL);
        sb.append("\n手机制造商： " + Build.PRODUCT);
        sb.append("\n描述Build的标签： " + Build.TAGS);
        sb.append("\nTIME: " + Build.TIME);
        sb.append("\nbuilder类型" + Build.TYPE);
        sb.append("\nUSER: " + Build.USER);
        return sb.toString();
    }


}
