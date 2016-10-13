package com.nishitadutta.auction.Fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.nishitadutta.auction.Objects.Product;
import com.nishitadutta.auction.R;
import com.nishitadutta.auction.Widgets.ProductViewHolder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Nishita on 10-10-2016.
 */

@EFragment(R.layout.fragment_allproducts)
public class MyProductsFragment extends Fragment {

    public static final String TAG = "MyProductsFragment";
    @ViewById(R.id.recycler_view_products)
    RecyclerView recyclerViewProducts;

    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

    @AfterViews
    public void setView() {

        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query = mRef.child("Product").orderByChild("SellerId").equalTo(uid);
        FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Product, ProductViewHolder>
                (Product.class, R.layout.list_item_product, ProductViewHolder.class, query) {

            @Override
            protected void populateViewHolder(ProductViewHolder viewHolder, Product model, int position) {

                viewHolder.setAttributes(model);
            }


        };
        recyclerViewProducts.setAdapter(firebaseRecyclerAdapter);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d(TAG, "setView: " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
    }
}
