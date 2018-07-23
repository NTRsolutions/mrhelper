package com.apitechnosoft.mrhelper.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.ContentData;
import com.apitechnosoft.mrhelper.models.ContentServicelist;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class AddressActivity extends AppCompatActivity {
    private Toolbar toolbar;
    EditText houneNo, location, landMark, name, mobileNo, password, emailaddress;
    String houneNoStr, locationstr, landMarkStr, nameStr, mobileNostr, passwordstr, emailaddressStr, timeViewStr, caladerDateStr;
    TextView caladerDate, timeView;
    String ServiceName;
    float totalAmount;
    String selectedValueSno;
    int NavigationFlag;
    int sNo, NoOfService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(AddressActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView() {
        NavigationFlag = getIntent().getIntExtra("NavigationFlag", 0);
        //come from repair screen
        if (NavigationFlag == 2) {
            ServiceName = getIntent().getStringExtra("ServiceName");
            totalAmount = getIntent().getFloatExtra("totalAmount", 0);
            sNo = getIntent().getIntExtra("Sno", 0);
            selectedValueSno = getIntent().getStringExtra("selectedValue");
            NoOfService = getIntent().getIntExtra("NoOfService", 0);
        }
        //come from party screen
        if (NavigationFlag == 1) {
            SharedPreferences prefs = getSharedPreferences("PartySaveDataPre", MODE_PRIVATE);
            ServiceName = prefs.getString("heading", "");
            sNo = getIntent().getIntExtra("Sno", 0);
        }
        TextView wheredo = (TextView) toolbar.findViewById(R.id.wheredo);
        wheredo.setText("Best " + ServiceName + " in This Area");

        houneNo = (EditText) findViewById(R.id.houneNo);
        location = (EditText) findViewById(R.id.location);
        landMark = (EditText) findViewById(R.id.landMark);
        caladerDate = (TextView) findViewById(R.id.caladerDate);
        timeView = (TextView) findViewById(R.id.timeView);
        name = (EditText) findViewById(R.id.name);
        mobileNo = (EditText) findViewById(R.id.mobileNo);
        password = (EditText) findViewById(R.id.password);
        emailaddress = (EditText) findViewById(R.id.emailaddress);
        LinearLayout nextButtonLayout = (LinearLayout) findViewById(R.id.nextButtonLayout);

        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");
        TextView arrowicon = (TextView) findViewById(R.id.arrowicon);
        arrowicon.setTypeface(materialdesignicons_font);
        arrowicon.setText(Html.fromHtml("&#xf054;"));
        nextButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate()) {
                    AddData();
                    getOtpservice();
                }
            }
        });

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        final SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                caladerDate.setText(sdf.format(myCalendar.getTime()));
            }
        };
        caladerDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddressActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        final SimpleDateFormat sdfTime = new SimpleDateFormat("HH.mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                myCalendar.set(Calendar.MINUTE, minute);
                timeView.setText(sdfTime.format(myCalendar.getTime()));
            }
        };
        timeView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(AddressActivity.this, time, myCalendar
                        .get(Calendar.HOUR_OF_DAY), myCalendar
                        .get(Calendar.MINUTE), true).show();
            }
        });
    }

    private boolean isValidate() {

        String emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        houneNoStr = houneNo.getText().toString();
        locationstr = location.getText().toString();
        landMarkStr = landMark.getText().toString();
        nameStr = name.getText().toString();
        mobileNostr = mobileNo.getText().toString();
        passwordstr = password.getText().toString();
        emailaddressStr = emailaddress.getText().toString();

        caladerDateStr = caladerDate.getText().toString();
        timeViewStr = timeView.getText().toString();

        if (houneNoStr.length() == 0) {
            Toast.makeText(this, "Please Enter Houne No", Toast.LENGTH_LONG).show();
            return false;
        } else if (locationstr.length() == 0) {
            Toast.makeText(this, "Please Enter Location", Toast.LENGTH_LONG).show();
            return false;
        } else if (landMarkStr.length() == 0) {
            Toast.makeText(this, "Please Enter Land Mark", Toast.LENGTH_LONG).show();
            return false;
        } else if (nameStr.length() == 0) {
            Toast.makeText(this, "Please Enter Name", Toast.LENGTH_LONG).show();
            return false;
        } else if (mobileNostr.length() == 0) {
            Toast.makeText(this, "Please Enter Mobile No", Toast.LENGTH_LONG).show();
            return false;
        }  else if (mobileNostr.length() != 10) {
            Toast.makeText(this, "Please Enter valid Mobile No", Toast.LENGTH_LONG).show();
            return false;
        }

        else if (passwordstr.length() == 0) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_LONG).show();
            return false;
        } else if (emailaddressStr.length() == 0) {
            Toast.makeText(this, "Please Enter Email Id", Toast.LENGTH_LONG).show();
            return false;
        } else if (!emailaddressStr.matches(emailRegex)) {
            Toast.makeText(this, "Please Enter Valid Email Id", Toast.LENGTH_LONG).show();
            return false;
        } else if (caladerDateStr.length() == 0) {
            Toast.makeText(this, "Please select date", Toast.LENGTH_LONG).show();
            return false;
        } else if (timeViewStr.length() == 0) {
            Toast.makeText(this, "Please select time", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    //for hid keyboard when tab outside edittext box
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    private void AddData() {
        SharedPreferences.Editor editor = getSharedPreferences("SaveDataPre", MODE_PRIVATE).edit();
        editor.putString("houneNoStr", houneNoStr);
        editor.putString("locationstr", locationstr);
        editor.putString("landMarkStr", landMarkStr);
        editor.putString("nameStr", nameStr);
        editor.putString("mobileNostr", mobileNostr);
        editor.putString("passwordstr", passwordstr);
        editor.putString("emailaddressStr", emailaddressStr);
        editor.putString("timeViewStr", timeViewStr);
        editor.putString("caladerDateStr", caladerDateStr);
        editor.putFloat("totalAmount", totalAmount);
        editor.putInt("Sno", sNo);
        editor.putString("selectedValueSno", selectedValueSno);
        editor.putInt("NoOfService", NoOfService);
        editor.commit();
    }

    private void getOtpservice() {
        if (Utility.isOnline(this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(AddressActivity.this);
            dotDialog.show();
            ServiceCaller serviceCaller = new ServiceCaller(this);
            serviceCaller.callOtpService(mobileNostr, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    ContentData data = new Gson().fromJson(result, ContentData.class);
                    if (data != null) {
                        if (data.getOtp() != null) {
                            Intent intent = new Intent(AddressActivity.this, OTPActivity.class);
                            intent.putExtra("ServiceName", ServiceName);
                            intent.putExtra("Phone", mobileNostr);
                            intent.putExtra("NavigationFlag", NavigationFlag);
                            intent.putExtra("OTP", data.getOtp());
                            startActivity(intent);
                        } else {
                            Utility.alertForErrorMessage(Contants.Error, AddressActivity.this);
                        }
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, AddressActivity.this);
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
}
