package com.example.sojin.busbellapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.item.BusStationInfoItem;

import java.util.ArrayList;

public class BusStationsByRouteListAdapter extends BaseAdapter {
    private ArrayList<BusStationInfoItem> busStationInfoItemList;

    public BusStationsByRouteListAdapter(ArrayList<BusStationInfoItem> busStationInfoItemList) {
        this.busStationInfoItemList = busStationInfoItemList;
    }

    @Override
    public int getCount() {
        return busStationInfoItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return busStationInfoItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.bus_stations_by_route_info_item, parent, false);
        }

        TextView stationNm = (TextView)convertView.findViewById(R.id.stationNm);
        TextView stationEtcInfo = (TextView)convertView.findViewById(R.id.stationEtcInfo);
        TextView busPos_plainNo = (TextView)convertView.findViewById(R.id.plainNo);
        ImageView busStopIm = (ImageView)convertView.findViewById(R.id.busIm);

        stationNm.setFocusable(false);
        stationEtcInfo.setFocusable(false);
        busPos_plainNo.setFocusable(false);

        BusStationInfoItem item = busStationInfoItemList.get(position);

        stationNm.setText(item.getStationNm());
        stationEtcInfo.setText(item.getStationNo() + " / " + item.getBeginTm() + "~" + item.getLastTm());
        busPos_plainNo.setText(item.getBusPos_plainNo());

        if(!busPos_plainNo.getText().equals(""))
            busStopIm.setImageResource(R.drawable.busim);

        return convertView;
    }
}
