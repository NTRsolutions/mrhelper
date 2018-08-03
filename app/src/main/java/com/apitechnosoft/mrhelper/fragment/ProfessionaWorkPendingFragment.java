package com.apitechnosoft.mrhelper.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.activity.SplashScreenActivity;
import com.apitechnosoft.mrhelper.adapters.OnGoingBookingAdapter;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.database.DbHelper;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.Bookservicelist;
import com.apitechnosoft.mrhelper.models.ContentMybooking;
import com.apitechnosoft.mrhelper.models.OrderBookedListEngineerWise;
import com.apitechnosoft.mrhelper.models.PartnerDetailsForPartner;
import com.apitechnosoft.mrhelper.models.Servicelist;
import com.apitechnosoft.mrhelper.professional.BecomeHostLoginActivity;
import com.apitechnosoft.mrhelper.professional.ProfessionalWorkPendingAdapter;
import com.apitechnosoft.mrhelper.professional.ProfessionalWorkSheetActivity;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfessionaWorkPendingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfessionaWorkPendingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String listStr;
    private String mParam2;


    public ProfessionaWorkPendingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfessionaWorkPendingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfessionaWorkPendingFragment newInstance(String param1, String param2) {
        ProfessionaWorkPendingFragment fragment = new ProfessionaWorkPendingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private Context context;
    private View view;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listStr = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        view = inflater.inflate(R.layout.fragment_professiona_work_pending, container, false);
        init();
        return view;
    }

    private void init() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        filterCompleteJob();
    }

    private void filterCompleteJob() {
        ArrayList<OrderBookedListEngineerWise> MainjobList = new Gson().fromJson(listStr, new TypeToken<ArrayList<OrderBookedListEngineerWise>>() {
        }.getType());
        ArrayList<OrderBookedListEngineerWise> jobList = new ArrayList<OrderBookedListEngineerWise>();
        for (OrderBookedListEngineerWise data : MainjobList) {
            if (!data.getBookingstatus().equals("Completed")) {
                jobList.add(data);
            }
        }
        if (jobList != null) {
            ProfessionalWorkPendingAdapter mAdapter = new ProfessionalWorkPendingAdapter(context, jobList, false);
            recyclerView.setAdapter(mAdapter);
        }
    }
}
