package com.app.cleartv;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.cleartv.applications.MyApplication;
import com.app.cleartv.fragments.ImageHelperFragment;
import com.app.cleartv.models.Customer;
import com.app.cleartv.models.CustomerErrorResponse;
import com.app.cleartv.models.District;
import com.app.cleartv.models.LoginErrorResponse;
import com.app.cleartv.network_protocol.ApiCalls;
import com.app.cleartv.network_protocol.RetrofitSingleton;
import com.app.cleartv.utils.AppContract;
import com.app.cleartv.utils.CommonMethods;
import com.app.cleartv.utils.CustomAlertDialog;
import com.app.cleartv.utils.DataFeeder;
import com.app.cleartv.utils.FileUtils;
import com.app.cleartv.utils.MySharedPreference;
import com.app.cleartv.utils.Payload;
import com.app.cleartv.utils.ViewUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

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
    @BindView(R.id.sp_subscription_type)
    Spinner sp_subscription_type;
    @BindView(R.id.sp_gender)
    Spinner spn_gender;
    @BindView(R.id.et_nationality)
    EditText et_nationality;
    @BindView(R.id.et_citizen_passport)
    EditText et_citizen_passport;
    @BindView(R.id.et_house_no)
    EditText et_house_no;
    @BindView(R.id.et_ward_no)
    EditText et_ward_no;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_contact_mobile_no)
    EditText et_contact_mobile_no;
    @BindView(R.id.et_applicants_name)
    EditText et_applicants_name;
    @BindView(R.id.et_tole_street_name)
    EditText et_tole_street_name;
    @BindView(R.id.et_vdc_municipality)
    EditText et_vdc_municipality;
    @BindView(R.id.et_clear_tv)
    EditText et_clear_tv;
    @BindView(R.id.et_cable_internet)
    EditText et_cable_internet;
    @BindView(R.id.et_ftth)
    EditText et_ftth;
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
    @BindView(R.id.radio_occupation)
    RadioGroup radio_occupation;

    ImageView iv_selected;

    @BindView(R.id.iv_applicant)
    ImageView iv_applicant;

    @BindView(R.id.iv_box_card)
    ImageView iv_box_card;

    @BindView(R.id.spn_box_cable_photo)
    Spinner spn_box_cable_photo;

    private String imagePath;

    ApiCalls apiCalls;
    private MySharedPreference mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriber_application);
        ButterKnife.bind(this);

        mPref = new MySharedPreference(this);

        if (!mPref.getStringValues(AppContract.Preferences.ACCESS_TOKEN).isEmpty())
            btn_login_submit.setText(R.string.submit);

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

        iv_applicant.setOnClickListener(this);
        iv_box_card.setOnClickListener(this);

        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.districts));
        DistrictAdapter districtArrayAdapter = new DistrictAdapter(districts);
        spn_district.setAdapter(districtAdapter);

        System.out.println(mPref.getStringValues(AppContract.Preferences.USER_ID) + " UserId");

        btn_login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    if (MyApplication.hasNetwork()) {

                        printPostData();

                        Call<Customer> customer = apiCalls.createCustomer(
                                getSalutation(),
                                et_applicants_name.getText().toString(),
                                Payload.applicantPhoto,
                                spn_gender.getSelectedItemId(),
                                et_house_no.getText().toString(),
                                et_ward_no.getText().toString(),
                                et_tole_street_name.getText().toString(),
                                et_vdc_municipality.getText().toString(),
                                spn_district.getSelectedItem().toString(),
                                et_contact_mobile_no.getText().toString(),
                                et_email.getText().toString(),
                                Payload.applicantSign,
                                Payload.boxPhoto,
                                Payload.cardPhoto,
                                sp_nationality.getSelectedItem().toString(),
                                mPref.getStringValues(AppContract.Preferences.USER_ID),
                                getOccupation(),
                                et_citizen_passport.getText().toString(),
                                isPassport() ? "true" : "false",
                                sp_subscription_type.getSelectedItem().toString(),
                                et_clear_tv.getText().toString(),
                                et_cable_internet.getText().toString(),
                                et_ftth.getText().toString(),
                                Payload.fingerPrint
                        );

                        customer.enqueue(new Callback<Customer>() {
                            @Override
                            public void onResponse(Call<Customer> call, Response<Customer> response) {
                                if (response != null && !response.isSuccessful() && response.errorBody() != null) {
                                    System.out.println("Rabin is testing: failure: " + response.code());
                                    Converter<ResponseBody, CustomerErrorResponse> errorConverter =
                                            RetrofitSingleton.getRetrofit().responseBodyConverter(CustomerErrorResponse.class, new Annotation[0]);
                                    try {
                                        CustomerErrorResponse error = errorConverter.convert(response.errorBody());
                                        CustomAlertDialog.showAlertDialog(SubscriberApplication.this, error.getOdataError().getInnererror().getMessage());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else if (response != null && response.isSuccessful()) {
                                    System.out.println("Rabin is testing: success");
                                }
                            }

                            @Override
                            public void onFailure(Call<Customer> call, Throwable t) {
                                System.out.println("Issue here");
                                t.printStackTrace();
                            }
                        });
                    } else
                        CustomAlertDialog.showAlertDialog(SubscriberApplication.this, "No internet connection. Please connect to the internet and try again.");
//                        Toast.makeText(SubscriberApplication.this, "No network", Toast.LENGTH_SHORT).show();
                }
            }


//            customer.enqueue(new Callback<Customer>() {
//                @Override
//                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                    System.out.println("Response size:" + response.body().size());
//                    newsTemp = response.body();
//                    Headers headers = response.headers();
//                    String temp = headers.get("Link").replace("<", "");
//                    temp = temp.replace(">", "");
//                    String string[] = temp.split(" ");
//                    String nextLink = "";
//                    System.out.println("Next linkss : " + temp + " Split: " + string[0] + " : " + string[1]);
//                    if (string[1].equals("rel=\"next\"")) {
//                        System.out.println("Next linkss : next: " + string[1]);
//                        nextLink = string[0];
//                    }
//                    populateNews(response.body());
//                    pd.dismiss();
//                }
//
//                @Override
//                public void onFailure(Call<List<Post>> call, Throwable t) {
//                    t.printStackTrace();
//                    pd.dismiss();
//                }
//            });

        });
    }

    private void printPostData() {

        System.out.println("Rabin is testing: Salutation " + getSalutation());
        System.out.println("Rabin is testing: Applicant Name " + et_applicants_name.getText().toString());
        if(Payload.applicantPhoto.length()>0)
        System.out.println("Rabin is testing: Applicant Photo");
        System.out.println("Rabin is testing: Gender " + spn_gender.getSelectedItem().toString());
        System.out.println("Rabin is testing: House No " + et_house_no.getText().toString());
        System.out.println("Rabin is testing: Ward No " + et_ward_no.getText().toString());
        System.out.println("Rabin is testing: Tole " + et_tole_street_name.getText().toString());
        System.out.println("Rabin is testing: Munc " + et_vdc_municipality.getText().toString());
        System.out.println("Rabin is testing: District " + spn_district.getSelectedItem().toString());
        System.out.println("Rabin is testing: Mobile " + et_contact_mobile_no.getText().toString());
        System.out.println("Rabin is testing: Email " + et_email.getText().toString());
        if(Payload.applicantSign.length()>0)
        System.out.println("Rabin is testing: Signature " + Payload.applicantSign);
        if(Payload.boxPhoto.length()>0)
        System.out.println("Rabin is testing: Box Photo " + Payload.boxPhoto);
        if(Payload.cardPhoto.length()>0)
        System.out.println("Rabin is testing: Card Photo " + Payload.cardPhoto);
        System.out.println("Rabin is testing: Nationality " + sp_nationality.getSelectedItem().toString());
        System.out.println("Rabin is testing: User Id " + mPref.getStringValues(AppContract.Preferences.USER_ID));
        System.out.println("Rabin is testing: Job type " + getOccupation());
        System.out.println("Rabin is testing: Passport/Citizen " + et_citizen_passport.getText().toString());
        System.out.println("Rabin is testing: is Passport " + isPassport());
        System.out.println("Rabin is testing: Subscription " + sp_subscription_type.getSelectedItem().toString());
        System.out.println("Rabin is testing: Clear TV " + et_clear_tv.getText().toString());
        System.out.println("Rabin is testing: Cable " + et_cable_internet.getText().toString());
        System.out.println("Rabin is testing: FTTH " + et_ftth.getText().toString());
        if(Payload.fingerPrint.length()>0)
        System.out.println("Rabin is testing: Fingerprint " + Payload.fingerPrint);
    }

    private String getOccupation() {
        int selectedId = radio_occupation.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        return radioButton.getText().toString();
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


    private boolean isValid() {

        boolean isValid = true;

        if (getSalutation().equals("")) {
            CustomAlertDialog.showAlertDialog(this, "Please enter the title");
            isValid = false;
        } else if (et_applicants_name.getText().length() == 0) {
            CustomAlertDialog.showAlertDialog(this, "Please enter the applicant's name");
            isValid = false;
        } else if (getNationality().equals("")) {
            CustomAlertDialog.showAlertDialog(this, "Please enter the applicant's nationality");
            isValid = false;
        } else if (/*isPassport() && */getCitizenPassport().equals("")) {
            CustomAlertDialog.showAlertDialog(this, "Please enter the identification detail of applicant");
            isValid = false;
        } else if (et_tole_street_name.getText().toString().equals("")) {
            CustomAlertDialog.showAlertDialog(this, "Please enter the tole / street name of applicant");
            isValid = false;
        } else if (!CommonMethods.isValidEmail(et_email.getText())) {
            CustomAlertDialog.showAlertDialog(this, "Please enter a valid email");
            isValid = false;
        } else if (current_bmp == null) {
            CustomAlertDialog.showAlertDialog(this, "Please provide applicant's signatures");
            isValid = false;
        } else if (iv_applicant.getDrawable() == null && iv_box_card.getDrawable() == null) {
            CustomAlertDialog.showAlertDialog(this, AppContract.Errors.APPLICANT_BOX_CARD);
            isValid = false;
        } else if (spn_box_cable_photo.getSelectedItemId() == 0 && iv_box_card.getDrawable() == null) {
            CustomAlertDialog.showAlertDialog(this, AppContract.Errors.BOX);
            isValid = false;
        } else if (spn_box_cable_photo.getSelectedItemId() == 1 && iv_box_card.getDrawable() == null) {
            CustomAlertDialog.showAlertDialog(this, AppContract.Errors.CARD);
            isValid = false;
        } else if (iv_applicant.getDrawable() == null) {
            CustomAlertDialog.showAlertDialog(this, AppContract.Errors.APPLICANT);
            isValid = false;
        } else {
            if (spn_box_cable_photo.getSelectedItemId() == 0) {
                Payload.boxPhoto = encoded;
                Payload.cardPhoto = "";
            } else {
                Payload.cardPhoto = encoded;
                Payload.boxPhoto = "";
            }
        }
        return isValid;
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
                SubscriberApplication.this.startActivityForResult(intent, AppContract.RequestCode.SIGNATURE);
                break;
            case R.id.iv_applicant:
                iv_selected = iv_applicant;
                accessCamera();
                break;
            case R.id.iv_box_card:
                iv_selected = iv_box_card;
                accessCamera();
                break;
        }
    }

    Bitmap current_bmp;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case AppContract.RequestCode.SIGNATURE:
                    byte[] byteArray = data.getByteArrayExtra("sign");
                    current_bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    Payload.applicantSign = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    iv_sign.setImageBitmap(current_bmp);
                    break;
                case AppContract.RequestCode.CAMERA:
                    sendBackImagePath();
                    break;
                default:
                    break;
            }
        }

    }


    //============================================================================ CAMERA FEATURE CLONED ===========================================


    private void accessCamera() {
        if (ActivityCompat.checkSelfPermission(SubscriberApplication.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(SubscriberApplication.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //Request permission to read write external storage and use camera.
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA},
                    AppContract.Permission.CAMERA);
            return;
        }
        openCamera();
    }
//    private ImageHelperFragment imageHelperFragment;
//
//    private void initImageHelperFragment() {
//        imageHelperFragment = ImageHelperFragment.newInstance(true, true);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(imageHelperFragment, ImageHelperFragment.TAG);
//        transaction.commit();
//        imageHelperFragment.setListener(new ImageHelperFragment.ImageHelperListener() {
//            @Override
//            public void onImageSuccess(String imagePath) {
//                Toast.makeText(CapturePhotos.this, "imagePath: "+imagePath, Toast.LENGTH_SHORT).show();
//            }
//        });

//        imageHelperFragment.setListener(imagePath -> {
//            Log.d("path", "onImageSuccess: " + imagePath);
//            Toast.makeText(this, "imagePath: "+imagePath, Toast.LENGTH_SHORT).show();
////            presenter.uploadImage(imagePath);
////            iv_applicant.setImageURI(Uri.parse(imagePath));
////            setProfileImage(imagePath, false);
//        });
//    }


    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri outputImageUri;
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            final File photoFile = new File(FileUtils.getImageDirectory(), FileUtils.getUniqueImageName());
            imagePath = photoFile.getPath();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputImageUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", photoFile);
                takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                outputImageUri = Uri.fromFile(photoFile);
            }
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputImageUri);
            // Create the File where the photo should go
            startActivityForResult(takePictureIntent, AppContract.RequestCode.CAMERA);
        } else {
            ViewUtils.showToastMessage(this, "No camera app available");
        }
    }
//    private void accessCamera() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            // comment here
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
//        } else CommonMethods.openCamera(CapturePhotos.this, imgName);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == 0) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
//                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                CommonMethods.openCamera(CapturePhotos.this, imgName);
//            }
//        }
//    }

//    private static File getOutputMediaFile() {
//        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), "CameraDemo");
//
//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                return null;
//            }
//        }
//
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        return new File(mediaStorageDir.getPath() + File.separator +
//                "IMG_" + timeStamp + ".jpg");
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case AppContract.Permission.CAMERA: {
                // If request is cancelled, the results arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else if (Build.VERSION.SDK_INT >= 23 && !shouldShowRequestPermissionRationale(permissions[0]) && !shouldShowRequestPermissionRationale(permissions[1])) {
                    CustomAlertDialog.showPermissionRequestDialog(SubscriberApplication.this, R.string.permission_rationale_camera_storage);
                } else {
                    ViewUtils.showToastMessage(SubscriberApplication.this, "You need to grant access to open camera");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case AppContract.RequestCode.CAMERA:
//                    sendBackImagePath();
//                    break;
//            }
//        }
//    }

    /**
     * Set callback
     *
     * @param listener {@link  ImageHelperFragment.ImageHelperListener}
     */
    public void setListener(@NonNull ImageHelperFragment.ImageHelperListener listener) {
        this.listener = listener;
    }


    private ImageHelperFragment.ImageHelperListener listener;
    private boolean showCropView, compressImage;

    private void sendBackImagePath() {
//        if (listener == null) {
//            throw new IllegalArgumentException("ImageHelperListener is not set");
//        } else {
        if (imagePath != null) {
            imagePath = FileUtils.resizeAndCompressImageBeforeSend(this, imagePath);

            Toast.makeText(this, "Path: " + imagePath, Toast.LENGTH_SHORT).show();
//                if (showCropView) {
            final File imgFile = new File(imagePath);
            if (imgFile.exists()) {
                new Handler().post(new Runnable() {

                    @Override
                    public void run() {
                        Bitmap myBitmap;
                        myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        myBitmap = manageOrientation(myBitmap);
                        setImageToPayload(myBitmap);
                        iv_selected.setImageBitmap(myBitmap);
                    }
                });
//78329


            }
//                CropImage.activity(Uri.parse(imagePath))
//                        .start(this);

//                CropImageFragment cropImageFragment = CropImageFragment.newInstance(imagePath);
//                cropImageFragment.show(getSupportFragmentManager(), CropImageFragment.TAG);
//                cropImageFragment.setCropImageListener(new CropImageFragment.CropImageListener() {
//                    @Override
//                    public void imageCropCompleted(@NonNull String croppedImagePath) {
//                        if (compressImage) {
//                            imagePath = FileUtils.resizeAndCompressImageBeforeSend(CapturePhotos.this, croppedImagePath);
//                        }
//                        listener.onImageSuccess(imagePath);
//                    }
//                });
//                } else {
//                    if (compressImage) {
//                        imagePath = FileUtils.resizeAndCompressImageBeforeSend(this, imagePath);
//                    }
//                    listener.onImageSuccess(imagePath);
//                }
        } else {
            CustomAlertDialog.showAlertDialog(this, AppContract.Errors.IMAGE_ERROR);
        }
    }

    String encoded;

    private void setImageToPayload(Bitmap myBitmap) {
        encoded = FileUtils.convertBitmapToBase64(myBitmap);
        if (iv_selected == iv_box_card) {
//            Payload.boxCardPhoto = encoded;
            if (spn_box_cable_photo.getSelectedItemId() == 0) Payload.boxPhoto = encoded;
            else Payload.cardPhoto = encoded;
        } else Payload.applicantPhoto = encoded;
    }


    private Bitmap manageOrientation(Bitmap myBitmap) {
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        return rotateBitmap(myBitmap, orientation);
    }

    //    }
    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {

        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }


}
