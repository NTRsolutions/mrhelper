package com.apitechnosoft.mrhelper.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.database.DbHelper;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.ContentData;
import com.apitechnosoft.mrhelper.models.User;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    TextView name, email, phoneno, registereIcon, register, aboutIcon, aboutus, shareIcon, shareapp, loginIcon, loginlogout;
    User user;

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
        loginIcon = (TextView) findViewById(R.id.loginIcon);
        loginlogout = (TextView) findViewById(R.id.loginlogout);

        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");
        Typeface fontawesome_webfont = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/fontawesome-webfont.ttf");
        registereIcon.setTypeface(materialdesignicons_font);
        registereIcon.setText(Html.fromHtml("&#xf2e8;"));
        shareIcon.setTypeface(materialdesignicons_font);
        shareIcon.setText(Html.fromHtml("&#xf497;"));
        aboutIcon.setTypeface(materialdesignicons_font);
        aboutIcon.setText(Html.fromHtml("&#xf2fd;"));
        loginIcon.setTypeface(materialdesignicons_font);
        LinearLayout shareappLayout = (LinearLayout) findViewById(R.id.shareappLayout);
        shareappLayout.setOnClickListener(this);
        LinearLayout aboutLayout = (LinearLayout) findViewById(R.id.aboutLayout);
        aboutLayout.setOnClickListener(this);
        LinearLayout registerLayout = (LinearLayout) findViewById(R.id.registerLayout);
        registerLayout.setOnClickListener(this);
        LinearLayout logoutLayout = (LinearLayout) findViewById(R.id.logoutLayout);
        logoutLayout.setOnClickListener(this);

        DbHelper dbHelper = new DbHelper(ProfileActivity.this);
        String phone = Utility.getUserPhoneNo(ProfileActivity.this);
        user = dbHelper.getUserData(phone);
        if (user != null) {
            loginlogout.setText("Logout");
            loginIcon.setText(Html.fromHtml("&#xf343;"));
            name.setText(user.getName());
            email.setText(user.getEmail());
            phoneno.setText(user.getMobileno());
        } else {
            loginIcon.setText(Html.fromHtml("&#xf342;"));
            loginlogout.setText("Login");
        }
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
            case R.id.logoutLayout:
                if (user != null) {
                    DbHelper dbHelper = new DbHelper(ProfileActivity.this);
                    dbHelper.deleteUserData();
                    SharedPreferences prefs = getSharedPreferences("UserPhonePreferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.clear().commit();
                    loginlogout.setText("Logout");
                    loginIcon.setText(Html.fromHtml("&#xf343;"));
                    Toast.makeText(ProfileActivity.this, "Logout Successfully.", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    intent.putExtra("ProfileScreen", true);
                    startActivityForResult(intent, 1);
                    loginIcon.setText(Html.fromHtml("&#xf342;"));
                    loginlogout.setText("Login");
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                // String result=data.getStringExtra("result");
                loginlogout.setText("Logout");
                loginIcon.setText(Html.fromHtml("&#xf343;"));
                callGetUserProfile();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    private void callGetUserProfile() {
        if (Utility.isOnline(this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(ProfileActivity.this);
            dotDialog.show();
            String phone = Utility.getUserPhoneNo(ProfileActivity.this);
            if (phone != null) {
                ServiceCaller serviceCaller = new ServiceCaller(this);
                serviceCaller.callUserProfileSrvice(phone, new IAsyncWorkCompletedCallback() {
                    @Override
                    public void onDone(String result, boolean isComplete) {
                        if (isComplete) {
                            ContentData data = new Gson().fromJson(result, ContentData.class);
                            if (data != null) {
                                if (data.getUser() != null) {
                                    DbHelper dbHelper = new DbHelper(ProfileActivity.this);
                                    dbHelper.upsertUserData(data.getUser());
                                    name.setText(data.getUser().getName());
                                    email.setText(data.getUser().getEmail());
                                    phoneno.setText(data.getUser().getMobileno());
                                }
                            }
                        }
                    }
                });
            }
            if (dotDialog.isShowing()) {
                dotDialog.dismiss();
            }
        }

    }
}
