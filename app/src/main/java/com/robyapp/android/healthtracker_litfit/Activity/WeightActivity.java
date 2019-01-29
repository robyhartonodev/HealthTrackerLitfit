package com.robyapp.android.healthtracker_litfit.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.robyapp.android.healthtracker_litfit.Adapter.WeightPagerAdapter;
import com.robyapp.android.healthtracker_litfit.R;

public class WeightActivity extends AppCompatActivity {

    // Variables for UI Components
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private WeightPagerAdapter mWeightPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set ViewPager and TabLayout
        setPagerTab();

        // Set Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go To AddWeight Activity
                Intent addWeightIntent = new Intent(WeightActivity.this, AddWeightActivity.class);
                startActivity(addWeightIntent);

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * This method sets ViewPager and Tablayout in this activity
     */
    private void setPagerTab(){
        // Create an instance of the tab layout from the view
        mTabLayout = findViewById(R.id.weight_tab_layout);

        // Set the text for each tab.
        mTabLayout.addTab(mTabLayout.newTab().setText("Weight"));
        mTabLayout.addTab(mTabLayout.newTab().setText("BMI"));

        // Set the tabs to fill the entire layout
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Use WeightPagerAdapter to manage page views in fragments
        // Each page is represented by its own fragment
        mViewPager = findViewById(R.id.weight_pager);
        mWeightPagerAdapter = new WeightPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());

        // Set Adapter
        mViewPager.setAdapter(mWeightPagerAdapter);

        // Setting a listener for clicks
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
