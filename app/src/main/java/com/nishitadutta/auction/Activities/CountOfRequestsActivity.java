package com.nishitadutta.auction.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nishitadutta.auction.Custom.Constants;
import com.nishitadutta.auction.Objects.Request;
import com.nishitadutta.auction.R;
import com.nishitadutta.auction.Utils.FirebaseManager;
import com.nishitadutta.auction.Widgets.CountofRequestsViewHolder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_count_of_requests)
public class CountOfRequestsActivity extends AppCompatActivity {

    public static final String TAG = "CountOfRequestsActivity";
    public static final String TABLE_PRODUCT = "Product";
    public static final String COLUMN_REQUEST = "request";
    public static final String TABLE_REQUEST = "Request";
    @ViewById(R.id.recycler_count_of_requests)
    RecyclerView recyclerViewProducts;
    @ViewById(R.id.tv_product_name_count)
    TextView tvProductName;
    @ViewById(R.id.tv_price_count)
    TextView tvPrice;
    String productId, productName, price;
    @ViewById(R.id.toolbar_c_o_r)
    Toolbar toolbar;

    @AfterViews
    void setLayout() {
        Intent intent = getIntent();
        productId = intent.getStringExtra(Constants.EXTRA_PRODUCTID);
        productName = intent.getStringExtra(Constants.EXTRA_NAME);
        price = intent.getStringExtra(Constants.EXTRA_PRICE);

        tvProductName.setText(productName);
        tvPrice.setText(price);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        final DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        final ArrayList<String> requestKeys = new ArrayList<>();

        mRef.child(FirebaseManager.TABLE_PRODUCT)
                .child(productId)
                .child(FirebaseManager.COLUMN_REQUEST)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            final String requestId = data.getKey();
                            //requestId = requestId.replace("=true", "");
                            final DatabaseReference requestRef = mRef.child(FirebaseManager.TABLE_REQUEST)
                                    .child(requestId);
                            requestKeys.add(requestId);

                            Log.e(TAG, "onDataChange: RequestId: " + requestId);
                        }

                        Query query = mRef.child(TABLE_REQUEST);

                        FirebaseRecyclerAdapter firebaseRecyclerAdapter =
                                new FirebaseRecyclerAdapter<Request, CountofRequestsViewHolder>
                                        (Request.class, R.layout.list_item_count_of_requests,
                                                CountofRequestsViewHolder.class, query) {

                                    @Override
                                    protected Request parseSnapshot(DataSnapshot snapshot) {
                                        Log.e(TAG, "parseSnapshot: " + snapshot.toString());
                                        if (requestKeys.indexOf(snapshot.getKey()) != -1)
                                            return snapshot.getValue(Request.class);
                                            // return super.parseSnapshot(snapshot);
                                        else
                                            return null;
                                    }

                                    @Override
                                    protected void populateViewHolder(CountofRequestsViewHolder viewHolder, Request model, int position) {
                                        // model.setRequestId(this.getRef(position).getKey());

                                        viewHolder.setAttributes(model);
                                    }
                                };

                        recyclerViewProducts.setAdapter(firebaseRecyclerAdapter);
                        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }
}
