package com.rrat.animationdrawdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

public class DialogLoad {

    private AlertDialog dialog;
    private TextView tvLoading;
    private static final String LOADING = "LOADING...";

    protected DialogLoad(@NonNull Context context) {
        createDialog(context);
    }

    public void createDialog(Context context)
    {
        int llPadding = 50;
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setPadding(llPadding, llPadding, llPadding, llPadding);
        ll.setGravity(Gravity.CENTER);
        ll.setClipToOutline(true);
        ll.setBackgroundColor(ContextCompat.getColor(context,android.R.color.transparent));
        ll.setBackgroundResource(android.R.color.transparent);
        ll.setBackground(ContextCompat.getDrawable(context, android.R.color.transparent));

        //ll.setForeground(ContextCompat.getDrawable(this, R.drawable.bg_linear_layout));
        LinearLayout.LayoutParams llParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        llParam.gravity = Gravity.CENTER;
        ll.setLayoutParams(llParam);

        ProgressBar progressBar = new ProgressBar(context);
        progressBar.setIndeterminate(true);
        progressBar.setPadding(0, 0, llPadding, 0);
        progressBar.setLayoutParams(llParam);

        llParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        llParam.gravity = Gravity.CENTER;
        tvLoading = new TextView(context);
        tvLoading.setText("Loading ...\n CARGANDO DATA TIPO A");
        tvLoading.setTextColor(Color.parseColor("#000000"));
        tvLoading.setTextSize(20);
        tvLoading.setTypeface(Typeface.DEFAULT_BOLD);
        tvLoading.setPadding(llPadding, llPadding, llPadding, llPadding);
        tvLoading.setLayoutParams(llParam);

        ll.addView(progressBar);
        ll.addView(tvLoading);

        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogStyle);
        builder.setCancelable(false);
        builder.setView(ll);

        dialog = builder.create();
    }

    public void showDialog()
    {
        dialog.show();
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(layoutParams);
        }
    }

    void setTextLoading(String text)
    {
        tvLoading.setText(LOADING + " " + text);
    }

}
