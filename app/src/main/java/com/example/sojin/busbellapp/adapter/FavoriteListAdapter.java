package com.example.sojin.busbellapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.item.Favorite;

import java.util.ArrayList;

public class FavoriteListAdapter extends BaseAdapter {
    private ArrayList<Favorite> favoriteItemList;

    public FavoriteListAdapter(ArrayList<Favorite> favoriteItemList) {
        this.favoriteItemList = favoriteItemList;
    }

    @Override
    public int getCount() {
        return favoriteItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return favoriteItemList.get(position);
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
            convertView = inflater.inflate(R.layout.favorite_item_dev, parent, false);
        }

        Favorite item = favoriteItemList.get(position);

        TextView favorite_idx = (TextView)convertView.findViewById(R.id.favorite_idx);
        TextView favorite_depart_station_id = (TextView)convertView.findViewById(R.id.favorite_depart_station_id);
        TextView favorite_depart_station_nm = (TextView)convertView.findViewById(R.id.favorite_depart_station_nm);
        TextView favorite_arrive_station_id = (TextView)convertView.findViewById(R.id.favorite_arrive_station_id);
        TextView favorite_arrive_station_nm = (TextView)convertView.findViewById(R.id.favorite_arrive_station_nm);
        TextView favorite_arrive_pre_station_id = (TextView)convertView.findViewById(R.id.favorite_arrive_pre_station_id);
        TextView favorite_route_id = (TextView)convertView.findViewById(R.id.favorite_route_id);
        TextView favorite_route_nm = (TextView)convertView.findViewById(R.id.favorite_route_nm);

        favorite_idx.setText(item.getIdx()+"");
        favorite_depart_station_id.setText(item.getDepart_station_id());
        favorite_depart_station_nm.setText(item.getDepart_station_nm());
        favorite_arrive_pre_station_id.setText(item.getArrive_pre_station_id());
        favorite_arrive_station_id.setText(item.getArrive_station_id());
        favorite_arrive_station_nm.setText(item.getArrive_station_nm());
        favorite_route_id.setText(item.getRoute_id());
        favorite_route_nm.setText(item.getRoute_nm());

        return convertView;
    }
}
