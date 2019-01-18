package com.robyapp.android.healthtracker_litfit.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.robyapp.android.healthtracker_litfit.Utils.SharedPreferencesVariable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.robyapp.android.healthtracker_litfit.Adapter.MainListAdapter;
import com.robyapp.android.healthtracker_litfit.Model.MainItem;
import com.robyapp.android.healthtracker_litfit.R;
import com.stephentuso.welcome.WelcomeHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Font
    // 16 - 21sp content
    // 32 - 40sp heading

    // UI Elements Variables
    private WelcomeHelper welcomeHelper;
    private RecyclerView mRecyclerView;

    // Adapter
    private MainListAdapter mMainListAdapter;

    //TODO Read SharedPreferences that contains info about the user and set it into the view
    //TODO Use SharedPreferences to see if user has already put their info, if not then go to CollectDataActivity first

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Splash Screen
        setTheme(R.style.AppTheme_NoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set SharedPreferences
        SharedPreferencesVariable.userSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Clear SharedPreference (Debug)
        // SharedPreferencesVariable.userSharedPreferences.edit().clear().apply();

        // Check if user has already put their information
        if (!SharedPreferencesVariable.userSharedPreferences.getBoolean(SharedPreferencesVariable.isDataColectedKey, false)) {
            // Debug Toast
            Toast.makeText(this,
                    "test: " + SharedPreferencesVariable.userSharedPreferences.getBoolean(SharedPreferencesVariable.isDataColectedKey, false),
                    Toast.LENGTH_SHORT).show();

            // Go to CollectDataActivity First
            Intent collectIntent = new Intent(MainActivity.this, CollectDataActivity.class);
            startActivity(collectIntent);

            // Terminate MainActivity
            finish();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testIntent = new Intent(MainActivity.this, CollectDataActivity.class);
                startActivity(testIntent);
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
        MainItem weightItem = new MainItem("Weight", SharedPreferencesVariable.userSharedPreferences
                .getString(SharedPreferencesVariable.userWeight, "Kg") + " Kg");
        // Create MainItem - Calorie Burn Object
        MainItem burnItem = new MainItem("Calorie Burn", "- Cal");
        // Create MainItem - Calorie Intake Object
        MainItem intakeItem = new MainItem("Calorie Intake", "- Cal");
        // Create MainItem - Water Intake Object
        MainItem waterItem = new MainItem("Water Intake", "- ml");
        // Create MainItem - Sleep Object
        MainItem sleepItem = new MainItem("Sleep", "- hours");
        // Create MainItem - Heartbeat Object
        MainItem heartItem = new MainItem("Heartbeat", "- bpm");

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
