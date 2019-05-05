package com.kanuma.useyourtime.DB;

import android.app.Application;
import android.os.AsyncTask;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;

public class Repository {

    private JobDao jobDao;
    private LiveData<List<Job>> allJobs;
    private LiveData<List<Date>> getAllDates;

    public Repository(Application application) {

        JobRoomDB db = JobRoomDB.getDatabase(application);
        jobDao =db.jobDao();
        allJobs =jobDao.getAllJobs();
        getAllDates=jobDao.getAllDates();

    }

    public LiveData<List<Job>> getAllJobs(){
        return allJobs;
    }

    public void insert(Job job)
    {
        new inBackgroundTask(jobDao,"insert").execute(job);
    }

    public void delete()
    {
        new inBackgroundTask(jobDao,"deleteAll").execute();
    }

    public LiveData<List<Date>> getAllDates()
    {
        return getAllDates;
    }


    private static class inBackgroundTask extends AsyncTask<Job, Void, Void> {

        private JobDao mAsyncJobDao;
        private String operation;


        inBackgroundTask(JobDao dao,String operation) {
            mAsyncJobDao = dao;
            this.operation=operation;
        }

        // understand again
        @Override
        protected Void doInBackground(Job... params) {

            switch (operation) {
                case "insert":
                    mAsyncJobDao.insertJob(params[0]);
                    break;
                case "deleteAll":
                    mAsyncJobDao.deleteAll();
                    break;
            }
            return null;
        }
    }
}
