package com.apitechnosoft.mrhelper.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.adapters.RecommendedServiceAdapter;
import com.apitechnosoft.mrhelper.adapters.LocationListAdapter;
import com.apitechnosoft.mrhelper.adapters.SlidingImage_Adapter_For_ItemDetails;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.database.DbHelper;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.Bookservicelist;
import com.apitechnosoft.mrhelper.models.ContentData;
import com.apitechnosoft.mrhelper.models.ContentMybooking;
import com.apitechnosoft.mrhelper.models.Locationreportdata;
import com.apitechnosoft.mrhelper.models.Searchreportdata;
import com.apitechnosoft.mrhelper.professional.BecomeHostLoginActivity;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextMessage;
    Typeface materialdesignicons_font;
    RecyclerView recyclerView, repair_recycler_view, other_recycler_view, helpbusiness_recycler_view, party_recycler_view, health_recycler_view;
    RecyclerView wedding_recycler_view, homedesign_recycler_view, shifthome_recycler_view, homeclean_recycler_view;
    ArrayList<Searchreportdata> searchReportlist;
    ArrayList<Locationreportdata> Locationlist;
    String searchdatastr;
    NestedScrollView allserviceLayout;
    TextView yoursercive, locationName;
    boolean locationFlage = false;
    String cityName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);// Removes action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);// Removes title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        setContentView(R.layout.activity_home);
        getHomedata();
        callGetUserProfile();
        initView();
        slideimage();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(HomeActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView() {
        allserviceLayout = (NestedScrollView) findViewById(R.id.allserviceLayout);
        materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");
        TextView downarrow = (TextView) findViewById(R.id.downarrow);
        downarrow.setTypeface(materialdesignicons_font);
        downarrow.setText(Html.fromHtml("&#xf140;"));
        TextView searchicon = (TextView) findViewById(R.id.searchicon);
        searchicon.setTypeface(materialdesignicons_font);
        searchicon.setText(Html.fromHtml("&#xf349;"));
        LinearLayout searchlayout = (LinearLayout) findViewById(R.id.searchlayout);
        searchlayout.setOnClickListener(this);
        LinearLayout selectlocation = (LinearLayout) findViewById(R.id.selectlocation);
        selectlocation.setOnClickListener(this);


        TextView menusearchicon = (TextView) findViewById(R.id.menusearchicon);
        menusearchicon.setTypeface(materialdesignicons_font);
        menusearchicon.setText(Html.fromHtml("&#xf349;"));
        LinearLayout menusearch = (LinearLayout) findViewById(R.id.menusearch);
        menusearch.setOnClickListener(this);

        TextView mybookingicon = (TextView) findViewById(R.id.mybookingicon);
        mybookingicon.setTypeface(materialdesignicons_font);
        mybookingicon.setText(Html.fromHtml("&#xf0bb;"));
        LinearLayout mybooking = (LinearLayout) findViewById(R.id.mybooking);
        mybooking.setOnClickListener(this);

        TextView profileicon = (TextView) findViewById(R.id.profileicon);
        profileicon.setTypeface(materialdesignicons_font);
        profileicon.setText(Html.fromHtml("&#xf009;"));
        LinearLayout profile = (LinearLayout) findViewById(R.id.profile);
        profile.setOnClickListener(this);

        TextView hosticon = (TextView) findViewById(R.id.hosticon);
        hosticon.setTypeface(materialdesignicons_font);
        hosticon.setText(Html.fromHtml("&#xf2e8;"));
        LinearLayout becomeHost = (LinearLayout) findViewById(R.id.becomeHost);
        becomeHost.setOnClickListener(this);
        yoursercive = (TextView) findViewById(R.id.yoursercive);
        locationName = (TextView) findViewById(R.id.locationName);

        recyclerView = (RecyclerView) findViewById(R.id.recommended_recycler_view);
        setLinearLayoutManager(recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        TextView seeall = (TextView) findViewById(R.id.seeall);


        other_recycler_view = (RecyclerView) findViewById(R.id.other_recycler_view);
        other_recycler_view.setNestedScrollingEnabled(false);
        other_recycler_view.setHasFixedSize(false);
        setLinearLayoutManager(other_recycler_view);
        TextView otherseeall = (TextView) findViewById(R.id.otherseeall);

        helpbusiness_recycler_view = (RecyclerView) findViewById(R.id.helpbusiness_recycler_view);
        helpbusiness_recycler_view.setNestedScrollingEnabled(false);
        helpbusiness_recycler_view.setHasFixedSize(false);
        setLinearLayoutManager(helpbusiness_recycler_view);
        TextView helpbusinessseeall = (TextView) findViewById(R.id.helpbusinessseeall);

        party_recycler_view = (RecyclerView) findViewById(R.id.party_recycler_view);
        party_recycler_view.setNestedScrollingEnabled(false);
        party_recycler_view.setHasFixedSize(false);
        setLinearLayoutManager(party_recycler_view);
        TextView partyseeall = (TextView) findViewById(R.id.partyseeall);

        health_recycler_view = (RecyclerView) findViewById(R.id.health_recycler_view);
        health_recycler_view.setNestedScrollingEnabled(false);
        health_recycler_view.setHasFixedSize(false);
        setLinearLayoutManager(health_recycler_view);
        TextView healthseeall = (TextView) findViewById(R.id.healthseeall);

        wedding_recycler_view = (RecyclerView) findViewById(R.id.wedding_recycler_view);
        wedding_recycler_view.setNestedScrollingEnabled(false);
        wedding_recycler_view.setHasFixedSize(false);
        setLinearLayoutManager(wedding_recycler_view);
        TextView weddingseeall = (TextView) findViewById(R.id.weddingseeall);

        homedesign_recycler_view = (RecyclerView) findViewById(R.id.homedesign_recycler_view);
        homedesign_recycler_view.setNestedScrollingEnabled(false);
        homedesign_recycler_view.setHasFixedSize(false);
        setLinearLayoutManager(homedesign_recycler_view);
        TextView homedesignseeall = (TextView) findViewById(R.id.homedesignseeall);

        shifthome_recycler_view = (RecyclerView) findViewById(R.id.shifthome_recycler_view);
        shifthome_recycler_view.setNestedScrollingEnabled(false);
        shifthome_recycler_view.setHasFixedSize(false);
        setLinearLayoutManager(shifthome_recycler_view);
        TextView shifthomeseeall = (TextView) findViewById(R.id.shifthomeseeall);

        homeclean_recycler_view = (RecyclerView) findViewById(R.id.homeclean_recycler_view);
        homeclean_recycler_view.setNestedScrollingEnabled(false);
        homeclean_recycler_view.setHasFixedSize(false);
        setLinearLayoutManager(homeclean_recycler_view);
        TextView homecleanseeall = (TextView) findViewById(R.id.homecleanseeall);

        repair_recycler_view = (RecyclerView) findViewById(R.id.repair_recycler_view);
        repair_recycler_view.setNestedScrollingEnabled(false);
        repair_recycler_view.setHasFixedSize(false);
        setLinearLayoutManager(repair_recycler_view);
        TextView repairseeall = (TextView) findViewById(R.id.repairseeall);


//www.androidhive.info/2016/01/android-working-with-recycler-view/
//www.androidhive.info/2018/01/android-content-placeholder-animation-like-facebook-using-shimmer/
    }

    private void setLinearLayoutManager(RecyclerView recyclerView) {
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.searchlayout:
                if (locationFlage) {
                    Intent intent1 = new Intent(HomeActivity.this, SearchForServiceActivity.class);
                    intent1.putExtra("Service", searchdatastr);
                    intent1.putExtra("cityName", cityName);
                    startActivity(intent1);
                } else {
                    Toast.makeText(HomeActivity.this, "First select location!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.selectlocation:
                showLocationListDialog();
                break;
            case R.id.menusearch:
                openScreen(HomeActivity.class);
                break;
            case R.id.mybooking:
                openScreen(MyBookingActivity.class);
                break;
            case R.id.profile:
                openScreen(ProfileActivity.class);
                break;
            case R.id.becomeHost:
                openScreen(BecomeHostLoginActivity.class);
                break;

        }

    }

    private void openScreen(Class<?> openScreen) {
        Intent intent = new Intent(HomeActivity.this, openScreen);
        startActivity(intent);
    }


    private void getHomedata() {
        if (Utility.isOnline(this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(HomeActivity.this);
            dotDialog.show();
            ServiceCaller serviceCaller = new ServiceCaller(this);
            serviceCaller.callHomeService(new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        allserviceLayout.setVisibility(View.VISIBLE);
                        parseHomeData(result);
                    } else {
                        allserviceLayout.setVisibility(View.GONE);
                        Utility.alertForErrorMessage(Contants.Error, HomeActivity.this);
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

    private void parseHomeData(String result) {
        ContentData data = new Gson().fromJson(result, ContentData.class);
        if (data != null) {
            if (data.getLocationreportdata() != null && data.getLocationreportdata().length > 0) {
                Locationlist = new ArrayList(Arrays.asList(data.getLocationreportdata()));

            }
            if (data.getSearchreportdata() != null && data.getSearchreportdata().length > 0) {
                ArrayList<Searchreportdata> list = new ArrayList(Arrays.asList(data.getSearchreportdata()));
                if (list != null && list.size() > 0) {
                    searchdatastr = new Gson().toJson(list);
                }
            }

            if (data.getSearchreportdata() != null && data.getSearchreportdata().length > 0) {
                searchReportlist = new ArrayList(Arrays.asList(data.getSearchreportdata()));
            }
            if (data.getRecommendedServicesdata() != null && data.getRecommendedServicesdata().length > 0) {
                ArrayList<?> list = new ArrayList(Arrays.asList(data.getRecommendedServicesdata()));
                if (list != null && list.size() > 0) {
                    setAdapter(recyclerView, list, 1);

                }
            }
            if (data.getRepairsdata() != null && data.getRepairsdata().length > 0) {
                ArrayList<?> list = new ArrayList(Arrays.asList(data.getRepairsdata()));
                if (list != null && list.size() > 0) {
                    setAdapter(repair_recycler_view, list, 2);
                }
            }
            if (data.getHomeCleaningdata() != null && data.getHomeCleaningdata().length > 0) {
                ArrayList<?> list = new ArrayList(Arrays.asList(data.getHomeCleaningdata()));
                if (list != null && list.size() > 0) {
                    setAdapter(homeclean_recycler_view, list, 3);
                }
            }
            if (data.getShiftingHomesdata() != null && data.getShiftingHomesdata().length > 0) {
                ArrayList<?> list = new ArrayList(Arrays.asList(data.getShiftingHomesdata()));
                if (list != null && list.size() > 0) {
                    setAdapter(shifthome_recycler_view, list, 4);
                }
            }
            if (data.getHomeDesigndata() != null && data.getHomeDesigndata().length > 0) {
                ArrayList<?> list = new ArrayList(Arrays.asList(data.getHomeDesigndata()));
                if (list != null && list.size() > 0) {
                    setAdapter(homedesign_recycler_view, list, 5);
                }
            }
            if (data.getWeddingServicesdata() != null && data.getWeddingServicesdata().length > 0) {
                ArrayList<?> list = new ArrayList(Arrays.asList(data.getWeddingServicesdata()));
                if (list != null && list.size() > 0) {
                    setAdapter(wedding_recycler_view, list, 6);
                }
            }
            if (data.getPartyandEventdata() != null && data.getPartyandEventdata().length > 0) {
                ArrayList<?> list = new ArrayList(Arrays.asList(data.getPartyandEventdata()));
                if (list != null && list.size() > 0) {
                    setAdapter(party_recycler_view, list, 7);
                }
            }
            if (data.getHelpForBusinesdata() != null && data.getHelpForBusinesdata().length > 0) {
                ArrayList<?> list = new ArrayList(Arrays.asList(data.getHelpForBusinesdata()));
                if (list != null && list.size() > 0) {
                    setAdapter(helpbusiness_recycler_view, list, 8);
                }
            }
            if (data.getOtherServiecsdata() != null && data.getOtherServiecsdata().length > 0) {
                ArrayList<?> list = new ArrayList(Arrays.asList(data.getOtherServiecsdata()));
                if (list != null && list.size() > 0) {
                    setAdapter(other_recycler_view, list, 9);
                }
            }
        }
    }

    private void setAdapter(RecyclerView recyclerView, ArrayList<?> relist, int dataId) {
        RecommendedServiceAdapter mAdapter = new RecommendedServiceAdapter(HomeActivity.this, relist, dataId);
        recyclerView.setAdapter(mAdapter);
        //mAdapter.notifyDataSetChanged();
    }

    //----------open dialog for Location name---------------
    private void showLocationListDialog() {

        LocationListAdapter adapter;
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.location_list);

        dialog.getWindow().getAttributes().windowAnimations = R.style.alertAnimation;

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        final ListView listView = (ListView) dialog.findViewById(R.id.state_list);
        listView.setAdapter(null);

        adapter = new LocationListAdapter(HomeActivity.this, Locationlist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                yoursercive.setText("Your Service Expert in " + Locationlist.get(pos).getPinCode());
                locationFlage = true;
                cityName = Locationlist.get(pos).getPinCode();
                locationName.setText(Locationlist.get(pos).getPinCode());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.homeservices_apps_main, R.drawable.how, R.drawable.download, R.drawable.screenshot, R.drawable.images};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    private void slideimage() {
        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter_For_ItemDetails(HomeActivity.this, ImagesArray));


       /* CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator.setRadius(5 * density);*/

        NUM_PAGES = IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
      /*  indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
*/
    }

    private void callGetUserProfile() {
        if (Utility.isOnline(this)) {
            String phone = Utility.getUserPhoneNo(HomeActivity.this);
            if (phone != null) {
                ServiceCaller serviceCaller = new ServiceCaller(this);
                serviceCaller.callUserProfileSrvice(phone, new IAsyncWorkCompletedCallback() {
                    @Override
                    public void onDone(String result, boolean isComplete) {
                        if (isComplete) {
                            ContentData data = new Gson().fromJson(result, ContentData.class);
                            if (data != null) {
                                if (data.getUser() != null) {
                                    if (data.getUser().getMobileno() != null && !data.getUser().getMobileno().equals("")) {
                                        DbHelper dbHelper = new DbHelper(HomeActivity.this);
                                        dbHelper.upsertUserData(data.getUser());
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }

    }
}
