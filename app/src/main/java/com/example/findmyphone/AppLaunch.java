package com.example.findmyphone;

import android.app.Application;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AppLaunch extends AppCompatActivity {

    private TextView header, slogan, header2;
    private ImageView background, icon, icon2, forward, firstNameImage, lastNameImage, emailImage, phoneImage, addressImage;
    private EditText firstName, lastName, email, phone, address;

    private Typeface typefaceheader, typefaceslogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_launch);

        initUi();
    }

    private void initUi() {

        typefaceheader = Typeface.createFromAsset(getAssets(),"fonts/heading.ttf");
        typefaceslogan = Typeface.createFromAsset(getAssets(),"fonts/subheading.ttf");


        header = (TextView) findViewById(R.id.header);
        slogan = (TextView) findViewById(R.id.slogan);
        header2 = (TextView) findViewById(R.id.header2);

        background = (ImageView) findViewById(R.id.background);
        icon = (ImageView) findViewById(R.id.icon);
        icon2 = (ImageView) findViewById(R.id.icon2);
        forward = (ImageView) findViewById(R.id.forward);
        firstNameImage = (ImageView) findViewById(R.id.firstNameImage);
        lastNameImage = (ImageView) findViewById(R.id.lastNameImage);
        emailImage = (ImageView) findViewById(R.id.emailImage);
        phoneImage = (ImageView) findViewById(R.id.phoneImage);
        addressImage = (ImageView) findViewById(R.id.addressImage);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);


        icon2.setVisibility(View.INVISIBLE);
        header2.setVisibility(View.INVISIBLE);
        forward.setVisibility(View.INVISIBLE);
        firstName.setVisibility(View.INVISIBLE);
        firstNameImage.setVisibility(View.INVISIBLE);
        lastName.setVisibility(View.INVISIBLE);
        lastNameImage.setVisibility(View.INVISIBLE);
        email.setVisibility(View.INVISIBLE);
        emailImage.setVisibility(View.INVISIBLE);
        phone.setVisibility(View.INVISIBLE);
        phoneImage.setVisibility(View.INVISIBLE);
        address.setVisibility(View.INVISIBLE);
        addressImage.setVisibility(View.INVISIBLE);


        header.setTypeface(typefaceheader);
        slogan.setTypeface(typefaceslogan);


        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                background.animate().translationY(-2200).setDuration(800).setStartDelay(300);
                icon.animate().translationY(-1800).setDuration(800).setStartDelay(300);
                header.setVisibility(View.INVISIBLE);
                slogan.setVisibility(View.INVISIBLE);



                new Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                icon2.setVisibility(View.VISIBLE);
                                forward.setVisibility(View.VISIBLE);
                                firstName.setVisibility(View.VISIBLE);
                                firstNameImage.setVisibility(View.VISIBLE);
                                lastName.setVisibility(View.VISIBLE);
                                lastNameImage.setVisibility(View.VISIBLE);
                                email.setVisibility(View.VISIBLE);
                                emailImage.setVisibility(View.VISIBLE);
                                phone.setVisibility(View.VISIBLE);
                                phoneImage.setVisibility(View.VISIBLE);
                                address.setVisibility(View.VISIBLE);
                                addressImage.setVisibility(View.VISIBLE);

                                header2.setVisibility(View.VISIBLE);
                                header2.setTypeface(typefaceheader);
                            }
                        },
                        800);

            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateForm()){
                    Toast.makeText(AppLaunch.this, "Please check the error in the form", Toast.LENGTH_SHORT).show();
                }else{
                    String $firstName = String.valueOf(firstName.getText());
                    String $lastName = String.valueOf(lastName.getText());
                    String $email = String.valueOf(email.getText());
                    String $phone = String.valueOf(phone.getText());
                    String $address = String.valueOf(address.getText());

                    Intent intent = new Intent(AppLaunch.this, AccountSetUpActivity.class);
                    intent.putExtra("firstName", $firstName);
                    intent.putExtra("lastName", $lastName);
                    intent.putExtra("email", $email);
                    intent.putExtra("phone", $phone);
                    intent.putExtra("address", $address);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                }
            }
        });

    }


    private boolean validateForm() {
        boolean valid = true;

        String $firstname = firstName.getText().toString();
        if (TextUtils.isEmpty($firstname)) {
            firstName.setError("Required.",getResources().getDrawable(R.drawable.error));
            valid = false;
        } else {
            firstName.setError(null);
        }

        String $lastName = lastName.getText().toString();
        if (TextUtils.isEmpty($lastName)) {
            lastName.setError("Required.", getResources().getDrawable(R.drawable.error));
            valid = false;
        } else {
            lastName.setError(null);
        }

        String $email = email.getText().toString();
        if (TextUtils.isEmpty($email)) {
            email.setError("Required.", getResources().getDrawable(R.drawable.error));
            valid = false;
        } else {
            email.setError(null);
        }

        String $phone = phone.getText().toString();
        if (TextUtils.isEmpty($phone)) {
            phone.setError("Required.", getResources().getDrawable(R.drawable.error));
            valid = false;
        } else {
            phone.setError(null);
        }

        String $address = address.getText().toString();
        if (TextUtils.isEmpty($address)) {
            address.setError("Required", getResources().getDrawable(R.drawable.error));
            valid = false;
        } else {
            address.setError(null);
        }

        return valid;
    }

}
