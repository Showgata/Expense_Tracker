package com.kanuma.useyourtime.DB;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface JobDao {

    @Insert
    void insertJob(Job job);

    @Query("Delete from jobs_table")
    void deleteAll();

    @Query("Select * from jobs_table")
    LiveData<List<Job>> getAllJobs();

    @Query("Select createdDate from jobs_table order by createdDate")
    LiveData<List<Date>> getAllDates();



}
