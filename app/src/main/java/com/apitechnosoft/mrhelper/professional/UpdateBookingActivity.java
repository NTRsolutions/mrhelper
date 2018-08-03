package com.apitechnosoft.mrhelper.professional;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.activity.SplashScreenActivity;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.ContentResponce;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;

public class UpdateBookingActivity extends AppCompatActivity {
    LinearLayout otpLayout, paymentLayout;
    String sNo;
    String JobId;
    String phoneNo;
    String otp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_booking);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        initView();
    }

    private void initView() {
        final EditText otpno = (EditText) findViewById(R.id.otpno);
        TextView ok = (TextView) findViewById(R.id.Ok);
        final EditText amount = (EditText) findViewById(R.id.amount);
        TextView complete = (TextView) findViewById(R.id.complete);
        paymentLayout = (LinearLayout) findViewById(R.id.paymentLayout);
        otpLayout = (LinearLayout) findViewById(R.id.otpLayout);

        sNo = getIntent().getStringExtra("sNumber");
        JobId = getIntent().getStringExtra("JobId");
        phoneNo = getIntent().getStringExtra("PhooneNo");
        otpRequest();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otpstr = otpno.getText().toString();
                if (otp.equals(otpstr) && !otp.equals("0")) {
                    otpLayout.setVisibility(View.GONE);
                    paymentLayout.setVisibility(View.VISIBLE);
                    Toast.makeText(UpdateBookingActivity.this, "OTP verified Successfully !", Toast.LENGTH_LONG).show();
                } else {
                    otpLayout.setVisibility(View.VISIBLE);
                    paymentLayout.setVisibility(View.GONE);
                    Toast.makeText(UpdateBookingActivity.this, "Client OTP not Match!", Toast.LENGTH_LONG).show();
                }
            }
        });
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountStr = amount.getText().toString();
                if (Double.parseDouble(amountStr) > 0) {
                    Toast.makeText(UpdateBookingActivity.this, "Please enter amount", Toast.LENGTH_LONG).show();
                } else {
                    UpdateBooking(amountStr);
                }
            }
        });
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(UpdateBookingActivity.this)) {

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void otpRequest() {
        if (Utility.isOnline(UpdateBookingActivity.this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(UpdateBookingActivity.this);
            dotDialog.show();

            ServiceCaller serviceCaller = new ServiceCaller(UpdateBookingActivity.this);
            serviceCaller.callOtpSrvice(sNo, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        ContentResponce data = new Gson().fromJson(result, ContentResponce.class);
                        if (data != null) {
                            if (data.getOrderbookedengineerwise() != null) {
                                otp = data.getOrderbookedengineerwise().getProf_otp();
                            } else {
                                Toast.makeText(UpdateBookingActivity.this, "Client OTP not found!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(UpdateBookingActivity.this, "Client OTP not found!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, UpdateBookingActivity.this);
                    }
                    if (dotDialog.isShowing()) {
                        dotDialog.dismiss();
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, UpdateBookingActivity.this);//off line msg....
        }
    }

    private void UpdateBooking(String amountStr) {
        if (Utility.isOnline(UpdateBookingActivity.this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(UpdateBookingActivity.this);
            dotDialog.show();

            ServiceCaller serviceCaller = new ServiceCaller(UpdateBookingActivity.this);
            serviceCaller.callUpdateBookingSrvice(amountStr, sNo, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        ContentResponce data = new Gson().fromJson(result, ContentResponce.class);
                        if (data != null) {
                            if (data.isStatus()) {
                                Toast.makeText(UpdateBookingActivity.this, "Service has been completed Successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(UpdateBookingActivity.this, FeedbackActivity.class);
                                intent.putExtra("sNumber", sNo);
                                intent.putExtra("JobId", JobId);
                                intent.putExtra("PhooneNo", phoneNo);
                                startActivity(intent);
                            } else {
                                Toast.makeText(UpdateBookingActivity.this, "Service has not been completed!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(UpdateBookingActivity.this, "Service has not been completed!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, UpdateBookingActivity.this);
                    }
                    if (dotDialog.isShowing()) {
                        dotDialog.dismiss();
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, UpdateBookingActivity.this);//off line msg....
        }
    }

}
