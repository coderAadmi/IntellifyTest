package com.sunokitab.intellifytest.controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sunokitab.intellifytest.R;
import com.sunokitab.intellifytest.viewModel.AttendanceRepository;
import com.sunokitab.intellifytest.viewModel.AuthViewModel;


public class AuthActivity extends AppCompatActivity {


    private AuthViewModel authViewModel;

    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        getWindow().setStatusBarColor(Color.BLACK);

        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mLogin = findViewById(R.id.login);
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        authViewModel.init();

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchToken();
            }
        });
    }

    private void fetchToken()
    {
        String token = AttendanceRepository.getInstance("").getToken(this);
        if(token == null)
        {
            //check for it's expiration too
            Intent intent = new Intent(AuthActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return;
        }
        String user = mUsername.getText().toString(), pass = mPassword.getText().toString();
        authViewModel.getToken(user, pass).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("CALL_TO_TOKEN", "TOKEN FETCHED: "+s);
                Intent intent = new Intent(AuthActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
