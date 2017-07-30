package com.example.sojin.busbellapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.sojin.busbellapp.R;
import com.example.sojin.busbellapp.SearchFragment;

public class FavoriteAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_add);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_favorite_add_frame_layout, new SearchFragment());
        fragmentTransaction.commit();
    }
}
