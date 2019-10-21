package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static com.example.medicare.MedicareContract.*;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.rvRecords)
    RecyclerView mRecyclerView;

    private  RecordAdapter mRecordAdapter;
     List<RecordModel> mRecords = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getAllRecords();
        mRecords.addAll(getAllRecords());
        mRecordAdapter = new RecordAdapter(this, mRecords);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mlayoutManager);
        mRecyclerView.setAdapter(mRecordAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddRecord.class));
            }
        });
    }


    public List<RecordModel> getAllRecords() {
        List<RecordModel> records = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + MedicareEntry.TABLE_NAME + " ORDER BY " +
                MedicareEntry.COLUMN_NAME_fullNames + " DESC";

        SQLiteDatabase db = new MedicareDbHelper(this).getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RecordModel recordModel = new RecordModel();
                recordModel.setFullnames(cursor.getString(cursor.getColumnIndex(MedicareEntry.COLUMN_NAME_fullNames)));
                recordModel.setAge(cursor.getInt(cursor.getColumnIndex(MedicareEntry.COLUMN_NAME_age)));
                recordModel.setPurpose(cursor.getString(cursor.getColumnIndex(MedicareEntry.COLUMN_NAME_purpose)));
                recordModel.setRecordDate(cursor.getString(cursor.getColumnIndex(MedicareEntry.COLUMN_NAME_date)));

                records.add(recordModel);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        Log.d("records", " " + records.size());
        // return notes list
        return records;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecords.clear();
        mRecords.addAll(getAllRecords());
        mRecordAdapter.notifyDataSetChanged();

    }
}
