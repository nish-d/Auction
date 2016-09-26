package com.nishitadutta.auction.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.nishitadutta.auction.Activities.LoginActivity;
import com.nishitadutta.auction.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import butterknife.OnClick;
@EActivity(R.layout.activity_signup)
public class SignupActivity extends AppCompatActivity {

    private static final String TAG="SignupActivity";
    @ViewById(R.id.et_name)
    EditText etName;

    @ViewById(R.id.btn_signup)
    Button btnSignup;

    @ViewById(R.id.et_email)
    EditText etEmail;

    @ViewById(R.id.et_password)
    EditText etPassword;

    @ViewById(R.id.et_confirmpassword)
    EditText etConfirmPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    public void onSignIn(View view)
    {
        Intent intent=new Intent(this, LoginActivity_.class);
        startActivity(intent);
        finish();

    }

    @Click(R.id.btn_signup)
    public void createAccount()
    {
        //TODO: show progress bar
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.e(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful()) {
                        FirebaseAuthException authException = (FirebaseAuthException) task.getException();
                        String errorCode = authException.getErrorCode();
                        Log.e(TAG, task.getException().toString() + "\n" + errorCode);

                    }
                    else{
                       // goToMain();
                        //TODO: hide progress bar
                    }
                }
            });
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
