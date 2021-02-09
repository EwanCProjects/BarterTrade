package com.example.group15project;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static String WELCOME_MESSAGE = "ca.dal.csci3130.a2.welcome";

    FirebaseDatabase database = null;
    DatabaseReference userNameRef = null;
    DatabaseReference emailRef = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //attaching the event handler
        Button postButton = findViewById(R.id.postButton);
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        //initiating the Firebase
        initializeDatabase();
    }

    protected void initializeDatabase(){
        this.emailRef=emailRef;
        this.userNameRef = userNameRef;
        FirebaseDatabase rootRefEmail = FirebaseDatabase.getInstance(String.valueOf(emailRef));
        FirebaseDatabase rootRefUser = FirebaseDatabase.getInstance(String.valueOf(userNameRef));
    }


    protected String getUserName() {
        EditText userName = findViewById(R.id.userName);
        return userName.getText().toString().trim();
    }

    protected String getEmailAddress() {
        EditText emailAddress = findViewById(R.id.emailAddress);
        return emailAddress.getText().toString().trim();
    }

    protected boolean isEmptyUserName(String userName) {
        return userName.isEmpty();
    }

    protected boolean isAlphanumericUserName(String userName) {
        //your business logic goes here!
        return (userName.matches("[A-Za-z0-9]+"));
    }

    protected boolean isValidEmailAddress(String emailAddress) {
        //your business logic goes here!
        String setPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(setPattern);
        java.util.regex.Matcher m = p.matcher(emailAddress);
        return m.matches();
    }

    protected void switch2WelcomeWindow(String userName, String emailAddress) {
        //your business logic goes here!
        Toast.makeText(MainActivity.this,"Firebase connection success", Toast.LENGTH_LONG).show();

    }

    protected void saveUserNameToFirebase(String userName) {
        //save user name to Firebase
        //DatabaseReference userNameRef = null;
        userNameRef.child(userName).setValue(userName);
    }

    protected void saveEmailToFirebase(String emailAddress) {
        //save email to Firebase
        userNameRef.child(emailAddress).setValue(emailAddress);
    }

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

    @Override
    public void onClick(View view) {

        String userName = getUserName();
        String emailAddress = getEmailAddress();
        String errorMessage = new String();

        boolean usernameAlphaNumb = isAlphanumericUserName(userName);
        boolean usernameEmpty = isEmptyUserName(userName);
        boolean emailOK = isValidEmailAddress(emailAddress);

        if (usernameEmpty) {
            errorMessage = getResources().getString(R.string.empty_string);
            setStatusMessage(errorMessage);
        }

        //check for valid user name
        if (!usernameAlphaNumb) {
            setStatusMessage(errorMessage);
            TextView statusLabel = findViewById(R.id.statusLabel);
            errorMessage = getResources().getString(R.string.NON_ALPHA_NUMERIC_USER_NAME); // usenmane NOT a.n
            statusLabel.setText(errorMessage);
        }

        //check for valid email address
        if (!emailOK) {
            setStatusMessage(errorMessage);
            TextView statusLabel = findViewById(R.id.statusLabel);
            errorMessage = getResources().getString(R.string.INVALID_EMAIL_ADDRESS); // usenmane NOT a.n
            statusLabel.setText(errorMessage);
        }

        if (errorMessage.isEmpty()) {
            //no errors were found!
            //much of the business logic goes here!
            TextView statusLabel = findViewById(R.id.statusLabel);
            errorMessage = getResources().getString(R.string.empty_string); // if is alphanumeric
            statusLabel.setText(errorMessage);



        } else {
            setStatusMessage(errorMessage);
        }
    }


}
