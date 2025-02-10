package com.iot.mechatronix.retrofit;



import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServiceInterface {


    String BASE_URL = "https://hosting.smsolutions.in/iotglobe/api/";

    @Multipart
    @POST("product-categories")
    Call<ResponceFetchProductType> FetchProductType(
            @Part("user_id") RequestBody ph


    );
//    @Multipart
//    @POST("products")
//    Call<ReesponceFetchProductName> FetchProductName(
//            @Part("user_id") RequestBody ph,
//            @Part("product_category_id") RequestBody id
//
//
//    );
//    @Multipart
//    @POST("products-shade-cards")
//    Call<ReesponceFetchShadeCard> FetchShadeCard(
//            @Part("user_id") RequestBody ph,
//            @Part("product_id") RequestBody id
//
//
//    );
    @Multipart
    @POST("all-db-shade-cards-w-product")
    Call<ReesponceFetchShadeCard> FetchShadeCardDB(
            @Part("user_id") RequestBody ph


    );
    @Multipart
    @POST("colours")
    Call<ResopnceFetchColors> FetchColors(
            @Part("user_id") RequestBody ph,
            @Part("product_shade_card_id") RequestBody id


    );
    @Multipart
    @POST("bases")
    Call<RresponceFetchBase> FetchBase(
            @Part("user_id") RequestBody id


    );

    @Multipart
    @POST("calculate-price")
    Call<ResponceFetchCalculatePrice> FetchCalculatedPrice(
            @Part("user_id") RequestBody id,
            @Part("colour_id") RequestBody color_id,
            @Part("colour_code") RequestBody color_code,
            @Part("base_id") RequestBody base_id,
            @Part("pack_id") RequestBody pack_id,
            @Part("no_of_pack") RequestBody no_pack


    );


    @Multipart
    @POST("all-db-products-w-cat")
    Call<ReesponceFetchProductName> FetchProductNameDB(
            @Part("user_id") RequestBody ph

    );
    @Multipart
    @POST("all-db-colours-w-cards")
    Call<ReesponceFetchColorNameDB> FetchProductColorDB(
            @Part("user_id") RequestBody ph

    );

    @Multipart
    @POST("tints")
    Call<ResponseFetchTints> FetchProductColorTint(
            @Part("user_id") RequestBody ph

    );
}
