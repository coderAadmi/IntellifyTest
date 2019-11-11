package com.sunokitab.intellifytest.retrofit;


import com.sunokitab.intellifytest.model.AttendanceModel;
import com.sunokitab.intellifytest.model.Sample;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

     String BASE_URL = "ll";

    @GET("/api/attendance?for=AllClassAttendance")
    Call<List<AttendanceModel>> getAttendance(@Query("student_id") int id);


    @FormUrlEncoded
    @POST("")
    Call<String> getToken(@Field("user") String user, @Field("pass") String pass);

    //To get sample data for testing from open source api
    @GET("posts")
    Call<List<Sample>> getData();

}
