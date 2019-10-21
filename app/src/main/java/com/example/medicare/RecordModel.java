package com.example.medicare;

public class RecordModel {

    String fullnames = "", purpose=" ", recordDate = " ";
    int age = 0 ;
    public RecordModel(){

    }

    public RecordModel(String date, String fullnames, String purpose, int age)
    {
        this.fullnames = fullnames;
        this.recordDate = date;
        this.purpose = purpose;
        this.age = age;
    }
    public String getFullnames() {
        return fullnames;
    }

    public void setFullnames(String fullnames) {
        this.fullnames = fullnames;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }
}
