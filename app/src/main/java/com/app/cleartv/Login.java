package com.app.cleartv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.app.cleartv.applications.MyApplication;
import com.app.cleartv.models.LoginErrorResponse;
import com.app.cleartv.models.LoginResponse;
import com.app.cleartv.network_protocol.ApiCalls;
import com.app.cleartv.network_protocol.RetrofitSingleton;
import com.app.cleartv.utils.AppContract;
import com.app.cleartv.utils.CustomAlertDialog;
import com.app.cleartv.utils.MySharedPreference;

import java.io.IOException;
import java.lang.annotation.Annotation;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.pb_loading)
    ProgressBar pb_loading;
    private ApiCalls apiCall;
    private MySharedPreference mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        apiCall = RetrofitSingleton.getApiCalls();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb_loading.setVisibility(View.VISIBLE);
                btn_login.setEnabled(false);
                if (isValid()) {
                    if (MyApplication.hasNetwork()) {
                        final Call<LoginResponse> login = apiCall.login(getString(R.string.grant_type), et_username.getText().toString(), et_password.getText().toString());
                        login.enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                if (response != null && !response.isSuccessful() && response.errorBody() != null) {
                                    Converter<ResponseBody, LoginErrorResponse> errorConverter =
                                            RetrofitSingleton.getRetrofit().responseBodyConverter(LoginErrorResponse.class, new Annotation[0]);
                                    try {
                                        LoginErrorResponse error = errorConverter.convert(response.errorBody());
                                        CustomAlertDialog.showAlertDialog(Login.this, error.getErrorDescription());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }finally {
                                        btn_login.setEnabled(true);
                                        pb_loading.setVisibility(View.INVISIBLE);
                                    }
                                } else if (response != null && response.isSuccessful()) {
                                    LoginResponse loginResponse = response.body();
                                    mPrefs = new MySharedPreference(Login.this);
                                    mPrefs.setKeyValues(AppContract.Preferences.ACCESS_TOKEN, loginResponse.getAccessToken());
                                    mPrefs.setKeyValues(AppContract.Preferences.USER_ID, loginResponse.getId());
                                    btn_login.setEnabled(true);
                                    pb_loading.setVisibility(View.INVISIBLE);
                                    Intent i = new Intent(Login.this, SubscriberApplication.class);
                                    startActivity(i);
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                Log.d("testing", "Failure");
                                t.printStackTrace();
                                pb_loading.setVisibility(View.INVISIBLE);
                                btn_login.setEnabled(true);
                            }
                        });
                    } else {
                        CustomAlertDialog.showAlertDialog(Login.this, "No internet connection. Please connect to the internet and try again.");
                        pb_loading.setVisibility(View.INVISIBLE);
                        btn_login.setEnabled(true);
                    }
                } else {
                    pb_loading.setVisibility(View.INVISIBLE);
                    btn_login.setEnabled(true);
                }
            }
        });
    }

    private boolean isValid() {
        boolean isValid = true;

        if (et_username.getText().toString().length() == 0) {
            CustomAlertDialog.showAlertDialog(this, "Please enter the username");
            isValid = false;
        } else if (et_password.getText().toString().length() == 0) {
            CustomAlertDialog.showAlertDialog(this, "Please enter your password");
            isValid = false;
        }
        return isValid;
    }

}
