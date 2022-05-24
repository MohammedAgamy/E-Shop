package com.example.e_shop.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.e_shop.R;
import com.example.e_shop.Adapter.SliderImageAdapter;
import com.example.e_shop.ui.StartRegisterActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class GetStartFragment extends Fragment implements View.OnClickListener {
    //view
    Button mBtn_GetStart;
    //slider filed
    private SliderView mSliderView;
    int[] mSlideImage;
    SliderImageAdapter sliderImageAdapter;

    public GetStartFragment() {
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
        return inflater.inflate(R.layout.fragment_get_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iniView(view);
        showSlider();
    }


    private void iniView(View view) {
        mBtn_GetStart = view.findViewById(R.id.btn_start);
        mBtn_GetStart.setOnClickListener(this);
        mSliderView = view.findViewById(R.id.imageSlider);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
               /*/ SignUpFragment signUpFragment = new SignUpFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_Register_fragment, signUpFragment).commit();*/
                getActivity().startActivity(new Intent(getActivity() , StartRegisterActivity.class));
                getActivity().finish();
                break;
        }
    }


    private void showSlider() {
        mSlideImage = new int[]{R.drawable.deliveryman, R.drawable.deliverymanchecking, R.drawable.deliveryman, R.drawable.deliverymanchecking};
        sliderImageAdapter = new SliderImageAdapter(mSlideImage);
        mSliderView.setSliderAdapter(sliderImageAdapter);
        mSliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        mSliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        mSliderView.startAutoCycle();
    }
}