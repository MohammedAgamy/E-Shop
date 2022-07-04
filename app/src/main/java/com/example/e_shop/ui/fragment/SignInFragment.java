package com.example.e_shop.ui.fragment;

import android.content.Intent;
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

import com.example.e_shop.R;
import com.example.e_shop.databinding.FragmentSignInBinding;
import com.example.e_shop.pojo.RegisterPackage.LogInModel;
import com.example.e_shop.pojo.RegisterPackage.SharedModel;
import com.example.e_shop.pojo.RegisterPackage.UserSignInLiveData;
import com.example.e_shop.ui.HomeActivity;
import com.google.android.material.snackbar.Snackbar;

public class SignInFragment extends Fragment implements View.OnClickListener {
    FragmentSignInBinding signInBinding;

    UserSignInLiveData signInLiveData;

    public SignInFragment() {
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
        signInBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false);
        View view = signInBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iniView(view);
    }

    private void iniView(View view) {
        signInBinding.btnSignIn.setOnClickListener(this);
        signInBinding.textGoSignUp.setOnClickListener(this::iniView);

        //model
        signInLiveData = new ViewModelProvider(this).get(UserSignInLiveData.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signIn:
                signInUser();
                break;
            case R.id.text_goLogIn:
                SignUpFragment signUpFragment = new SignUpFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_Register_fragment, signUpFragment).commit();
                break;

        }
    }


    private void signInUser() {
        String email = signInBinding.emailSignIn.getText().toString().trim();
        String password = signInBinding.passwordSignIn.getText().toString().trim();
        if (validation(email, password)) {
            signInLiveData.setUserDataToApi(email, password);
            signInLiveData.mutableLiveDataSignIn.observe(this, new Observer<LogInModel>() {
                @Override
                public void onChanged(LogInModel logInModel) {

                    Log.d("messageFrom Api = ", logInModel.getMessage());
                    SharedModel model =new SharedModel(getActivity());
                    model.SaveData(null , email,null,true);
                    getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
                    getActivity().finish();


                }
            });
        }

    }

    private boolean validation(String email, String password) {

        if (email.isEmpty()) {
            Snackbar.make(getView(), " Your Email is Empty", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Snackbar.make(getView(), " Check Your Email ", Snackbar.LENGTH_LONG).show();
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

        return true;
    }

}