
package com.example.e_shop.pojo.HomePackage.CatogeryHome;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("banners")
    @Expose
    private List<Banner> banners = null;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("ad")
    @Expose
    private String ad;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

}
