package com.example.sojin.busbellapp.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.sojin.busbellapp.R;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button okButton;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        okButton = (Button)findViewById(R.id.okButton);
        listView = (ListView)findViewById(R.id.listView);

        if(Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList busRouteList =  BusRouteInfo.getBusRouteList(editText.getText().toString());
                BusRouteListAdapter busRouteListAdapter = new BusRouteListAdapter(busRouteList);

                listView.setAdapter(busRouteListAdapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BusRouteInfoItem item = (BusRouteInfoItem) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, BusStationsListActivity.class);
                intent.putExtra("routeId",item.getBusRouteId());

                startActivity(intent);
            }
        });
    }
}