package com.app.cleartv;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.cleartv.models.Customer;
import com.app.cleartv.models.District;
import com.app.cleartv.network_protocol.ApiCalls;
import com.app.cleartv.network_protocol.RetrofitSingleton;
import com.app.cleartv.utils.CommonMethods;
import com.app.cleartv.utils.CustomAlertDialog;
import com.app.cleartv.utils.DataFeeder;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

public class SubscriberApplication extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    @BindView(R.id.sp_salutation)
    Spinner sp_salutation;
    @BindView(R.id.et_salutation)
    EditText et_salutation;
    @BindView(R.id.sp_nationality)
    Spinner sp_nationality;
    @BindView(R.id.sp_identification)
    Spinner sp_identification;
    @BindView(R.id.spn_district)
    Spinner spn_district;
    @BindView(R.id.et_nationality)
    EditText et_nationality;
    @BindView(R.id.et_citizen_passport)
    EditText et_citizen_passport;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_applicants_name)
    EditText et_applicants_name;
    @BindView(R.id.et_tole_street_name)
    EditText et_tole_street_name;
    @BindView(R.id.iv_nationality_del)
    ImageView iv_nationality_del;
    @BindView(R.id.iv_salutation_del)
    ImageView iv_salutation_del;
    @BindView(R.id.rl_salutation)
    RelativeLayout rl_salutation;
    @BindView(R.id.rl_nationality)
    RelativeLayout rl_nationality;
    @BindView(R.id.rl_sign)
    RelativeLayout rl_sign;
    @BindView(R.id.iv_sign)
    ImageView iv_sign;
    @BindView(R.id.btn_login_submit)
    Button btn_login_submit;

    ApiCalls apiCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriber_application);
        ButterKnife.bind(this);
        apiCalls = RetrofitSingleton.getApiCalls();

        ArrayList<District> districts = new ArrayList<>();
        JSONArray ja = DataFeeder.District();

        for (int i = 0; i < ja.length(); i++) {
            District d = new District();
            try {
                d.setDistrict(ja.getJSONObject(i).getString("district"));
                d.setId(ja.getJSONObject(i).getInt("id"));
                districts.add(d);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//        sp_salutation.on

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
        sp_identification.setOnItemSelectedListener(this);

//        et_salutation.setOnTouchListener(this);
//        et_nationality.setOnTouchListener(this);
        iv_nationality_del.setOnClickListener(this);
        iv_salutation_del.setOnClickListener(this);
        rl_sign.setOnClickListener(this);

        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.districts));
        DistrictAdapter districtArrayAdapter = new DistrictAdapter(districts);
        spn_district.setAdapter(districtAdapter);

        btn_login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validate();
//                Call<Customer> customer = apiCalls.createCustomer()
            }
        });
    }

    private String getSalutation() {
        return sp_salutation.getVisibility() == View.VISIBLE ? sp_salutation.getSelectedItem().toString() : et_salutation.getText().toString();
    }

    private String getNationality() {
        return sp_nationality.getVisibility() == View.VISIBLE ? sp_nationality.getSelectedItem().toString() : et_nationality.getText().toString();
    }

    private String getCitizenPassport() {
        return et_citizen_passport.getText().toString();
    }

    private boolean isPassport() {
        return sp_identification.getSelectedItemPosition() == 0 ? false : true;
    }


    private void Validate() {

        if (getSalutation().equals(""))
            CustomAlertDialog.showAlertDialog(this, "Please enter the title");
        else if (et_applicants_name.getText().length() == 0)
            CustomAlertDialog.showAlertDialog(this, "Please enter the applicant's name");
        else if (getNationality().equals(""))
            CustomAlertDialog.showAlertDialog(this, "Please enter the applicant's nationality");
        else if(/*isPassport() && */getCitizenPassport().equals(""))
            CustomAlertDialog.showAlertDialog(this, "Please enter the identification detail of applicant");
        else if(et_tole_street_name.getText().toString().equals(""))
            CustomAlertDialog.showAlertDialog(this, "Please enter the tole / street name of applicant");
        else if(!CommonMethods.isValidEmail(et_email.getText()))
            CustomAlertDialog.showAlertDialog(this, "Please enter a valid email");
        else if(current_bmp==null)
            CustomAlertDialog.showAlertDialog(this, "Please provide applicant's signatures");

    }

    public class DistrictAdapter extends BaseAdapter {

        private ArrayList<District> districts;

        public DistrictAdapter(ArrayList<District> districts) {
            this.districts = districts;
        }

        @Override
        public int getCount() {
            return districts.size();
        }

        @Override
        public Object getItem(int position) {
            return districts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return districts.get(position).getId();
        }

        class Holder {
            private TextView tvCountryName;
        }

        private LayoutInflater inflater;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView = null;
            try {
                Holder holder;
                myView = convertView;

                if (myView == null) {
                    inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    myView = inflater.inflate(android.R.layout.simple_spinner_item, null);

                    holder = new Holder();
                    holder.tvCountryName = (TextView) myView.findViewById(android.R.id.text1);
                    myView.setTag(holder);
                } else {
                    holder = (Holder) myView.getTag();
                }

                holder.tvCountryName.setText(districts.get(position).getDistrict());

            } catch (Exception e) {
                e.printStackTrace();
            }

            return myView;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView == sp_salutation) {
            ShowETSalutation(i);
        } else if (adapterView == sp_nationality) {
            ShowETNationality(i);
        } else if (adapterView == sp_identification) {
            et_citizen_passport.setHint(sp_identification.getSelectedItemPosition() == 0 ? getString(R.string.citizen_no) : getString(R.string.passport_no));
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
        switch (view.getId()) {
            case R.id.iv_salutation_del:
                RemoveETSalutation();
                break;
            case R.id.iv_nationality_del:
                RemoveETNationality();
                break;
            case R.id.rl_sign:
                Intent intent = new Intent();
                intent.setClass(SubscriberApplication.this, Signature.class);
                SubscriberApplication.this.startActivityForResult(intent, 1);
                break;
        }
    }

    Bitmap current_bmp;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
//            editorBitmapArray.add(current_bmp);
//            current_bmp = data.get
            byte[] byteArray = data.getByteArrayExtra("sign");
            current_bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            iv_sign.setImageBitmap(current_bmp);
        }
    }
}
