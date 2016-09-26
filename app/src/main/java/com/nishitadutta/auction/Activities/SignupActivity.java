package com.nishitadutta.auction.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nishitadutta.auction.Activities.LoginActivity;
import com.nishitadutta.auction.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void onSignIn(View view)
    {
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    public void createAccount(View view)
    {
        /*mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful()) {

                    }
                }
            });*/
    }
}
