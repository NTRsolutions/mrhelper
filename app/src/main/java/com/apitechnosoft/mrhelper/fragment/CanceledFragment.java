package com.apitechnosoft.mrhelper.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.adapters.CancledServiceAdapter;
import com.apitechnosoft.mrhelper.adapters.CompletedServiceAdapter;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.Cancleservicelist;
import com.apitechnosoft.mrhelper.models.CompleteJobList;
import com.apitechnosoft.mrhelper.models.ContentMybooking;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CanceledFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CanceledFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CanceledFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CanceledFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CanceledFragment newInstance(String param1, String param2) {
        CanceledFragment fragment = new CanceledFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Context context;
    private View view;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        view = inflater.inflate(R.layout.fragment_canceled, container, false);
        init();
        return view;
    }

    private void init() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void getCancledService() {
        if (Utility.isOnline(context)) {
            String phone = Utility.getUserPhoneNo(context);
            if (phone != null) {
                final CircleDotDialog dotDialog = new CircleDotDialog(context);
                dotDialog.show();
                ServiceCaller serviceCaller = new ServiceCaller(context);
                serviceCaller.callCanceledService(phone, new IAsyncWorkCompletedCallback() {
                    @Override
                    public void onDone(String result, boolean isComplete) {
                        if (isComplete) {
                            parseCancledData(result);
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

    private void parseCancledData(String result) {
        ContentMybooking data = new Gson().fromJson(result, ContentMybooking.class);
        if (data != null) {
            if (data.getCancleservicelist() != null) {
                List<Cancleservicelist> list = new ArrayList<Cancleservicelist>();
                for (Cancleservicelist bookservicelist : data.getCancleservicelist()) {
                    list.add(bookservicelist);
                }
                CancledServiceAdapter mAdapter = new CancledServiceAdapter(context, list, false);
                recyclerView.setAdapter(mAdapter);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getCancledService();
    }

}
