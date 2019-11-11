package com.sunokitab.intellifytest.controller;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunokitab.intellifytest.R;
import com.sunokitab.intellifytest.adapter.AttendanceListAdapter;
import com.sunokitab.intellifytest.model.AttendanceModel;
import com.sunokitab.intellifytest.model.Sample;
import com.sunokitab.intellifytest.viewModel.HomeActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mList;

    private AttendanceListAdapter mAdapter;

    private ProgressBar mProgress;

    private List<AttendanceModel> attendanceList = new ArrayList<>();
    private HomeActivityViewModel viewModel;


    /**
     * Put student id & token here
     */
    int studentId = 1234;
    String token = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(Color.BLACK);
        mList = findViewById(R.id.list);

        mProgress= findViewById(R.id.progress);
        mList.setLayoutManager(new LinearLayoutManager(this));

        viewModel = ViewModelProviders.of(this).get(HomeActivityViewModel.class);
        viewModel.init(studentId,token);
        viewModel.getAttendance().observe(this, new Observer<List<AttendanceModel>>() {
            @Override
            public void onChanged(List<AttendanceModel> attendanceModels) {
                if(attendanceModels==null)
                {
                    Toast.makeText(HomeActivity.this, "Plz put student id & token", Toast.LENGTH_SHORT).show();
                    return;
                }
                mProgress.setVisibility(View.GONE);
                attendanceList.addAll(attendanceModels);
                mAdapter.notifyDataSetChanged();
            }
        });

        init();

    }

    private void init()
    {
        if(mAdapter == null)
        {
            mAdapter = new AttendanceListAdapter(attendanceList);
            mList.setAdapter(mAdapter);
            mList.setItemAnimator(new DefaultItemAnimator());
            mList.setNestedScrollingEnabled(true);
        }
        else
        {
            mAdapter.notifyDataSetChanged();
        }
    }

}
