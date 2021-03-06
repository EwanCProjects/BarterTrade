package testActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import com.example.group15project.HomeActivity;
import com.example.group15project.R;
import com.example.group15project.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class TestRegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    //public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    public String currUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button postButton = findViewById(R.id.signUpButton);
        postButton.setOnClickListener(this);
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

    public boolean isEmptyFirstName(String firstName) {
        return firstName.isEmpty();
    }

    public boolean isEmptyLastName(String lastName) {
        return lastName.isEmpty();
    }

    public boolean isEmptyEmail(String email) {
        return email.isEmpty();
    }

    protected boolean isEmptyPassword(String password) {
        return password.isEmpty();
    }

    public boolean isValidEmailAddress(String emailAddress) {
        return PatternsCompat.EMAIL_ADDRESS.matcher(emailAddress).matches();
    }

    public boolean passwordsMatch(String password, String passwordConfirm) {
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

    @Override
    public void onClick(View view) {

        String firstName = getFirstName();
        String lastName = getLastName();
        String password = getPassword();
        String passwordConfirm = getPasswordConfirmation();
        String emailAddress = getEmailAddress();
        String errorMessage = getResources().getString(R.string.empty_string);

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
            currUser = emailAddress;

            //addUserToFirebase(realTimeDatabase, user, emailAddress);
            //switchToHomeWindow();

        } else {
            setStatusMessage(errorMessage);
        }
    }
}




