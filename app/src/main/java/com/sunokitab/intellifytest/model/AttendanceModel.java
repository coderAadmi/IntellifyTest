package com.sunokitab.intellifytest.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class AttendanceModel {

    @SerializedName("class_name")
    public String className;


    @SerializedName("total_lectures")
    public int totalLectures;

    @SerializedName("attended")
    public int attended;

    public AttendanceModel() {
    }

    public AttendanceModel(String className, int totalLectures, int attended) {
        this.className = className;
        this.totalLectures = totalLectures;
        this.attended = attended;
    }

    public String getClassName() {
        return className;
    }

    public int getTotalLectures() {
        return totalLectures;
    }

    public int getAttended() {
        return attended;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setTotalLectures(int totalLectures) {
        this.totalLectures = totalLectures;
    }

    public void setAttended(int attended) {
        this.attended = attended;
    }

    @NonNull
    @Override
    public String toString() {
        return className + " : "+totalLectures+ " : "+attended;
    }
}
