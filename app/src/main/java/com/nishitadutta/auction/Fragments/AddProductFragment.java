package com.nishitadutta.auction.Fragments;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.nishitadutta.auction.Objects.Product;
import com.nishitadutta.auction.R;
import com.nishitadutta.auction.Utils.FirebaseManager;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;



@EFragment(R.layout.fragment_add_product)
public class AddProductFragment extends Fragment {
    public static final String TAG="AddProductFragment";

    DatabaseReference mDatabaseReference;

    private Product product;
    @ViewById(R.id.product_name)
    MaterialEditText productName;

    @ViewById(R.id.product_description)
    MaterialEditText productDescription;

    @ViewById(R.id.product_price)
    MaterialEditText productPrice;

    @Click(R.id.button_add)
    void addProduct() {
        product=new Product(productDescription.getText().toString(),
                productName.getText().toString(),
                Float.parseFloat(productPrice.getText().toString()));
        FirebaseManager.addProduct(product);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Product");
        return null;
    }


}
