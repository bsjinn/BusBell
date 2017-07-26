package com.example.sojin.busbellapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sojin.busbellapp.BusApiService;
import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.item.BusPosInfoItem;
import com.example.sojin.busbellapp.item.BusPosInfoWrapper;
import com.example.sojin.busbellapp.item.BusStationInfoItem;
import com.example.sojin.busbellapp.item.BusStationInfoWrapper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusStationsListActivity extends AppCompatActivity {
    ListView listView;

    ArrayList<BusPosInfoItem> busPos_ArrayList;
    ArrayList<BusStationInfoItem> busStation_ArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_stations_list_activity_main);

        setCustomActionBar();
        TextView titleText=(TextView)findViewById(R.id.title_text);
        titleText.setText(R.string.busRoute_Info);

        Intent intent = new Intent(this.getIntent());
        String routeId = intent.getStringExtra("routeId");
        String API_KEY = "3nvztFALDhnl5ffO0FuwkATq9JDSLJPHjSSVRByOsG78s9vF%2F4SuBbuKcle1XytZB0hkdU19wrBSnqDKHWHpdA%3D%3D";


        TextView txt = (TextView)findViewById(R.id.txt);
        txt.setText(routeId.toString());

        listView = (ListView)findViewById(R.id.listView_bus_station_list);

        BusApiService station_service = BusApiService.retrofit.create(BusApiService.class);
        Call<BusStationInfoWrapper> station_call = station_service.getBusStationByRoute(routeId, API_KEY);
        station_call.enqueue(new Callback<BusStationInfoWrapper>() {
            @Override
            public void onResponse(Call<BusStationInfoWrapper> call, Response<BusStationInfoWrapper> response) {
                if(response.isSuccessful()){
                    BusStationInfoWrapper result = response.body();
                    busStation_ArrayList = result.getBusStationList();
                }else{

                }

            }

            @Override
            public void onFailure(Call<BusStationInfoWrapper> call, Throwable t) {

            }
        });

        Call<BusPosInfoWrapper> pos_call = station_service.getBusPosByRtid(routeId, API_KEY);
        pos_call.enqueue(new Callback<BusPosInfoWrapper>() {
            @Override
            public void onResponse(Call<BusPosInfoWrapper> call, Response<BusPosInfoWrapper> response) {
                if(response.isSuccessful()){
                    BusPosInfoWrapper result = response.body();
                    busPos_ArrayList = result.getBusPosList();
                }else{

                }
            }

            @Override
            public void onFailure(Call<BusPosInfoWrapper> call, Throwable t) {

            }
        });


        Log.e("!@#!@#!@#",busStation_ArrayList.size() + ":" + busStation_ArrayList);
//        for(int i=0; i < busPos_ArrayList.size(); i++){
//            BusPosInfoItem posItem = busPos_ArrayList.get(i);
//
//            for(int j=0; j < busStation_ArrayList.size(); j++) {
//                BusStationInfoItem stnItem = busStation_ArrayList.get(j);
//
//                if (posItem.getSectionId().equals(stnItem.getSection())){
//                    stnItem.setBusPos_plainNo(posItem.getPlainNo());
//                    busStation_ArrayList.set(j,stnItem);
//                }
//            }
//        }
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                BusStationInfoItem item = (BusStationInfoItem) parent.getItemAtPosition(position);
//
//                Intent intent = new Intent(BusStationsListActivity.this, AlarmAtBusRouteListActicity.class);
//
//                intent.putExtra("stId", item.getStation());
//                intent.putExtra("routeId", item.getBusRouteId());
//                intent.putExtra("ord", item.getSeq());
//
//                startActivity(intent);
//            }
//        });
//
//        BusStationsByRouteListAdapter busStationsByRouteListAdapter = new BusStationsByRouteListAdapter(busStation_ArrayList);
//        listView.setAdapter(busStationsByRouteListAdapter);
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
