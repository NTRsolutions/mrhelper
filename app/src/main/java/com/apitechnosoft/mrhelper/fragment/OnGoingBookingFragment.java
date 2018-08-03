package com.apitechnosoft.mrhelper.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.activity.HomeActivity;
import com.apitechnosoft.mrhelper.activity.MyBookingActivity;
import com.apitechnosoft.mrhelper.activity.RepairServiceAddToCardActivity;
import com.apitechnosoft.mrhelper.adapters.OnGoingBookingAdapter;
import com.apitechnosoft.mrhelper.adapters.RepairServiceAdapter;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.database.DbHelper;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.Bookservicelist;
import com.apitechnosoft.mrhelper.models.ContentMybooking;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class OnGoingBookingFragment extends Fragment {


    public OnGoingBookingFragment() {
        // Required empty public constructor
    }

    private Context context;
    private View view;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        view = inflater.inflate(R.layout.fragment_on_going_booking, container, false);
        init();
        return view;
    }

    private void init() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


       /* DbHelper dbHelper = new DbHelper(context);
        List<Bookservicelist> bookservicelists= dbHelper.getAllMyBookingData();
        if(bookservicelists!=null) {
            List<Bookservicelist> serviceList=new ArrayList<Bookservicelist>();
            for(Bookservicelist data: bookservicelists){
                if(data.getBookingStatus().equalsIgnoreCase("pending")){
                    serviceList.add(data);
                }
            }
            OnGoingBookingAdapter mAdapter = new OnGoingBookingAdapter(context, serviceList,true);
            recyclerView.setAdapter(mAdapter);
        }*/
    }

    private void getMyBooking() {
        if (Utility.isOnline(context)) {
            String phone = Utility.getUserPhoneNo(context);
            if (phone != null) {
                final CircleDotDialog dotDialog = new CircleDotDialog(context);
                dotDialog.show();
                ServiceCaller serviceCaller = new ServiceCaller(context);
                serviceCaller.callMyBookingService(phone, new IAsyncWorkCompletedCallback() {
                    @Override
                    public void onDone(String result, boolean isComplete) {
                        if (isComplete) {
                            parseMyBookingData(result);
                        } else {
                            Utility.alertForErrorMessage("Data not Found!", context);
                        }
                        if (dotDialog.isShowing()) {
                            dotDialog.dismiss();
                        }
                    }
                });
            }
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, context);//off line msg....
        }
    }

    private void parseMyBookingData(String result) {
        ContentMybooking data = new Gson().fromJson(result, ContentMybooking.class);
        if (data != null) {
            if (data.getBookservicelist() != null) {
                DbHelper dbHelper = new DbHelper(context);
                //dbHelper.deleteMyBookingData();
                List<Bookservicelist> list = new ArrayList<Bookservicelist>();
                for (Bookservicelist bookservicelist : data.getBookservicelist()) {
                    // dbHelper.upsertMyBookingData(bookservicelist);
                    list.add(bookservicelist);
                }
                OnGoingBookingAdapter mAdapter = new OnGoingBookingAdapter(context, list, true);
                recyclerView.setAdapter(mAdapter);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getMyBooking();
    }
}
