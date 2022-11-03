package com.example.e_shop.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.e_shop.Adapter.CategoryAdapter;
import com.example.e_shop.Adapter.SliderHomeAdapter;
import com.example.e_shop.R;
import com.example.e_shop.databinding.FragmentHomeBinding;
import com.example.e_shop.pojo.ApiService;
import com.example.e_shop.pojo.Client;
import com.example.e_shop.pojo.HomePackage.Banners.Datum;
import com.example.e_shop.pojo.HomePackage.Banners.ResultBannersHome;
import com.example.e_shop.pojo.HomePackage.Categories.CategoryViewModel;
import com.example.e_shop.pojo.HomePackage.Categories.DatumCategory;
import com.example.e_shop.pojo.HomePackage.Categories.ResultCategories;
import com.example.e_shop.pojo.HomePackage.CatogeryHome.Data;
import com.example.e_shop.pojo.HomePackage.CatogeryHome.HomeCatogray;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding mHomeBinding;

    //Adapter
    SliderHomeAdapter mSliderHomeAdapter;
    List<Datum> mSlideImage;

    //
    CategoryViewModel mCategoryViewModel;
    CategoryAdapter mCatAdapter;
    List<Data> mLisCat;

    public HomeFragment() {
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
        mHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View view = mHomeBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iniView(view);
    }

    private void iniView(View view) {

        showSlider();
        showCategories();

    }

    private void showCategories() {
       /* ApiService apiService = Client.getRetrofit().create(ApiService.class);
        Call<ResultCategories> call = apiService.getCategory();

        call.enqueue(new Callback<ResultCategories>() {
            @Override
            public void onResponse(Call<ResultCategories> call, Response<ResultCategories> response) {
                mLisCat.add(response.body().getData());
                mCatAdapter = new CategoryAdapter(getActivity(), mLisCat);
                mHomeBinding.recyclerCat.setAdapter(mCatAdapter);
                Log.e("getCat", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<ResultCategories> call, Throwable t) {
                Log.e("errorgetCat", t.getMessage());

            }
        });*/

        ApiService apiService=Client.getRetrofit().create(ApiService.class);
        Call<HomeCatogray> call=apiService.getHomeCat();

        call.enqueue(new Callback<HomeCatogray>() {
            @Override
            public void onResponse(Call<HomeCatogray> call, Response<HomeCatogray> response) {
                mLisCat.add(response.body().getData());
                mCatAdapter = new CategoryAdapter(getActivity(), mLisCat);
                mHomeBinding.recyclerCat.setAdapter(mCatAdapter);
                Log.e("getCat", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<HomeCatogray> call, Throwable t) {
                Log.e("getCat", t.getMessage());

            }
        });

        mLisCat = new ArrayList<>();
        mCatAdapter = new CategoryAdapter(getActivity(), mLisCat);

        mHomeBinding.recyclerCat.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mHomeBinding.recyclerCat.setAdapter(mCatAdapter);
        mCatAdapter.notifyDataSetChanged();

    }

    public void showSlider() {
        ApiService apiService = Client.getRetrofit().create(ApiService.class);
        Call<ResultBannersHome> call = apiService.getBanners();

        call.enqueue(new Callback<ResultBannersHome>() {
            @Override
            public void onResponse(Call<ResultBannersHome> call, Response<ResultBannersHome> response) {
                Log.e("showMassage", response.body().getData().get(0).getImage());
                mSlideImage = response.body().getData();
                mSliderHomeAdapter = new SliderHomeAdapter(mSlideImage, getActivity());
                mHomeBinding.imageSlider.setSliderAdapter(mSliderHomeAdapter);
            }

            @Override
            public void onFailure(Call<ResultBannersHome> call, Throwable t) {
                Log.e("error showMassage", t.getMessage());

            }
        });

        mSlideImage = new ArrayList<>();
        mSliderHomeAdapter = new SliderHomeAdapter(mSlideImage, getActivity());
        mHomeBinding.imageSlider.setSliderAdapter(mSliderHomeAdapter);
        mHomeBinding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        mHomeBinding.imageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        mHomeBinding.imageSlider.startAutoCycle();
    }


    @Override
    public void onStart() {
        super.onStart();
        showSlider();
        showCategories();
    }
}

