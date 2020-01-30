package com.example.testlist3.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MessageModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MessageDao messageDao();
}