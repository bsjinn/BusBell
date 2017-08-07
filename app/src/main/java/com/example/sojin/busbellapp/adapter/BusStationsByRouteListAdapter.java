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

        TextView station_nm = (TextView)convertView.findViewById(R.id.bus_station_item_station_nm);
        TextView station_info1 = (TextView)convertView.findViewById(R.id.bus_station_item_info_1);
        TextView station_info2 = (TextView)convertView.findViewById(R.id.bus_station_item_info_2);
        TextView station_plain_no = (TextView)convertView.findViewById(R.id.bus_station_item_plain_no);
        ImageView bus_img = (ImageView)convertView.findViewById(R.id.bus_station_item_img);

//        station_nm.setFocusable(false);
//        station_info1.setFocusable(false);
//        station_info2.setFocusable(false);
//        station_plain_no.setFocusable(false);

        BusStationInfoItem item = busStationInfoItemList.get(position);

        station_nm.setText(item.getStationNm());
        station_info1.setText(item.getStationNo());
        station_info2.setText(item.getBeginTm() + "~" + item.getLastTm());
        station_plain_no.setText(item.getBusPos_plainNo());

        if(station_plain_no.getText().length() > 0)
            bus_img.setImageResource(R.drawable.busim);

        return convertView;
    }
}
