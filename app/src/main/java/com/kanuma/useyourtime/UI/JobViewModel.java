package com.kanuma.useyourtime.UI;

import android.app.Application;

import com.kanuma.useyourtime.DB.Job;
import com.kanuma.useyourtime.DB.Repository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class JobViewModel extends AndroidViewModel {

    private Repository jobRepo;
    private LiveData<List<Job>> allJobs;

    public JobViewModel(@NonNull Application application) {
        super(application);

        jobRepo = new Repository(application);
        allJobs = jobRepo.getAllJobs();

    }

    public LiveData<List<Job>> getAllJobs(){
        return allJobs;
    }

    public void insert(Job job){jobRepo.insert(job);}
    public void deleteAll(){jobRepo.delete();}
}
