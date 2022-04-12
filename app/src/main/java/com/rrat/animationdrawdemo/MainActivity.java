package com.rrat.animationdrawdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.splashscreen.SplashScreen;

public class MainActivity extends AppCompatActivity {

    ImageView skyBackground;
    ProgressBar progressBar;
    PhoneAlertAnim phoneAlertAnim;
    int pole = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen.installSplashScreen(this).setKeepOnScreenCondition(new SplashScreen.KeepOnScreenCondition() {
            @Override
            public boolean shouldKeepOnScreen() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
        setContentView(R.layout.activity_main);
        skyBackground = findViewById(R.id.sky_background);
        progressBar = findViewById(R.id.progress_bar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        phoneAlertAnim = new PhoneAlertAnim(this, menu);


        return true;
    }


    public void onMoveImage(View view) {
        Intent intent = new Intent(this, AnimationActivity.class);
        startActivity(intent);
    }

    public void onProgressDialog(View view) {
        DialogLoad dialogLoad = new DialogLoad(this);
        dialogLoad.setTextLoading("AUTENTIFICANDO");
        dialogLoad.showDialog();


    }

    public void onAlertDialog(View view) {



        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
        alertDialogBuilder.setMessage("NEW MESSAGE");
        alertDialogBuilder.setTitle("Title");
        alertDialogBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i("MAIN", "ACCEPT");
                phoneAlertAnim.startAnimation();
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i("MAIN", "CANCEL");
                phoneAlertAnim.stopHardAnimation();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}