package com.example.android.healthtracker_litfit.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.healthtracker_litfit.Adapter.MainListAdapter;
import com.example.android.healthtracker_litfit.Model.MainItem;
import com.example.android.healthtracker_litfit.R;
import com.stephentuso.welcome.WelcomeHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Font
    // 16 - 21sp content
    // 32 - 40sp heading

    // UI Elements Variables
    private WelcomeHelper welcomeHelper;
    private RecyclerView mRecyclerView;

    // Adapter
    private MainListAdapter mMainListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Show OnBoardingActivity
        welcomeHelper = new WelcomeHelper(this, OnBoardingActivity.class);
        welcomeHelper.show(savedInstanceState);

        // Set Main UI Components
        setMainListItem();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeHelper.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_welcome) {
            welcomeHelper.forceShow();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method sets the list items menu like weight, water intake, calorie, etc.
     * 0 : Weight
     * 1 : Calorie Burn
     * 2 : Calorie Intake
     * 3 : Water Intake
     * 4 : Sleep
     * 5 : Heartbeat
     */
    private void setMainListItem() {
        // Create array list of main item
        ArrayList<MainItem> mainItemList = new ArrayList<>();

        // Create MainItem - Weight Object
        MainItem weightItem = new MainItem("Weight", "Kg");
        // Create MainItem - Calorie Burn Object
        MainItem burnItem = new MainItem("Calorie Burn", "Cal");
        // Create MainItem - Calorie Intake Object
        MainItem intakeItem = new MainItem("Calorie Intake", "Cal");
        // Create MainItem - Water Intake Object
        MainItem waterItem = new MainItem("Water Intake", "Cal");
        // Create MainItem - Sleep Object
        MainItem sleepItem = new MainItem("Sleep", "Duration");
        // Create MainItem - Heartbeat Object
        MainItem heartItem = new MainItem("Heartbeat", "bpm");

        // Add the MainItem objects into the array list
        mainItemList.add(weightItem);
        mainItemList.add(burnItem);
        mainItemList.add(intakeItem);
        mainItemList.add(waterItem);
        mainItemList.add(sleepItem);
        mainItemList.add(heartItem);

        // Initialize adapter & recycler view
        mRecyclerView = findViewById(R.id.recyclerView_main);
        mMainListAdapter = new MainListAdapter(MainActivity.this, mainItemList);

        // Setup layout manager
        int gridColumnCount = getResources().getInteger(R.integer.grid_colum_count);
        LinearLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, gridColumnCount);

        // Set LayoutManager for the recycler view
        mRecyclerView.setLayoutManager(layoutManager);

        // Set the adapter
        mRecyclerView.setAdapter(mMainListAdapter);

    }
}
