package com.nishitadutta.auction.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.nishitadutta.auction.Global.MyApplication_;
import com.nishitadutta.auction.Objects.User;
import com.mvc.imagepicker.ImagePicker;
import com.nishitadutta.auction.Custom.Constants;
import com.nishitadutta.auction.R;
import com.nishitadutta.auction.Utils.FirebaseManager;
import com.nishitadutta.auction.Utils.SharedPreferenceUtils;
import com.nishitadutta.auction.Utils.ToastManager;
import com.nishitadutta.auction.Utils.ToastManager_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import static com.nishitadutta.auction.Utils.FirebaseManager.TABLE_USER;

import java.io.ByteArrayOutputStream;

/**
 * Created by Nishita on 10-10-2016.
 */
@EFragment(R.layout.fragment_myprofile)
public class MyProfileFragment extends Fragment {


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    Bitmap bmp;

    public static final String TAG = "MyprofileFragment";
    private static final int REQUEST_CODE_CAMERA = 1;


    @ViewById(R.id.et_phone_profile)
    EditText etPhoneProfile;
    @ViewById(R.id.profile_pic)
    ImageView profilePic;
    @ViewById(R.id.et_username_profile)
    EditText etUsernameProfile;
    @ViewById(R.id.et_email_profile)
    TextView etEmailProfile;
    @ViewById(R.id.btn_edit)
    Button btnEdit;

    Boolean editMode = false;
    //firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    ToastManager toastManager = ToastManager_.getInstance_(MyApplication_.getInstance());

    public void getPhone() {
        DatabaseReference databaseReference = FirebaseManager.databaseReference;
        FirebaseUser firebaseUser = FirebaseManager.firebaseUser;
        DatabaseReference ref = databaseReference.child(TABLE_USER).child(firebaseUser.getUid());
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Log.e(TAG, "onDataChange: " + user.getPhone());
                etPhoneProfile.setText(user.getPhone());
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        ref.addValueEventListener(valueEventListener);
    }

    @Click(R.id.profile_pic)
    public void onProfileImage()
    {
        Toast.makeText(getActivity(), "Image clicked", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        editor=sharedPreferences.edit();

        if(requestCode==REQUEST_CODE_CAMERA)
            if(resultCode==Activity.RESULT_OK)
            {
                bmp = (Bitmap) data.getExtras().get("data");
                profilePic.setImageBitmap(bmp);
                //editor.putString(Constants.SHAREDPREFERENCE, bmp.toString());
                //editor.commit();
            }
        editor.putString(Constants.SHAREDPREFERENCE, encodeTobase64(bmp));
       // super.onActivityResult(requestCode, resultCode, data);
    }

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @AfterViews
    public void setUpLayout() {
        toggleEditable(false);
        etUsernameProfile.setText(firebaseUser.getDisplayName());
        etEmailProfile.setText(firebaseUser.getEmail());
        etEmailProfile.setEnabled(false);
        etEmailProfile.setFocusable(false);
        getPhone();

        SharedPreferenceUtils sharedPreferenceUtils=SharedPreferenceUtils.getInstance(getContext());

        etPhoneProfile.setText(sharedPreferenceUtils.getStringValue(Constants.SHAREDPREFERENCEPHONE));
        etPhoneProfile.setEnabled(false);
        etPhoneProfile.setFocusable(false);

        if (firebaseUser.getPhotoUrl() != null) {
            profilePic.setImageURI(firebaseUser.getPhotoUrl());
        }
    }

    @Click(R.id.btn_edit)
    public void setBtnEdit() {
        toggleEditable(!editMode);
        if (editMode) {
            //if editMode is true and user clicks on save button, save the information
            String userName = etUsernameProfile.getText().toString();
            String phone = etPhoneProfile.getText().toString();
            FirebaseManager.addUser(userName, phone);
            editMode = false;

            if (phone.equals("") || userName.equals("")) {
                toastManager.show("Phone or UserName cannot be left empty");
                toggleEditable(true);       //everything should be enabled
            } else {
                btnEdit.setText("Edit");
                FirebaseManager.addUser(userName, phone);
                editMode = false;
            }
        } else {
            //editmode is false and user clicks on edit button
            btnEdit.setText("Save");
            etUsernameProfile.setText("");
            etPhoneProfile.setText("");
            editMode = true;
        }
    }


    public void toggleEditable(boolean b) {
        etUsernameProfile.setEnabled(b);
        etPhoneProfile.setEnabled(b);
        etUsernameProfile.setFocusableInTouchMode(b);
        etPhoneProfile.setFocusableInTouchMode(b);

    }

    @AfterViews
    public void onSharedPreference()
    {
        sharedPreferences=this.getActivity().getSharedPreferences(Constants.SHAREDPREFERENCE,
                Context.MODE_PRIVATE);

        String committed =sharedPreferences.getString(Constants.SHAREDPREFERENCE, null);

        if(committed==null)
        {
            profilePic.setImageResource(R.drawable.header_blank_user);
        }
        else{
            Bitmap bmp=decodeBase64(committed);
            profilePic.setImageBitmap(bmp);
        }
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

}
