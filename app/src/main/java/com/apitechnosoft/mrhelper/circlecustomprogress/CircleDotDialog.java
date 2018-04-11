package com.apitechnosoft.mrhelper.circlecustomprogress;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;


/**
 * Created by Neeraj on 8/18/2017.
 */

public class CircleDotDialog extends AlertDialog {

    private CharSequence message;

    public CircleDotDialog(Context context) {
        this(context, R.style.CircleDotLoadingDialog);
        setvalue();
    }

    public CircleDotDialog(Context context, CharSequence message) {
        this(context);
        this.message = message;
        setvalue();
    }

    public CircleDotDialog(Context context, CharSequence message, int theme) {
        this(context, theme);
        this.message = message;
        setvalue();
    }

    public CircleDotDialog(Context context, int theme) {
        super(context, theme);
    }

    public CircleDotDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setvalue();
    }

    private void setvalue() {
        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(wlmp);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.circle_dot_progress_dialog);
        setCanceledOnTouchOutside(false);

        Circle mCircleDrawable = new Circle();
        mCircleDrawable.setBounds(0, 0, 120, 120);//What u want of size progress bar dots...
        mCircleDrawable.setColor(Color.parseColor("#F66D0F"));// What u want of color progress bar dots...

        TextView progressBar_text = (TextView) findViewById(R.id.progress);// Your Textview..
        progressBar_text.setCompoundDrawables(null, null, mCircleDrawable, null);// Set Progress bar on Textview..
        mCircleDrawable.start();
        initMessage();
    }

    private void initMessage() {
        if (message != null && message.length() > 0) {
            ((TextView) findViewById(R.id.title)).setText(message);
        }
    }
}

