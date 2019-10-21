package com.example.medicare;

import android.provider.BaseColumns;

public final class MedicareContract {

    private MedicareContract(){

    }

    public static class  MedicareEntry implements BaseColumns {
        public static final String TABLE_NAME = "patient_Records";
        public static final String COLUMN_NAME_fullNames = "fullNames";
        public static final String COLUMN_NAME_age = "age";
        public static final String COLUMN_NAME_date = "date_visited";
        public static final String COLUMN_NAME_purpose = "reasons";


        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_fullNames+ " TEXT, " +
                COLUMN_NAME_age + " INTEGER, " +
                COLUMN_NAME_date + " TEXT," +
                COLUMN_NAME_purpose + " TEXT"  + ")";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }


}
