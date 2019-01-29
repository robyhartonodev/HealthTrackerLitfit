package com.robyapp.android.healthtracker_litfit.RoomDatabase;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * This class holds the implementation code for the methods that interact with the database.
 * Using a repository allows us to group the implementation methods together,
 * and allows the UserWeightRecordViewModel to be a clean interface between the rest of the app
 * and the database.
 *
 * For insert, update and delete, and longer-running queries,
 * you must run the database interaction methods in the background.
 *
 * Typically, all you need to do to implement a database method
 * is to call it on the data access object (DAO), in the background if applicable.
 */

public class UserWeightRecordRepository {

    private UserWeightRecordDao mUserWeightRecordDao;
    private LiveData<List<UserWeightRecord>> mAllUserWeightRecord;

    UserWeightRecordRepository(Application application) {
        UserWeightRecordDatabase db = UserWeightRecordDatabase.getDatabase(application);
        mUserWeightRecordDao = db.userWeightRecordDao();
        mAllUserWeightRecord = mUserWeightRecordDao.getAllUserWeightRecord();
    }

    LiveData<List<UserWeightRecord>> getAllRecords() {
        return mAllUserWeightRecord;
    }

    public void insert(UserWeightRecord userWeightRecord) {
        new insertAsyncTask(mUserWeightRecordDao).execute(userWeightRecord);
    }

    public void update(UserWeightRecord userWeightRecord)  {
        new updateWeightRecordAsyncTask(mUserWeightRecordDao).execute(userWeightRecord);
    }

    public void deleteAll()  {
        new deleteAllWeightRecordsAsyncTask(mUserWeightRecordDao).execute();
    }

    // Must run off main thread
    public void deleteUserWeightRecord(UserWeightRecord userWeightRecord) {
        new deleteWeightRecordAsyncTask(mUserWeightRecordDao).execute(userWeightRecord);
    }

    // Static inner classes below here to run database interactions in the background.

    /**
     * Inserts a user weight record into the database.
     */
    private static class insertAsyncTask extends AsyncTask<UserWeightRecord, Void, Void> {

        private UserWeightRecordDao mAsyncTaskDao;

        insertAsyncTask(UserWeightRecordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final UserWeightRecord... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * Deletes all user weight records from the database (does not delete the table).
     */
    private static class deleteAllWeightRecordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserWeightRecordDao mAsyncTaskDao;

        deleteAllWeightRecordsAsyncTask(UserWeightRecordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /**
     *  Deletes a single weight record from the database.
     */
    private static class deleteWeightRecordAsyncTask extends AsyncTask<UserWeightRecord, Void, Void> {
        private UserWeightRecordDao mAsyncTaskDao;

        deleteWeightRecordAsyncTask(UserWeightRecordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final UserWeightRecord... params) {
            mAsyncTaskDao.deleteUserWeightRecord(params[0]);
            return null;
        }
    }

    /**
     *  Updates a weight record in the database.
     */
    private static class updateWeightRecordAsyncTask extends AsyncTask<UserWeightRecord, Void, Void> {
        private UserWeightRecordDao mAsyncTaskDao;

        updateWeightRecordAsyncTask(UserWeightRecordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final UserWeightRecord... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}
