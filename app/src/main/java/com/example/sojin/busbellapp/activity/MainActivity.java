package com.example.sojin.busbellapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sojin.busbellapp.AlarmApiService;
import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.fragment.FavoriteFragment;
import com.example.sojin.busbellapp.fragment.SearchFragment;
import com.example.sojin.busbellapp.item.DeleteItem;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;
    Fragment fragment = null;

    private SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //actionbar
        //setCustomActionBar();
        //fcm
        FirebaseMessaging.getInstance().subscribeToTopic("news");
//        String deviceToken= FirebaseInstanceId.getInstance().getToken();


        //예약 내역 있을때 reserved window 노출
        mPref = getSharedPreferences("pref",MODE_PRIVATE);
        setReservedWindow();

        fragmentTransaction = getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.activity_main_frame_layout, new FavoriteFragment());
        fragmentTransaction.commit();

        BottomNavigationView navigation = (BottomNavigationView)findViewById(R.id.activity_main_navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_favorite:
                        fragment = new FavoriteFragment();
                        break;
                    case R.id.navigation_search:
                        fragment = new SearchFragment();
                        break;
                }

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_frame_layout, fragment);
                fragmentTransaction.commit();

                return true;
            }
        });
    }

    public void setReservedWindow() {

        String busNum, deptStn, arvStn;
        final int reqId;

        reqId=mPref.getInt("reqID",0);
        Log.v("RESERVED::::::","reqID" + reqId);

        if(reqId>0) {

            busNum=mPref.getString("busNum","");
            deptStn=mPref.getString("deptStn","");
            arvStn=mPref.getString("arvStn","");

            Log.v("***SHARED PREFERENCE***","reqID::" +reqId + "busNum::"+busNum+", from::"+deptStn+", to::"+arvStn);

            final LinearLayout inLayout=(LinearLayout)findViewById(R.id.reserved_view);

            LayoutInflater vi=(LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View v=vi.inflate(R.layout.reserved_window,null,false);

            TextView bus=(TextView) v.findViewById(R.id.bus_num);
            TextView fromStn=(TextView) v.findViewById(R.id.station_from);
            TextView toStn=(TextView) v.findViewById(R.id.station_to);
            ImageView imageView=(ImageView) v.findViewById(R.id.image_view);
            Button cancelBtn=(Button)v.findViewById(R.id.cancel_btn);

            bus.setText(busNum);
            fromStn.setText(deptStn);
            toStn.setText(arvStn);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.bus_icon));

            inLayout.addView(v);

            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    inLayout.removeView(v);

                    AlarmApiService service = AlarmApiService.retrofit.create(AlarmApiService.class);
                    Call<DeleteItem> call = service.delete(Integer.toString(reqId));
                    call.enqueue(new Callback<DeleteItem>() {
                        @Override
                        public void onResponse(Call<DeleteItem> call, Response<DeleteItem> response) {
                            if(response.isSuccessful()){

                                SharedPreferences.Editor editor = mPref.edit();
                                editor.clear();
                                editor.commit();

                                Log.v("***DELETED***","shared preference data cleared");

                            }else {
                            }
                        }

                        @Override
                        public void onFailure(Call<DeleteItem> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });

                }
            });
        }
    }

}