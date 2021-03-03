package com.nishitadutta.auction.Fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.nishitadutta.auction.Global.MyApplication_;
import com.nishitadutta.auction.Objects.Product;
import com.nishitadutta.auction.R;
import com.nishitadutta.auction.Utils.FirebaseManager;
import com.nishitadutta.auction.Utils.ToastManager;
import com.nishitadutta.auction.Utils.ToastManager_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;



@EFragment(R.layout.fragment_add_product)
public class AddProductFragment extends Fragment {
    public static final String TAG="AddProductFragment";

    DatabaseReference mDatabaseReference;

    private Product product;
    @ViewById(R.id.et_product_name)
    EditText productName;

    @ViewById(R.id.product_description)
    EditText productDescription;

    @ViewById(R.id.product_price)
    EditText productPrice;

    @Click(R.id.button_add)
    void addProduct() {
        if(!(productName.getText().equals("")&&productDescription.getText().equals("")&&productPrice.equals("")))
            product=new Product(productDescription.getText().toString(),
                productName.getText().toString(),
                Float.parseFloat(productPrice.getText().toString()));
        else {
            ToastManager toastManager = ToastManager_.getInstance_(MyApplication_.getInstance());
            toastManager.show("Please enter all the values");
        }
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
