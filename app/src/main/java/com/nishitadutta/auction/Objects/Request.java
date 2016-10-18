package com.nishitadutta.auction.Objects;

import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by madhu_000 on 10/11/2016.
 */
public class Request {


    public final static String COLUMN_BIDPRICE = "bidPrice";
    public final static String COLUMN_PRODUCTID = "productId";
    public final static String COLUMN_USERID = "userId";
    public final static String COLUMN_USERNAME = "userName";

    private String productId;
    private float bidPrice;
    private String userID;
    private String requestId;
    private String userName;
    private Map<String, Object> map;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Request() {

    }

    public Request(float price, String productIdRequest) {


        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //String userName=FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        this.productId = productIdRequest;
        bidPrice = price;
        this.userID = userId;
        map = new HashMap<String, Object>();
        map.put(COLUMN_PRODUCTID, productIdRequest);
        map.put(COLUMN_BIDPRICE, bidPrice);
        map.put(COLUMN_USERID, userId);
        map.put(COLUMN_USERNAME, FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
    }

    public Request(Map<String, Object> map) {
        this.map = map;
    }

    public String getRequestId() {
        return requestId;
    }

    public String setRequestId(String requestId) {
        this.requestId = requestId;
        return this.requestId;
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

    public float getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(float bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String setProductId(String productId) {
        this.productId = productId;
        return productId;
    }

}
