package com.robyapp.android.healthtracker_litfit.RoomDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Data Access Object (DAO) for a UserWeightRecord.
 * Each method performs a database operation, such as inserting or deleting a weight record,
 * running a DB query, or deleting all user weight records.
 */
@Dao
public interface UserWeightRecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UserWeightRecord userWeightRecord);

    @Query("DELETE FROM USER_WEIGHT_TABLE")
    void deleteAll();

    @Delete
    void deleteUserWeightRecord(UserWeightRecord userWeightRecord);

    @Query("SELECT * from USER_WEIGHT_TABLE LIMIT 1")
    UserWeightRecord[] getAnyRecord();

    @Query("SELECT * from USER_WEIGHT_TABLE ORDER BY id_weight ASC")
    LiveData<List<UserWeightRecord>> getAllUserWeightRecord();

    @Update
    void update(UserWeightRecord... userWeightRecords);
}
