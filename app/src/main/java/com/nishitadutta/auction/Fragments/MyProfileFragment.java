package com.nishitadutta.auction.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mvc.imagepicker.ImagePicker;
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

    private static final int REQUEST_CODE_CAMERA=1;

    Bitmap bmp;
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


    @Click(R.id.profile_pic)
    public void onProfileImage()
    {
        //ImagePicker.setMinQuality(600,600);
        //ImagePicker.pickImage(getActivity(), "Select your image:");
        Toast.makeText(getActivity(), "Image clicked", Toast.LENGTH_LONG).show();
        Intent intent=new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        /*bmp = ImagePicker.getImageFromResult(getActivity(), requestCode, resultCode, data);
        profilePic.setImageBitmap(bmp);*/

        if(requestCode==REQUEST_CODE_CAMERA)
            if(resultCode==Activity.RESULT_OK)
            {
                //bmp = ImagePicker.getImageFromResult(getActivity(), requestCode, resultCode, data);
                //profilePic.setImageBitmap(bmp);
                bmp = (Bitmap) data.getExtras().get("data");
                profilePic.setImageBitmap(bmp);
            }

        super.onActivityResult(requestCode, resultCode, data);
    }

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
