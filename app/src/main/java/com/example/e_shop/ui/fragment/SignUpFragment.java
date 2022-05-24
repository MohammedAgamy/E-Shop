package com.example.e_shop.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.e_shop.pojo.ApiService;
import com.example.e_shop.pojo.Client;
import com.example.e_shop.pojo.RegisterModel;
import com.example.e_shop.pojo.UserSignUpLiveData;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpFragment extends Fragment implements View.OnClickListener {
    //filed View
    private TextInputEditText mName, mEmail, mPhone, mPassword;
    private CheckBox mAgreeCheck;
    private Button mBtn_SignUp;
    private LinearLayout mBtn_SignIn;

    //Model
    UserSignUpLiveData mSignUpLiveData;


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
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iniView(view);
    }

    private void iniView(View view) {
        //find View Register
        mName = view.findViewById(R.id.name_register);
        mEmail = view.findViewById(R.id.email_register);
        mPhone = view.findViewById(R.id.phone_register);
        mPassword = view.findViewById(R.id.password_register);
        mBtn_SignUp = view.findViewById(R.id.btn_SignUp);
        mBtn_SignUp.setOnClickListener(this);
        mBtn_SignIn = view.findViewById(R.id.text_goLogIn);
        mBtn_SignIn.setOnClickListener(this);
        mAgreeCheck = view.findViewById(R.id.checkbox_register);


        //
        mSignUpLiveData = new ViewModelProvider(this).get(UserSignUpLiveData.class);

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
        String name = mName.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String phone = mPhone.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        if (validation(name, email, phone, password)) {

            mSignUpLiveData.setUserDataToApi(name, email, phone, password);
            mSignUpLiveData.mutableLiveDataSignUp.observe(this, new Observer<RegisterModel>() {
                @Override
                public void onChanged(RegisterModel registerModel) {
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
        if (!mAgreeCheck.isChecked()) {
            Snackbar.make(getView(), " Your Mast Agree To Our Conditions First ", Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}

