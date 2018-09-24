package com.apitechnosoft.mrhelper.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.ContentResponce;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;

public class PaymentConfirmActivity extends AppCompatActivity implements View.OnClickListener {
    private Typeface materialdesignicons_font;
    int NavigationFlag;
    String ServiceName;
    private String dataUrl;
    TextView totalservice, serviceamount, amountservicetax;
    String usermobileNo;
    String paymentMode = "";
    double aftertaxamount = 0;
    String emailaddressStr;
    String nameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirm);
        chechPortaitAndLandSacpe();
        initView();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(PaymentConfirmActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView() {
        materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(getApplicationContext(), "fonts/materialdesignicons-webfont.otf");
        TextView font_cash = (TextView) findViewById(R.id.font_cash);
        TextView payonline = (TextView) findViewById(R.id.arrow_icon);
        font_cash.setTypeface(materialdesignicons_font);
        font_cash.setText(Html.fromHtml("&#xf1af;"));
        payonline.setTypeface(materialdesignicons_font);
        payonline.setText(Html.fromHtml("&#xf19c;"));
        LinearLayout onlineLayout = (LinearLayout) findViewById(R.id.onlineLayout);
        onlineLayout.setOnClickListener(this);
        LinearLayout codLayout = (LinearLayout) findViewById(R.id.codLayout);
        codLayout.setOnClickListener(this);

        totalservice = (TextView) findViewById(R.id.totalservice);
        serviceamount = (TextView) findViewById(R.id.serviceamount);
        amountservicetax = (TextView) findViewById(R.id.amountservicetax);
        EditText promocode = (EditText) findViewById(R.id.promocode);

        NavigationFlag = getIntent().getIntExtra("NavigationFlag", 0);

        ServiceName = getIntent().getStringExtra("ServiceName");
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.onlineLayout:
                paymentMode = "Online";
                COD();
                break;
            case R.id.codLayout:
                paymentMode = "COD";
                COD();
                break;
        }
    }

    private void COD() {
        if (NavigationFlag == 1) {
            getSavePartyService();
        } else {
            getSaveService();
        }
    }

    private void getData() {
        SharedPreferences prefs = getSharedPreferences("SaveDataPre", MODE_PRIVATE);
        String houneNoStr = prefs.getString("houneNoStr", "");
        String locationstr = prefs.getString("locationstr", "");
        String landMarkStr = prefs.getString("landMarkStr", "");
        nameStr = prefs.getString("nameStr", "");
        String mobileNostr = prefs.getString("mobileNostr", "");
        usermobileNo = mobileNostr;
        String passwordstr = prefs.getString("passwordstr", "");
        emailaddressStr = prefs.getString("emailaddressStr", "");
        String timeViewStr = prefs.getString("timeViewStr", "");
        String caladerDateStr = prefs.getString("caladerDateStr", "");
        float totalAmount = prefs.getFloat("totalAmount", 0);
        String selectedValueSno = prefs.getString("selectedValueSno", "");
        int sno = prefs.getInt("Sno", 0);
        int NoOfService = prefs.getInt("NoOfService", 0);
        double gstTax = ((18 / 100) * totalAmount);
        aftertaxamount = gstTax + totalAmount;
        if (NoOfService != 0) {
            totalservice.setText(NoOfService + "");
        }
        if (totalAmount != 0) {
            serviceamount.setText(totalAmount + "");
        }
        if (aftertaxamount != 0) {
            amountservicetax.setText(aftertaxamount + "");
        }

        dataUrl = "servicesno=" + sno + "&password=" + passwordstr + "&email=" + emailaddressStr + "&section_home_service_sno=" + selectedValueSno + "&totalservice=" + NoOfService + "&name=" + nameStr + "&mobileno=" + mobileNostr + "&serviceamount=" + totalAmount + "&aftertaxamount=" + aftertaxamount + " &servicetime=" + timeViewStr + "&servicedate=" + caladerDateStr + "&houseno=" + houneNoStr + "&location=" + locationstr + "&landmark=" + landMarkStr + "&servicename=" + sno + "";
    }

    private void getPartyStepData() {
        SharedPreferences prefs = getSharedPreferences("SaveDataPre", MODE_PRIVATE);
        String houneNoStr = prefs.getString("houneNoStr", "");
        String locationstr = prefs.getString("locationstr", "");
        String landMarkStr = prefs.getString("landMarkStr", "");
        nameStr = prefs.getString("nameStr", "");
        String mobileNostr = prefs.getString("mobileNostr", "");
        usermobileNo = mobileNostr;
        String passwordstr = prefs.getString("passwordstr", "");
        emailaddressStr = prefs.getString("emailaddressStr", "");
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

    private void getSaveService() {
        if (Utility.isOnline(this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(PaymentConfirmActivity.this);
            dotDialog.show();
            ServiceCaller serviceCaller = new ServiceCaller(this);
            String url = dataUrl + "+&paymentmode=" + paymentMode + "";
            serviceCaller.callSaveService(url, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    ContentResponce data = new Gson().fromJson(result, ContentResponce.class);
                    if (data != null) {
                        if (data.isStatus()) {
                            String jobId = data.getJobid();
                            if (paymentMode.equals("Online")) {
                                Utility.setUserPhoneNo(PaymentConfirmActivity.this, usermobileNo);
                                Intent intent = new Intent(PaymentConfirmActivity.this, PayMentGateWay.class);
                                intent.putExtra("jobId", jobId);
                                intent.putExtra("UsePhoneNO", usermobileNo);
                                intent.putExtra("Amount", String.valueOf(aftertaxamount));
                                intent.putExtra("Email", emailaddressStr);
                                intent.putExtra("Name", nameStr);
                                startActivity(intent);
                            } else {
                                codPaymentDone();
                            }
                        } else {
                            Utility.alertForErrorMessage("Your order has not been placed successfully!", PaymentConfirmActivity.this);
                        }
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, PaymentConfirmActivity.this);
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


    private void getSavePartyService() {
        if (Utility.isOnline(this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(PaymentConfirmActivity.this);
            dotDialog.show();
            ServiceCaller serviceCaller = new ServiceCaller(this);
            String url = dataUrl + "+&paymentmode=" + paymentMode + "";
            serviceCaller.callPartySaveService(url, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    ContentResponce data = new Gson().fromJson(result, ContentResponce.class);
                    if (data != null) {
                        if (data.isStatus()) {
                            codPaymentDone();
                        } else {
                            Utility.alertForErrorMessage("Your order has not been placed successfully!", PaymentConfirmActivity.this);
                        }
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, PaymentConfirmActivity.this);
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

    private void codPaymentDone() {
        Toast.makeText(PaymentConfirmActivity.this, "Your order has been placed successfully.", Toast.LENGTH_LONG).show();
        Utility.setUserPhoneNo(PaymentConfirmActivity.this, usermobileNo);

        Intent intent = new Intent(PaymentConfirmActivity.this, ThankYouActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        SharedPreferences.Editor editor = getSharedPreferences("SaveDataPre", MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();

        SharedPreferences.Editor Partyeditor = getSharedPreferences("PartySaveDataPre", MODE_PRIVATE).edit();
        Partyeditor.clear();
        Partyeditor.commit();
    }
}
