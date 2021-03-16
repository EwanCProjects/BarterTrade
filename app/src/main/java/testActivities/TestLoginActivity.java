package testActivities;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.group15project.HomeActivity;
import com.example.group15project.R;
import com.example.group15project.RegistrationActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class TestLoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static String currUser = null;
    static String extractedUserEmail = null;
    static String extractedUserPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //attaching the event handler
        Button loginButton = findViewById(R.id.loginB);
        loginButton.setOnClickListener(this);
        Button registerButton = findViewById(R.id.registerB);
        registerButton.setOnClickListener(this);
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

    public static boolean isEmptyUserName(String username) {
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

    public static boolean isValidEmailAddress(String emailAddress) {
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

    @SuppressLint("NonConstantResourceId")
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

                            if (!extractedUserPassword.equals(password)) {
                                errorMessage = getResources().getString(R.string.wrong_password).trim();
                            }

                            if (extractedUserEmail == null) {
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
