package com.kanuma.useyourtime.Utils;

import com.kanuma.useyourtime.DB.Job;

import java.util.List;

public class GeneralItem extends ListItem {

    private Job jobs;

    public GeneralItem(Job jobs) {
        this.jobs = jobs;
    }

    public Job getJobs() {
        return jobs;
    }

    public void setJobs(Job jobs) {
        this.jobs = jobs;
    }

    @Override
    public int getType() {
        return TYPE_GENERAL;
    }
}
