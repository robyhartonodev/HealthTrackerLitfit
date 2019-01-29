package com.robyapp.android.healthtracker_litfit.RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Entity class that represents a user in the database
 */

//TODO Change from sharedPref to this model

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "age")
    private int mAge;

    @NonNull
    @ColumnInfo(name = "height")
    private double mHeight;

    @NonNull
    @ColumnInfo(name = "weight")
    private double mWeight;

    @NonNull
    @ColumnInfo(name = "gender")
    private String mGender;

    /**
     * Constructor
     *
     * @param id
     * @param mName
     * @param mAge
     * @param mHeight
     * @param mWeight
     * @param mGender
     */
    @Ignore
    public User(int id,
                @NonNull String mName,
                @NonNull int mAge,
                @NonNull double mHeight,
                @NonNull double mWeight,
                @NonNull String mGender) {
        this.id = id;
        this.mName = mName;
        this.mAge = mAge;
        this.mHeight = mHeight;
        this.mWeight = mWeight;
        this.mGender = mGender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getmName() {
        return mName;
    }

    public void setmName(@NonNull String mName) {
        this.mName = mName;
    }

    @NonNull
    public int getmAge() {
        return mAge;
    }

    public void setmAge(@NonNull int mAge) {
        this.mAge = mAge;
    }

    @NonNull
    public double getmHeight() {
        return mHeight;
    }

    public void setmHeight(@NonNull double mHeight) {
        this.mHeight = mHeight;
    }

    @NonNull
    public double getmWeight() {
        return mWeight;
    }

    public void setmWeight(@NonNull double mWeight) {
        this.mWeight = mWeight;
    }

    @NonNull
    public String getmGender() {
        return mGender;
    }

    public void setmGender(@NonNull String mGender) {
        this.mGender = mGender;
    }
}
