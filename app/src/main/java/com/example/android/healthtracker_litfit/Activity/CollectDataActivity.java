package com.example.android.healthtracker_litfit.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.android.healthtracker_litfit.R;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CollectDataActivity extends AppCompatActivity implements CalendarDatePickerDialogFragment.OnDateSetListener {

    private final Calendar myCalendar = Calendar.getInstance();
    private MaterialEditText mBirthEditText;
    private MaterialSpinner mGenderSpinner;

    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";
    private String dateBirthString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Save User's information in sharedpreferences

                // Go To MainActivity
                Intent mainIntent = new Intent(CollectDataActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        // Set EditText for Birth Date
        mBirthEditText = findViewById(R.id.materialedittext_birth);

        // Set OnClickListener for the EditText
        mBirthEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                        .setOnDateSetListener(CollectDataActivity.this);
                cdp.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);
            }
        });

        // Set Spinner for Gender select
        mGenderSpinner = findViewById(R.id.spinner_gender);
        mGenderSpinner.setItems("Male", "Female");
        mGenderSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                // Show Toast of selected gender
                Toast.makeText(CollectDataActivity.this, item + " selected", Toast.LENGTH_SHORT).show();
                // TODO Store selected gender in sharedpreference
            }
        });
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        dateBirthString = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
        mBirthEditText.setText(dateBirthString);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CalendarDatePickerDialogFragment calendarDatePickerDialogFragment = (CalendarDatePickerDialogFragment) getSupportFragmentManager()
                .findFragmentByTag(FRAG_TAG_DATE_PICKER);
        if (calendarDatePickerDialogFragment != null) {
            calendarDatePickerDialogFragment.setOnDateSetListener(this);
        }
    }
}
