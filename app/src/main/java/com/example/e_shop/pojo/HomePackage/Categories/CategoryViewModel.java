package com.example.e_shop.pojo.HomePackage.Categories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_shop.pojo.ApiService;
import com.example.e_shop.pojo.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryViewModel extends ViewModel {
    MutableLiveData<ResultCategories> mutableLiveData ;
    ResultCategories resultCategories ;



    public void getCategoriesFromApi() {

        ApiService apiService = Client.getRetrofit().create(ApiService.class);
        //Call<ResultCategories> call = apiService.getCategory();
/*
        call.enqueue(new Callback<ResultCategories>() {
            @Override
            public void onResponse(Call<ResultCategories> call, Response<ResultCategories> response) {
              response.body();
                Log.e("getCat" , String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<ResultCategories> call, Throwable t) {
                Log.e("errorgetCat" ,t.getMessage());

            }
        });*/
    }
}
