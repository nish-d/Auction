package com.nishitadutta.auction.Fragments;

import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nishitadutta.auction.R;
import com.nishitadutta.auction.Utils.FirebaseManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Nishita on 10-10-2016.
 */
@EFragment(R.layout.fragment_myprofile)
public class MyProfileFragment extends Fragment {

    @ViewById(R.id.profile_pic)
    ImageView profilePic;

    @ViewById(R.id.et_username_profile)
    EditText etUsernameProfile;

    @ViewById(R.id.et_email_profile)
    TextView etEmailProfile;

    @ViewById(R.id.et_phone_profile)
    EditText etPhoneProfile;

    @ViewById(R.id.btn_edit)
    Button btnEdit;

    Boolean editMode = false;

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @AfterViews
    public void setUpLayout() {
        toggleEditable(false);
        etUsernameProfile.setText(firebaseUser.getDisplayName());
        etEmailProfile.setText(firebaseUser.getEmail());
        FirebaseManager.getPhone(etPhoneProfile);
        if (firebaseUser.getPhotoUrl() != null) {
            profilePic.setImageURI(firebaseUser.getPhotoUrl());
        }
    }

    @Click(R.id.btn_edit)
    public void setBtnEdit() {
        toggleEditable(!editMode);
        if (editMode) {
            String userName = etUsernameProfile.getText().toString();
            String phone = etPhoneProfile.getText().toString();
            FirebaseManager.addUser(userName, phone);
            editMode = false;
        }
    }

    public void toggleEditable(boolean b) {
        if (b) {
            btnEdit.setText("Save");
        } else {
            btnEdit.setText("Edit");
        }
        etUsernameProfile.setEnabled(b);
        etPhoneProfile.setEnabled(b);
        etUsernameProfile.setFocusableInTouchMode(b);
        etPhoneProfile.setFocusableInTouchMode(b);
    }

}
