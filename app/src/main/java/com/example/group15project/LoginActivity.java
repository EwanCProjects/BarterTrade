package com.example.group15project;


import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.gms.tasks.Task;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static String WELCOME_MESSAGE = "ca.dal.csci3130.a2.welcome";

    FirebaseDatabase database = null;
    DatabaseReference userNameRef = null;
    DatabaseReference emailRef = null;
    EditText emailAddress = null; //global
    View username = null; //global
    //View password = null; //global




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailAddress = findViewById(R.id.email);
        //password = findViewById(R.id.password);
        username = findViewById(R.id.username);



        //attaching the event handler
        Button registerButton = findViewById(R.id.login);
        registerButton.setOnClickListener(this);

        //initiating the Firebase
        initializeDatabase();
    }

    protected void initializeDatabase(){
        database = FirebaseDatabase.getInstance(getResources().getString(R.string.FIREBASE_DB_URL));
        userNameRef = database.getReference("username");
        emailRef = database.getReference("email");
        //password = database.getReference("password");


    }


    protected String getUserName() {
        EditText userName = findViewById(R.id.username);
        return userName.getText().toString().trim();
    }


protected String getPassword() {
    EditText password = findViewById(R.id.username);
    return password.getText().toString().trim();
}
    protected String getEmailAddress() {
        EditText emailAddress = findViewById(R.id.email);
        return emailAddress.getText().toString().trim();
    }

    protected static boolean isEmptyUserName(String username) {
        return username.isEmpty();
    }

    protected boolean isAlphanumericUserName(String userName) {
        //your business logic goes here!
        return (userName.matches("[A-Za-z0-9]+"));
    }



     protected static boolean isEmptyPassword(String password) {
     return password.isEmpty();
     }

     protected static boolean isAlphanumericPassword(String password) {
     //your business logic goes here!
     return (password.matches("[A-Za-z0-9]+"));
     }

    protected static boolean isValidEmailAddress(String emailAddress) {
        //your business logic goes here!
        String setPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(setPattern);
        java.util.regex.Matcher m = p.matcher(emailAddress);
        return m.matches();
    }



    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

    protected Task<Void> saveUserNameToFirebase(String userName) {
        return userNameRef.setValue(userName);
    }

    protected Task<Void> saveEmailToFirebase(String emailAddress) {
        return emailRef.setValue(emailAddress);
    }




    @Override
    public void onClick(View view) {

       // String userName = getUserName();
        String email = getEmailAddress();
        String errorMessage = new String();

        if (isEmptyUserName(username)) {
            errorMessage = getResources().getString(R.string.EMPTY_USER_NAME);
        } else {
            if (!isAlphanumericUserName(username)) {
                errorMessage = getResources().getString(R.string.NON_ALPHA_NUMERIC_USER_NAME);
            }
        }

        if (!isValidEmailAddress(email)) {
            errorMessage = getResources().getString(R.string.INVALID_EMAIL_ADDRESS).trim();
        }

        if (errorMessage.isEmpty()) {
            //saveUserNameToFirebase(userName);
            saveEmailToFirebase(email);
            //switch2WelcomeWindow(userName, emailAddress);
        } else {
            setStatusMessage(errorMessage);
        }
    }




}