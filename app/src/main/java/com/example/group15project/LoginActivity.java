package com.example.group15project;


import android.content.Intent;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    static String currUser = null;

    DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    DataSnapshot userTree = null;
    String extractedUserEmail = null;
    String extractedUserPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //attaching the event handler
        Button loginButton = findViewById(R.id.loginB);
        loginButton.setOnClickListener(this);
        Button registerButton = findViewById(R.id.registerB);
        registerButton.setOnClickListener(this);

        databaseRead(realTimeDatabase);
    }

     public void databaseRead(DatabaseReference db) {
         //code for database initialization and accessing the credentials
         ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userTree = dataSnapshot.child("Users");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
         };
        db.addValueEventListener(userListener);
     }

    protected String getEmail() {
        EditText email = findViewById(R.id.email);
        return email.getText().toString().trim();
    }

    protected String getPassword() {
        EditText password = findViewById(R.id.password);
        return password.getText().toString().trim();
    }

    protected static boolean isEmptyEmail(String username) {
        return username.isEmpty();
    }

    protected static boolean isEmptyPassword(String password) {
        return password.isEmpty();
    }

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

    protected String formatEmail(String email) {
        char[] emailChars = email.toCharArray();
        String formattedEmail = getResources().getString(R.string.empty_string);
        Character period = '.';
        for(int i=0;i < emailChars.length; i++) {
            if (period.equals(emailChars[i])) {
                formattedEmail = email.replace(email.charAt(i), '-');
            }
        }
        return formattedEmail;
    }

    protected static boolean isValidEmailAddress(String emailAddress) {
        return PatternsCompat.EMAIL_ADDRESS.matcher(emailAddress).matches();
    }

    protected void switchToRegisterWindow() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    protected void switchToHomeWindow() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginB:
                String email = getEmail();
                String password = getPassword();
                String errorMessage = getResources().getString(R.string.empty_string);

                if (isEmptyEmail(email)) {
                    errorMessage = getResources().getString(R.string.empty_email).trim();
                }

                else {
                    if (!isValidEmailAddress(email)) {
                        errorMessage = getResources().getString(R.string.invalid_email).trim();
                    }

                    else {
                        if (isEmptyPassword(password)) {
                            errorMessage = getResources().getString(R.string.empty_password).trim();
                        }

                        else {
                            currUser = formatEmail(email);
                            if (userTree.hasChild(currUser)) {
                                extractedUserPassword = userTree.child(currUser).child("password").getValue(String.class);
                                extractedUserEmail = userTree.child(currUser).child("email").getValue(String.class);
                                if (!extractedUserPassword.equals(password)) {
                                    currUser = null;
                                    errorMessage = getResources().getString(R.string.wrong_password).trim();
                                }
                                else {currUser = formatEmail(email);}
                            }
                            else {
                                errorMessage = getResources().getString(R.string.no_account).trim();
                            }
                        }
                    }
                }

                if (errorMessage.isEmpty()) {
                    switchToHomeWindow();
                }

                else {
                    setStatusMessage(errorMessage);
                }
                break;

            case R.id.registerB:
                switchToRegisterWindow();
                break;

            default:
                break;
        }
    }




}
