package com.example.sojin.busbellapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.item.BusRouteInfoItem;

import java.util.ArrayList;

public class BusRouteListAdapter extends BaseAdapter {
    private ArrayList<BusRouteInfoItem> busRouteInfoList;

    public BusRouteListAdapter(ArrayList<BusRouteInfoItem> busRouteInfoList) {
        this.busRouteInfoList = busRouteInfoList;
    }

    @Override
    public int getCount() {
        return busRouteInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return busRouteInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.bus_route_info_item, parent, false);
        }

        TextView busRouteNm = (TextView)convertView.findViewById(R.id.bus_route_item_route_nm);
        TextView busCorpNm = (TextView)convertView.findViewById(R.id.bus_route_item_corp_nm);
        TextView stationNm = (TextView)convertView.findViewById(R.id.bus_route_item_station_nm);

        BusRouteInfoItem item = busRouteInfoList.get(position);

        busRouteNm.setText(item.getBusRouteNm());
        busCorpNm.setText("운수사명 : " + item.getCorpNm());
        stationNm.setText("노선정보 : " + item.getStStationNm() + " ~ " + item.getEdStationNm());

        return convertView;
    }
}
