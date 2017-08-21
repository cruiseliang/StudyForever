package com.example.studyforever.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.studyforever.R;
import com.example.studyforever.widget.SmileView;

/**
 * Created by Liang on 2017/8/18 0018.
 */

public class SmileViewActivity extends Activity {
    SeekBar seekBar;
    LinearLayout backGround;
    ImageView smileFace;

    SmileView smileView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smileview);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        backGround = (LinearLayout) findViewById(R.id.backGround);
        smileFace = (ImageView) findViewById(R.id.smileFace);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) smileFace.getLayoutParams();
                layoutParams.bottomMargin = i*3;
                smileFace.setLayoutParams(layoutParams);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        smileView = (SmileView) findViewById(R.id.smileView);
        smileView.setNum(60,40);

    }
}
