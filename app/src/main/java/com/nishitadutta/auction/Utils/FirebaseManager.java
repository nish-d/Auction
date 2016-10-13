package com.nishitadutta.auction.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nishitadutta.auction.Global.MyApplication_;
import com.nishitadutta.auction.Objects.Product;
import com.nishitadutta.auction.Objects.Request;
import com.nishitadutta.auction.Objects.User;

import org.androidannotations.annotations.Click;

/**
 * Created by Nishita on 25-09-2016.
 */
public class FirebaseManager {

    public static final String TAG="FirebaseManager";
    public static final String TABLE_PRODUCT = "Product";
    public static final String TABLE_USER = "User";
    public static final String TABLE_REQUEST = "Request";
    public final static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    public static final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    public static ToastManager toastManager = ToastManager_.getInstance_(MyApplication_.getInstance());

    public static void addProduct(final Product product, final Context context){
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

    public static void addRequest(Request request, final Context context){


        DatabaseReference ref;
        ref=databaseReference.child(TABLE_REQUEST).push();
        ref.setValue(request.getMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                toastManager.show("You have bid your price");
            }
        });
        request.setRequestId(ref.getKey());
       /* ref = databaseReference.child(TABLE_USER).child(firebaseUser.getUid())
                .child("products").child(product.getProductId());
        ref.setValue("true");*/
    }

    public static void addUser(String userName, String phone) {
        DatabaseReference ref = databaseReference.child(TABLE_USER).child(firebaseUser.getUid());
        ref.setValue(new User(firebaseUser.getUid(), phone, userName));
        firebaseUser.updateProfile(new UserProfileChangeRequest
                .Builder()
                .setDisplayName(userName).build())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                toastManager.show("Profile updated successfully");
            }
        });
    }

    public static void getPhone(final EditText et) {
        // User user = new User(firebaseUser.getUid(),"9838572377",firebaseUser.getDisplayName());
        DatabaseReference ref = databaseReference.child(TABLE_USER).child(firebaseUser.getUid());
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Log.e(TAG, "onDataChange: " + user.getPhone() );
                et.setText(user.getPhone());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

}
