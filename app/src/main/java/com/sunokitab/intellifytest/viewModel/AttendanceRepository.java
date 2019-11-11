package com.sunokitab.intellifytest.viewModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sunokitab.intellifytest.model.AttendanceModel;
import com.sunokitab.intellifytest.model.Sample;
import com.sunokitab.intellifytest.retrofit.Api;
import com.sunokitab.intellifytest.retrofit.CallInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceRepository {

    private static AttendanceRepository attendanceRepository;

    public static AttendanceRepository getInstance(String token)
    {
        if(attendanceRepository == null)
        {
            attendanceRepository = new AttendanceRepository(token);
        }
        return attendanceRepository;
    }

    private Api api;

    public AttendanceRepository(String token)
    {
            api = CallInstance.createApi(token);
    }

    public MutableLiveData<List<AttendanceModel>> getAttendance(int studentId)
    {
        final MutableLiveData<List<AttendanceModel>> attendance = new MutableLiveData<>();
        api.getAttendance(studentId).enqueue(new Callback<List<AttendanceModel>>() {
            @Override
            public void onResponse(Call<List<AttendanceModel>> call, Response<List<AttendanceModel>> response) {
                if(response.isSuccessful())
                {
                    attendance.setValue(response.body());
                }
                else
                {
                    Log.d("CALL_TO_SER","FAILED: response not succesfull");
                }
            }

            @Override
            public void onFailure(Call<List<AttendanceModel>> call, Throwable t) {
                attendance.setValue(null);
                Log.d("CALL_TO_SERV","ERROR: "+t.getMessage());
            }
        });
        return attendance;
    }

    public  MutableLiveData<List<Sample>> getData()
    {
        final MutableLiveData<List<Sample>> samples = new MutableLiveData<>();
        api.getData().enqueue(new Callback<List<Sample>>() {
            @Override
            public void onResponse(Call<List<Sample>> call, Response<List<Sample>> response) {
                if(response.isSuccessful())
                {
                    Log.d("CALL_P","SUCCESS ");
                    samples.setValue(response.body());
                }
                else
                {
                    Log.d("CALL_TF","TF");
                }
            }

            @Override
            public void onFailure(Call<List<Sample>> call, Throwable t) {
                    Log.d("CALL_E",t.getMessage());
                    samples.setValue(null);
            }
        });

        return samples;
    }

    public MutableLiveData<String> getToken(String username, String pass)
    {
        /**
         * for fetching token from username and password
         */
        final MutableLiveData<String > token  = new MutableLiveData<>();
        api.getToken(username, pass).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {
                    Log.d("CALL_T","TOken found");
                    token.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("CALL_E",t.getMessage());
                token.setValue(null);
            }
        });
        return token;
    }

    public void saveToken(Context context, String token)
    {
        /**
         * Save token in sharedpreference for further use
         */
        SharedPreferences.Editor editor = context.getSharedPreferences("AUTH_TOKEN",Context.MODE_PRIVATE).edit();
        editor.putString("BEARER",token);
        editor.commit();
        editor.apply();
    }

    public String getToken(Context context)
    {
        /**
         * Get saved token from sharedpreference
         */
        SharedPreferences sharedPreferences = context.getSharedPreferences("AUTH_TOKEN",Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("BEARER",null);
        return token;
    }


}
