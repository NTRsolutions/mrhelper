package com.apitechnosoft.mrhelper.activity;

import android.app.Activity;
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
import android.widget.Toast;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.ContentResponce;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;

public class SignupActivity extends AppCompatActivity {
    Typeface materialdesignicons_font;
    EditText edt_phone, email, name, password, landmark, location, hno;
    private String phonestr, emailstr, namestr, passwordstr, hnostr, locationstr, landmarkstr;

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

        TextView password_icon = (TextView) findViewById(R.id.password_icon);
        password_icon.setTypeface(materialdesignicons_font);
        password_icon.setText(Html.fromHtml("&#xf341;"));

        TextView user_icon = (TextView) findViewById(R.id.user_icon);
        user_icon.setTypeface(materialdesignicons_font);
        user_icon.setText(Html.fromHtml("&#xf004;"));
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        email = (EditText) findViewById(R.id.email);
        name = (EditText) findViewById(R.id.name);
        //edt_phone.setText(number);
        password = (EditText) findViewById(R.id.password);

        TextView hno_icon = (TextView) findViewById(R.id.hno_icon);
        hno_icon.setTypeface(materialdesignicons_font);
        hno_icon.setText(Html.fromHtml("&#xf2dc;"));
        TextView location_icon = (TextView) findViewById(R.id.location_icon);
        location_icon.setTypeface(materialdesignicons_font);
        location_icon.setText(Html.fromHtml("&#xf352;"));
        TextView landmark_icon = (TextView) findViewById(R.id.landmark_icon);
        landmark_icon.setTypeface(materialdesignicons_font);
        landmark_icon.setText(Html.fromHtml("&#xf5f8;"));
        hno = (EditText) findViewById(R.id.hno);
        location = (EditText) findViewById(R.id.location);
        landmark = (EditText) findViewById(R.id.landmark);

        LinearLayout loginbutton = (LinearLayout) findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate()) {
                    callSignup();
                }
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

    private void callSignup() {
        if (Utility.isOnline(this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(SignupActivity.this);
            dotDialog.show();

            ServiceCaller serviceCaller = new ServiceCaller(this);
            serviceCaller.callSignUpSrvice(phonestr, passwordstr, emailstr, namestr, hnostr, locationstr, landmarkstr, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        ContentResponce data = new Gson().fromJson(result, ContentResponce.class);
                        if (data != null) {
                            if (data.isStatus()) {
                                Utility.setUserPhoneNo(SignupActivity.this, phonestr);
                                Toast.makeText(SignupActivity.this, "SignUp Successfully.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignupActivity.this, "SignUp not Successfully!", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, SignupActivity.this);
                    }
                    if (dotDialog.isShowing()) {
                        dotDialog.dismiss();
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, this);//off line msg....
        }

    }

    // ----validation -----
    private boolean isValidate() {
        String emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        phonestr = edt_phone.getText().toString();
        passwordstr = password.getText().toString();
        emailstr = email.getText().toString();
        namestr = name.getText().toString();
        hnostr = hno.getText().toString();
        locationstr = location.getText().toString();
        landmarkstr = landmark.getText().toString();

        if (phonestr.length() == 0) {
            showToast("Please enter mobile number");
            return false;
        } else if (phonestr.length() != 10) {
            showToast("Please enter valid mobile number");
            return false;
        } else if (emailstr.length() == 0) {
            showToast("Please enter email id");
            return false;
        } else if (!emailstr.matches(emailRegex)) {
            showToast("Please enter valid email id");
            return false;
        } else if (namestr.length() == 0) {
            showToast("Please enter full name");
            return false;
        } else if (passwordstr.length() == 0) {
            showToast("Please enter password");
            return false;
        } else if (hnostr.length() == 0) {
            showToast("Please enter house number");
            return false;
        } else if (locationstr.length() == 0) {
            showToast("Please enter location");
            return false;
        } else if (landmarkstr.length() == 0) {
            showToast("Please enter landmark");
            return false;
        }
        return true;
    }

    private void showToast(String message) {
        Toast.makeText(SignupActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
