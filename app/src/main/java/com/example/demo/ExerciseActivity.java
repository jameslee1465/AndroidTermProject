package com.example.demo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;

    @SuppressLint("WrongViewCast")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        button1 = (Button) findViewById(R.id.patr1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("back", getString(R.string.part1));
                setResult(88,intent);
                finish();
            }
        });

        button2 = (Button) findViewById(R.id.patr2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("back",getString(R.string.part2));
                setResult(88,intent);
                finish();
            }
        });

        button3 = (Button) findViewById(R.id.patr3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("back",getString(R.string.part3));
                setResult(88,intent);
                finish();
            }
        });

        button4 = (Button) findViewById(R.id.patr4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("back",getString(R.string.part4));
                setResult(88,intent);
                finish();
            }
        });

        button5 = (Button) findViewById(R.id.patr5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("back",getString(R.string.part5));
                setResult(88,intent);
                finish();
            }
        });

        button6 = (Button) findViewById(R.id.patr6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("back",getString(R.string.part6));
                setResult(88,intent);
                finish();
            }
        });
    }

}