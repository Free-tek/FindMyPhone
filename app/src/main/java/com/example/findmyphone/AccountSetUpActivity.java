package com.example.findmyphone;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daasuu.ahp.AnimateHorizontalProgressBar;

public class AccountSetUpActivity extends AppCompatActivity {

    private String mfirstName, mlastName, memail, mphone, maddress, mGender;
    private TextView header1, question, header2;
    private EditText password, confirmPassword, securityQuestion;
    private RadioGroup radioGroup;
    private RadioButton male, female;
    private Spinner security;
    private Button done;
    private ImageView complete, passwordImage, confirmPasswordImage, securityQuestionImage;
    private AnimateHorizontalProgressBar progressBar;

    private Typeface typefaceheader, typefaceslogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_setup_activity);

        Bundle bundle = getIntent().getExtras();
        mfirstName = bundle.getString("firstName");
        mlastName = bundle.getString("lastName");
        memail = bundle.getString("email");
        mphone = bundle.getString("phone");
        maddress = bundle.getString("address");

        initUi();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void initUi(){
        progressBar = (AnimateHorizontalProgressBar) findViewById(R.id.animate_progress_bar);
        progressBar.setMax(2000);
        progressBar.setProgress(700);
        progressBar.setMaxWithAnim(1000);
        progressBar.setProgressWithAnim(10);

        typefaceheader = Typeface.createFromAsset(getAssets(),"fonts/heading.ttf");
        typefaceslogan = Typeface.createFromAsset(getAssets(),"fonts/subheading.ttf");


        header1 = (TextView) findViewById(R.id.header1);
        header2 = (TextView) findViewById(R.id.header2);
        question = (TextView) findViewById(R.id.question);
        header1.setTypeface(typefaceheader);
        header2.setTypeface(typefaceheader);

        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        securityQuestion = (EditText) findViewById(R.id.securityQuestion);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);

        security = (Spinner) findViewById(R.id.security);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AccountSetUpActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.security_question));
        dataAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        security.setAdapter(dataAdapter);


        done = (Button) findViewById(R.id.done);

        passwordImage = (ImageView) findViewById(R.id.passwordImage);
        confirmPasswordImage = (ImageView) findViewById(R.id.confirmPasswordImage);
        securityQuestionImage = (ImageView) findViewById(R.id.securityQuestionImage);
        complete = (ImageView) findViewById(R.id.complete);


        complete.setVisibility(View.INVISIBLE);
        header2.setVisibility(View.INVISIBLE);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()){
                    complete.setVisibility(View.VISIBLE);
                    header2.setVisibility(View.VISIBLE);

                    header1.setVisibility(View.INVISIBLE);
                    question.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    password.setVisibility(View.INVISIBLE);
                    confirmPassword.setVisibility(View.INVISIBLE);
                    securityQuestion.setVisibility(View.INVISIBLE);
                    radioGroup.setVisibility(View.INVISIBLE);
                    male.setVisibility(View.INVISIBLE);
                    female.setVisibility(View.INVISIBLE);
                    security.setVisibility(View.INVISIBLE);
                    done.setVisibility(View.INVISIBLE);
                    passwordImage.setVisibility(View.INVISIBLE);
                    confirmPasswordImage.setVisibility(View.INVISIBLE);
                    securityQuestionImage.setVisibility(View.INVISIBLE);

                    Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
                    complete.startAnimation(rotate);


                    String $password = String.valueOf(password.getText());
                    String $gender = getSex();
                    String $answer = String.valueOf(securityQuestion.getText());



                }else{
                    Toast.makeText(AccountSetUpActivity.this, "Please check the error in the form", Toast.LENGTH_SHORT ).show();
                }
            }
        });

        security.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String $question = parent.getItemAtPosition(position).toString();
                question.setText($question);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private String getSex() {
        if (female.isChecked()) {
            mGender = "female";
        } else if (male.isChecked()) {
            mGender = "male";
        }
        return mGender;
    }


    private boolean validateForm() {
        boolean valid = true;

        String $password = password.getText().toString();
        if (TextUtils.isEmpty($password)) {
            password.setError("Required.",getResources().getDrawable(R.drawable.error));
            valid = false;
        } else {
            password.setError(null);
        }

        String $confirmPassword = confirmPassword.getText().toString();
        if (TextUtils.isEmpty($confirmPassword)) {
            confirmPassword.setError("Required.", getResources().getDrawable(R.drawable.error));
            valid = false;
        } else {
            confirmPassword.setError(null);
        }

        String $securityQuestion = securityQuestion.getText().toString();
        if (TextUtils.isEmpty($securityQuestion)) {
            securityQuestion.setError("Required.", getResources().getDrawable(R.drawable.error));
            valid = false;
        } else {
            securityQuestion.setError(null);
        }

        if(!$password.equals($confirmPassword)){
            Toast.makeText(AccountSetUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            valid = false;
        }else{
            password.setError(null);
        }

        return valid;
    }
}
