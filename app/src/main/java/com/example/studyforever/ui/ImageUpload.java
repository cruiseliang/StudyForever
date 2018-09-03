package com.example.studyforever.ui;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Liang on 2018/9/3 0003.
 */

public class ImageUpload {

    public void picassoUpload(Context context, ImageView  img) {
        Picasso.with(context).load("http://n.sinaimg.cn/translate/20160819/9BpA-fxvcsrn8627957.jpg")
                .resize(200,200)
                .into(img);

    }
}
