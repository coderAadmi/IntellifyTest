package com.sunokitab.intellifytest.retrofit;

import androidx.lifecycle.MutableLiveData;

import com.sunokitab.intellifytest.model.Sample;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallInstance {
    public static String BASE_URL = "http://intellify.services.in";

    public static Retrofit instance  = null;

    public static Retrofit getInstance(final String token)
    {
        if(instance == null)
        {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            Request newRequest  = chain.request().newBuilder()
                                    .addHeader("Authorization", "Bearer " + token)
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    }).build();

            instance = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

    public static Api createApi(String token)
    {
        if(instance == null)
        {
           getInstance(token);
        }
        return instance.create(Api.class);
    }
}
