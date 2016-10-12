package com.nishitadutta.auction.Objects;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by madhu_000 on 10/11/2016.
 */
public class Request {


    public final static String COLUMN_BIDPRICE="bidPrice";
    public final static String COLUMN_PRODUCTID="productId";
    public final static String COLUMN_USERID="userId";


    private String productId;
    private float Price;
    private String userID;
    private String requestId;


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    private Map<String, Object> map;

    public Request(){}

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String getProductId() {
        return productId;
    }

    public float getPrice() {
        return Price;
    }

    public String getUserID() {
        return userID;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Request(float price) {


        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        //String userName=FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        Price = price;
        map=new HashMap<String, Object>();

        map.put(COLUMN_PRODUCTID, productId);
        map.put(COLUMN_BIDPRICE,Price);
        map.put(COLUMN_USERID, userId);
    }

    public Request(Map<String, Object> map) {
        this.map = map;
    }

}
