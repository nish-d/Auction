package com.nishitadutta.auction.Fragments;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.nishitadutta.auction.Objects.Product;
import com.nishitadutta.auction.R;
import com.nishitadutta.auction.Widgets.MyRequestsViewHolder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by madhu_000 on 10/15/2016.
 */
@EFragment(R.layout.fragment_my_requests)
public class MyRequestsFragment extends Fragment {


    public static final String TAG = "MyProductsFragment";
    @ViewById(R.id.recycler_view_my_requests)
    RecyclerView recyclerViewProducts;

    Query ref;
    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

    @AfterViews
    public void setView() {

        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        ref=mRef.child("User").child("Requests");
        ref=  mRef.child("Request").child("requestId").child("userId").equalTo(uid).orderByChild("productId");
        Query query = mRef.child("Product").orderByChild("productId").equalTo(String.valueOf(ref.getRef()));

        FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Product, MyRequestsViewHolder>
                (Product.class, R.layout.list_item_my_request, MyRequestsViewHolder.class, query) {

            @Override
            protected void populateViewHolder(MyRequestsViewHolder viewHolder, Product model, int position) {
                String id =this.getRef(position).getKey();
                model.setProductId(id);

                Log.e(TAG, "setAttributes: " + model.getProductId() + model.getName() +
                        model.getPrice() + model.getDescription());
                viewHolder.setAttributes(model);
            }

        };
        recyclerViewProducts.setAdapter(firebaseRecyclerAdapter);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d(TAG, "setView: " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
    }

}
