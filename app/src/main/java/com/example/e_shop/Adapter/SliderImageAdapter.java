package com.example.e_shop.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.e_shop.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderImageAdapter extends SliderViewAdapter<SliderImageAdapter.SliderItemHolder> {
    int[] image;

    public SliderImageAdapter(int[] image) {
        this.image = image;
    }

    @Override
    public SliderItemHolder onCreateViewHolder(ViewGroup parent) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false);
        SliderItemHolder item_view = new SliderItemHolder(rowItem);
        return item_view;
    }

    @Override
    public void onBindViewHolder(SliderItemHolder viewHolder, int position) {
        viewHolder.slide_image.setImageResource(image[position]);

    }

    @Override
    public int getCount() {
        return image.length;
    }


    public class SliderItemHolder extends SliderViewAdapter.ViewHolder {
        ImageView slide_image;

        public SliderItemHolder(View itemView) {
            super(itemView);

            slide_image = itemView.findViewById(R.id.slide_image);
        }
    }
}
