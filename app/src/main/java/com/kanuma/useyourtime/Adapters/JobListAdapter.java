package com.kanuma.useyourtime.Adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kanuma.useyourtime.R;
import com.kanuma.useyourtime.Utils.DateItem;
import com.kanuma.useyourtime.Utils.ExpenseItem;
import com.kanuma.useyourtime.Utils.GeneralItem;
import com.kanuma.useyourtime.Utils.ListItem;

import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ListItem> dataList;
    private Activity activity;

    public JobListAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        RecyclerView.ViewHolder viewHolder = null;
        switch (i){

            case ListItem.TYPE_GENERAL:
                View v1 = LayoutInflater.from(activity)
                        .inflate(R.layout.snippet_single_job_layout,viewGroup,false);
                viewHolder = new JobListViewHolder(v1);

                break;


            case ListItem.TYPE_DATE:

                View v2 = LayoutInflater.from(activity)
                        .inflate(R.layout.snippet_date_label,viewGroup,false);
                viewHolder = new DateViewHolder(v2);

                break;

            case ListItem.TYPE_TOTAL:

                View v3 = LayoutInflater.from(activity)
                        .inflate(R.layout.snippet_total_exp,viewGroup,false);
                viewHolder = new TotalExpViewHolder(v3);

                break;


        }




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {

            case ListItem.TYPE_GENERAL:
                GeneralItem generalItem
                        = (GeneralItem) dataList.get(position);
                JobListViewHolder jobViewHolder
                        = (JobListViewHolder) holder;
                // Populate general item data here

                jobViewHolder.jobDesc.setText(generalItem.getJobs().getJobDesc());
                jobViewHolder.jobExpense.setText("Rs."+generalItem.getJobs().getExpenseAmount());

                break;

            case ListItem.TYPE_DATE:
                DateItem dateItem
                        = (DateItem) dataList.get(position);
                DateViewHolder dateViewHolder
                        = (DateViewHolder) holder;

                dateViewHolder.dateLabel.setText(dateItem.getDate());


                break;
            case ListItem.TYPE_TOTAL:
                ExpenseItem expItem
                        = (ExpenseItem) dataList.get(position);
                TotalExpViewHolder expHolder
                        = (TotalExpViewHolder) holder;

                expHolder.totalLabel.setText("Rs."+expItem.getTotalExpense());


                break;
        }


    }


    public void setDataList(List<ListItem> jobList){
        dataList = jobList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (dataList != null)
            return dataList.size();
        else return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getType();
    }

   class JobListViewHolder extends RecyclerView.ViewHolder
    {

        private TextView jobDesc;
        private TextView jobExpense;

        JobListViewHolder(@NonNull View v) {
            super(v);

            jobDesc = v.findViewById(R.id.tv_job_desc);
            jobExpense =v.findViewById(R.id.tv_duration);
        }

    }


    class DateViewHolder extends RecyclerView.ViewHolder {

        private TextView dateLabel;
        DateViewHolder(View v) {
            super(v);
            dateLabel = v.findViewById(R.id.date_label);
        }

        public TextView getDateLabel() {
            return dateLabel;
        }
    }

    private class TotalExpViewHolder extends RecyclerView.ViewHolder {
        private TextView totalLabel;
        TotalExpViewHolder(View v) {
            super(v);
            totalLabel = v.findViewById(R.id.total_exp);
        }
    }
}
