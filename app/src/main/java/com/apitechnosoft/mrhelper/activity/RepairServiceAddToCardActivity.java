package com.apitechnosoft.mrhelper.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.adapters.RepairServiceAdapter;
import com.apitechnosoft.mrhelper.models.AddToCard;
import com.apitechnosoft.mrhelper.models.DetailListDashboarddata;
import com.apitechnosoft.mrhelper.models.Locationreportdata;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;

public class RepairServiceAddToCardActivity extends AppCompatActivity {
    private Toolbar toolbar;
    int sNo;
    ArrayList<AddToCard> addList;
    private float totalPrice = 0;
    TextView totalAmountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_service_add_to_card);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(RepairServiceAddToCardActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

    }

    private void initView() {
        addList = new ArrayList<AddToCard>();

        totalAmountText = (TextView) findViewById(R.id.totalAmount);
        TextView count = (TextView) findViewById(R.id.count);
        final String ServiceName = getIntent().getStringExtra("ServiceName");
        String MenuDetail = getIntent().getStringExtra("MenuDetail");
        sNo = getIntent().getIntExtra("Sno", 0);
        TextView wheredo = (TextView) toolbar.findViewById(R.id.wheredo);
        wheredo.setText("Best " + ServiceName + " in This Area");

        ArrayList<DetailListDashboarddata> detaiArraylList = new Gson().fromJson(MenuDetail, new TypeToken<ArrayList<DetailListDashboarddata>>() {
        }.getType());
        ArrayList<DetailListDashboarddata> detailList = new ArrayList<DetailListDashboarddata>();
        for (DetailListDashboarddata dashboarddata : detaiArraylList) {
            if (dashboarddata.getName().equals(ServiceName)) {
                detailList.add(dashboarddata);
            }
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.repair_recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RepairServiceAdapter mAdapter = new RepairServiceAdapter(RepairServiceAddToCardActivity.this, detailList, ServiceName);
        recyclerView.setAdapter(mAdapter);

        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");

        TextView arrowicon = (TextView) findViewById(R.id.arrowicon);
        arrowicon.setTypeface(materialdesignicons_font);
        arrowicon.setText(Html.fromHtml("&#xf054;"));
        LinearLayout checkoutLayout = (LinearLayout) findViewById(R.id.checkoutLayout);
        checkoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addList.size() > 0) {
                    String snoList = addSnoValue();
                    Intent intent = new Intent(RepairServiceAddToCardActivity.this, AddressActivity.class);
                    intent.putExtra("ServiceName", ServiceName);
                    intent.putExtra("totalAmount", totalPrice);
                    intent.putExtra("NavigationFlag", 2);
                    intent.putExtra("Sno", sNo);
                    intent.putExtra("selectedValue", snoList);
                    intent.putExtra("NoOfService", addList.size());
                    startActivity(intent);
                }else{
                    Toast.makeText(RepairServiceAddToCardActivity.this,"Please select at least one service",Toast.LENGTH_LONG).show();
                }

              /*  if(totalAmount>599){

                }else{
                    Utility.alertForErrorMessage("Min Checkout Amount - Rs.599",RepairServiceAddToCardActivity.this);
                }*/
            }
        });

    }

    //add item sno with comma
    private String addSnoValue() {
        StringBuilder snoBuilder = new StringBuilder();
        if (addList.size() > 0) {
            for (AddToCard item : addList) {
                snoBuilder.append(item.getSno()).append(",");
                // nameBuilder.append("'").append(n.replace("'", "\\'")).append("',");
                // can also do the following
                // nameBuilder.append("'").append(n.replace("'", "''")).append("',");
            }

            snoBuilder.deleteCharAt(snoBuilder.length() - 1);
        }
        return snoBuilder.toString();
    }


    //to call add to card item
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //   context.unregisterReceiver(receiver);
            if (intent.getAction().equalsIgnoreCase("AddItem")) {
                boolean AddRemove = intent.getBooleanExtra("AddRemove", false);
                String sno = intent.getStringExtra("ItemSon");
                String Price = intent.getStringExtra("Price");
                if (AddRemove) {
                    addCartValue(sno, Price);
                } else {
                    removeCartValue(sno, Price);
                }

            }
        }

        private void addCartValue(String sno, String Price) {
            AddToCard addToCard = new AddToCard();
            addToCard.setSno(sno);
            float floatPrice = Float.parseFloat(Price);
            totalPrice = totalPrice + floatPrice;
            totalAmountText.setText(totalPrice + "");
            addToCard.setPrice(floatPrice);
            addList.add(addToCard);
        }

        //remove item from list
        private void removeCartValue(String sno, String Price) {
            for (int i = 0; i < addList.size(); i++) {
                if (addList.get(i).getSno().equals(sno)) {
                    addList.remove(i);
                    float floatPrice = Float.parseFloat(Price);
                    totalPrice = totalPrice - floatPrice;
                    totalAmountText.setText(totalPrice + "");
                }
            }
        }

    };

    public void delete(StringBuilder sb, String s) {
        int start = sb.indexOf(s);
        if (start < 0)
            return;

        sb.delete(start, start + s.length());
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter("AddItem"));
    }

    @Override
    protected void onDestroy() {
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }
}
