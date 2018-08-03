package com.apitechnosoft.mrhelper.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.apitechnosoft.mrhelper.fragment.CanceledFragment;
import com.apitechnosoft.mrhelper.fragment.OldBookingFragment;
import com.apitechnosoft.mrhelper.fragment.OnGoingBookingFragment;

public class MyBookingPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public MyBookingPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                OnGoingBookingFragment tab1 = new OnGoingBookingFragment();
                return tab1;
            case 1:
                OldBookingFragment tab2 = new OldBookingFragment();
                return tab2;
            case 2:
                CanceledFragment tab3 = new CanceledFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
