package com.example.medicare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordsViewHolder> {

    List<RecordModel> recordModelList ;
    Context context;
    public class RecordsViewHolder extends RecyclerView.ViewHolder{

        public TextView tvAge;
        public TextView tvDate;
        public TextView tvfullNames;
        public TextView tvPurpose;

        public RecordsViewHolder( View itemView) {
            super(itemView);

            tvAge = itemView.findViewById(R.id.tvrecordAge);
            tvDate = itemView.findViewById(R.id.tvrecordDate);
            tvfullNames = itemView.findViewById(R.id.tvrecordFullnames);
            tvPurpose = itemView.findViewById(R.id.tvrecordPurpose);

        }
    }

    public RecordAdapter(Context context,  List<RecordModel> recordModelList){

        this.context = context;
        this.recordModelList = recordModelList;
    }

    @Override
    public RecordsViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_list, parent, false);
        return new RecordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecordsViewHolder holder, int position) {
       RecordModel recordModel = recordModelList.get(position);
        holder.tvAge.setText("Age:"+ " "+ recordModel.getAge());
        holder.tvDate.setText("Date Visited: "+" "+recordModel.getRecordDate());
        holder.tvfullNames.setText("Full Names:" +" "+recordModel.getFullnames());
        holder.tvPurpose.setText("Purpose of Visit:"+" "+ recordModel.getPurpose());
    }

    @Override
    public int getItemCount() {
        return recordModelList.size();
    }



}
