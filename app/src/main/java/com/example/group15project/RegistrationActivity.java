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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference fbdb = FirebaseDatabase.getInstance().getReference();
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
    }

    /*
  WHAT IT USED TO BE FOR POST-ING EMAIL, NAMES, ETC:
      protected String getPostDescription() {
        EditText emailAddress = findViewById(R.id.descriptionTextField);
        return emailAddress.getText().toString().trim();
    }
 */

    protected String getPostFullName() {
        EditText fName = findViewById(R.id.editTextFirstName);
        EditText lName = findViewById(R.id.editTextLastName);
        String fullName = (fName.getText().toString().trim())+" "+(lName.getText().toString().trim());
        return fullName;
    }
    protected String getPostFirstName() {
        EditText fName = findViewById(R.id.editTextFirstName);
        String firstName = (fName.getText().toString().trim());
        return firstName;
    }

    protected String getPostLastName() {
        EditText fName = findViewById(R.id.editTextFirstName);
        String lastName = (fName.getText().toString().trim());
        return lastName;
    }
    protected String getPostEmail() {
        EditText emailAddress = findViewById(R.id.editTextEmailAddress);
        return emailAddress.getText().toString().trim();
    }

    protected String getPostPassword() {
        EditText emailAddress = findViewById(R.id.editTextPassword);
        return emailAddress.getText().toString().trim();
    }

    protected boolean isEmptyTitle(String title) {
        return title.isEmpty();
    }

    protected boolean isEmptyDescription(String description) {
        return description.isEmpty();
    }

    protected boolean isEmptyCategory(String category) {
        return category.isEmpty();
    }

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

//====================NEW===================================
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
    protected String getFullName() {
        EditText lastName = findViewById(R.id.editTextLastName);
        EditText firstName = findViewById(R.id.editTextFirstName);
        String fullName = (firstName.getText().toString().trim())+" "+(lastName.getText().toString().trim());
        return fullName;
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
/*
    protected boolean isAlphanumericUserName(String userName) {
        return userName.matches("[A-Za-z0-9]+");
    }*/

    protected boolean isValidEmailAddress(String emailAddress) {
        return PatternsCompat.EMAIL_ADDRESS.matcher(emailAddress).matches();
    }

    protected boolean passwordsMatch(String password, String passwordConfirm) {
        return (getPassword().equals(getPasswordConfirmation()));
    }

    protected boolean passwordsNotMatch(String password, String passwordConfirm) {
        return !(getPassword().equals(getPasswordConfirmation()));
    }
/*
    protected void switch2WelcomeWindow(String userName, String emailAddress) {
        Intent myIntent = new Intent(this, WelcomeActivity.class);
        myIntent.putExtra(WELCOME_MESSAGE, "Welcome " + userName + "! A welcome email was sent to " + emailAddress);
        startActivity(myIntent);
    }*/

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
//========================================================//
    @Override
    public void onClick(View view) {
        String postFirstName = getPostFirstName();
        String postlastName = getPostLastName();
        String postEmail = getPostEmail();
        String postPassword = getPostPassword();

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
        if (!isEmptyPassword(password)) {
            errorMessage = "Password field empty";
        } else {
                if(passwordsNotMatch(password,passwordConfirmation)){
                    errorMessage = "Passwords do not match";
                }
            }

        if (errorMessage.isEmpty()) {
            savefirstNameToFirebase(firstName);
            savelastNameToFirebase(lastName);
            saveEmailToFirebase(emailAddress);
            savePasswordToFirebase(password);
            //switch2WelcomeWindow(userName, emailAddress);
        } else {
            setStatusMessage(errorMessage);
        }
    }
}




