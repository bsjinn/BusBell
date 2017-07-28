package com.example.sojin.busbellapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sojin.busbellapp.BusApiService;
import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.adapter.BusStationsByRouteListAdapter;
import com.example.sojin.busbellapp.item.BusPosInfoItem;
import com.example.sojin.busbellapp.item.BusPosInfoWrapper;
import com.example.sojin.busbellapp.item.BusStationInfoItem;
import com.example.sojin.busbellapp.item.BusStationInfoWrapper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusStationsListActivity extends AppCompatActivity {
    private ListView listView;
    private Button button;
    private TextView on_info;
    private TextView off_info;

    private ArrayList<BusPosInfoItem> busPos_ArrayList;
    private ArrayList<BusStationInfoItem> busStation_ArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_stations_list_activity_main);

        Intent intent = new Intent(this.getIntent());
        String ROUTE_ID = intent.getStringExtra("routeId");
        String API_KEY = getString(R.string.api_key);

        listView = (ListView)findViewById(R.id.listView_bus_station_list);
        on_info = (TextView)findViewById(R.id.bus_station_list_on_info);
        off_info = (TextView)findViewById(R.id.bus_station_list_off_info);
        button = (Button)findViewById(R.id.bus_station_list_button);

        BusApiService station_service = BusApiService.retrofit.create(BusApiService.class);
        final Call<BusStationInfoWrapper> station_call = station_service.getBusStationByRoute(ROUTE_ID, API_KEY);
        station_call.enqueue(new Callback<BusStationInfoWrapper>() {
            @Override
            public void onResponse(Call<BusStationInfoWrapper> call, Response<BusStationInfoWrapper> response) {
                if (response.isSuccessful()) {
                    BusStationInfoWrapper result = response.body();
                    busStation_ArrayList = result.getBusStationList();

                    /* Wait for the result while getting BUS POSITION ARRAY */
                    if (busPos_ArrayList == null) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    /* Set bus plate number to station if there is bus near a station. */
                    for (int i = 0; i < busPos_ArrayList.size(); i++) {
                        BusPosInfoItem posItem = busPos_ArrayList.get(i);

                        for (int j = 0; j < busStation_ArrayList.size(); j++) {
                            BusStationInfoItem stnItem = busStation_ArrayList.get(j);

                            if (posItem.getSectionId().equals(stnItem.getSection())) {
                                stnItem.setBusPos_plainNo(posItem.getPlainNo());
                                busStation_ArrayList.set(j, stnItem);
                            }
                        }
                    }

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String station_name = busStation_ArrayList.get(i).getStationNm();

                            if(on_info.getText().length() > 0)
                                off_info.setText(station_name);
                            else
                                on_info.setText(station_name);
                        }
                    });

                    BusStationsByRouteListAdapter busStationsByRouteListAdapter = new BusStationsByRouteListAdapter(busStation_ArrayList);
                    listView.setAdapter(busStationsByRouteListAdapter);
                }
            }

            @Override
            public void onFailure(Call<BusStationInfoWrapper> call, Throwable t) {

            }
        });

        Call<BusPosInfoWrapper> pos_call = station_service.getBusPosByRtid(ROUTE_ID, API_KEY);
        pos_call.enqueue(new Callback<BusPosInfoWrapper>() {
            @Override
            public void onResponse(Call<BusPosInfoWrapper> call, Response<BusPosInfoWrapper> response) {
                if(response.isSuccessful()){
                    BusPosInfoWrapper result = response.body();
                    busPos_ArrayList = result.getBusPosList();

                    // TODO: Check if Station-Thread is waited
                }else{

                }
            }

            @Override
            public void onFailure(Call<BusPosInfoWrapper> call, Throwable t) {

            }
        });

        on_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on_info.setText("");
            }
        });
        off_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                off_info.setText("");
            }
        });

        //TODO: Impletment onClickListner -> Make a POST REQUEST with arrving info
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Making a Reservation is successed!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
