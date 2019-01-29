package com.robyapp.android.healthtracker_litfit.RoomDatabase;


import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * The UserWeightRecordViewModel provides the interface between the UI and the data layer of the app,
 * represented by the Repository
 */
public class UserWeightRecordViewModel extends AndroidViewModel {

    private UserWeightRecordRepository mRepository;

    private LiveData<List<UserWeightRecord>> mAllWords;

    public UserWeightRecordViewModel(Application application) {
        super(application);
        mRepository = new UserWeightRecordRepository(application);
        mAllWords = mRepository.getAllRecords();
    }

    LiveData<List<UserWeightRecord>> getAllRecords() {
        return mAllWords;
    }

    public void insert(UserWeightRecord userWeightRecord) {
        mRepository.insert(userWeightRecord);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void deleteWord(UserWeightRecord userWeightRecord) {
        mRepository.deleteUserWeightRecord(userWeightRecord);
    }

    public void update(UserWeightRecord userWeightRecord) {
        mRepository.update(userWeightRecord);
    }
}
