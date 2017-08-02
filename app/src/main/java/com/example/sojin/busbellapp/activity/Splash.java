package com.example.sojin.busbellapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.sojin.busbellapp.R;

/**
 * Created by LG on 2017-07-05.
 */

public class Splash extends Activity {

    //로딩 화면 떠 있는 시간
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    //처음 액티비티 생성 시 불러진다.
    @Override
    public void onCreate(Bundle icicle){
        super.onCreate(icicle);
        setContentView(R.layout.splah);

        //Splash 뒤에 MainActivity 실행시키고 종효
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                //MainActivity 실행하고 로딩화면 죽임.
                Intent mainIntent = new Intent(Splash.this,MainActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }

        },SPLASH_DISPLAY_LENGTH);
    }


}
