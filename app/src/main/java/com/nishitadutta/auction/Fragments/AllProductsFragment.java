package com.nishitadutta.auction.Fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nishitadutta.auction.Objects.Product;
import com.nishitadutta.auction.R;
import com.nishitadutta.auction.Widgets.ProductViewHolder;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Nishita on 29-09-2016.
 */
@EFragment(R.layout.fragment_allproducts)
public class AllProductsFragment extends Fragment {
@ViewById(R.id.recycler_view_products)
    RecyclerView recyclerViewProducts;

    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

    @AfterInject
    public void setView(){
        FirebaseRecyclerAdapter firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Product, ProductViewHolder>
        (Product.class, R.layout.list_item_product,ProductViewHolder.class, mRef ){
            @Override
            protected void populateViewHolder(ProductViewHolder viewHolder, Product model, int position) {
                viewHolder.setAttributes(model);
            }
        };
    }

}
