package com.example.sojin.busbellapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sojin.busbellapp.BusArrInfo;
import com.example.sojin.busbellapp.item.BusArrInfoItem;
import com.example.sojin.busbellapp.adapter.BusArrInfoListAdapter;
import com.example.sojin.busbellapp.BusRouteInfo;
import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.adapter.BusStationsByRouteListAdapter;
import com.example.sojin.busbellapp.item.BusStationsByRouteInfoItem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by KSM on 2017-07-03.
 */

public class AlarmAtBusRouteListActicity extends AppCompatActivity {

    String deviceID;
    String busID;
    String preStnID;

    boolean isArrivalChecked = false;
    boolean isGetoffChecked = false;

    ListView arrival_listview;
    ListView getoff_listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_at_bus_route_info_item);

        Intent intent = new Intent(this.getIntent());
        final String stId = intent.getStringExtra("stId");
        final String routeId = intent.getStringExtra("routeId");
        final String ord = intent.getStringExtra("ord");

        ArrayList<BusArrInfoItem> busArrInfoList = BusArrInfo.getArrInfoByRouteList(stId,routeId,ord);
        BusArrInfoListAdapter busArrInfoListAdapter = new BusArrInfoListAdapter(busArrInfoList);

        arrival_listview = (ListView)findViewById(R.id.listView_station_arrival_list);
        arrival_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TelephonyManager tm =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                BusArrInfoItem item = (BusArrInfoItem)arrival_listview.getItemAtPosition(position);

                deviceID = tm.getDeviceId();
                busID = item.getVehId();

                isArrivalChecked = true;

                if(isArrivalChecked == true && isGetoffChecked == true){
                    sendInfoToServer(deviceID, busID, preStnID);
                    Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG);

                    isArrivalChecked = false; isGetoffChecked = false;
                }
            }
        });

        arrival_listview.setAdapter(busArrInfoListAdapter);

        ArrayList<BusStationsByRouteInfoItem> busStationsByRouteInfoList = BusRouteInfo.getStationByRouteList(routeId);
        BusStationsByRouteListAdapter busStationsByRouteListAdapter = new BusStationsByRouteListAdapter(busStationsByRouteInfoList);

        getoff_listview = (ListView)findViewById(R.id.listView_station_getoff_list);
        getoff_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BusStationsByRouteInfoItem item = (BusStationsByRouteInfoItem)getoff_listview.getItemAtPosition(position-1);

                preStnID = item.getStation();

                isGetoffChecked = true;

                if(isArrivalChecked == true && isGetoffChecked == true){
                    sendInfoToServer(deviceID, busID, preStnID);
                    Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG);

                    isArrivalChecked = false; isGetoffChecked = false;
                }
            }
        });
        getoff_listview.setAdapter(busStationsByRouteListAdapter);
    }

    public String sendInfoToServer(String deviceID, String busID, String preStnID){
        final String serverAddress = "http://54.214.224.43";
        final String serverPort = "3000";
        String[] parameter = { deviceID, busID, preStnID };

        StringBuilder sb = null;

        try {
            StringBuilder urlBuilder = new StringBuilder(serverAddress + ":" + serverPort + "/request");
            URL url = new URL(urlBuilder.toString());

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            if(conn != null){
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                writer.write("deviceID=" + parameter[0] + "&busID=" + parameter[1] + "&preStnID=" + parameter[2]);
                writer.flush();

                int resCode = conn.getResponseCode();
                if(resCode == HttpURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    sb = new StringBuilder();
                    String line = null;

                    while((line = reader.readLine()) != null)
                        sb.append(line);

                    writer.close();
                    reader.close();
                    conn.disconnect();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}