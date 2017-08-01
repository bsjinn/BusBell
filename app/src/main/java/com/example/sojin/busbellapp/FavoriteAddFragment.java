package com.example.sojin.busbellapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sojin.busbellapp.adapter.BusStationsByRouteListAdapter;
import com.example.sojin.busbellapp.item.BusStationInfoItem;
import com.example.sojin.busbellapp.item.BusStationInfoWrapper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteAddFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private String routeId;
    private ArrayList<BusStationInfoItem> stationList;

    private Button button;
    private ListView listview;
    private TextView on_station;
    private TextView off_station;

    public FavoriteAddFragment() { }

    public static FavoriteAddFragment newInstance(String param1) {
        FavoriteAddFragment fragment = new FavoriteAddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            routeId = getArguments().getString(ARG_PARAM1);
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

        on_station = (TextView)view.findViewById(R.id.fragment_favorite_add_on_station);
        on_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on_station.setText("");
            }
        });

        off_station = (TextView)view.findViewById(R.id.fragment_favorite_add_off_station);
        off_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                off_station.setText("");
            }
        });

        listview = (ListView)view.findViewById(R.id.fragment_favorite_add_station_list);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BusStationInfoItem item = stationList.get(i);

                if(on_station.getText().length() > 0)
                    off_station.setText(item.getStationNm());
                else
                    on_station.setText(item.getStationNm());
            }
        });

        button = (Button)view.findViewById(R.id.fragment_favorite_add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        return view;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
