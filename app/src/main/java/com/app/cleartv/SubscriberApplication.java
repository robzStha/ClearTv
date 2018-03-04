package com.app.cleartv;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubscriberApplication extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    @BindView(R.id.sp_salutation)
    Spinner sp_salutation;
    @BindView(R.id.et_salutation)
    EditText et_salutation;
    @BindView(R.id.sp_nationality)
    Spinner sp_nationality;
    @BindView(R.id.et_nationality)
    EditText et_nationality;
    @BindView(R.id.et_applicants_name)
    EditText et_applicants_name;
    @BindView(R.id.iv_nationality_del)
    ImageView iv_nationality_del;
    @BindView(R.id.iv_salutation_del)
    ImageView iv_salutation_del;
    @BindView(R.id.rl_salutation)
    RelativeLayout rl_salutation;
    @BindView(R.id.rl_nationality)
    RelativeLayout rl_nationality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriber_application);
        ButterKnife.bind(this);

//        sp_salutation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (i == 2) {
//                    sp_salutation.setVisibility(View.GONE);
//                    et_salutation.setVisibility(View.VISIBLE);
//                    et_salutation.requestFocus();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
        sp_salutation.setOnItemSelectedListener(this);
        sp_nationality.setOnItemSelectedListener(this);

//        et_salutation.setOnTouchListener(this);
//        et_nationality.setOnTouchListener(this);
        iv_nationality_del.setOnClickListener(this);
        iv_salutation_del.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView == sp_salutation) {
            ShowETSalutation(i);
        } else if (adapterView == sp_nationality) {
            ShowETNationality(i);
        }
    }

    private void ShowETNationality(int i) {
        if (i == 1) {
            sp_nationality.setVisibility(View.GONE);
            rl_nationality.setVisibility(View.VISIBLE);
            et_nationality.requestFocus();
        }
    }

    private void ShowETSalutation(int i) {
        if (i == 2) {
            sp_salutation.setVisibility(View.GONE);
            rl_salutation.setVisibility(View.VISIBLE);
            et_salutation.requestFocus();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//    @Override
//    public boolean onTouch(View view, MotionEvent event) {
//        final int DRAWABLE_LEFT = 0;
//        final int DRAWABLE_TOP = 1;
//        final int DRAWABLE_RIGHT = 2;
//        final int DRAWABLE_BOTTOM = 3;
//
//
//        if (event.getAction() == MotionEvent.ACTION_UP) {
//            if (event.getRawX() >= (et_salutation.getRight() - et_salutation.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                if (view == et_salutation) {
//                    RemoveETSalutation();
//                } else if (view == et_nationality) {
//                    RemoveETNationality();
//                }
//                return true;
//            }
//        }
//        return false;
//    }

    private void RemoveETNationality() {
        et_nationality.getText().clear();
        rl_nationality.setVisibility(View.GONE);
        sp_nationality.setSelection(0);
        sp_nationality.setVisibility(View.VISIBLE);
        et_nationality.clearFocus();
        et_applicants_name.requestFocus();
    }

    private void RemoveETSalutation() {
        et_salutation.getText().clear();
        rl_salutation.setVisibility(View.GONE);
        sp_salutation.setSelection(0);
        sp_salutation.setVisibility(View.VISIBLE);
        et_salutation.clearFocus();
        et_applicants_name.requestFocus();
    }

    @Override
    public void onClick(View view) {
        if (view == iv_salutation_del) {
            RemoveETSalutation();
        } else if (view == iv_nationality_del) {
            RemoveETNationality();
        }
    }
}
