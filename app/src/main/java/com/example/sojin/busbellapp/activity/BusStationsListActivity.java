package com.example.sojin.busbellapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sojin.busbellapp.BusPos;
import com.example.sojin.busbellapp.BusRouteInfo;
import com.example.sojin.busbellapp.item.BusStationsByRouteInfoItem;
import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.adapter.BusStationsByRouteListAdapter;
import com.example.sojin.busbellapp.item.BusPosInfoItem;

import java.util.ArrayList;

public class BusStationsListActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_stations_list_activity_main);

        setCustomActionBar();
        TextView titleText=(TextView)findViewById(R.id.title_text);
        titleText.setText(R.string.busRoute_Info);

        Intent intent = new Intent(this.getIntent());
        final String routeId = intent.getStringExtra("routeId");

        TextView txt = (TextView)findViewById(R.id.txt);
        txt.setText(routeId.toString());

        listView = (ListView)findViewById(R.id.listView_bus_station_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BusStationsListActivity.this, AlarmAtBusRouteListActicity.class);
                intent.putExtra("routeId", routeId);

                startActivity(intent);
            }
        });

        ArrayList<BusStationsByRouteInfoItem> busStationByRouteList = BusRouteInfo.getStationByRouteList(routeId);
        ArrayList<BusPosInfoItem> busPosByRtidList = BusPos.getBusPosByRtidList(routeId);

        for(int i=0; i < busPosByRtidList.size(); i++){
            BusPosInfoItem posItem = busPosByRtidList.get(i);

            for(int j=0; j < busStationByRouteList.size(); j++) {
                BusStationsByRouteInfoItem stnItem = busStationByRouteList.get(j);

                if (posItem.getSectionID().equals(stnItem.getSection())){
                    stnItem.setBusPos_plainNo(posItem.getPlainNo());
                    busStationByRouteList.set(j,stnItem);
                }
            }
        }

        BusStationsByRouteListAdapter busStationsByRouteListAdapter = new BusStationsByRouteListAdapter(busStationByRouteList);
        listView.setAdapter(busStationsByRouteListAdapter);



    }

    private boolean setCustomActionBar(){
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);//커스텀 액션바 보이게한다
        //기존 액션바 숨기기
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        //layout inflate
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionBarView = inflater.inflate(R.layout.activity_actionbar,null);

        actionBar.setCustomView(actionBarView);

        //delete empty space
        Toolbar parent = (Toolbar)actionBarView.getParent();
        parent.setContentInsetsAbsolute(0,0);

        return true;
    }

}
