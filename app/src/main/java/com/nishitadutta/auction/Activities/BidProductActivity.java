package com.nishitadutta.auction.Activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nishitadutta.auction.Objects.Request;
import com.nishitadutta.auction.Utils.FirebaseManager;
import com.nishitadutta.auction.Custom.Constants;
import com.nishitadutta.auction.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_bid_product)
public class BidProductActivity extends AppCompatActivity {

    private static final String TAG="BidProductActivity";
    DatabaseReference mDatabaseReference;
    String productId;
    Request request;

    @ViewById(R.id.tv_description_request)
    TextView tvDescriptionRequest;

    @ViewById(R.id.tv_product_name_request)
    TextView tvProductNameRequest;

    @ViewById(R.id.tv_price_request)
    TextView tvPriceRequest;

    @ViewById(R.id.et_bid_price)
    EditText etBidPrice;
@ViewById(R.id.toolbar_bid_product)
    Toolbar toolbar;

    @Click(R.id.btn_submit)
    void addRequest(){
        Log.e(TAG, "addRequest: " );
        request=new Request(Float.parseFloat(etBidPrice.getText().toString()),productId);
        Log.e(TAG, "addRequest: " + request.toString() );
        FirebaseManager.addRequest(request);
    }

    @AfterViews
    void setTextViews(){

        Intent intent=getIntent();
        String productName=intent.getStringExtra(Constants.EXTRA_NAME);
        String price=intent.getStringExtra(Constants.EXTRA_PRICE);
        String description= intent.getStringExtra(Constants.EXTRA_DESCRIPTION);
        productId= intent.getStringExtra(Constants.EXTRA_PRODUCTID);
        toolbar.setTitle("");
        tvProductNameRequest.setText(productName);
        tvPriceRequest.setText(price);
        tvDescriptionRequest.setText(description);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //Toast.makeText(this, productName+price+description+productId, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_product);


        try{
            mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Request");
        }
        catch (Exception e){
            System.out.print(e);
        }

    }

}
