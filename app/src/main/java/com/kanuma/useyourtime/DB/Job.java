package com.kanuma.useyourtime.DB;


import com.kanuma.useyourtime.Utils.TimeConverter;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "jobs_table")
public class Job {

    @PrimaryKey(autoGenerate=true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "job_desc")
    private String jobDesc;

    @NonNull
    @ColumnInfo(name = "expenseAmount")
    private Integer expenseAmount;


    @NonNull
    @ColumnInfo(name = "createdDate")
    @TypeConverters({TimeConverter.class})
    private Date createdDate;


    public Job(@NonNull String jobDesc, @NonNull Integer expenseAmount, @NonNull Date createdDate) {
        this.jobDesc = jobDesc;
        this.expenseAmount = expenseAmount;
        this.createdDate = createdDate;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public Integer getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Integer expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(@NonNull Date createdDate) {
        this.createdDate = createdDate;
    }
}
