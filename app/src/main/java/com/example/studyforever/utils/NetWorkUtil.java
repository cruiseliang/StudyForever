package com.example.studyforever.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class NetWorkUtil {


    private static final String TAG = "NetWorkUtil";
    private static final int TIME_OUT = 10 * 1000; // 超时时间
    private static final String CHARSET = "utf-8"; // 设置编码


    /**
     * 判断网络是否可用 true 有网络 false 网络异常
     *
     * @param context
     * @return
     */

    public static boolean isNetworkAvailable(Context context) {

        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {

                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断WIFI是否打开
     *
     * @param context
     * @return
     */
    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mgrTel = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    /**
     * 判断是wifi还是3g网络
     *
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null
                && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 网络请求返回值回调函数
     *
     * @author weiyunchao
     */
    public interface ResponseCallBack {
        public void response(JSONObject obj);
    }

    /**
     * 网络请求返回值回调函数
     *
     * @author weiyunchao
     */
    public interface ErrorListener {
        public void errorCallBack(String msg);
    }







    /**
     * 获取错误信息
     *
     * @param error
     * @return
     */
    public static String getErrorMsg(VolleyError error) {

        Throwable th = error.getCause();
        if (th != null) {
            if (th instanceof ConnectException) {
                return "无网络连接";
            }
            if (th instanceof UnknownHostException) {
                return "网络异常";
            }
            if (th instanceof JSONException) {
                return "服务器错误";
            }
            if (th instanceof SocketException) {
                return "链接超时";
            }
        }
        return "服务器维护中o(︶︿︶)o";
    }


}
