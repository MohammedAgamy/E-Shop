package com.example.e_shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.e_shop.R;
import com.example.e_shop.pojo.HomePackage.Datum;
import com.example.e_shop.pojo.HomePackage.ResultBannersHome;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderHomeAdapter extends SliderViewAdapter<SliderHomeAdapter.SliderItemHolder> {
    List<Datum> mList;
    Context mContext;

    public SliderHomeAdapter(List<Datum> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public SliderItemHolder onCreateViewHolder(ViewGroup parent) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false);
        SliderItemHolder item_view = new SliderItemHolder(rowItem);
        return item_view;
    }

    @Override
    public void onBindViewHolder(SliderItemHolder viewHolder, int position) {
        Glide.with(mContext).load(mList.get(position).getImage()).into(viewHolder.slide_image);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    protected class SliderItemHolder extends SliderViewAdapter.ViewHolder {
        ImageView slide_image;

        public SliderItemHolder(View itemView) {
            super(itemView);
            slide_image = itemView.findViewById(R.id.slide_image);
        }
    }

}