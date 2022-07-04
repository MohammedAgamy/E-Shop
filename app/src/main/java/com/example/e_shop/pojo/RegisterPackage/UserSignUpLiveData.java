package com.example.e_shop.pojo.RegisterPackage;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_shop.pojo.ApiService;
import com.example.e_shop.pojo.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSignUpLiveData extends ViewModel {

    public  MutableLiveData<RegisterModel> mutableLiveDataSignUp = new MutableLiveData<>();


    public MutableLiveData<RegisterModel> setUserData() {
        return mutableLiveDataSignUp;
    }

    public void setUserDataToApi( String name ,String email ,String phone ,String password)
    {
        ApiService apiService= Client.getRetrofit().create(ApiService.class);

        Call<RegisterModel> call =apiService.SignUpUser(name ,email,phone,password);

        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                mutableLiveDataSignUp.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {

            }
        });
    }
}
