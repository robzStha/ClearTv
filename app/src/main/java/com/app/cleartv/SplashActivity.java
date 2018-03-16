package com.app.cleartv;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.cleartv.utils.AppContract;
import com.app.cleartv.utils.MySharedPreference;

public class SplashActivity extends AppCompatActivity {
    Context context;
    MySharedPreference mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
        context = this;
        mPrefs = new MySharedPreference(this);
        Intent intent = new Intent();
        if (mPrefs.getStringValues(AppContract.Preferences.ACCESS_TOKEN).isEmpty()) {
            intent.setClass(context, Login.class);
//        intent.setClass(context, CapturePhotos.class);git status
//        intent.setClass(context, Signature.class);
        } else {
            intent.setClass(context, SubscriberApplication.class);
        }
        context.startActivity(intent);
        finish();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//            }
//        }, 0);
    }
}
