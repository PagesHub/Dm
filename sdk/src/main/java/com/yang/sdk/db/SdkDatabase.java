package com.yang.sdk.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

/**
 * Describe:  RoomDatabase
 * Created by Yang on 2019/4/16.
 */
public class SdkDatabase extends RoomDatabase {

    private SdkDatabase() {

    }

    private static class SdkDatabaseInstance {
        private static final SdkDatabase INSTANCE = new SdkDatabase();
    }

    public static SdkDatabase getInstance() {
        return SdkDatabaseInstance.INSTANCE;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {

        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {


        return null;
    }

    @Override
    public void clearAllTables() {
    }
}
