package com.example.sojin.busbellapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sojin.busbellapp.activity.BusStationsListActivity;
import com.example.sojin.busbellapp.adapter.BusRouteListAdapter;
import com.example.sojin.busbellapp.item.BusRouteInfoItem;
import com.example.sojin.busbellapp.item.BusRouteInfoWrapper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    private EditText editText;
    private Button okButton;
    private ListView listView;

    //TODO : Hide a keyboard when input is finished and button is clicked
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        editText = (EditText) view.findViewById(R.id.fragment_search_edittext);
        okButton = (Button) view.findViewById(R.id.fragment_search_button);
        listView = (ListView) view.findViewById(R.id.fragment_search_listview);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String BUS_NUM = editText.getText().toString();
                String API_KEY = getString(R.string.api_key);

                BusApiService service = BusApiService.retrofit.create(BusApiService.class);
                Call<BusRouteInfoWrapper> call = service.getBusRouteList(BUS_NUM, API_KEY);
                call.enqueue(new Callback<BusRouteInfoWrapper>() {
                    @Override
                    public void onResponse(Call<BusRouteInfoWrapper> call, Response<BusRouteInfoWrapper> response) {
                        if (response.isSuccessful()) {
                            BusRouteInfoWrapper result = response.body();
                            ArrayList<BusRouteInfoItem> list = result.getBusRouteList();

                            BusRouteListAdapter busRouteListAdapter = new BusRouteListAdapter(list);
                            listView.setAdapter(busRouteListAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<BusRouteInfoWrapper> call, Throwable t) {

                    }
                });
            }
        });

        // TODO : Change the logic depend on which activity called the fragment
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BusRouteInfoItem item = (BusRouteInfoItem) parent.getItemAtPosition(position);
                String activity_name = getActivity().getClass().getName();

                switch (getActivity().getClass().getSimpleName()){
                    case "FavoriteAddActivity":
                        //Toast.makeText(getContext(),"Favorite",Toast.LENGTH_LONG).show();

                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.activity_favorite_add_frame_layout, new FavoriteAddFragment());
                        fragmentTransaction.commit();

                        break;
                    case "MainActivity":
                        Toast.makeText(getContext(),"Main",Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getActivity(), BusStationsListActivity.class);
                        intent.putExtra("routeId", item.getBusRouteId());

                        startActivity(intent);

                        break;
                }
            }
        });

        return view;
    }
}
