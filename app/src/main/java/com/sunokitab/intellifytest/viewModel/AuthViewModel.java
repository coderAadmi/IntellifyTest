package com.sunokitab.intellifytest.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel {

    private MutableLiveData<String> token;
    private AttendanceRepository repository;

    public void init()
    {
        if(repository != null)
        {
            return;
        }
        repository = AttendanceRepository.getInstance("");

    }

    public LiveData<String> getToken(String user, String pass)
    {
        token = repository.getToken(user, pass);
        return token;
    }

    public LiveData<String> getToken()
    {
        return token;
    }
}
