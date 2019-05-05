package com.kanuma.useyourtime.DB;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Job.class},version = 1,exportSchema = false)
public abstract class JobRoomDB extends RoomDatabase {

    private static JobRoomDB INSTANCE;

    public abstract JobDao jobDao();

    public static JobRoomDB getDatabase(final Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (JobRoomDB.class){
                if(INSTANCE == null)
                {
                    //create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            JobRoomDB.class, "jobs_database")
                            .fallbackToDestructiveMigration() //migration strategy
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };



    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final JobDao dao;
        String[] x = {"dolphin", "crocodile", "cobra"};
        Integer[] y ={100,200,400};


        PopulateDbAsync(JobRoomDB db) {
            dao = db.jobDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.

            dao.deleteAll();
            Date d = Calendar.getInstance().getTime();

            for (int i = 0; i <= x.length - 1; i++) {
                Job job = new Job(x[i],y[i],d);
                dao.insertJob(job);
            }
            return null;
        }
    }

}
