package com.robyapp.android.healthtracker_litfit.RoomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * UserWeightRecordDatabase. Includes code to create the database.
 * After the app creates the database, all further interactions
 * with it happen through the UserWeightRecordViewModel.
 */

@Database(entities = {UserWeightRecord.class}, version = 1, exportSchema = false)
public abstract class UserWeightRecordDatabase extends RoomDatabase {

    public abstract UserWeightRecordDao userWeightRecordDao();

    private static UserWeightRecordDatabase INSTANCE;

    public static UserWeightRecordDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserWeightRecordDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here.
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserWeightRecordDatabase.class, "user_weight_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // This callback is called when the database has opened.
    // In this case, use PopulateDbAsync to populate the database
    // with the initial data set if the database has no entries.
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    // Populate the database with the initial data set
    // only if the database has no entries.
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserWeightRecordDao mDao;

        // Initial data set
//        private static String[] words = {"dolphin", "crocodile", "cobra", "elephant", "goldfish",
//                "tiger", "snake"};

        PopulateDbAsync(UserWeightRecordDatabase db) {
            mDao = db.userWeightRecordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // If we have no words, then create the initial list of words.
//            if (mDao.getAnyRecord().length < 1) {
//                for (int i = 0; i <= words.length - 1; i++) {
//                    Word word = new Word(words[i]);
//                    mDao.insert(word);
//                }
//            }
            return null;
        }
    }
}
