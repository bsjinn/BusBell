package com.example.sojin.busbellapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.db.Favorite;

import java.util.ArrayList;

public class FavoriteListAdapter extends BaseAdapter implements View.OnClickListener {
    private ArrayList<Favorite> favoriteItemList;

    public FavoriteListAdapter(ArrayList<Favorite> favoriteItemList, DeleteBtnClickListener deleteBtnClickListener) {
        this.favoriteItemList = favoriteItemList;
        this.deleteBtnClickListener = deleteBtnClickListener;
    }

    public interface DeleteBtnClickListener{
        void onDeleteBtnClick(int position);
    }

    private DeleteBtnClickListener deleteBtnClickListener;

    @Override
    public void onClick(View view) {
        favoriteItemList.remove((int)view.getTag());
        this.notifyDataSetChanged();

        if(this.deleteBtnClickListener != null)
            this.deleteBtnClickListener.onDeleteBtnClick((int)view.getTag());
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
            convertView = inflater.inflate(R.layout.favorite_item, parent, false);
        }

        Favorite item = favoriteItemList.get(position);

        TextView favorite_title = (TextView)convertView.findViewById(R.id.favorite_item_title);
        TextView favorite_station = (TextView)convertView.findViewById(R.id.favorite_item_station_info);
        TextView favorite_route = (TextView)convertView.findViewById(R.id.favorite_item_bus_info);

        favorite_title.setText(item.getFavorite_title());
        favorite_station.setText(item.getDepart_station_nm()+ " -> " + item.getArrive_station_nm());
        favorite_route.setText(item.getRoute_nm());

        ImageButton delete_button = (ImageButton)convertView.findViewById(R.id.favorite_item_delete_button);
        delete_button.setTag(position);
        delete_button.setOnClickListener(this);

//        TextView favorite_idx = (TextView)convertView.findViewById(R.id.favorite_idx);
//        TextView favorite_depart_station_id = (TextView)convertView.findViewById(R.id.favorite_depart_station_id);
//        TextView favorite_depart_station_nm = (TextView)convertView.findViewById(R.id.favorite_depart_station_nm);
//        TextView favorite_arrive_station_id = (TextView)convertView.findViewById(R.id.favorite_arrive_station_id);
//        TextView favorite_arrive_station_nm = (TextView)convertView.findViewById(R.id.favorite_arrive_station_nm);
//        TextView favorite_arrive_pre_station_id = (TextView)convertView.findViewById(R.id.favorite_arrive_pre_station_id);
//        TextView favorite_route_id = (TextView)convertView.findViewById(R.id.favorite_route_id);
//        TextView favorite_route_nm = (TextView)convertView.findViewById(R.id.favorite_route_nm);
//
//        favorite_idx.setText(item.getIdx()+"");
//        favorite_depart_station_id.setText(item.getDepart_station_id());
//        favorite_depart_station_nm.setText(item.getDepart_station_nm());
//        favorite_arrive_pre_station_id.setText(item.getArrive_pre_station_id());
//        favorite_arrive_station_id.setText(item.getArrive_station_id());
//        favorite_arrive_station_nm.setText(item.getArrive_station_nm());
//        favorite_route_id.setText(item.getRoute_id());
//        favorite_route_nm.setText(item.getRoute_nm());

        return convertView;
    }
}
