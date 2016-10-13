package com.nishitadutta.auction.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nishitadutta.auction.Activities.BidProductActivity;
import com.nishitadutta.auction.Custom.Constants;
import com.nishitadutta.auction.Objects.Product;
import com.nishitadutta.auction.R;
import com.nishitadutta.auction.Widgets.ProductViewHolder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Nishita on 29-09-2016.
 */
@EFragment(R.layout.fragment_allproducts)
public class AllProductsFragment extends Fragment {
    public static final String TAG = "AllProductsFragment";
    @ViewById(R.id.recycler_view_products)
    RecyclerView recyclerViewProducts;

    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

    @AfterViews
    public void setView() {
        FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Product, ProductViewHolder>
                (Product.class, R.layout.list_item_product, ProductViewHolder.class, mRef.child("Product")) {
            @Override
            protected void populateViewHolder(ProductViewHolder viewHolder, Product model, int position) {
                String productId=this.getRef(position).getKey();
                Log.e(TAG, "populateViewHolder: " + productId );
                model.setProductId(productId);
                viewHolder.setAttributes(model);

            }

        };
        recyclerViewProducts.setAdapter(firebaseRecyclerAdapter);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d(TAG, "setView: " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
    }

}

