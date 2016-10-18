package com.nishitadutta.auction.Objects;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nishita on 25-09-2016.
 */
public class Product {

    public final static String COLUMN_NAME="Name"; //Name of the product
    public final static String COLUMN_DESCRIPTION="Description";
    public final static String COLUMN_PRICE="Price";
    public final static String COLUMN_USERID="SellerId";
    public final static String COLUMN_USERNAME="SellerName";
    public final static String COLUMN_REQUEST="request";

    private String productId;
    private String Description;
    private String Name;
    private float Price;
    private String SellerId;
    private String SellerName;

    private Map<String, Object> map;
    private ArrayList<Reviews> reviews;

    public Product(){}

    private class Reviews {
        public String review;
        public String reviewer;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
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

  /*  public String getBidPrice() {
        return Price;
    }*/

  public float getPrice() {
      return Price;
  }

  /*  public void setBidPrice(String price){
        Price = price;
    }*/
  public void setPrice(float price) {
      Price = price;
  }

    public String getSellerId() {
        return SellerId;
    }

    public void setSellerId(String sellerId) {
        this.SellerId = sellerId;
    }

    public Product( String description, String name, float price) {
        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        String userName=FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        Description = description;
        Name = name;
        Price = price;
        map=new HashMap<String, Object>();
        map.put(COLUMN_DESCRIPTION, description);
        map.put(COLUMN_NAME, name);
        map.put(COLUMN_PRICE, price);
        map.put(COLUMN_USERID, userId);
        map.put(COLUMN_USERNAME,userName);
    }
}
