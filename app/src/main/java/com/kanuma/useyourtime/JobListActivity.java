package com.kanuma.useyourtime;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kanuma.useyourtime.Adapters.JobListAdapter;
import com.kanuma.useyourtime.DB.Job;
import com.kanuma.useyourtime.Fragments.AddNewJobDialog;
import com.kanuma.useyourtime.Utils.GroupUtils;
import com.kanuma.useyourtime.Utils.ListItem;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class JobListActivity extends AppCompatActivity implements AddNewJobDialog.OnMyDialogResult {


    private RecyclerView jobListView;
    private FloatingActionButton addJob;
    private AddNewJobDialog addJobDialog;
    private JobViewModel jobViewModel;
    private JobListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        jobViewModel = ViewModelProviders.of(this).get(JobViewModel.class);

        addJobDialog = new AddNewJobDialog(this);
        addJobDialog.setDialogResult(this);

        jobListView =findViewById(R.id.rv_jobList);
        adapter =new JobListAdapter(this);
        jobListView.setAdapter(adapter);
        jobListView.setLayoutManager(new LinearLayoutManager(this ));

        addJob =findViewById(R.id.fab_addJob);

        jobViewModel.getJob().observe(this, new Observer<List<Job>>() {
            @Override
            public void onChanged(List<Job> jobs) {

                HashMap<String, List<Job>> sortedList = GroupUtils.groupDataIntoHashMap(jobs);
                List<ListItem> groupedList = GroupUtils.GroupToArray(sortedList);
                adapter.setDataList(groupedList);

            }
        });


    }

    public void showAddJobDialog(View view) {

        addJobDialog.show();
    }

    @Override
    public void finish(Job newJob) {
        Toast.makeText(this,newJob.getJobDesc(),Toast.LENGTH_SHORT).show();
        jobViewModel.insert(newJob);
    }
}
