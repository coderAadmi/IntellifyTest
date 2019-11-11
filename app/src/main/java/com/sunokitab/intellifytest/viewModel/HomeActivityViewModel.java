package com.sunokitab.intellifytest.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sunokitab.intellifytest.model.AttendanceModel;
import com.sunokitab.intellifytest.model.Sample;

import java.util.List;

public class HomeActivityViewModel extends ViewModel
{
    private MutableLiveData<List<AttendanceModel>> attendanceList;
    private AttendanceRepository repository;


    public void init(int studentId, String token)
    {
        if(repository != null)
        {
            return;
        }
        repository = AttendanceRepository.getInstance(token);
        attendanceList = repository.getAttendance(studentId);
    }



    public LiveData<List<AttendanceModel>> getAttendance()
    {
        return attendanceList;
    }
}
