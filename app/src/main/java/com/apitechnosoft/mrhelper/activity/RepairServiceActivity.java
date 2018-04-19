package com.apitechnosoft.mrhelper.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.adapters.RepairExpandableListAdapter;
import com.apitechnosoft.mrhelper.adapters.RepairServiceAdapter;
import com.apitechnosoft.mrhelper.adapters.SelectLocationAdapter;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.ContentData;
import com.apitechnosoft.mrhelper.models.DetailListDashboarddata;
import com.apitechnosoft.mrhelper.models.Locationreportdata;
import com.apitechnosoft.mrhelper.models.MenuheadingtData;
import com.apitechnosoft.mrhelper.models.RepairContentData;
import com.apitechnosoft.mrhelper.models.RepairItemsListParentModel;
import com.apitechnosoft.mrhelper.models.Submenu;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepairServiceActivity extends AppCompatActivity {
    Toolbar toolbar;
    private int sNumber;
    ExpandableListView expListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_service);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(RepairServiceActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

    }

    private void initView() {
        sNumber = getIntent().getIntExtra("sNumber", 0);
        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");


        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        expListView.setDividerHeight(1);
      /*  expListView.setBackgroundColor(Color.parseColor(ThemeManager.getApplicationbackGroundColor()));
        expListView.setChildDivider(new ColorDrawable(Color.parseColor(ThemeManager.getHeaderBackground())));
        expListView.setDivider(new ColorDrawable(Color.parseColor(ThemeManager.getHeaderBackground())));*/
        getRepairdata();
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
               /* Toast.makeText(getApplicationContext(),
                        groupPosition + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();*/
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });
    }

    private void getRepairdata() {
        if (Utility.isOnline(this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(RepairServiceActivity.this);
            dotDialog.show();
            ServiceCaller serviceCaller = new ServiceCaller(this);
            serviceCaller.callRepairService(sNumber, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        parseRepairData(result);
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, RepairServiceActivity.this);
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

    private void parseRepairData(String result) {
        RepairContentData data = new Gson().fromJson(result, RepairContentData.class);
        if (data != null) {
            DetailListDashboarddata[] detailList = data.getDetailListDashboarddata();
            MenuheadingtData[] menuList = data.getMenuheadingtData();
            if (menuList != null && menuList.length > 0) {
                List<RepairItemsListParentModel> newMenuParentList=new ArrayList<RepairItemsListParentModel>();
                for(MenuheadingtData menuData:menuList){
                    RepairItemsListParentModel parentModel=new RepairItemsListParentModel();
                    parentModel.setSno(menuData.getSno());
                    parentModel.setServiceName(menuData.getServiceName());
                    parentModel.setRate1(menuData.getRate1());
                    parentModel.setRate2(menuData.getRate2());
                    parentModel.setTotalAmount(menuData.getTotalAmount());
                    parentModel.setJobid(menuData.getJobid());
                    parentModel.setSectionSno(menuData.getSectionSno());
                    ArrayList<Submenu> submenusList = new ArrayList<Submenu>(Arrays.asList(menuData.getSubmenu()));
                    parentModel.setSubmenuList(submenusList);
                    newMenuParentList.add(parentModel);
                }
                //List<MenuheadingtData> menuListdata = Arrays.asList(menuList);
                if (newMenuParentList != null && newMenuParentList.size() > 0) {
                    RepairExpandableListAdapter listAdapter = new RepairExpandableListAdapter(RepairServiceActivity.this, newMenuParentList);
                    expListView.setAdapter(listAdapter);
                }
            }

        }
    }

}
