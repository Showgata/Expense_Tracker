package com.kanuma.useyourtime;

import android.app.Application;

import com.kanuma.useyourtime.DB.Job;
import com.kanuma.useyourtime.DB.Repository;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class JobViewModel extends AndroidViewModel {

    private Repository repo;
    private LiveData<List<Job>> job;

    public JobViewModel(Application application){
        super(application);
        repo = new Repository(application);
        job = repo.getAllJobs();
    }

    public LiveData<List<Job>> getJob() {
        return job;
    }

    public void insert(Job job){
        repo.insert(job);
    }

    public LiveData<List<Date>> getAllDates(){
        return repo.getAllDates();
    }
}
