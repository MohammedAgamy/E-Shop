package com.example.e_shop.pojo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

public class UserSignInLiveData extends ViewModel {
    public MutableLiveData<LogInModel> mutableLiveDataSignIn = new MutableLiveData<>();


    public MutableLiveData<LogInModel> setUserData() {
        return mutableLiveDataSignIn;
    }


    public void setUserDataToApi(String email, String password) {
        ApiService apiService = Client.getRetrofit().create(ApiService.class);

        Call<LogInModel> call = apiService.signInUser(email, password);

        call.enqueue(new Callback<LogInModel>() {
            @Override
            public void onResponse(Call<LogInModel> call, Response<LogInModel> response) {

                mutableLiveDataSignIn.setValue(response.body());
                Log.d("if logIn sucss", response.body().getMessage());


            }

            @Override
            public void onFailure(Call<LogInModel> call, Throwable t) {
                Log.d("if logIn filed", t.getMessage());

            }
        });


    }


}
