package com.nishitadutta.auction.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.nishitadutta.auction.Global.MyApplication_;
import com.nishitadutta.auction.Objects.Request;
import com.nishitadutta.auction.Utils.ToastManager_;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.nishitadutta.auction.Custom.Constants;
import com.nishitadutta.auction.Objects.Product;
import com.nishitadutta.auction.R;
import com.nishitadutta.auction.Widgets.CountofRequestsViewHolder;
import com.nishitadutta.auction.Widgets.ProductViewHolder;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import butterknife.BindView;

public class CountOfRequestsActivity extends AppCompatActivity {


    public static final String TAG = "CountOfRequestsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_of_requests);
        RecyclerView recyclerViewProducts= (RecyclerView) findViewById(R.id.recycler_count_of_requests);
        final TextView tvProductName= (TextView) findViewById(R.id.tv_product_name_count);
        final TextView tvPrice= (TextView) findViewById(R.id.tv_price_count);

        Intent intent=getIntent();
        final String productId= intent.getStringExtra(Constants.EXTRA_PRODUCTID);
        final String productName=intent.getStringExtra(Constants.EXTRA_NAME);
        final String price= intent.getStringExtra(Constants.EXTRA_PRICE);

        tvProductName.setText(productName);
        tvPrice.setText(price);
        Log.e(TAG, "EXTRA PRODUCTID "+productId );

        final DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

        final Query query = mRef.child("Request").orderByChild("productId").equalTo(productId);

            final FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Request, CountofRequestsViewHolder>
                    (Request.class, R.layout.list_item_count_of_requests, CountofRequestsViewHolder.class,
                            query) {

                float bidPrice;

                @Override
                protected void populateViewHolder(CountofRequestsViewHolder viewHolder, Request model, int position) {
                    // id =this.getRef(position).getKey();

                    model.setProductId(productId);


                    mRef.orderByChild("auction-action-e8d91/Request/bidPrice").
                            addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                }

                                @Override
                                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                                    String data=dataSnapshot.getValue().toString();
                                    Log.e(TAG, "onChildChanged: "+data);
                                }

                                @Override
                                public void onChildRemoved(DataSnapshot dataSnapshot) {

                                }

                                @Override
                                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                    model.setPrice(bidPrice);
                    Log.e(TAG, "countofRequestsViewHolder" + productId+" " + bidPrice);
                    viewHolder.setAttributes(model);
                }
            };
            recyclerViewProducts.setAdapter(firebaseRecyclerAdapter);
            recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
            Log.d(TAG, "setView: " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());


    }
}
