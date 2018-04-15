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
import com.apitechnosoft.mrhelper.models.SalModel;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.FontManager;

import java.util.ArrayList;

public class Sal1ServiceActivity extends AppCompatActivity {

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

                SlaServiceAdapter mAdapter = new SlaServiceAdapter(Sal1ServiceActivity.this,list);
                recyclerView.setAdapter(mAdapter);



        }
    }