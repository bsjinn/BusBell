package com.example.sojin.busbellapp.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sojin.busbellapp.AlarmApiService;
import com.example.sojin.busbellapp.BusApiService;
import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.activity.BusStationsListActivity;
import com.example.sojin.busbellapp.activity.FavoriteAddActivity;
import com.example.sojin.busbellapp.activity.MainActivity;
import com.example.sojin.busbellapp.adapter.FavoriteListAdapter;
import com.example.sojin.busbellapp.db.Favorite;
import com.example.sojin.busbellapp.db.Migration;
import com.example.sojin.busbellapp.item.BusArrInfoItem;
import com.example.sojin.busbellapp.item.BusArrInfoWrapper;
import com.example.sojin.busbellapp.item.RequestItem;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class FavoriteFragment extends Fragment {
    private ArrayList<Favorite> favoriteList;
    private FavoriteListAdapter favoriteAdapter;

    private Realm realm;
    private String busID;
    SharedPreferences mPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPref = ((MainActivity)getActivity()).getSharedPreferences("pref",MODE_PRIVATE);

        Realm.init(getContext());
        realm = Realm.getInstance(new RealmConfiguration.Builder()
                .schemaVersion(2)
                .migration(new Migration())
                .build()
        );

        FavoriteListAdapter.DeleteBtnClickListener clickListener = new FavoriteListAdapter.DeleteBtnClickListener() {
            @Override
            public void onDeleteBtnClick(int position) {
                realm.beginTransaction();
                RealmResults<Favorite> result = realm.where(Favorite.class).findAll();
                result.deleteFromRealm(position);
                realm.commitTransaction();
            }
        };

        favoriteList = new ArrayList<Favorite>(realm.where(Favorite.class).findAll());
        favoriteAdapter = new FavoriteListAdapter(favoriteList, clickListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        final ListView listview = (ListView)view.findViewById(R.id.fragment_favorite_listview);

        // TODO : Change the way that how to delete the record from database
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                realm.beginTransaction();
//                RealmResults<Favorite> result = realm.where(Favorite.class).findAll();
//                result.deleteFromRealm(i);
//                favoriteList.remove(i);
//                favoriteAdapter.notifyDataSetChanged();
//                realm.commitTransaction();
//            }
//        });

        // TODO : Send an alarm request using favorite data
        // TODO : Solve synchronization problem between Alram service and Bus service
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String API_KEY = getString(R.string.api_key);

                final Favorite item = favoriteList.get(i);

                final String preStnID = item.getArrive_pre_station_id();
                final String destStnID = item.getArrive_station_id();

                BusApiService bus_service = BusApiService.retrofit.create(BusApiService.class);
                Call<BusArrInfoWrapper> call = bus_service.getArrInfoByRoute(item.getRoute_id()
                                                                            ,item.getDepart_station_id()
                                                                            ,item.getDepart_station_seq()
                                                                            ,API_KEY);
                call.enqueue(new Callback<BusArrInfoWrapper>(){
                    @Override
                    public void onResponse(Call<BusArrInfoWrapper> call, Response<BusArrInfoWrapper> response) {
                        if(response.isSuccessful()){
                            BusArrInfoWrapper result = response.body();
                            BusArrInfoItem arr_item = result.getBusArrInfoList().get(0);

                            busID = result.getBusArrInfoList().get(0).getVehId1();

                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                            alertBuilder.setTitle("예약하기")
                                    .setMessage("승차정류장 : " + item.getDepart_station_nm()+ "\n" +
                                            "하차정류장 : " + item.getArrive_station_nm() + "\n" +
                                            "버스정보 : " + arr_item.getPlainNo1() + " - " + arr_item.getArrmsg1())
                                    .setCancelable(false)
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            TelephonyManager tm = (TelephonyManager)getContext().getSystemService(Context.TELEPHONY_SERVICE);
                                            AlarmApiService alarmApiService = AlarmApiService.retrofit.create(AlarmApiService.class);
                                            Call<RequestItem> call = alarmApiService.request(tm.getDeviceId(), busID, preStnID, destStnID);
                                            call.enqueue(new Callback<RequestItem>() {
                                                @Override
                                                public void onResponse(Call<RequestItem> call, Response<RequestItem> response) {
                                                    RequestItem result = response.body();

                                                    int reqId=result.getReqID();

                            /* DB */

                                                    SharedPreferences.Editor editor = mPref.edit();
                                                    editor.putInt("reqID", reqId);
                                                    editor.putString("busNum", item.getRoute_nm());
                                                    editor.putString("deptStn", item.getDepart_station_nm());
                                                    editor.putString("arvStn", item.getArrive_station_nm());
                                                    editor.commit();

                                                    ((MainActivity)getActivity()).setReservedWindow();
                                                    Toast.makeText(getContext(), "예약을 성공하였습니다.", Toast.LENGTH_SHORT).show();

                                                    return;
                                                }

                                                @Override
                                                public void onFailure(Call<RequestItem> call, Throwable t) {

                                                }
                                            });
                                        }
                                    }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            return;
                                        }
                                    });

                            AlertDialog alertDialog = alertBuilder.create();
                            alertDialog.show();
                        }else {

                        }
                    }

                    @Override
                    public void onFailure(Call<BusArrInfoWrapper> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
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
