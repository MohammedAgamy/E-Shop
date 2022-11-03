package com.example.e_shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_shop.R;
import com.example.e_shop.pojo.HomePackage.Categories.DatumCategory;
import com.example.e_shop.pojo.HomePackage.CatogeryHome.Data;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryItem> {
    Context mContext;
    List<com.example.e_shop.pojo.HomePackage.CatogeryHome.Data> mList;

    public CategoryAdapter(Context mContext, List<Data> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public CategoryItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.catogory_item, parent, false);
        CategoryItem item_view = new CategoryItem(rowItem);
        return item_view;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItem holder, int position) {
        Glide.with(mContext).load(mList.get(position).getProducts().get(position).getImage()).into(holder.mImageCat);
        //holder.mName_Cat.setText(mList.get(position).getData().get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class CategoryItem extends RecyclerView.ViewHolder {
        ImageView mImageCat;
        TextView mName_Cat;

        public CategoryItem(@NonNull View itemView) {
            super(itemView);

            mName_Cat = itemView.findViewById(R.id.name_cat);
            mImageCat = itemView.findViewById(R.id.cat_image);

        }
    }
}
