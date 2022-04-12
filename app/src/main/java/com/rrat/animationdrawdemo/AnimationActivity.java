package com.rrat.animationdrawdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class AnimationActivity extends AppCompatActivity {
    private ViewGroup parent;
    private ImageView imgInner, imgOuter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        parent = (ViewGroup) findViewById(R.id.parent_loop);
        imgInner = (ImageView) findViewById(R.id.image_loop_inner);
        imgOuter = (ImageView) findViewById(R.id.image_loop_outer);

        setImageLoop();

    }

    private void setImageLoop()
    {
        imgInner.post(() -> {
            TranslateAnimation outAnim = getTranslateAnimation(0, parent.getWidth());
            TranslateAnimation inAnim = getTranslateAnimation(-parent.getWidth(), 0);
            imgInner.startAnimation(outAnim);
            imgOuter.startAnimation(inAnim);
        });
    }



    private TranslateAnimation getTranslateAnimation(float fromXDelta, float toXDelta){
        TranslateAnimation translateAnim = new TranslateAnimation(
                fromXDelta, toXDelta, 0f, 0f);

        translateAnim.setInterpolator(new LinearInterpolator());
        translateAnim.setRepeatMode(Animation.INFINITE);
        translateAnim.setRepeatCount(Animation.INFINITE);
        translateAnim.setDuration(20000);

        return translateAnim;
    }


}