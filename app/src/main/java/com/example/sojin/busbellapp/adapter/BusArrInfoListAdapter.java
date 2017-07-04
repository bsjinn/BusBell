package com.example.sojin.busbellapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sojin.busbellapp.item.BusArrInfoItem;
import com.example.sojin.busbellapp.R;

import java.util.ArrayList;

import static com.example.sojin.busbellapp.R.id.text_bus_arr_arrmsg;

/**
 * Created by KSM on 2017-07-03.
 */

public class BusArrInfoListAdapter extends BaseAdapter {

    private ArrayList<BusArrInfoItem> busArrInfoItemArrayList;

    public BusArrInfoListAdapter(ArrayList<BusArrInfoItem> busArrInfoItemArrayList) {
        this.busArrInfoItemArrayList = busArrInfoItemArrayList;
    }

    @Override
    public int getCount() {
        return busArrInfoItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return busArrInfoItemArrayList.get(position);
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
            convertView = inflater.inflate(R.layout.bus_arr_info_item, parent, false);
        }

        TextView arrmsg = (TextView)convertView.findViewById(text_bus_arr_arrmsg);
        TextView plainNo = (TextView)convertView.findViewById(R.id.text_bus_arr_plainNo);
        TextView stationNm = (TextView)convertView.findViewById(R.id.text_bus_arr_stationNm);

        BusArrInfoItem item = busArrInfoItemArrayList.get(position);

        arrmsg.setText(item.getArrmsg());
        plainNo.setText(item.getPlainNo());
        stationNm.setText(item.getStationNm());

        return convertView;
    }
}
