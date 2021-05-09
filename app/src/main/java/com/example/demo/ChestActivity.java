package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ChestActivity extends AppCompatActivity {

    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private Button btn_finish;
    private TextView textView;
    private TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest);

        textView = (TextView)findViewById(R.id.editTextNumber);
        label = (TextView)findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        label.setVisibility(View.INVISIBLE);

        checkBox1 = (CheckBox)findViewById(R.id.checkBox_upper);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox_flat);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox_lower);
        checkBox4 = (CheckBox)findViewById(R.id.checkBox_tri);

        checkBox1.setOnCheckedChangeListener(checkBoxOnCheckedChange);
        checkBox2.setOnCheckedChangeListener(checkBoxOnCheckedChange);
        checkBox3.setOnCheckedChangeListener(checkBoxOnCheckedChange);
        checkBox4.setOnCheckedChangeListener(checkBoxOnCheckedChange);

        btn_finish = (Button) findViewById(R.id.chest_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Calendar mCal = Calendar.getInstance();
                String dateformat = "yyyyMMdd";
                SimpleDateFormat df = new SimpleDateFormat(dateformat);
                String today = df.format(mCal.getTime());

                // Create a new user with a first and last name
                Map<String, Object> data = new HashMap<>();
                data.put("date", Integer.valueOf(today));
                //data.put("date", mCal.getTimeInMillis());
                if (textView.getText().toString().equals("")) {
                    data.put("weight", 0);
                }
                else {
                    data.put("weight", Integer.valueOf(textView.getText().toString()));
                }
                // Add a new document with a generated ID
                db.collection("weight").document(today).set(data);
                finish();
            }
        });
    }


    private CompoundButton.OnCheckedChangeListener checkBoxOnCheckedChange =
            new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if(checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked()) {
                        btn_finish.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                        label.setVisibility(View.VISIBLE);
                    }
                    else {
                        btn_finish.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                        label.setVisibility(View.INVISIBLE);
                    }
                }
            };
}