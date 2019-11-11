package com.sunokitab.intellifytest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunokitab.intellifytest.R;
import com.sunokitab.intellifytest.model.AttendanceModel;
import com.sunokitab.intellifytest.model.Sample;

import java.util.List;

public class AttendanceListAdapter extends RecyclerView.Adapter<AttendanceListAdapter.AttendanceViewHolder> {

    private List<AttendanceModel> modelList;

    public AttendanceListAdapter(List<AttendanceModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_card_view,parent,false);
        return new AttendanceViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {
        AttendanceModel model= modelList.get(holder.getAdapterPosition());
        holder.mClassName.setText(model.getClassName());
        holder.mTotalClasses.setText(model.getTotalLectures());
        holder.mAttended.setText(model.getAttended());
    }

    @Override
    public int getItemCount() {
        if(modelList == null)
            return 0;
        return modelList.size();
    }

    class AttendanceViewHolder extends RecyclerView.ViewHolder{

        TextView mClassName;
        TextView mTotalClasses;
        TextView mAttended;
        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            mClassName = itemView.findViewById(R.id.class_name);
            mTotalClasses = itemView.findViewById(R.id.total_classes);
            mAttended = itemView.findViewById(R.id.attended);
        }
    }
}
