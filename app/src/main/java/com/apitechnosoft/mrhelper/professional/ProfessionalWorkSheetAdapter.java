package com.apitechnosoft.mrhelper.professional;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.apitechnosoft.mrhelper.fragment.OldBookingFragment;
import com.apitechnosoft.mrhelper.fragment.OnGoingBookingFragment;
import com.apitechnosoft.mrhelper.fragment.ProfessionaWorkCompletedFragment;
import com.apitechnosoft.mrhelper.fragment.ProfessionaWorkPendingFragment;

public class ProfessionalWorkSheetAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ProfessionalWorkSheetAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ProfessionaWorkPendingFragment tab1 = new ProfessionaWorkPendingFragment();
                return tab1;
            case 1:
                ProfessionaWorkCompletedFragment tab2 = new ProfessionaWorkCompletedFragment();
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

