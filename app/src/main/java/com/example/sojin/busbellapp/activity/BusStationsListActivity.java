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

import com.example.sojin.busbellapp.AlarmApiService;
import com.example.sojin.busbellapp.BusApiService;
import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.adapter.BusStationsByRouteListAdapter;
import com.example.sojin.busbellapp.item.BusArrInfoWrapper;
import com.example.sojin.busbellapp.item.BusPosInfoItem;
import com.example.sojin.busbellapp.item.BusPosInfoWrapper;
import com.example.sojin.busbellapp.item.BusStationInfoItem;
import com.example.sojin.busbellapp.item.BusStationInfoWrapper;
import com.example.sojin.busbellapp.item.RequestItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusStationsListActivity extends AppCompatActivity {
    private ListView listView;
    private Button button;

    private TextView depart_info;
    private TextView arrive_info;
    private TextView arrive_bus_info;

    private String busID;
    private String preStnID;
    private String destStnID;

    private ArrayList<BusPosInfoItem> busPos_ArrayList;
    private ArrayList<BusStationInfoItem> busStation_ArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stations_list);

        Intent intent = new Intent(this.getIntent());

        String ROUTE_ID = intent.getStringExtra("routeId");
        final String API_KEY = getString(R.string.api_key);

        listView = (ListView)findViewById(R.id.listView_bus_station_list);

        depart_info = (TextView)findViewById(R.id.bus_station_list_depart_info);
        arrive_info = (TextView)findViewById(R.id.bus_station_list_arrive_info);
        arrive_bus_info = (TextView)findViewById(R.id.bus_station_list_arrive_bus_info);

        button = (Button)findViewById(R.id.bus_station_list_button);

        BusApiService station_service = BusApiService.retrofit.create(BusApiService.class);
        Call<BusStationInfoWrapper> station_call = station_service.getBusStationByRoute(ROUTE_ID, API_KEY);
        station_call.enqueue(new Callback<BusStationInfoWrapper>() {
            @Override
            public void onResponse(Call<BusStationInfoWrapper> call, Response<BusStationInfoWrapper> response) {
                if (response.isSuccessful()) {
                    BusStationInfoWrapper result = response.body();
                    busStation_ArrayList = result.getBusStationList();

                    /* Wait for the result while getting BUS POSITION ARRAY */
                    if (busPos_ArrayList == null) {
//                        try {
//                            this.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
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
                            BusStationInfoItem selected_cur_station = busStation_ArrayList.get(i);
                            BusStationInfoItem selected_prev_station = busStation_ArrayList.get(i-1);

                            if(depart_info.getText().length() > 0) {
                                arrive_info.setText(selected_cur_station.getStationNm());

                                destStnID = selected_cur_station.getStation();
                                preStnID = selected_prev_station.getSection();
                            } else {
                                BusApiService arr_service = BusApiService.retrofit.create(BusApiService.class);
                                Call<BusArrInfoWrapper> arr_call = arr_service.getArrInfoByRoute(selected_cur_station.getBusRouteId()
                                                                                                ,selected_cur_station.getStation()
                                                                                                ,selected_cur_station.getSeq(),API_KEY);
                                arr_call.enqueue(new Callback<BusArrInfoWrapper>() {
                                    @Override
                                    public void onResponse(Call<BusArrInfoWrapper> call, Response<BusArrInfoWrapper> response) {
                                        if(response.isSuccessful()){
                                            BusArrInfoWrapper result = response.body();
                                            String bus_plain_no = result.getBusArrInfoList().get(0).getPlainNo1();
                                            String bus_arrive_msg = result.getBusArrInfoList().get(0).getArrmsg1();

                                            busID = result.getBusArrInfoList().get(0).getVehId1();

                                            arrive_bus_info.setText(bus_plain_no + "  " + bus_arrive_msg);
                                        }else{

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<BusArrInfoWrapper> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });

                                depart_info.setText(selected_cur_station.getStationNm());
                            }
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

        BusApiService pos_service = BusApiService.retrofit.create(BusApiService.class);
        Call<BusPosInfoWrapper> pos_call = pos_service.getBusPosByRtid(ROUTE_ID, API_KEY);
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
                t.printStackTrace();
            }
        });

        depart_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depart_info.setText("");
            }
        });
        arrive_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrive_info.setText("");
            }
        });

        //TODO: Impletment onClickListner -> Make a POST REQUEST with arrving info
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmApiService service = AlarmApiService.retrofit.create(AlarmApiService.class);
                Call<RequestItem> call = service.request("my device_id", busID, preStnID, destStnID);
                call.enqueue(new Callback<RequestItem>() {
                    @Override
                    public void onResponse(Call<RequestItem> call, Response<RequestItem> response) {
                        if(response.isSuccessful()){
                            RequestItem result = response.body();

                        }else {

                        }
                    }

                    @Override
                    public void onFailure(Call<RequestItem> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                Toast.makeText(getApplicationContext(),"Making a Reservation is successed!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
