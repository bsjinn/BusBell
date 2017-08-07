package com.example.sojin.busbellapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.fragment.FavoriteFragment;
import com.example.sojin.busbellapp.fragment.SearchFragment;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button okButton;
    ListView listView;

    FragmentTransaction fragmentTransaction;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //actionbar
        //setCustomActionBar();
        //fcm
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        //String deviceToken=FirebaseInstanceId.getInstance().getToken();
        //Log.d("","token:"+deviceToken);

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

//    private boolean setCustomActionBar(){
//        ActionBar actionBar = getSupportActionBar();
//
//        actionBar.setDisplayShowCustomEnabled(true);//커스텀 액션바 보이게한다
//        //기존 액션바 숨기기
//        actionBar.setDisplayHomeAsUpEnabled(false);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayShowHomeEnabled(false);
//
//        //layout inflate
//        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
//        View actionBarView = inflater.inflate(R.layout.activity_actionbar,null);
//
//        actionBar.setCustomView(actionBarView);
//
//        //delete empty space
//        Toolbar parent = (Toolbar)actionBarView.getParent();
//        parent.setContentInsetsAbsolute(0,0);
//
//        return true;
//    }
}