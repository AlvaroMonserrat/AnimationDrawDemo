package com.rrat.animationdrawdemo;

import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

public class PhoneAlertAnim {
    private MenuItem item;
    private final Context mContext;
    Animation animationClock;
    Animation animationAntiClock;
    ImageView imageView;
    boolean isStopped = false;

    public PhoneAlertAnim(Context context, Menu menu) {
        mContext = context;
        initAnimation(menu);
    }

    private void initAnimation(Menu menu)
    {

        item = menu.findItem(R.id.action_instrucciones);

        if(item==null) return;

        startAnimation();
    }
    public void stopHardAnimation()
    {
        isStopped = true;
        if(item.getActionView()!= null)
        {

            animationClock = null;
            animationAntiClock = null;
            item.getActionView().clearAnimation();
            item.getActionView().setVisibility(View.GONE);
        }

    }

    public void stopCleanAnimation()
    {

        if(item.getActionView()!= null)
        {

            animationClock.cancel();
            animationAntiClock.cancel();
            item.getActionView().clearAnimation();
            item.getActionView().setVisibility(View.GONE);
        }

    }

    public void startAnimation()
    {
        isStopped = false;
        animationClock = new RotateAnimation(-30.0f,
                30.f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        animationClock.setDuration(200);
        animationClock.setInterpolator(new LinearInterpolator());
        animationClock.setRepeatCount(Animation.ABSOLUTE);


        animationAntiClock = new RotateAnimation(30.0f,
                -30.f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        animationAntiClock.setDuration(200);
        animationAntiClock.setInterpolator(new LinearInterpolator());
        animationAntiClock.setRepeatCount(Animation.ABSOLUTE);


        imageView = new ImageView(mContext);
        imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_phone));
        imageView.startAnimation(animationClock);
        item.setActionView(imageView);

        animationClock.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("ANIMATION", "START");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("ANIMATION", "END");
                if(!isStopped){
                    stopCleanAnimation();
                    imageView.startAnimation(animationAntiClock);
                    item.setActionView(imageView);
                }


            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i("ANIMATION", "REPEAT");
            }
        });


        animationAntiClock.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("ANIMATION", "START");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("ANIMATION", "END");
                if(!isStopped){
                    stopCleanAnimation();
                    imageView.startAnimation(animationClock);
                    item.setActionView(imageView);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i("ANIMATION", "REPEAT");

            }
        });
    }
}
