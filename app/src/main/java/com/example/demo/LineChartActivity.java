package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class LineChartActivity extends AppCompatActivity {

    private static final String TAG = "firebase";
    private ArrayList<mData> data = new ArrayList<>();
    private LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        chart = (LineChart) findViewById(R.id.chart);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("weight")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                addData(document.getData());
                            }
                            creatChat();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        Button btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private class mData {
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(Object object) {
            int num = ((Number)object).intValue();
            this.x = num;
        }

        public void setY(Object object) {
            int num = ((Number)object).intValue();
            this.y = num;
        }
    }

    public void addData(Map<String, Object> map) {
        mData newData = new mData();
        newData.setX(map.get("date"));
        newData.setY(map.get("weight"));
        data.add(newData);
    }

    private void creatChat() {
        LineChart chart = (LineChart) findViewById(R.id.chart);
        List<Entry> entries = new ArrayList<Entry>();
        for(int i = 0; i < data.size(); i++){
            entries.add(new Entry(i, data.get(i).getY()));
        }
        LineDataSet dataSet = new LineDataSet(entries, "體重"); // add entries to dataset
        dataSet.setMode(LineDataSet.Mode.LINEAR);//類型為折線
        dataSet.setColor(Color.GREEN);//線的顏色
        dataSet.setLineWidth(1.5f);//線寬
        dataSet.setDrawFilled(true);//使用範圍背景填充(預設不使用)
        dataSet.setCircleColor(Color.BLACK);//圓點顏色
        dataSet.setCircleRadius(3);//圓點大小
        dataSet.setDrawCircleHole(false);//圓點為實心(預設空心)
        dataSet.setValueTextSize(20f);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value) + "kg";
            }
        });


        /**設定圖表框架↓*/
        YAxis leftAxis = chart.getAxisLeft();//設置Y軸(左)
        YAxis rightAxis = chart.getAxisRight();//設置Y軸(右)
        rightAxis.setEnabled(false);//讓右邊Y消失
        XAxis xAxis = chart.getXAxis();//設定X軸
        //xAxis.setGranularity(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//將x軸表示字移到底下
        //xAxis.setLabelCount(3,false);//設定X軸上要有幾個標籤
//      xAxis.setEnabled(false);//去掉X軸數值
//      xAxis.setLabelRotationAngle(-45f);//讓字變成斜的
        //xAxis.setDrawGridLines(false);//將X軸格子消失掉
        xAxis.setValueFormatter(new MyValueFormatter());//設置X軸
        xAxis.setSpaceMin(0.5f);//折線起點距離左側Y軸距離
        xAxis.setSpaceMax(0.5f);//折線終點距離右側Y軸距離
        xAxis.setTextColor(Color.WHITE);
        leftAxis.setTextColor(Color.WHITE);

        //圖表本身一般樣式
        chart.getDescription().setEnabled(false);//讓右下角文字消失
        chart.setNoDataText("暫無資料");
        chart.setTouchEnabled(false);
        chart.setGridBackgroundColor(Color.WHITE);
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
    }

    private class MyValueFormatter extends ValueFormatter{//設置Ｘ軸
        @Override
        public String getFormattedValue(float value) {
            //Log.d(TAG, "getFormattedValue: " + String.valueOf(data.get((int) value).getX()));
            return String.valueOf(data.get((int) value).getX());
        }
    }
}