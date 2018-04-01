package com.app.cleartv.network_protocol;

import com.app.cleartv.models.Customer;
import com.app.cleartv.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiCalls {
    //
    @FormUrlEncoded
    @POST("/odata/CustomerInfo")
    Call<Customer> createCustomer(
//            @Field("ApplicantId") int applicantId,
            @Field("Title") String title,
            @Field("ApplicantName") String name,
            @Field("ApplicantPhoto") String applicantPhoto,
            @Field("Gender") long gender,
            @Field("PHouseNo") String houseNo,
            @Field("PWardNo") String wardNo,
            @Field("PStreetName") String street,
            @Field("PVdcMunicipality") String vdcMunicipality,
            @Field("PDistrict") String district,
//                                  @Field("PZone") String houseNo,
//                                  @Field("PCountry") String wardNo,
            @Field("ContactNo") String contact_no,
            @Field("MobileNo") String mobile_no,
            @Field("AlternativeNo") String alt_contact_no,
            @Field("Email") String email,
            @Field("ApplicantSign") String applicantSign,
            @Field("BoxNoPhoto") String boxNoPhoto,
            @Field("CardNoPhoto") String cardNoPhoto,
            @Field("Nationality") String nationality,
            @Field("UserId") String userId,
            @Field("Occupation") String occupation,
            @Field("Identification") String identification,
            @Field("IsPassport") String isPassport,
            @Field("SubscriptionType") String subscriptionType,
            @Field("ClearTV") String clearTv,
            @Field("CableInternet") String cableInernet,
            @Field("FTTH") String ftth,
            @Field("RightFingerPrint") String fingerprintRight,
            @Field("LeftFingerPrint") String fingerprintLeft,
            @Field("IdentificationPhoto") String identificationPhoto,
            @Field("TermsAndConditionPhoto") String tncPhoto
    );

    @FormUrlEncoded
    @POST("/token")
    Call<LoginResponse> login(@Field("grant_type") String grantType,
                              @Field("username") String userName,
                              @Field("password") String password);

}