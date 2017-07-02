package com.example.sojin.busbellapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BusStationsListActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_stations_list_activity_main);

        Intent intent = new Intent(this.getIntent());
        String routeId = intent.getStringExtra("routeId");

        TextView txt = (TextView)findViewById(R.id.txt);
        txt.setText(routeId.toString());

        listView = (ListView)findViewById(R.id.listView_bus_station_list);

        ArrayList<BusStationsByRouteInfoItem> busStationByRouteList = BusRouteInfo.getStationByRouteList(routeId);
        BusStationsByRouteListAdapter busStationsByRouteListAdapter = new BusStationsByRouteListAdapter(busStationByRouteList);

        listView.setAdapter(busStationsByRouteListAdapter);
    }
}
