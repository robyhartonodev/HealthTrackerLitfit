package com.example.android.healthtracker_litfit.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.example.android.healthtracker_litfit.Utils.SharedPreferencesVariable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.android.healthtracker_litfit.R;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class CollectDataActivity extends AppCompatActivity implements CalendarDatePickerDialogFragment.OnDateSetListener {

    // Variables for UI components
    private MaterialEditText mBirthEditText;
    private MaterialEditText mWeightEditText;
    private MaterialEditText mHeightEditText;
    private MaterialEditText mNameEditText;
    private MaterialSpinner mGenderSpinner;

    // Variables
    private final Calendar myCalendar = Calendar.getInstance();
    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";
    private String dateBirthString;
    private String genderString;
    private String ageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setup UI Variables
        mHeightEditText = findViewById(R.id.materialedittext_height);
        mWeightEditText = findViewById(R.id.materialedittext_weight);
        mNameEditText = findViewById(R.id.materialedittext_name);

        // Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Save User's information in sharedpreference

                /**Check user inputs*/

                // Check Height EditText
                if (TextUtils.isEmpty(mHeightEditText.getText().toString())) {
                    // Show Toast for Height
                    Toast.makeText(CollectDataActivity.this, "Please enter your height", Toast.LENGTH_SHORT).show();
                }
                // Check Weight EditText
                else if (TextUtils.isEmpty(mWeightEditText.getText().toString())) {
                    Toast.makeText(CollectDataActivity.this, "Please enter your weight", Toast.LENGTH_SHORT).show();
                }
                // Check Name EditText
                else if (TextUtils.isEmpty(mNameEditText.getText().toString())) {
                    Toast.makeText(CollectDataActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                }
                // Check Birth Date EditText
                else if (TextUtils.isEmpty(mBirthEditText.getText().toString())) {
                    Toast.makeText(CollectDataActivity.this, "Please enter your birth date", Toast.LENGTH_SHORT).show();
                } else {
                    // Go To MainActivity
                    Intent mainIntent = new Intent(CollectDataActivity.this, MainActivity.class);
                    startActivity(mainIntent);

                    // Set SharedPreferences
                    // SharedPreferencesVariable.userSharedPreferences = PreferenceManager.getDefaultSharedPreferences(CollectDataActivity.this);

                    // Save name
                    SharedPreferencesVariable.userSharedPreferences.edit()
                            .putString(SharedPreferencesVariable.userName, mNameEditText.getText().toString()).apply();

                    // Save age
                    SharedPreferencesVariable.userSharedPreferences.edit()
                            .putString(SharedPreferencesVariable.userAge, ageString).apply();

                    // Save height
                    SharedPreferencesVariable.userSharedPreferences.edit()
                            .putString(SharedPreferencesVariable.userHeight, mHeightEditText.getText().toString()).apply();

                    // Save weight
                    SharedPreferencesVariable.userSharedPreferences.edit()
                            .putString(SharedPreferencesVariable.userWeight, mWeightEditText.getText().toString()).apply();

                    // Save gender
                    SharedPreferencesVariable.userSharedPreferences.edit()
                            .putString(SharedPreferencesVariable.userGender, genderString).apply();

                    // Set isDataColected SharedPref to true
                    // SharedPreferencesVariable.userSharedPreferences.edit().putBoolean(SharedPreferencesVariable.isDataColectedKey, true).apply();

                    // Terminate CollectDataActivity
                    finish();
                }
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
                // Set selected String from spinner in genderString
                genderString = item;
            }
        });
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {

        // Create calendar instance
        Calendar myBirthCalendar = Calendar.getInstance();

        // Set the Calendar with year, month and day from the calendar picker
        myBirthCalendar.set(year, monthOfYear, dayOfMonth);

        // Get Date Object of birth date and current date
        Date birthDate = myBirthCalendar.getTime();
        Date currentDate = Calendar.getInstance().getTime();

        // Set Age String
        ageString = getDiffYears(birthDate, currentDate);

        // Set Date birth String for the EditText
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

    /**
     * Get the difference of years between two date
     * @param first birth date Date Object
     * @param last current date Date Object
     * @return int year difference between two date
     */
    private String getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return String.valueOf(diff);
    }

    /**
     * Get the Calendar Object
     */
    private Calendar getCalendar(Date date){
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
