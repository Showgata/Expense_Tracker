package com.kanuma.useyourtime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.flexbox.FlexboxLayout;
import com.kanuma.useyourtime.DB.Job;
import com.kanuma.useyourtime.Utils.GroupUtils;
import com.kanuma.useyourtime.Utils.ListItem;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ExpenseByDateActivity extends AppCompatActivity {

    FlexboxLayout addViewLayout;
    private JobViewModel jobViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_by_date);

        addViewLayout =findViewById(R.id.addViewLayout);

        jobViewModel = ViewModelProviders.of(this).get(JobViewModel.class);

        jobViewModel.getAllDates().observe(this, new Observer<List<Date>>() {
            @Override
            public void onChanged(List<Date> dates) {

                Set<String> formattedDates= GroupUtils.listOfFormattedDates(dates);
                setInViewGroup(formattedDates);

            }
        });

    }

    private void setInViewGroup(Set<String> formattedDates) {

        for(String date : formattedDates){
            Button myButton = new Button(this);
            myButton.setText(date);
            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT);
            addViewLayout.addView(myButton,lp);
        }


    }
}
