package com.example.sojin.busbellapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sojin.busbellapp.BusApiService;
import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.adapter.BusStationsByRouteListAdapter;
import com.example.sojin.busbellapp.item.BusStationInfoItem;
import com.example.sojin.busbellapp.item.BusStationInfoWrapper;
import com.example.sojin.busbellapp.item.Favorite;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteAddFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<BusStationInfoItem> stationList;

    private Button button;
    private ListView listview;
    private EditText editText;
    private TextView depart_station;
    private TextView arrive_station;

    private String routeId;
    private String routeNm;

    private String favorite_title;
    private String depart_station_id;
    private String depart_station_nm;
    private String arrive_pre_station_id;
    private String arrive_station_id;
    private String arrive_station_nm;

    public FavoriteAddFragment() { }

    public static FavoriteAddFragment newInstance(String param1, String param2) {
        FavoriteAddFragment fragment = new FavoriteAddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            routeId = getArguments().getString(ARG_PARAM1);
            routeNm = getArguments().getString(ARG_PARAM2);
        }

        String API_KEY = getString(R.string.api_key);

        BusApiService service = BusApiService.retrofit.create(BusApiService.class);
        Call<BusStationInfoWrapper> call = service.getBusStationByRoute(routeId, API_KEY);
        call.enqueue(new Callback<BusStationInfoWrapper>() {
            @Override
            public void onResponse(Call<BusStationInfoWrapper> call, Response<BusStationInfoWrapper> response) {
                if(response.isSuccessful()){
                    BusStationInfoWrapper result = response.body();
                    stationList = result.getBusStationList();

                    BusStationsByRouteListAdapter adapter = new BusStationsByRouteListAdapter(stationList);
                    listview.setAdapter(adapter);
                }else{

                }
            }

            @Override
            public void onFailure(Call<BusStationInfoWrapper> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_add, container, false);

        editText = (EditText)view.findViewById(R.id.fragment_favorite_add_title);

        depart_station = (TextView)view.findViewById(R.id.fragment_favorite_add_on_station);
        depart_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depart_station.setText("");
            }
        });

        arrive_station = (TextView)view.findViewById(R.id.fragment_favorite_add_off_station);
        arrive_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrive_station.setText("");
            }
        });

        listview = (ListView)view.findViewById(R.id.fragment_favorite_add_station_list);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BusStationInfoItem item = stationList.get(i);
                BusStationInfoItem prev_item = stationList.get(i-1);

                if(depart_station.getText().length() > 0) {
                    arrive_station.setText(item.getStationNm());

                    arrive_pre_station_id = prev_item.getStation();
                    arrive_station_id = item.getStation();
                    arrive_station_nm = item.getStationNm();
                }
                else {
                    depart_station.setText(item.getStationNm());

                    depart_station_id = item.getStation();
                    depart_station_nm = item.getStationNm();
                }
            }
        });

        button = (Button)view.findViewById(R.id.fragment_favorite_add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Favorite item = new Favorite(0, "temp", depart_station_id, depart_station_nm, arrive_pre_station_id, arrive_station_id, arrive_station_nm, routeId, routeNm);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("item", item);

                getActivity().setResult(Activity.RESULT_OK, returnIntent);
                getActivity().finish();
            }
        });

        return view;
    }
}
