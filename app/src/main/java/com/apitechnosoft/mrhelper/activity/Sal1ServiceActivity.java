package com.apitechnosoft.mrhelper.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.adapters.SlaServiceAdapter;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.ContentData;
import com.apitechnosoft.mrhelper.models.SalModel;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Sal1ServiceActivity extends AppCompatActivity {
private int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sal1_service);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        //chech Portait And LandSacpe Orientation
    }
        public void chechPortaitAndLandSacpe() {
            if (CompatibilityUtility.isTablet(Sal1ServiceActivity.this)) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
            initView();
        }
        private void initView(){
             number = getIntent().getIntExtra("sNumber",0);

        ArrayList<SalModel> list=new ArrayList<SalModel>();
            SalModel salModel=new SalModel();
            salModel.setTitle("Packages");
            salModel.setImageId(R.drawable.packages);
            list.add(salModel);

            SalModel salModel1=new SalModel();
            salModel1.setTitle("Facial");
            salModel1.setImageId(R.drawable.facial);
            list.add(salModel1);

            SalModel salModel2=new SalModel();
            salModel2.setTitle("Mani-Pedi");
            salModel2.setImageId(R.drawable.manipadle);
            list.add(salModel2);

            SalModel salModel3=new SalModel();
            salModel3.setTitle("Rica Was");
            salModel3.setImageId(R.drawable.ricawax);
            list.add(salModel3);

            SalModel salModel4=new SalModel();
            salModel4.setTitle("Regular Was");
            salModel4.setImageId(R.drawable.regularwax);
            list.add(salModel4);

            SalModel salModel5=new SalModel();
            salModel5.setTitle("Hair");
            salModel5.setImageId(R.drawable.hair);
            list.add(salModel5);

            SalModel salModel6=new SalModel();
            salModel6.setTitle("Threading");
            salModel6.setImageId(R.drawable.threading);
            list.add(salModel6);

            SalModel salModel7=new SalModel();
            salModel7.setTitle("Bleach");
            salModel7.setImageId(R.drawable.bleech);
            list.add(salModel7);

            Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");

           // String locationstr="";
          //  Bundle extras = getIntent().getExtras();

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sla_recycler_view);
                recyclerView.setHasFixedSize(false);
                StaggeredGridLayoutManager gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(gaggeredGridLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

              /*  ArrayList<Locationreportdata> locationList = new Gson().fromJson(locationstr, new TypeToken<ArrayList<Locationreportdata>>() {
                }.getType());*/

                SlaServiceAdapter mAdapter = new SlaServiceAdapter(Sal1ServiceActivity.this,list,number);
                recyclerView.setAdapter(mAdapter);

        }
   /* private void getSaldata() {
        if (Utility.isOnline(this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(Sal1ServiceActivity.this);
            dotDialog.show();
            ServiceCaller serviceCaller = new ServiceCaller(this);
            serviceCaller.callRepairService(number,new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        parseSalData(result);
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, Sal1ServiceActivity.this);
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
    private void parseSalData(String result) {
        ContentData data = new Gson().fromJson(result, ContentData.class);
        if (data != null) {

        }
    }*/
    }