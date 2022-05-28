package com.example.e_shop.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.e_shop.R;
import com.example.e_shop.databinding.FragmentSignUpBinding;
import com.example.e_shop.pojo.ApiService;
import com.example.e_shop.pojo.Client;
import com.example.e_shop.pojo.RegisterModel;
import com.example.e_shop.pojo.SharedModel;
import com.example.e_shop.pojo.UserSignUpLiveData;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpFragment extends Fragment implements View.OnClickListener {
    //Model
    UserSignUpLiveData mSignUpLiveData;
    SharedModel sharedModel;

    FragmentSignUpBinding signUpBinding;


    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        signUpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        View view = signUpBinding.getRoot();
        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iniView(view);
    }

    private void iniView(View view) {
        signUpBinding.btnSignUp.setOnClickListener(this);
        signUpBinding.textGoLogIn.setOnClickListener(this);

        //model
        mSignUpLiveData = new ViewModelProvider(this).get(UserSignUpLiveData.class);
        sharedModel=new SharedModel(getActivity());


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_SignUp:
                signUpUser();
                break;
            case R.id.text_goLogIn:
                SignInFragment signInFragment = new SignInFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_Register_fragment, signInFragment).commit();
                break;

        }

    }

    private void signUpUser() {
        String name = signUpBinding.nameRegister.getText().toString().trim();
        String email = signUpBinding.emailRegister.getText().toString().trim();
        String phone = signUpBinding.phoneRegister.getText().toString().trim();
        String password = signUpBinding.passwordRegister.getText().toString().trim();
        if (validation(name, email, phone, password)) {

            mSignUpLiveData.setUserDataToApi(name, email, phone, password);
            mSignUpLiveData.mutableLiveDataSignUp.observe(this, new Observer<RegisterModel>() {
                @Override
                public void onChanged(RegisterModel registerModel) {
                    sharedModel.SaveData(name,email,phone,true);
                    Log.d("messageFrom Api = ", registerModel.getMessage());
                }
            });
        }

    }

    private boolean validation(String name, String email, String phone, String password) {

        if (name.isEmpty()) {
            Snackbar.make(getView(), " Your Name is Empty", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (email.isEmpty()) {
            Snackbar.make(getView(), " Your Email is Empty", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Snackbar.make(getView(), " Check Your Email ", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (phone.isEmpty()) {
            Snackbar.make(getView(), " Your Phone is Empty", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (password.isEmpty()) {
            Snackbar.make(getView(), " Your Password is Empty", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (password.length() < 8) {
            Snackbar.make(getView(), " Your Password is Short", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (!signUpBinding.checkboxRegister.isChecked()) {
            Snackbar.make(getView(), " Your Mast Agree To Our Conditions First ", Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}

