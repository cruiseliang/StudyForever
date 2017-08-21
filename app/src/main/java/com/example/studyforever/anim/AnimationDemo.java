package com.example.studyforever.anim;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.studyforever.R;

/**
 * Created by Liang on 2017/8/3 0003.
 * 回忆动画基础
 */

public class AnimationDemo extends Activity {
private ImageView spaceshipImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim);
         spaceshipImage = (ImageView) findViewById(R.id.spaceshipImage);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.bujian);
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        hyperspaceJumpAnimation.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        spaceshipImage.clearAnimation();
    }
}
