package com.nishitadutta.auction.Fragments;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
        final FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Product, ProductViewHolder>
                (Product.class, R.layout.list_item_product, ProductViewHolder.class, mRef.child("Product")) {

            @Override
            protected void populateViewHolder(ProductViewHolder viewHolder, Product model, int position) {
                String id =this.getRef(position).getKey();
                model.setProductId(id);
                Log.e(TAG, "populateViewHolder:"+id );
                viewHolder.setAttributes(model);
            }

        };
        recyclerViewProducts.setAdapter(firebaseRecyclerAdapter);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d(TAG, "setView: " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
    }

}

