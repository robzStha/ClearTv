package com.app.cleartv.network_protocol;

import com.app.cleartv.models.Customer;

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
                                  @Field("Gender") String gender,
                                  @Field("PHouseNo") String houseNo,
                                  @Field("PWardNo") String wardNo,
                                  @Field("PStreetName") String street,
                                  @Field("PVdcMunicipality") String vdcMunicipality,
                                  @Field("PDistrict") String district,
//                                  @Field("PZone") String houseNo,
//                                  @Field("PCountry") String wardNo,
                                  @Field("ContactNo") String contact_no,
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
                                  @Field("FingerPrint") String fingerprint
    );

//    @GET("menu_category")
//    Call<CategoryNewsListing> getMenuCategories();
//
//
//    @GET("home")
//    Call<HomeResponse> getHomeResponse();
//
//    @GET("category/{id}")
//    Call<MenuCategory> getCategoriesNewsById(@Path("id") long id);

//    @GET(CommonMethods.UrlHelper.DATA+"categories?parent=0&per_page=15")
//    Call<List<Category>> getCategories();
//
//    @GET(CommonMethods.UrlHelper.DATA+"categories?per_page=100")
//    Call<List<Category>> getAllCategories();
//
//    @GET(CommonMethods.UrlHelper.DATA+"posts")
//    Call<List<Post>> getPosts(@Query("categories") int id);
//
//    @GET(CommonMethods.UrlHelper.DATA+"posts")
//    Call<List<Post>> getLatestPosts();
//
//    @GET(CommonMethods.UrlHelper.DATA+"posts")
//    Call<List<Post>> getPostsNext(@Query("categories") int id, @Query("page") int count);
//
//    @GET(CommonMethods.UrlHelper.DATA+"posts")
//    Call<List<Post>> getLatestPostsNext(@Query("page") int count);
//
//    @GET(CommonMethods.UrlHelper.MENU+"menus")
//    Call<List<Menu>> getMenus();
//
//    @GET(CommonMethods.UrlHelper.MENU+"menus/{id}")
//    Call<ActiveMenuList> getActiveMenuList(@Path("id") int id);
//
//    @GET(CommonMethods.UrlHelper.DATA+"media/{id}")
//    Call<Media> getMedia(@Path("id") int id);
//
//    @GET(CommonMethods.UrlHelper.DATA+"media?after=2017-03-28T15:41:44")
//    Call<List<Media>> getMediaList(@Query("per_page") int per_page);
//
//    @GET(CommonMethods.UrlHelper.DATA+"media")
//    Call<List<Media>> getMediaListNext(@Query("per_page") int per_page, @Query("page") int page);
//
//    @GET(CommonMethods.UrlHelper.NOTIFICATION+"register")
//    Call<ResponseToken> setDeviceToken(@Query("os_type") String os_type, @Query("user_email_id") String email_id, @Query("device_token") String token);

}