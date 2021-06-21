package com.example.payroll.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.payroll.data.OwnerDao;
import com.example.payroll.data.Ownerd;

@Database(entities  ={Ownerd.class},version = 2)
public abstract class OwnerDatabase extends RoomDatabase {

    private  static OwnerDatabase instance;
    public  abstract OwnerDao ownerDao();

    public  static  synchronized  OwnerDatabase getInstance(Context context)
    {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    OwnerDatabase.class, "owner_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
