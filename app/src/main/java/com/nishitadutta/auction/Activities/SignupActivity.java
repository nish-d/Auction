package com.nishitadutta.auction.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.nishitadutta.auction.Custom.Constants;
import com.nishitadutta.auction.R;
import com.nishitadutta.auction.Utils.FirebaseManager;
import com.nishitadutta.auction.Utils.SharedPreferenceUtils;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_signup)
public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    @ViewById(R.id.progress_bar_signup)
    ProgressBar progressBarSignup;

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

    @ViewById(R.id.et_phone)
    EditText etPhone;

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

    public void onSignIn(View view) {
        Intent intent = new Intent(this, LoginActivity_.class);
        startActivity(intent);
        finish();

    }



    @Click(R.id.btn_signup)
    public void createAccount() {
        btnSignup.setText("");
        progressBarSignup.setVisibility(View.VISIBLE);

            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String confirmpassword= etConfirmPassword.getText().toString();
            final String name = etName.getText().toString();
            final String phone = etPhone.getText().toString();
            final SharedPreferenceUtils sharedPreferenceUtils = SharedPreferenceUtils.getInstance(this);
            //if (email != "" && password != "" && phone != "" && name != "")
            if(!(email.equals("")&&password.equals("")&&name.equals("")&&phone.equals("")
                    &&password.equals("")&&confirmpassword.equals(""))) {
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
                                    btnSignup.setText("Sign Up");
                                    progressBarSignup.setVisibility(View.INVISIBLE);


                                } else {
                                    sharedPreferenceUtils.setValue(Constants.SHAREDPREFERENCEPHONE, phone);
                                    FirebaseManager.addUser(name, phone);
                                    goToMain();

                                }
                            }
                        });
            }
            else {
                btnSignup.setText("Sign Up");
                progressBarSignup.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Please enter all the details", Toast.LENGTH_LONG).show();
            }

    }

    private void goToMain() {
        btnSignup.setText("Sign Up");
        progressBarSignup.setVisibility(View.INVISIBLE);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

