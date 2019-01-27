package com.robyapp.android.healthtracker_litfit.Adapter;

import com.robyapp.android.healthtracker_litfit.Fragment.BMIFragment;
import com.robyapp.android.healthtracker_litfit.Fragment.WeightActivityFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class WeightPagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    public WeightPagerAdapter(@NonNull FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new WeightActivityFragment();
            case 1:
                return new BMIFragment();
            default:
                return null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
