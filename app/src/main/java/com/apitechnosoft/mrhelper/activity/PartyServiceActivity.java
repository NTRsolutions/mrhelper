package com.apitechnosoft.mrhelper.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.adapters.PartyServiceAdapter;
import com.apitechnosoft.mrhelper.adapters.SelectLocationAdapter;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.Locationreportdata;
import com.apitechnosoft.mrhelper.models.PartyMServicesdata;
import com.apitechnosoft.mrhelper.models.PartyMServicesdataFour;
import com.apitechnosoft.mrhelper.models.PartyMServicesdataOne;
import com.apitechnosoft.mrhelper.models.PartyMServicesdataThree;
import com.apitechnosoft.mrhelper.models.PartyMServicesdataTwo;
import com.apitechnosoft.mrhelper.models.PartyServiceContentData;
import com.apitechnosoft.mrhelper.models.RepairContentData;
import com.apitechnosoft.mrhelper.models.SalModel;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class PartyServiceActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    private int sNumber;
    TextView wheredo, subHeading;
    String serviceName;
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
        setContentView(R.layout.activity_party_service);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        initView();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(PartyServiceActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView() {

        sNumber = getIntent().getIntExtra("sNumber", 0);

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
        getPartydata();
    }

    private void getPartydata() {
        if (Utility.isOnline(this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(PartyServiceActivity.this);
            dotDialog.show();
            ServiceCaller serviceCaller = new ServiceCaller(this);
            serviceCaller.callPartyService(sNumber, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        parsePartyData(result);
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, PartyServiceActivity.this);
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


    private void parsePartyData(String result) {
        data = new Gson().fromJson(result, PartyServiceContentData.class);
        if (data != null) {
            partyMServicesdata = data.getPartyMServicesdata();
            partyMServicesdataOne = data.getPartyMServicesdataOne();
            partyMServicesdataTow = data.getPartyMServicesdataTwo();
            partyMServicesdataThree = data.getPartyMServicesdataThree();
            partyMServicesdataFour = data.getPartyMServicesdataFour();
            if (partyMServicesdata != null) {
                heading = partyMServicesdata.getHeading();
                sunheading = partyMServicesdata.getSubHeading();
                wheredo.setText(heading);
                subHeading.setText(sunheading);
                serviceName = partyMServicesdata.getServiceName();
                firstOption.setText(partyMServicesdata.getOption());
                secondOption.setText(partyMServicesdata.getOption2());
                thirdOption.setText(partyMServicesdata.getOption3());
                fourthOption.setText(partyMServicesdata.getOption4());
                fifthOption.setText(partyMServicesdata.getOption5());
            }
        }
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
                    alertForErrorMessage();
                } else {
                    Utility.alertForErrorMessage("Please select anyone option!", PartyServiceActivity.this);
                }
                break;
        }
    }

    public void alertForErrorMessage() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(PartyServiceActivity.this);
        Typeface roboto_regular = Typeface.createFromAsset(getAssets(), "fonts/roboto.regular.ttf");
        final AlertDialog alert = builder.create();
        // alert.getWindow().getAttributes().windowAnimations = R.style.alertAnimation;
        View view = alert.getLayoutInflater().inflate(R.layout.custom_popup_alert, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setTypeface(roboto_regular);
        TextView ok = (TextView) view.findViewById(R.id.Ok);
        TextView heding = (TextView) view.findViewById(R.id.heding);
        TextView Cancel = (TextView) view.findViewById(R.id.Cancel);
        ok.setTypeface(roboto_regular);
        heding.setText(heading);
        title.setText("We will ask a few questions to connect you with the right " + heading);
        alert.setCustomTitle(view);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("PartySaveDataPre", MODE_PRIVATE).edit();
                editor.putString("serviceName", serviceName);
                editor.putString("Step", selectedValue());
                editor.putString("heading", heading);
                editor.putInt("Sno", sNumber);
                editor.commit();
                if (isfirst) {
                    if (!partyMServicesdataOne.getSno().equals("0") && partyMServicesdataOne.getOption() != null && !partyMServicesdataOne.getOption().equals("")) {
                        Intent intent = new Intent(PartyServiceActivity.this, PartyMServicesdataOneActivity.class);
                        String contentData = new Gson().toJson(data);
                        intent.putExtra("contentData", contentData);
                        intent.putExtra("heading", heading);
                        startActivity(intent);
                    } else {
                        openAddressScreen();
                    }
                } else if (isSecond) {
                    if (!partyMServicesdataTow.getSno().equals("0") && partyMServicesdataTow.getOption() != null && !partyMServicesdataTow.getOption().equals("")) {
                        Intent intent = new Intent(PartyServiceActivity.this, PartyMServicesdataTwoActivity.class);
                        String contentData = new Gson().toJson(data);
                        intent.putExtra("contentData", contentData);
                        intent.putExtra("heading", heading);
                        startActivity(intent);
                    } else {
                        openAddressScreen();
                    }
                } else if (isThree) {
                    if (!partyMServicesdataThree.getSno().equals("0") && partyMServicesdataThree.getOption() != null && !partyMServicesdataThree.getOption().equals("")) {

                        Intent intent = new Intent(PartyServiceActivity.this, PartyMServicesdataThreeActivity.class);
                        String contentData = new Gson().toJson(data);
                        intent.putExtra("contentData", contentData);
                        intent.putExtra("heading", heading);
                        startActivity(intent);
                    } else {
                        openAddressScreen();
                    }
                } else if (isFour) {
                    if (!partyMServicesdataFour.getSno().equals("0") && partyMServicesdataFour.getOption() != null && !partyMServicesdataFour.getOption().equals("")) {

                        Intent intent = new Intent(PartyServiceActivity.this, PartyMServicesdataFourActivity.class);
                        String contentData = new Gson().toJson(data);
                        intent.putExtra("contentData", contentData);
                        intent.putExtra("heading", heading);
                        startActivity(intent);
                    } else {
                        openAddressScreen();
                    }
                } else {
                    openAddressScreen();
                }

                alert.dismiss();
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        alert.show();
    }

    //open address screen dirct when no step found
    private void openAddressScreen() {
        Intent intent = new Intent(PartyServiceActivity.this, AddressActivity.class);
        intent.putExtra("NavigationFlag", 1);
        startActivity(intent);
    }
   /* public void alertNative() {
        android.support.v7.app.AlertDialog.Builder builder =
                new android.support.v7.app.AlertDialog.Builder(PartyServiceActivity.this, R.style.AlertDialogTheme);
        final android.support.v7.app.AlertDialog dialog = builder.create();
        //dialog.getWindow().getAttributes().windowAnimations = R.style.alertAnimation;
        dialog.setMessage("We will ask a few questions to connect you with the right " + heading);
        dialog.setTitle(heading);
        dialog.setButton(Dialog.BUTTON_POSITIVE, "Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#ffffff"));
        dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#ffffff"));
    }*/

    private String selectedValue() {
        String selectvalue = "";
        if (partyMServicesdata != null) {
            if (isfirst) {
                selectvalue = partyMServicesdata.getOption();
            } else if (isSecond) {
                selectvalue = partyMServicesdata.getOption2();
            } else if (isThree) {
                selectvalue = partyMServicesdata.getOption3();
            } else if (isFour) {
                selectvalue = partyMServicesdata.getOption4();
            } else if (isFive) {
                selectvalue = partyMServicesdata.getOption5();
            }
        }
        return selectvalue;
    }

}
