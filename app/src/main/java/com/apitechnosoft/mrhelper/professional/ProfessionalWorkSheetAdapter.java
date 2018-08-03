package com.apitechnosoft.mrhelper.professional;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.apitechnosoft.mrhelper.fragment.OldBookingFragment;
import com.apitechnosoft.mrhelper.fragment.OnGoingBookingFragment;
import com.apitechnosoft.mrhelper.fragment.ProfessionaWorkCompletedFragment;
import com.apitechnosoft.mrhelper.fragment.ProfessionaWorkPendingFragment;
import com.apitechnosoft.mrhelper.models.OrderBookedListEngineerWise;

import java.util.ArrayList;

public class ProfessionalWorkSheetAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    ArrayList<OrderBookedListEngineerWise> jobList;
    String listStr;
    public ProfessionalWorkSheetAdapter(FragmentManager fm, int NumOfTabs,String listStr) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.listStr=listStr;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ProfessionaWorkPendingFragment tab1 = ProfessionaWorkPendingFragment.newInstance(listStr, "");
                return tab1;
            case 1:
                ProfessionaWorkCompletedFragment tab2 = ProfessionaWorkCompletedFragment.newInstance(listStr, "");
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

