package com.nishitadutta.auction.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nishita on 25-09-2016.
 */
public class Product {

    private String productId;
    private String Description;
    private String Name;
    private float Price;
    private String userID;

    private Map<String, String> map;
    private ArrayList<Reviews> reviews;


    private class Reviews {
        public String review;
        public String reviewer;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getProductId() {

        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Product( String description, String name, float price) {
        Description = description;
        Name = name;
        Price = price;
        map=new HashMap<>();
        map.put("Description", description);
        map.put("Name", name);
        map.put("Price", String.valueOf(price));

    }
}
