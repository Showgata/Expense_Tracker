package com.kanuma.useyourtime;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void showListOfJobs(View view) {

        Intent i =new Intent(this,JobListActivity.class);
        startActivity(i);


    }
}
