package com.example.sojin.busbellapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.activity.FavoriteAddActivity;
import com.example.sojin.busbellapp.adapter.FavoriteListAdapter;
import com.example.sojin.busbellapp.item.Favorite;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class FavoriteFragment extends Fragment {
    private ArrayList<Favorite> favoriteList;
    private FavoriteListAdapter favoriteAdapter;

    private Realm realm;

    private String busID;
//    private String API_KEY = getString(R.string.api_key);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(getContext());
        realm = Realm.getDefaultInstance();

        favoriteList = new ArrayList<Favorite>(realm.where(Favorite.class).findAll());
        favoriteAdapter = new FavoriteListAdapter(favoriteList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        final ListView listview = (ListView)view.findViewById(R.id.fragment_favorite_listview);

        // TODO : Change the way that how to delete the record from database
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                realm.beginTransaction();
                RealmResults<Favorite> result = realm.where(Favorite.class).findAll();
                result.deleteFromRealm(i);
                favoriteList.remove(i);
                favoriteAdapter.notifyDataSetChanged();
                realm.commitTransaction();
            }
        });

        // TODO : Send an alarm request using favorite data
        // TODO : Solve synchronization problem between Alram service and Bus service
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                BusFavoriteItem item = favoriteList.get(i);
//
//                BusApiService bus_service = BusApiService.retrofit.create(BusApiService.class);
//                Call<BusArrInfoWrapper> call = bus_service.getArrInfoByRoute(item.getRoute_id(), item.getDepart_station_id(), "123", API_KEY);
//                call.enqueue(new Callback<BusArrInfoWrapper>() {
//                    @Override
//                    public void onResponse(Call<BusArrInfoWrapper> call, Response<BusArrInfoWrapper> response) {
//                        if(response.isSuccessful()){
//                            BusArrInfoWrapper result = response.body();
//                            busID = result.getBusArrInfoList().get(0).getVehId1();
//
//                            FavoriteFragment.this.notify();
//                        }else {
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<BusArrInfoWrapper> call, Throwable t) {
//                        t.printStackTrace();
//                    }
//                });
//
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                AlarmApiService alarm_service = AlarmApiService.retrofit.create(AlarmApiService.class);
//                Call<RequestItem> alarm_call = alarm_service.request("my device_id", busID, item.getArrive_pre_station_id(), item.getArrive_station_id());
//                alarm_call.enqueue(new Callback<RequestItem>() {
//                    @Override
//                    public void onResponse(Call<RequestItem> call, Response<RequestItem> response) {
//                        if(response.isSuccessful()){
//                            RequestItem result = response.body();
//
//                            Log.i("####",response.raw().toString());
//                        }else {
//                            Log.i("$$$$",response.raw().toString());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<RequestItem> call, Throwable t) {
//                        t.printStackTrace();
//                    }
//                });
//            }
//        });
        listview.setAdapter(favoriteAdapter);

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fragment_favorite_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FavoriteAddActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                Favorite item = (Favorite)data.getSerializableExtra("item");

                Number maxId = realm.where(Favorite.class).max("idx");
                int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;

                item.setIdx(nextId);
                realm.beginTransaction();
                realm.insert(item);
                realm.commitTransaction();

                favoriteList.add(item);
                favoriteAdapter.notifyDataSetChanged();

            }else if(resultCode == Activity.RESULT_CANCELED){

            }
        }
    }
}
