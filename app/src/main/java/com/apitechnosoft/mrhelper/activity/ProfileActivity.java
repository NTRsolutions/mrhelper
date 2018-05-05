package com.apitechnosoft.mrhelper.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.FontManager;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    TextView name, email, phoneno, registereIcon, register, aboutIcon, aboutus, shareIcon, shareapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        chechPortaitAndLandSacpe();
        initView();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(ProfileActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView() {
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        phoneno = (TextView) findViewById(R.id.phoneno);
        registereIcon = (TextView) findViewById(R.id.registereIcon);
        register = (TextView) findViewById(R.id.register);
        aboutIcon = (TextView) findViewById(R.id.aboutIcon);
        aboutus = (TextView) findViewById(R.id.aboutus);
        shareIcon = (TextView) findViewById(R.id.shareIcon);
        shareapp = (TextView) findViewById(R.id.shareapp);

        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");
        Typeface fontawesome_webfont = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/fontawesome-webfont.ttf");
        registereIcon.setTypeface(fontawesome_webfont);
        registereIcon.setText(Html.fromHtml("&#xf2b5;"));
        shareIcon.setTypeface(materialdesignicons_font);
        shareIcon.setText(Html.fromHtml("&#xf497;"));
        aboutIcon.setTypeface(materialdesignicons_font);
        aboutIcon.setText(Html.fromHtml("&#xf2fd;"));
        LinearLayout shareappLayout = (LinearLayout) findViewById(R.id.shareappLayout);
        shareappLayout.setOnClickListener(this);
        LinearLayout aboutLayout = (LinearLayout) findViewById(R.id.aboutLayout);
        aboutLayout.setOnClickListener(this);
        LinearLayout registerLayout = (LinearLayout) findViewById(R.id.registerLayout);
        registerLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shareappLayout:
                shareApp();
                break;
            case R.id.aboutLayout:
                openScreen(AboutUsActivity.class);
                break;
            case R.id.registerLayout:
                openScreen(BecomeHostActivity.class);
                break;
        }
    }

    private void openScreen(Class<?> openScreen) {
        Intent intent = new Intent(ProfileActivity.this, openScreen);
        startActivity(intent);
    }

    private void shareApp() {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Mr Helper");
            String sAux = "\nLet me recommend you this application\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=com.apitechnosoft.mrhelper \n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "Choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }
}
