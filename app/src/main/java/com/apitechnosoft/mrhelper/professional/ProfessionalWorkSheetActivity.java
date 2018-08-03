package com.apitechnosoft.mrhelper.professional;

import android.content.pm.ActivityInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.activity.MyBookingActivity;
import com.apitechnosoft.mrhelper.adapters.MyBookingPagerAdapter;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.database.DbHelper;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.ContentMybooking;
import com.apitechnosoft.mrhelper.models.OrderBookedListEngineerWise;
import com.apitechnosoft.mrhelper.models.PartnerDetailsForPartner;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfessionalWorkSheetActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_work_sheet);
        chechPortaitAndLandSacpe();
        initView();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(ProfessionalWorkSheetActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView() {


        viewPager = (ViewPager) findViewById(R.id.pager);
        getOrderBookedList();
    }

    private void setPager(String listStr) {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Pending"));
        tabLayout.addTab(tabLayout.newTab().setText("Completed"));
        //tabLayout.addTab(tabLayout.newTab().setText("CANCELLED"));
        //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ProfessionalWorkSheetAdapter adapter = new ProfessionalWorkSheetAdapter(getSupportFragmentManager(), tabLayout.getTabCount(),listStr);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void getOrderBookedList() {
        if (Utility.isOnline(ProfessionalWorkSheetActivity.this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(ProfessionalWorkSheetActivity.this);
            dotDialog.show();
            DbHelper dbhelper = new DbHelper(ProfessionalWorkSheetActivity.this);
            PartnerDetailsForPartner data = dbhelper.getProfessionalUserData();
            String requestUrl = null;
            if (data != null) {
                requestUrl = "servicesno=" + data.getService() + "&" + "type=" + "&" + "provider=Y" + "&" + "booking=" + "&" + "mobile=" + data.getMobileNo();
            }

            ServiceCaller serviceCaller = new ServiceCaller(ProfessionalWorkSheetActivity.this);
            serviceCaller.callOrderBookedListService(requestUrl, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        ContentMybooking data = new Gson().fromJson(result, ContentMybooking.class);
                        if (data != null) {
                            if (data.getOrderBookedListEngineerWise() != null && data.getOrderBookedListEngineerWise().length > 0) {
                                ArrayList<OrderBookedListEngineerWise> jobList = new ArrayList<OrderBookedListEngineerWise>(Arrays.asList(data.getOrderBookedListEngineerWise()));
                                String listStr=new Gson().toJson(jobList);
                                setPager(listStr);
                            } else {
                                Toast.makeText(ProfessionalWorkSheetActivity.this, "No Job assigned to you!", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, ProfessionalWorkSheetActivity.this);
                    }
                    if (dotDialog.isShowing()) {
                        dotDialog.dismiss();
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, ProfessionalWorkSheetActivity.this);//off line msg....
        }

    }

}
