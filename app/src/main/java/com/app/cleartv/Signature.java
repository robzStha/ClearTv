package com.app.cleartv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.simplify.ink.InkView;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Signature extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btn_back)
    Button btn_back;

    @BindView(R.id.btn_clear)
    Button btn_clear;

    @BindView(R.id.btn_save)
    Button btn_save;

    @BindView(R.id.ink)
    InkView ink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        ButterKnife.bind(this);
        btn_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_clear:
                ink.clear();
                break;
            case R.id.btn_save:
                Bitmap sign = ink.getBitmap();
                //create empty bitmap with same config and height and width as per my sign bitmap.
                Bitmap emptyBitmap = Bitmap.createBitmap(sign.getWidth(), sign.getHeight(), sign.getConfig());
                //check if my bitmap is empty or not
                if(!sign.sameAs(emptyBitmap)){
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    sign.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent i = new Intent();
                    i.putExtra("sign", byteArray);
                    setResult(RESULT_OK,i);
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
