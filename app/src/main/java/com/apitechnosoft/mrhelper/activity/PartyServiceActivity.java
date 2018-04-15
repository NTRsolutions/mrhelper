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
import android.widget.TextView;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.adapters.PartyServiceAdapter;
import com.apitechnosoft.mrhelper.adapters.SelectLocationAdapter;
import com.apitechnosoft.mrhelper.models.Locationreportdata;
import com.apitechnosoft.mrhelper.models.SalModel;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class PartyServiceActivity extends AppCompatActivity {
    Toolbar toolbar;
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
    private void initView(){
        TextView wheredo = (TextView) toolbar.findViewById(R.id.wheredo);

        ArrayList<SalModel> list=new ArrayList<SalModel>();
        SalModel salModel=new SalModel();
        salModel.setTitle("Weight Gain");
        list.add(salModel);

        SalModel salModel1=new SalModel();
        salModel1.setTitle("Weight Loss");
        list.add(salModel1);

        SalModel salModel2=new SalModel();
        salModel2.setTitle("Improving medical condition (PCOD, Thyroid, Diabetes etc.)");
        list.add(salModel2);

        SalModel salModel3=new SalModel();
        salModel3.setTitle("Others");
        list.add(salModel3);



        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");

      //  String locationstr="";
       // Bundle extras = getIntent().getExtras();

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.party_recycler_view);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

           /* ArrayList<Locationreportdata> locationList = new Gson().fromJson(locationstr, new TypeToken<ArrayList<Locationreportdata>>() {
            }.getType());*/

            PartyServiceAdapter mAdapter = new PartyServiceAdapter(PartyServiceActivity.this,list);
            recyclerView.setAdapter(mAdapter);

    }
}
