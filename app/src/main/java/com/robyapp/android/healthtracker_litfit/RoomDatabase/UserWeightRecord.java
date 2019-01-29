package com.robyapp.android.healthtracker_litfit.RoomDatabase;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

/**
 * Entity class that represents user weight data in the database
 */

@Entity(tableName = "user_weight_table")

@TypeConverters(DateConverter.class)
public class UserWeightRecord {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id_weight")
    private int idWeight;

    @NonNull
    @ColumnInfo(name = "weight_value")
    private double valWeight;

    @NonNull
    @ColumnInfo(name = "date_weight")
    private Date dateWeight;

    @Ignore
    public UserWeightRecord(@NonNull int idWeight, @NonNull double valWeight, @NonNull Date dateWeight) {
        this.idWeight = idWeight;
        this.valWeight = valWeight;
        this.dateWeight = dateWeight;
    }

    public UserWeightRecord() {

    }

    @NonNull
    public int getIdWeight() {
        return idWeight;
    }

    public void setIdWeight(@NonNull int idWeight) {
        this.idWeight = idWeight;
    }

    @NonNull
    public double getValWeight() {
        return valWeight;
    }

    public void setValWeight(@NonNull double valWeight) {
        this.valWeight = valWeight;
    }

    @NonNull
    public Date getDateWeight() {
        return dateWeight;
    }

    public void setDateWeight(@NonNull Date dateWeight) {
        this.dateWeight = dateWeight;
    }
}
