package com.example.e_shop.pojo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    static Retrofit retrofit = null ;

    public static Retrofit getRetrofit()
    {
        if(retrofit == null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl("https://student.valuxapps.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit ;
    }
}
