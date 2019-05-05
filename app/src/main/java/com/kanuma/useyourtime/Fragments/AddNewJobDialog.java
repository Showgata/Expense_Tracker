package com.kanuma.useyourtime.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.kanuma.useyourtime.DB.Job;
import com.kanuma.useyourtime.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewJobDialog extends Dialog implements
        android.view.View.OnClickListener {

    private AppCompatActivity c;
    public Dialog d;
    private Button yes, no;
    private Spinner hours,mins;
    private TextView jobDesc;
    private TextView expAmt;

    private OnMyDialogResult mDialogResult;

    public AddNewJobDialog(AppCompatActivity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.snippet_add_job_dialog);

        yes =findViewById(R.id.btn_set);
        no =findViewById(R.id.btn_close);
        jobDesc =findViewById(R.id.tv_job_desc_dialog);
        expAmt = findViewById(R.id.tv_expenseAmt);


        //for click event
        yes.setOnClickListener(this);
        no.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set:
                onSet(v);
                break;
            case R.id.btn_close:
                dismiss();
                break;
            default:
                break;
        }

    }

    private void onSet(View v)
    {
        String desc = jobDesc.getText().toString().trim();
        Integer ea = Integer.parseInt(expAmt.getText().toString());
        Date d =Calendar.getInstance().getTime();
        Job newJob = new Job(desc,ea,d);

        if( mDialogResult != null ){
            mDialogResult.finish(newJob);
        }
        this.dismiss();
    }


    private ArrayList<Integer> getArray(int limit) {
        ArrayList<Integer> arr =new ArrayList<>();

        int[] data = new int[limit];

        for(int i=0;i<=limit;i++)
            arr.add(i);


        return arr;

    }

    public void setDialogResult(OnMyDialogResult dialogResult){
        mDialogResult = dialogResult;
    }

    public interface OnMyDialogResult{
        void finish(Job job);
    }
}