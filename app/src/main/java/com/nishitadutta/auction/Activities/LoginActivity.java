package com.nishitadutta.auction.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.nishitadutta.auction.R;

public class LoginActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    EditText usernameEt, passwordEt;
    String email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        usernameEt= (EditText) findViewById(R.id.et_email);
        passwordEt= (EditText) findViewById(R.id.et_password);

        email=usernameEt.getText().toString();
        password=passwordEt.getText().toString();

       /* mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Sign in", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("Sign out", "onAuthStateChanged:signed_out");
                }
            }
        };*/



    }

    /*@Override
    public void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }*/

public void onSignup(View view)
{
    Intent intent=new Intent(this, SignupActivity.class);
    startActivity(intent);


}
}
