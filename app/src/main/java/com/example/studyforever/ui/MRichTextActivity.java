package com.example.studyforever.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.studyforever.R;
import com.example.studyforever.utils.UploadResult;
import com.example.studyforever.utils.runtimepermissions.PermissionsManager;
import com.example.studyforever.utils.runtimepermissions.PermissionsResultAction;
import com.hdl.mricheditor.bean.CamaraRequestCode;
import com.hdl.mricheditor.view.MRichEditor;
import com.hdl.myhttputils.CommCallback;
import com.hdl.myhttputils.MyHttpUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Liang on 2017/8/21 0021.
 */

public class MRichTextActivity extends Activity {
    private MRichEditor editor;//编辑器
    private EditText etTitle;//文章标题对象
    private static final String BASE_URL = "http://192.168.2.153:8080/MRichEditorDemoServer/upload.action";//文件上传的接口
    private static final String IMG_URL = "http://192.168.2.153:8080/MRichEditorDemoServer/upload";//文件存放的路径
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mrichtext);
        /**
         * 请求所有必要的权限----android6.0必须要动态申请权限,否则选择照片和拍照功能 用不了哦
         */
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {//权限通过了
            }

            @Override
            public void onDenied(String permission) {//权限拒绝了

            }
        });
        initMRichEditor();//初始化编辑器
    }

    /**
     * 初始化富文本编辑器
     */
    private void initMRichEditor() {
        etTitle = (EditText) findViewById(R.id.et_main_title);
        editor = (MRichEditor) findViewById(R.id.mre_editor);
        imageView = (ImageView) findViewById(R.id.img_back);
        editor.setServerImgDir(IMG_URL);//设置图片存放的路径
        editor.setOnPreviewBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priview();//预览
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 预览
     */
    private void priview() {
        editor.setHtmlTitle(etTitle.getText().toString().trim());//设置html的标题
        String htmlStr = editor.createHtmlStr();//创建html字符串
        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(MRichTextActivity.this);
        View view = View.inflate(MRichTextActivity.this, R.layout.dialog_preiview_html, null);
        final WebView wvPreiview = (WebView) view.findViewById(R.id.wv_dialog_preiview_html);
        ImageView ivClose = (ImageView) view.findViewById(R.id.iv_dialog_close);
        ImageView ivRefresh = (ImageView) view.findViewById(R.id.iv_dialog_refresh);
        ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wvPreiview.reload();
            }
        });
        wvPreiview.loadData(htmlStr, "text/html; charset=UTF-8", null);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();
        final AlertDialog finalDialog = dialog;
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog.dismiss();
            }
        });
        uploadImg();//上传图片
    }

    /**
     * 需要重写这个方法,并且加上下面的判断(照写即可)
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "取消操作", Toast.LENGTH_LONG).show();
            return;
        }
        if (requestCode == CamaraRequestCode.CAMARA_GET_IMG) {
            editor.insertImg(data.getData());
        } else if (requestCode == CamaraRequestCode.CAMARA_TAKE_PHOTO) {
            editor.insertImg(data);
        }
    }

    /**
     * 完成按钮---将文件和图片提交到服务器
     *
     * @param view
     */
    public void onFinished(View view) {
        editor.setHtmlTitle(etTitle.getText().toString().trim());//设置html的标题,在创建html文件之前,需要将文章的标题(即title标签)设置进去,之后设置无效.
        editor.createHtmlStr();//创建html字符串,会返回一个html字符串.[必须调用,否则内容为空]
        File file = editor.getHtmlFile("sdcard/test.html");//创建html文件,并设置保存的路径
        //添加List<File>的文件列表,用于MyHttpUtils多文件上传的参数.
        List<File> fileList = new ArrayList<>();
        fileList.add(file);
        for (String filePath : editor.getImgPath()) {
            fileList.add(new File(filePath));
        }
        //MyHttpUtils网络请求框架,详细使用介绍:http://blog.csdn.net/qq137722697/article/details/52843336 .
        new MyHttpUtils()
                .url(BASE_URL)//文件上传的接口 (url)
                .addUploadFiles(fileList)//需上传的多个文件
                .setJavaBean(UploadResult.class)//上传完成返回的json格式的数据对应的javabean对象
                .uploadFileMult(new CommCallback<UploadResult>() {//执行多文件上传任务
                    @Override
                    public void onSucess(UploadResult uploadResult) {//成功之后回调
                        Toast.makeText(MRichTextActivity.this, uploadResult.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(String msg) {//失败时候回调
                        Toast.makeText(MRichTextActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 上传图片(这里用于实时预览,上传了图片才可以预览哦,否则看不到图片,只能看见文字)
     */
    private void uploadImg() {
        List<File> fileList = new ArrayList<>();
        for (String filePath : editor.getImgPath()) {
            fileList.add(new File(filePath));
        }
        new MyHttpUtils()
                .url(BASE_URL)
                .addUploadFiles(fileList)
                .setJavaBean(UploadResult.class)
                .uploadFileMult(new CommCallback<UploadResult>() {
                    @Override
                    public void onSucess(UploadResult uploadResult) {
                        Toast.makeText(MRichTextActivity.this, uploadResult.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(String msg) {
                        Toast.makeText(MRichTextActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        Log.e("MRichEditorDemo", editor.getHtmlStr());
    }

    /**
     * 打开帮助页面
     *
     * @param view
     */
    public void onHelp(View view) {
        Toast.makeText(this, " 操作手册\n点击-->修改(图片除外),长按-->删除", Toast.LENGTH_LONG).show();
    }

    //使用新的方法上传多张图片
    public void multiFileUpload(View view) {
        /**
         * 多文件同时上传
         * @param view
         */
        File file = new File(Environment.getExternalStorageDirectory(), "1.jpg");
        File file2 = new File(Environment.getExternalStorageDirectory(), "2.txt");
        if (!file.exists() || !file2.exists()) {
            Toast.makeText(MRichTextActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        //        params.put("username", "杨光福");
        //        params.put("password", "123");
        PostFormBuilder build = OkHttpUtils.post();
        List<File> fileList = new ArrayList<>();
        for (String filePath : editor.getImgPath()) {
            fileList.add(new File(filePath));
        }
        for (File d : fileList) {
            if (d.exists()) {
                //当本地文件存在时，就可以上传
                build.addFile("img", d.getName(), d);
            }
            build.url(BASE_URL).params(params).build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {

                }
                @Override
                public void onResponse(String response) {

                }
            });

        }
    }
}
