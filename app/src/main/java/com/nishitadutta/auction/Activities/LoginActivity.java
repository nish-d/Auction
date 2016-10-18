package com.nishitadutta.auction.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nishitadutta.auction.Global.MyApplication_;
import com.nishitadutta.auction.R;
import com.nishitadutta.auction.Utils.ToastManager;
import com.nishitadutta.auction.Utils.ToastManager_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @ViewById(R.id.progress_bar_login)
    ProgressBar progressBarLogin;

    @ViewById(R.id.et_email)
    EditText usernameEt;

    @ViewById(R.id.et_password)
    EditText passwordEt;

    @ViewById(R.id.btn_login)
    Button btnLogin;
    ToastManager toastManager = ToastManager_.getInstance_(MyApplication_.getInstance());
    String email, password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Click(R.id.btn_login)
    void setBtnLogin() {
        btnLogin.setText("");
        progressBarLogin.setVisibility(View.VISIBLE);
        email = usernameEt.getText().toString();
        password = passwordEt.getText().toString();

        if(!(email.equals("")&&password.equals("")))
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {
                            btnLogin.setText("Login");
                            progressBarLogin.setVisibility(View.INVISIBLE);
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            toastManager.show("Authentication failed");
                        }

                        // ...
                    }
                });

        else{
            btnLogin.setText("Login");
            progressBarLogin.setVisibility(View.INVISIBLE);
            Toast.makeText(this,"Please enter all the details!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Sign in", "onAuthStateChanged:signed_in:" + user.getUid());
                    goToMain();
                } else {
                    // User is signed out
                    Log.d("Sign out", "onAuthStateChanged:signed_out");
                }
            }
        };


    }

    private void goToMain() {

        btnLogin.setText("Login");
        progressBarLogin.setVisibility(View.INVISIBLE);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void onSignup(View view) {
        Intent intent = new Intent(this, SignupActivity_.class);
        startActivity(intent);
        finish();
    }
}
