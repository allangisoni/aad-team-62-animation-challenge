package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddRecord extends AppCompatActivity {

    @BindView(R.id.tvDate) TextView tvDate;
    @BindView(R.id.etfullnames) EditText etFullNames;
    @BindView(R.id.etage) EditText etAge;
    @BindView(R.id.etpurpose) EditText etPurpose;
    @BindView(R.id.btSave) Button btSave;
    @BindView(R.id.addConstraint)
    ConstraintLayout mConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        ButterKnife.bind(this);
        mConstraintLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slidein));
        //btSave.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fadeinout));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = " ";
        Calendar calendar = Calendar.getInstance();
        currentDate = dateFormat.format(calendar.getTime());
        tvDate.setText(currentDate);


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (checkFields())
                savetoDb();
            }
        });
    }

    private void savetoDb() {
        btSave.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
        if(etFullNames.getText().toString().trim().length() != 0 || !etFullNames.getText().toString().isEmpty()) {
            // Gets the data repository in write mode
            SQLiteDatabase db = new MedicareDbHelper(this).getWritableDatabase();

// Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(MedicareContract.MedicareEntry.COLUMN_NAME_fullNames, etFullNames.getText().toString());
            values.put(MedicareContract.MedicareEntry.COLUMN_NAME_age, etAge.getText().toString());
            values.put(MedicareContract.MedicareEntry.COLUMN_NAME_date, tvDate.getText().toString());
            values.put(MedicareContract.MedicareEntry.COLUMN_NAME_purpose, etPurpose.getText().toString());

// Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(MedicareContract.MedicareEntry.TABLE_NAME, null, values);
            Toast.makeText(AddRecord.this, "Success", Toast.LENGTH_SHORT).show();

            etFullNames.setText(" ");
            etAge.setText(" ");
            etPurpose.setText(" ");

        } else{
            Toast.makeText(AddRecord.this, "Missing Name", Toast.LENGTH_SHORT).show();

        }
    }


    private boolean checkFields(){

        if(etFullNames.getText().toString().trim().equals(" ") ||etAge.getText().toString().trim().equals(" ") ||etPurpose.getText().toString().trim().equals(" ")){
            Toast.makeText(AddRecord.this, "Missing Values", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }

    }



}
