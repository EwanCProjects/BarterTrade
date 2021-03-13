package com.example.group15project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import java.util.Arrays;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    static String currUser = null;

    DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    DataSnapshot userTree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button postButton = findViewById(R.id.signUpButton);
        postButton.setOnClickListener(this);

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

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

    protected String getFirstName() {
        EditText firstName = findViewById(R.id.editTextFirstName);
        return firstName.getText().toString().trim();
    }

    protected String getLastName() {
        EditText lastName = findViewById(R.id.editTextLastName);
        return lastName.getText().toString().trim();
    }

    protected String getEmailAddress() {
        EditText emailAddress = findViewById(R.id.editTextEmailAddress);
        return emailAddress.getText().toString().trim();
    }

    protected String getPassword() {
        EditText password = findViewById(R.id.editTextPassword);
        return password.getText().toString().trim();
    }

    protected String getPasswordConfirmation() {
        EditText passwordConfirmation = findViewById(R.id.editTextConfirmPassword);
        return passwordConfirmation.getText().toString().trim();
    }

    protected boolean isEmptyFirstName(String firstName) {
        return firstName.isEmpty();
    }

    protected boolean isEmptyLastName(String lastName) {
        return lastName.isEmpty();
    }

    protected boolean isEmptyEmail(String email) {
        return email.isEmpty();
    }

    protected boolean isEmptyPassword(String password) {
        return password.isEmpty();
    }

    protected boolean isValidEmailAddress(String emailAddress) {
        return PatternsCompat.EMAIL_ADDRESS.matcher(emailAddress).matches();
    }

    protected boolean passwordsMatch(String password, String passwordConfirm) {
        return (password.equals(passwordConfirm));
    }

    protected User createUser(String firstName, String lastName, String email, String password) {
        return new User(firstName, lastName, email, password);
    }

    protected void addUserToFirebase(DatabaseReference mDatabase, User user, String email){
        mDatabase.child("Users").child(email).setValue(user);
    }

    protected void switchToHomeWindow() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
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

    @Override
    public void onClick(View view) {

        String firstName = getFirstName();
        String lastName = getLastName();
        String password = getPassword();
        String passwordConfirm = getPasswordConfirmation();
        String emailAddress = getEmailAddress();
        String errorMessage = getResources().getString(R.string.empty_string);

        if (userTree.hasChild(formatEmail(emailAddress))) {
            errorMessage = "Email already registered!";
        }

        if (!passwordsMatch(password, passwordConfirm)) {
            errorMessage = "Passwords do not match!";
        }

        if (isEmptyPassword(password)) {
            errorMessage = "Password field empty!";
        }

        if (!isValidEmailAddress(emailAddress)) {
            errorMessage = "Email is invalid!";
        }

        if (isEmptyEmail(emailAddress)) {
            errorMessage = "Email is empty!";
        }

        if (isEmptyLastName(lastName)) {
            errorMessage = "Last name is empty!";
        }

        if (isEmptyFirstName(firstName)) {
            errorMessage = "First name is empty!";
        }

        if (errorMessage.isEmpty()) {
            User user = createUser(firstName, lastName, emailAddress, password);
            currUser = formatEmail(emailAddress);

            addUserToFirebase(realTimeDatabase, user, currUser);
            switchToHomeWindow();

        } else {
            setStatusMessage(errorMessage);
        }
    }
}




