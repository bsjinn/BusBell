package com.example.sojin.busbellapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sojin.busbellapp.BusApiService;
import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.adapter.BusRouteListAdapter;
import com.example.sojin.busbellapp.item.BusRouteInfoItem;
import com.example.sojin.busbellapp.item.BusRouteInfoWrapper;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button okButton;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //actionbar
        setCustomActionBar();
        //fcm
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        //String deviceToken=FirebaseInstanceId.getInstance().getToken();
        //Log.d("","token:"+deviceToken);


        editText = (EditText)findViewById(R.id.editText);
        okButton = (Button)findViewById(R.id.okButton);
        listView = (ListView)findViewById(R.id.listView);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bus_number = editText.getText().toString();
                String API_KEY = "3nvztFALDhnl5ffO0FuwkATq9JDSLJPHjSSVRByOsG78s9vF%2F4SuBbuKcle1XytZB0hkdU19wrBSnqDKHWHpdA%3D%3D";

                BusApiService service = BusApiService.retrofit.create(BusApiService.class);
                Call<BusRouteInfoWrapper> call = service.getBusRouteList(bus_number, API_KEY);
                call.enqueue(new Callback<BusRouteInfoWrapper>() {
                    @Override
                    public void onResponse(Call<BusRouteInfoWrapper> call, Response<BusRouteInfoWrapper> response) {
                        if(response.isSuccessful()){
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BusRouteInfoItem item = (BusRouteInfoItem) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, BusStationsListActivity.class);
                intent.putExtra("routeId",item.getBusRouteId());

                startActivity(intent);
            }
        });
    }

    private boolean setCustomActionBar(){
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);//커스텀 액션바 보이게한다
        //기존 액션바 숨기기
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        //layout inflate
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionBarView = inflater.inflate(R.layout.activity_actionbar,null);

        actionBar.setCustomView(actionBarView);

        //delete empty space
        Toolbar parent = (Toolbar)actionBarView.getParent();
        parent.setContentInsetsAbsolute(0,0);

        return true;
    }
}