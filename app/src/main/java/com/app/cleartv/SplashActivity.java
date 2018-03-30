package com.app.cleartv;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

//import com.app.cleartv.barcodereader.BarCodeActivity;
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
//            intent.setClass(context, BarCodeActivity.class);
//            intent.setClass(context, CaptureActivity.class);
//            intent.putExtra("SAVE_HISTORY", false);

        }
//        startActivityForResult(intent,0);
        startActivity(intent);
        finish();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//            }
//        }, 0);
    }

    String TAG = "Rabin is testing";

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 0) {
//            if (resultCode == RESULT_OK) {
//                String contents = data.getStringExtra("SCAN_RESULT");
//                Log.d(TAG, "contents: " + contents);
//            } else if (resultCode == RESULT_CANCELED) {
//                Log.d(TAG, "RESULT_CANCELED");
//            }
//        }
//    }
}
