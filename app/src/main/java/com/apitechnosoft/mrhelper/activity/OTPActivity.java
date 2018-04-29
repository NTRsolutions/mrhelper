package com.apitechnosoft.mrhelper.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.apitechnosoft.mrhelper.utilities.Utility;

public class OTPActivity extends AppCompatActivity {
    private Toolbar toolbar;
    EditText otpView;
    private String dataUrl;
    String otpViewStr;
    int NavigationFlag;
    String ServiceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(OTPActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView() {
        NavigationFlag = getIntent().getIntExtra("NavigationFlag", 0);

        ServiceName = getIntent().getStringExtra("ServiceName");
        final String Phone = getIntent().getStringExtra("Phone");
        //come from repair screen
        if (NavigationFlag == 2) {
            getData();
        }
        //come from party screen
        if (NavigationFlag == 1) {
            SharedPreferences prefs = getSharedPreferences("PartySaveDataPre", MODE_PRIVATE);
            ServiceName = prefs.getString("heading", "");
            getPartyStepData();
        }
        TextView wheredo = (TextView) toolbar.findViewById(R.id.wheredo);
        wheredo.setText("Best " + ServiceName + " in This Area");

        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");
        TextView arrowicon = (TextView) findViewById(R.id.arrowicon);
        arrowicon.setTypeface(materialdesignicons_font);
        arrowicon.setText(Html.fromHtml("&#xf054;"));
        TextView mobileno = (TextView) findViewById(R.id.mobileno);
        mobileno.setText(Phone);
        otpView = (EditText) findViewById(R.id.otpView);

        LinearLayout nextButtonLayout = (LinearLayout) findViewById(R.id.nextButtonLayout);
        nextButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate()) {
                    if (NavigationFlag == 1) {
                        getSavePartyService();
                    } else {
                        getSaveService();
                    }
                }
            }
        });


    }

    private void getData() {
        SharedPreferences prefs = getSharedPreferences("SaveDataPre", MODE_PRIVATE);
        String houneNoStr = prefs.getString("houneNoStr", "");
        String locationstr = prefs.getString("locationstr", "");
        String landMarkStr = prefs.getString("landMarkStr", "");
        String nameStr = prefs.getString("nameStr", "");
        String mobileNostr = prefs.getString("mobileNostr", "");
        String passwordstr = prefs.getString("passwordstr", "");
        String emailaddressStr = prefs.getString("emailaddressStr", "");
        String timeViewStr = prefs.getString("timeViewStr", "");
        String caladerDateStr = prefs.getString("caladerDateStr", "");
        float totalAmount = prefs.getFloat("totalAmount", 0);
        String selectedValueSno = prefs.getString("selectedValueSno", "");
        int sno = prefs.getInt("Sno", 0);
        int NoOfService = prefs.getInt("NoOfService", 0);

        dataUrl = "servicesno=" + sno + "&password=" + passwordstr + "&email=" + emailaddressStr + "&section_home_service_sno=" + selectedValueSno + "&totalservice=" + NoOfService + "&name=" + nameStr + "&mobileno=" + mobileNostr + "&serviceamount=" + totalAmount + "&aftertaxamount=" + totalAmount + " &servicetime=" + timeViewStr + "&servicedate=" + caladerDateStr + "&houseno=" + houneNoStr + "&location=" + locationstr + "&landmark=" + landMarkStr + "&servicename=" + sno + "";
    }

    private void getPartyStepData() {
        SharedPreferences prefs = getSharedPreferences("SaveDataPre", MODE_PRIVATE);
        String houneNoStr = prefs.getString("houneNoStr", "");
        String locationstr = prefs.getString("locationstr", "");
        String landMarkStr = prefs.getString("landMarkStr", "");
        String nameStr = prefs.getString("nameStr", "");
        String mobileNostr = prefs.getString("mobileNostr", "");
        String passwordstr = prefs.getString("passwordstr", "");
        String emailaddressStr = prefs.getString("emailaddressStr", "");
        // String timeViewStr = prefs.getString("timeViewStr", "");
        String caladerDateStr = prefs.getString("caladerDateStr", "");
        //String totalAmount = prefs.getString("totalAmount", "");
        // int sno = prefs.getInt("Sno", 0);

        SharedPreferences Stepprefs = getSharedPreferences("PartySaveDataPre", MODE_PRIVATE);
        String Step = Stepprefs.getString("Step", "");
        String Step1 = Stepprefs.getString("Step1", "");
        String Step2 = Stepprefs.getString("Step2", "");
        String Step3 = Stepprefs.getString("Step3", "");
        String Step4 = Stepprefs.getString("Step4", "");
        ServiceName = Stepprefs.getString("heading", "");
        int SNo = Stepprefs.getInt("Sno", 0);
        dataUrl = "step1=" + Step + "&step2=" + Step1 + "&step3=" + Step2 + "&step4=" + Step3 + "&step5=" + Step4 + "&password=" + passwordstr + "&emailid=" + emailaddressStr + "&name=" + nameStr + "&mobileno=" + mobileNostr + "&servicedate=" + caladerDateStr + "&houseno=" + houneNoStr + "&location=" + locationstr + "&landmark=" + landMarkStr + "&servicename=" + SNo + "";
    }

    private boolean isValidate() {

        String emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        otpViewStr = otpView.getText().toString();

        if (otpViewStr.length() == 0) {
            Toast.makeText(this, "Please Enter OTP No", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void getSaveService() {
        if (Utility.isOnline(this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(OTPActivity.this);
            dotDialog.show();
            ServiceCaller serviceCaller = new ServiceCaller(this);
            serviceCaller.callSaveService(dataUrl, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    Intent intent = new Intent(OTPActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    SharedPreferences.Editor editor = getSharedPreferences("SaveDataPre", MODE_PRIVATE).edit();
                    editor.clear();
                    editor.commit();
                   /* if (isComplete) {

                    } else {
                        Utility.alertForErrorMessage(Contants.Error, OTPActivity.this);
                    }*/
                    if (dotDialog.isShowing()) {
                        dotDialog.dismiss();
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, this);//off line msg....
        }
    }


    private void getSavePartyService() {
        if (Utility.isOnline(this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(OTPActivity.this);
            dotDialog.show();
            ServiceCaller serviceCaller = new ServiceCaller(this);
            serviceCaller.callPartySaveService(dataUrl, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    Intent intent = new Intent(OTPActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    SharedPreferences.Editor editor = getSharedPreferences("SaveDataPre", MODE_PRIVATE).edit();
                    editor.clear();
                    editor.commit();

                    SharedPreferences.Editor Partyeditor = getSharedPreferences("PartySaveDataPre", MODE_PRIVATE).edit();
                    Partyeditor.clear();
                    Partyeditor.commit();
                   /* if (isComplete) {

                    } else {
                        Utility.alertForErrorMessage(Contants.Error, OTPActivity.this);
                    }*/
                    if (dotDialog.isShowing()) {
                        dotDialog.dismiss();
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, this);//off line msg....
        }
    }
}
