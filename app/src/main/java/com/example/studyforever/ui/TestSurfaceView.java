package com.example.studyforever.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Liang on 2017/8/24 0024.
 * SurfaceView  基础使用
 */

public class TestSurfaceView extends Activity {
    /** Called when the activity is first created. */
    int  index=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    class MyView extends SurfaceView implements SurfaceHolder.Callback,Runnable{
        SurfaceHolder holder=null;
        Paint paint;
        public MyView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
            holder=getHolder();
            holder.addCallback(this);
            paint=new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.RED);

            this.setFocusable(true);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            // TODO Auto-generated method stub

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            // TODO Auto-generated method stub
            Thread t=new Thread(this);
            t.start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // TODO Auto-generated method stub
            isRunning=false;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            canvas=holder.lockCanvas();
            //刷屏
            canvas.drawColor(Color.BLACK);

            canvas.drawCircle(x, y, 10, paint);

            holder.unlockCanvasAndPost(canvas);
            Log.e("dddd",index+"onDraw");
        }
        private void paint(Paint paint) {
            Canvas canvas=holder.lockCanvas();
            //刷屏
            canvas.drawColor(Color.BLACK);

            canvas.drawCircle(x, y, 10, paint);

            holder.unlockCanvasAndPost(canvas);
            Log.e("dddd",index+"paint");
        }

        boolean isRunning=true;
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (isRunning) {
//              onDraw(null);
                paint(paint);
                move();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }

        private int x,y;
        private void move(){
            x+=2;
            y+=2;
        }
    }

}
