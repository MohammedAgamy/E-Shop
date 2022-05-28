package com.example.e_shop.pojo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    static Retrofit retrofit = null;
    private static String url = "https://student.valuxapps.com/api/";

    public static Retrofit getRetrofit() {


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
