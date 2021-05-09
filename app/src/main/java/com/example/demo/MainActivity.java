package com.example.demo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private MyAdapter adapter;
    private ArrayList<String> mData = new ArrayList<>();
    private Button btn_new;
    private Button btn_skip;
    private Button btn_start;
    private Button btn_chart;
    private static final String PREFS_NAME = "LIST";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences userList = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int size = userList.getInt("size", 0);
        for(int i = 0; i < size; i++) {
            mData.add(userList.getString(String.valueOf(i), ""));
        }

        // 連結元件
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        // 設置RecyclerView為列表型態
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        // 設置格線
        //recycler_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // 將資料交給adapter
        adapter = new MyAdapter(mData);
        // 設置adapter給recycler_view
        recycler_view.setAdapter(adapter);

        btn_new = (Button) findViewById(R.id.btn_new);
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 新增一個項目
                //adapter.addItem("New");
                //intent.putExtra(, mData);
                Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
                //intent.putExtra("data","data");
                startActivityForResult(intent, 99);
            }
        });

        btn_skip = (Button) findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItemOnLast(mData.get(0));
                adapter.removeItem(0);

            }
        });

        btn_chart = (Button)findViewById(R.id.btn_chart);
        btn_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LineChartActivity.class);
                startActivity(intent);
            }
        });


        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(mData.get(0).equals(getString(R.string.part1))) {
                    intent = new Intent(MainActivity.this, ChestActivity.class);
                    startActivity(intent);
                    adapter.addItemOnLast(mData.get(0));
                    adapter.removeItem(0);
                }
                else if(mData.get(0).equals(getString(R.string.part2))) {

                    intent = new Intent(MainActivity.this, BackActivity.class);
                    startActivity(intent);
                    adapter.addItemOnLast(mData.get(0));
                    adapter.removeItem(0);

                }
                else if(mData.get(0).equals(getString(R.string.part3))) {
                    intent = new Intent(MainActivity.this, LegActivity.class);
                    startActivity(intent);
                    adapter.addItemOnLast(mData.get(0));
                    adapter.removeItem(0);
                }
                else if(mData.get(0).equals(getString(R.string.part4))) {
                    intent = new Intent(MainActivity.this, ShoulderActivity.class);
                    startActivity(intent);
                    adapter.addItemOnLast(mData.get(0));
                    adapter.removeItem(0);
                }
                else if(mData.get(0).equals(getString(R.string.part5))) {
                    intent = new Intent(MainActivity.this, CoreActivity.class);
                    startActivity(intent);
                    adapter.addItemOnLast(mData.get(0));
                    adapter.removeItem(0);
                }
                else if(mData.get(0).equals(getString(R.string.part6))){
                    intent = new Intent(MainActivity.this, RunActivity.class);
                    startActivity(intent);
                    adapter.addItemOnLast(mData.get(0));
                    adapter.removeItem(0);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 99){
            if(resultCode == 88){
                Bundle bundle = data.getExtras();
                String back = bundle.getString("back");
                Toast.makeText(MainActivity.this, "已新增 " + back + "訓練",Toast.LENGTH_SHORT).show();
                adapter.addItemOnLast(back);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences userList = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = userList.edit();
        editor.clear();

        editor.putInt("size", mData.size());

        for(int i=0; i < mData.size(); i++) {
            editor.putString(String.valueOf(i), mData.get(i));
        }
        editor.commit();
    }
}
