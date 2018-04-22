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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.models.PartyMServicesdata;
import com.apitechnosoft.mrhelper.models.PartyMServicesdataFour;
import com.apitechnosoft.mrhelper.models.PartyMServicesdataOne;
import com.apitechnosoft.mrhelper.models.PartyMServicesdataThree;
import com.apitechnosoft.mrhelper.models.PartyMServicesdataTwo;
import com.apitechnosoft.mrhelper.models.PartyServiceContentData;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;

public class PartyMServicesdataOneActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    TextView wheredo, subHeading;
    private boolean isfirst = false;
    private boolean isSecond = false;
    private boolean isThree = false;
    private boolean isFour = false;
    private boolean isFive = false;
    TextView radiobuton5, radiobuton4, radiobuton3, radiobuton2, radiobuton1;
    TextView firstOption, secondOption, thirdOption, fourthOption, fifthOption;
    String heading = "";
    String sunheading = "";
    PartyMServicesdataOne partyMServicesdataOne;
    PartyMServicesdataTwo partyMServicesdataTow;
    PartyMServicesdataThree partyMServicesdataThree;
    PartyMServicesdataFour partyMServicesdataFour;
    PartyMServicesdata partyMServicesdata;
    PartyServiceContentData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_mservicesdata_one);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        initView();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(PartyMServicesdataOneActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView() {

        heading = getIntent().getStringExtra("heading");
        String contentData = getIntent().getStringExtra("contentData");
        data = new Gson().fromJson(contentData, PartyServiceContentData.class);

        wheredo = (TextView) toolbar.findViewById(R.id.wheredo);
        subHeading = (TextView) findViewById(R.id.subHeading);

        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");

           /* ArrayList<Locationreportdata> locationList = new Gson().fromJson(locationstr, new TypeToken<ArrayList<Locationreportdata>>() {
            }.getType());*/

        // PartyServiceAdapter mAdapter = new PartyServiceAdapter(PartyServiceActivity.this, list);
        //  recyclerView.setAdapter(mAdapter);

        TextView arrowicon = (TextView) findViewById(R.id.arrowicon);
        arrowicon.setTypeface(materialdesignicons_font);
        arrowicon.setText(Html.fromHtml("&#xf054;"));
        radiobuton5 = (TextView) findViewById(R.id.radiobuton5);
        fifthOption = (TextView) findViewById(R.id.fifthOption);
        radiobuton4 = (TextView) findViewById(R.id.radiobuton4);
        fourthOption = (TextView) findViewById(R.id.fourthOption);
        thirdOption = (TextView) findViewById(R.id.thirdOption);
        radiobuton3 = (TextView) findViewById(R.id.radiobuton3);
        secondOption = (TextView) findViewById(R.id.secondOption);
        radiobuton2 = (TextView) findViewById(R.id.radiobuton2);
        firstOption = (TextView) findViewById(R.id.firstOption);
        radiobuton1 = (TextView) findViewById(R.id.radiobuton1);

        radiobuton5.setTypeface(materialdesignicons_font);
        radiobuton4.setTypeface(materialdesignicons_font);
        radiobuton3.setTypeface(materialdesignicons_font);
        radiobuton2.setTypeface(materialdesignicons_font);
        radiobuton1.setTypeface(materialdesignicons_font);


        LinearLayout nextButtonLayout = (LinearLayout) findViewById(R.id.nextButtonLayout);
        LinearLayout radioLayout5 = (LinearLayout) findViewById(R.id.radioLayout5);
        LinearLayout radioLayout4 = (LinearLayout) findViewById(R.id.radioLayout4);
        LinearLayout radioLayout3 = (LinearLayout) findViewById(R.id.radioLayout3);
        LinearLayout radioLayout2 = (LinearLayout) findViewById(R.id.radioLayout2);
        LinearLayout radioLayout1 = (LinearLayout) findViewById(R.id.radioLayout1);
        radioLayout1.setOnClickListener(this);
        radioLayout2.setOnClickListener(this);
        radioLayout3.setOnClickListener(this);
        radioLayout4.setOnClickListener(this);
        radioLayout5.setOnClickListener(this);
        nextButtonLayout.setOnClickListener(this);
        setRadioValue();
        parsePartyData();
    }

    private void parsePartyData() {
        if (data != null) {
            partyMServicesdata = data.getPartyMServicesdata();
            partyMServicesdataOne = data.getPartyMServicesdataOne();
            partyMServicesdataTow = data.getPartyMServicesdataTwo();
            partyMServicesdataThree = data.getPartyMServicesdataThree();
            partyMServicesdataFour = data.getPartyMServicesdataFour();
            if (partyMServicesdataOne != null) {
                sunheading = partyMServicesdataOne.getSubHeading();
                wheredo.setText(heading);
                subHeading.setText(sunheading);
                firstOption.setText(partyMServicesdataOne.getOption());
                secondOption.setText(partyMServicesdataOne.getOption2());
                thirdOption.setText(partyMServicesdataOne.getOption3());
                fourthOption.setText(partyMServicesdataOne.getOption4());
                fifthOption.setText(partyMServicesdataOne.getOption5());
            }
        }
    }

    private void setRadioValue() {
        radiobuton1.setText(Html.fromHtml("&#xf130;"));
        radiobuton2.setText(Html.fromHtml("&#xf130;"));
        radiobuton3.setText(Html.fromHtml("&#xf130;"));
        radiobuton4.setText(Html.fromHtml("&#xf130;"));
        radiobuton5.setText(Html.fromHtml("&#xf130;"));
    }

    private void setRadioValue1() {
        isfirst = true;
        isSecond = false;
        isThree = false;
        isFour = false;
        isFive = false;
        radiobuton1.setText(Html.fromHtml("&#xf134;"));
        radiobuton2.setText(Html.fromHtml("&#xf130;"));
        radiobuton3.setText(Html.fromHtml("&#xf130;"));
        radiobuton4.setText(Html.fromHtml("&#xf130;"));
        radiobuton5.setText(Html.fromHtml("&#xf130;"));
    }

    private void setRadioValue2() {
        isfirst = false;
        isSecond = true;
        isThree = false;
        isFour = false;
        isFive = false;
        radiobuton2.setText(Html.fromHtml("&#xf134;"));
        radiobuton1.setText(Html.fromHtml("&#xf130;"));
        radiobuton3.setText(Html.fromHtml("&#xf130;"));
        radiobuton4.setText(Html.fromHtml("&#xf130;"));
        radiobuton5.setText(Html.fromHtml("&#xf130;"));
    }

    private void setRadioValue3() {
        isfirst = false;
        isSecond = false;
        isThree = true;
        isFour = false;
        isFive = false;
        radiobuton3.setText(Html.fromHtml("&#xf134;"));
        radiobuton1.setText(Html.fromHtml("&#xf130;"));
        radiobuton2.setText(Html.fromHtml("&#xf130;"));
        radiobuton4.setText(Html.fromHtml("&#xf130;"));
        radiobuton5.setText(Html.fromHtml("&#xf130;"));
    }

    private void setRadioValue4() {
        isfirst = false;
        isSecond = false;
        isThree = false;
        isFour = true;
        isFive = false;
        radiobuton4.setText(Html.fromHtml("&#xf134;"));
        radiobuton1.setText(Html.fromHtml("&#xf130;"));
        radiobuton3.setText(Html.fromHtml("&#xf130;"));
        radiobuton2.setText(Html.fromHtml("&#xf130;"));
        radiobuton5.setText(Html.fromHtml("&#xf130;"));
    }

    private void setRadioValue5() {
        isfirst = false;
        isSecond = false;
        isThree = false;
        isFour = false;
        isFive = true;
        radiobuton5.setText(Html.fromHtml("&#xf134;"));
        radiobuton1.setText(Html.fromHtml("&#xf130;"));
        radiobuton3.setText(Html.fromHtml("&#xf130;"));
        radiobuton4.setText(Html.fromHtml("&#xf130;"));
        radiobuton2.setText(Html.fromHtml("&#xf130;"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radioLayout1:
                setRadioValue1();
                break;
            case R.id.radioLayout2:
                setRadioValue2();
                break;
            case R.id.radioLayout3:
                setRadioValue3();
                break;
            case R.id.radioLayout4:
                setRadioValue4();
                break;
            case R.id.radioLayout5:
                setRadioValue5();
                break;
            case R.id.nextButtonLayout:
                if (isfirst || isSecond || isThree || isFour || isFive) {
                    setValue();
                } else {
                    Utility.alertForErrorMessage("Please select anyone option!", PartyMServicesdataOneActivity.this);
                }
                break;
        }
    }

    private void setValue() {
        SharedPreferences.Editor editor = getSharedPreferences("PartySaveDataPre", MODE_PRIVATE).edit();
        editor.putString("Step1", selectedValue());
        editor.commit();
        if (!partyMServicesdataTow.getSno().equals("0") && partyMServicesdataTow.getOption() != null && !partyMServicesdataTow.getOption().equals("")) {
            Intent intent = new Intent(PartyMServicesdataOneActivity.this, PartyMServicesdataTwoActivity.class);
            String option2Str = new Gson().toJson(partyMServicesdataTow);
            intent.putExtra("Option2", option2Str);
            intent.putExtra("heading", heading);
            startActivity(intent);
        } else {
            Intent intent = new Intent(PartyMServicesdataOneActivity.this, AddressActivity.class);
            intent.putExtra("NavigationFlag", 1);
            startActivity(intent);
        }
    }

    private String selectedValue() {
        String selectvalue = "";
        if (partyMServicesdataOne != null) {
            if (isfirst) {
                selectvalue = partyMServicesdataOne.getOption();
            } else if (isSecond) {
                selectvalue = partyMServicesdataOne.getOption2();
            } else if (isThree) {
                selectvalue = partyMServicesdataOne.getOption3();
            } else if (isFour) {
                selectvalue = partyMServicesdataOne.getOption4();
            } else if (isFive) {
                selectvalue = partyMServicesdataOne.getOption5();
            }
        }
        return selectvalue;
    }
}
