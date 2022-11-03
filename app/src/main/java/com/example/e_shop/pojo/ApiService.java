package com.example.e_shop.pojo;


import com.example.e_shop.pojo.HomePackage.Categories.ResultCategories;
import com.example.e_shop.pojo.HomePackage.Banners.ResultBannersHome;
import com.example.e_shop.pojo.HomePackage.CatogeryHome.HomeCatogray;
import com.example.e_shop.pojo.RegisterPackage.LogInModel;
import com.example.e_shop.pojo.RegisterPackage.RegisterModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {
    @POST("register")
    @FormUrlEncoded
    Call<RegisterModel> SignUpUser(@Field("name") String name,
                                   @Field("email") String email,
                                   @Field("phone") String phone,
                                   @Field("password") String password);


    @POST("login")
    @FormUrlEncoded
    Call<LogInModel> signInUser(@Field("email") String email,
                                @Field("password") String password);


    @GET("banners")
    Call<ResultBannersHome>getBanners();

    @GET("categories")
    Call<ResultCategories> getCategory();

    @GET("home")
    Call<HomeCatogray> getHomeCat();

}
