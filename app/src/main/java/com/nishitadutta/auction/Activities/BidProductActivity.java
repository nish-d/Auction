package com.nishitadutta.auction.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nishitadutta.auction.Objects.Product;
import com.nishitadutta.auction.Objects.Request;
import com.nishitadutta.auction.Utils.FirebaseManager;
import com.nishitadutta.auction.Custom.Constants;
import com.nishitadutta.auction.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import butterknife.BindView;

@EActivity(R.layout.activity_bid_product)
public class BidProductActivity extends AppCompatActivity {

    DatabaseReference mDatabaseReference;
    Request request;

    @ViewById(R.id.tv_description_request)
    TextView tvDescriptionRequest;

    @ViewById(R.id.tv_product_name_request)
    TextView tvProductNameRequest;

    @ViewById(R.id.tv_price_request)
    TextView tvPriceRequest;

    @ViewById(R.id.et_bid_price)
    EditText etBidPrice;

    @Click(R.id.btn_submit)
    void addRequest(){

        request=new Request(Float.parseFloat(etBidPrice.getText().toString()));
        FirebaseManager.addRequest(request, getApplicationContext());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_product);
        Toast.makeText(this, "Entered BidProductActivity", Toast.LENGTH_SHORT).show();

        Intent intent=getIntent();
        String productName=intent.getStringExtra(Constants.EXTRA_NAME);
        String price=intent.getStringExtra(Constants.EXTRA_PRICE);
        String description= intent.getStringExtra(Constants.EXTRA_DESCRIPTION);

        tvProductNameRequest.setText(productName);
        tvPriceRequest.setText(price);
        tvDescriptionRequest.setText(description);

        try{
            mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Request");
        }
        catch (Exception e){
            System.out.print(e);
        }

    }


    public void onSubmit(View view)
    {
        Toast.makeText(this, "Request Button Clicked", Toast.LENGTH_LONG).show();
    }
}
