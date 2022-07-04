package com.example.e_shop.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_shop.R;
import com.example.e_shop.pojo.RegisterPackage.SharedModel;
import com.example.e_shop.ui.HomeActivity;

public class SplashFragment extends Fragment {
    SharedModel sharedModel;

    public SplashFragment() {
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
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startSplash();
        sharedModel = new SharedModel(getActivity());

    }

    private void startSplash() {
        new Handler().postDelayed(() -> {

            if (sharedModel.isLogin()) {
                getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
                getActivity().finish();
            } else {
                GetStartFragment getStartFragment = new GetStartFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, getStartFragment).commit();
            }


        }, 3000);
    }
}