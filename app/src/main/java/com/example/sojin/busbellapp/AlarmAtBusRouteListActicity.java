package com.example.sojin.busbellapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by KSM on 2017-07-02.
 */

public class AlarmAtBusRouteListActicity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_at_bus_route_info_item);
//
//        Intent intent = new Intent(this.getIntent());
//        String routeId = intent.getStringExtra("routeId");
//
//        TextView txt = (TextView)findViewById(R.id.textview1);
//
//        /* 정보들을 받아와서 채우기*/
//
//        listView = (ListView)findViewById(R.id.listView_alarm);
//
//        ArrayList<BusStationsByRouteInfoItem> busStationByRouteList = BusRouteInfo.getStationByRouteList(routeId);
//        BusStationsByRouteListAdapter busStationsByRouteListAdapter = new BusStationsByRouteListAdapter(busStationByRouteList);
//
//        listView.setAdapter(busStationsByRouteListAdapter);
    }
}
