package com.robyapp.android.healthtracker_litfit.Fragment;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robyapp.android.healthtracker_litfit.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class CalorieBurnActivityFragment extends Fragment {

    public CalorieBurnActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calorie_burn, container, false);
    }
}
