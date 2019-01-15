package com.example.android.healthtracker_litfit.Utils;

import android.content.SharedPreferences;

/**
 * This class contains global variables for shared preferences that stores user's data (weight, height, name, etc.)
 */
public class SharedPreferencesVariable {
    // Shared Preferences that used for whole app
    public static SharedPreferences userSharedPreferences;

    // Key to check if user already put their data in
    public static String isDataColectedKey = "DATA_COLECTED_KEY";
    // Key for user name
    public static String userName = "USER_NAME_KEY";
    // Key for user age
    public static String userAge = "USER_AGE_KEY";
    // Key for user height
    public static String userHeight = "USER_HEIGHT_KEY";
    // Key for user weight
    public static String userWeight = "USER_WEIGHT_KEY";
    // Key for user gender
    public static String userGender = "USER_GENDER_KEY";

}
