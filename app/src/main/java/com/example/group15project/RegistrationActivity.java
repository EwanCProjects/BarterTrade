package com.example.group15project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;

import com.google.android.gms.tasks.Task;


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase database = null;
    DatabaseReference firstNameRef = null;
    DatabaseReference lastNameRef = null;
    DatabaseReference emailRef = null;
    DatabaseReference passwordRef = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button postButton = findViewById(R.id.signUpButton);
        postButton.setOnClickListener(this);

        initializeDatabase();
    }

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

protected void initializeDatabase() {
    database = FirebaseDatabase.getInstance();
    firstNameRef = database.getReference("firstName");
    lastNameRef = database.getReference("lastName");
    emailRef = database.getReference("emailAddress");
    passwordRef = database.getReference("password");
}

    protected String getFirstName() {
        EditText firstName = findViewById(R.id.editTextFirstName);
        return firstName.getText().toString().trim();
    }

    protected String getLastName() {
        EditText lastName = findViewById(R.id.editTextLastName);
        return lastName.getText().toString().trim();
    }

    //Do we need this function? Not sure. I'm just keeping this for now.
   /* protected String getFullName() {
        EditText lastName = findViewById(R.id.editTextLastName);
        EditText firstName = findViewById(R.id.editTextFirstName);
        String fullName = (firstName.getText().toString().trim())+" "+(lastName.getText().toString().trim());
        return fullName;
    }*/
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
//edited
    protected boolean passwordsMatch(String password, String passwordConfirm) {
        return (password.equals(passwordConfirm));
    }

    protected Task<Void> savefirstNameToFirebase(String firstName) {
        return firstNameRef.setValue(firstName);
    }

    protected Task<Void> savelastNameToFirebase(String lastName) {
        return lastNameRef.setValue(lastName);
    }

    protected Task<Void> saveEmailToFirebase(String emailAddress) {
        return emailRef.setValue(emailAddress);
    }

    protected Task<Void> savePasswordToFirebase(String password) {
        return passwordRef.setValue(password);
    }

    @Override
    public void onClick(View view) {

        String firstName = getFirstName();
        String lastName = getLastName();
        String password = getPassword();
        String passwordConfirmation = getPasswordConfirmation();
        String emailAddress = getEmailAddress();
        String errorMessage = new String();

        if (isEmptyFirstName(firstName)) {
            errorMessage = "First name is empty";
        } else {
            if (isEmptyLastName(lastName)) {
                errorMessage = "Last name is empty";
            }
        }
        if (!isValidEmailAddress(emailAddress)) {
            errorMessage = "Email is empty";
        }
        if (isEmptyPassword(password)) {
            errorMessage = "Password field empty";
        } else {
                if(!passwordsMatch(password,passwordConfirmation)){
                    errorMessage = "Passwords do not match";
                }
            }

        if (errorMessage.isEmpty()) {
            savefirstNameToFirebase(firstName);
            savelastNameToFirebase(lastName);
            saveEmailToFirebase(emailAddress);
            savePasswordToFirebase(password);
        } else {
            setStatusMessage(errorMessage);
        }
    }
}




