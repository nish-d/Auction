package com.nishitadutta.auction.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nishitadutta.auction.Global.MyApplication_;
import com.nishitadutta.auction.Objects.Product;

/**
 * Created by Nishita on 25-09-2016.
 */
public class FirebaseManager {

    public static final String TABLE_PRODUCT="Product";
    public static final String TABLE_USER="User";
    public static final String TABLE_REQUEST="Request";
    public static ToastManager toastManager = ToastManager_.getInstance_(MyApplication_.getInstance());
    public final static DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

    public static void addProduct(Product product, final Context context){
        DatabaseReference ref;
        ref=databaseReference.child(TABLE_PRODUCT).push();
        ref.setValue(product.getMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //Toast.makeText(context, "Your product has been added succesfully", Toast.LENGTH_SHORT).show();
                toastManager.show("Your product has been added succesfully");
            }
        });
        product.setProductId(ref.getKey());
    }

}
