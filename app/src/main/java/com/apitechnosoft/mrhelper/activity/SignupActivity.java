package com.apitechnosoft.mrhelper.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.FontManager;

public class SignupActivity extends AppCompatActivity {
    Typeface materialdesignicons_font;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation

        String number = getIntent().getStringExtra("Number");
        materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");
        TextView phone_icon = (TextView) findViewById(R.id.phone_icon);
        phone_icon.setTypeface(materialdesignicons_font);
        phone_icon.setText(Html.fromHtml("&#xf3f2;"));
        TextView arrowicon = (TextView) findViewById(R.id.arrowicon);
        arrowicon.setTypeface(materialdesignicons_font);
        arrowicon.setText(Html.fromHtml("&#xf054;"));

        TextView email_icon = (TextView) findViewById(R.id.email_icon);
        email_icon.setTypeface(materialdesignicons_font);
        email_icon.setText(Html.fromHtml("&#xf1ee;"));

        TextView user_icon = (TextView) findViewById(R.id.user_icon);
        user_icon.setTypeface(materialdesignicons_font);
        user_icon.setText(Html.fromHtml("&#xf004;"));
        EditText edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_phone.setText(number);

        LinearLayout loginbutton = (LinearLayout) findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, DashboardActivity.class);
               // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(SignupActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
