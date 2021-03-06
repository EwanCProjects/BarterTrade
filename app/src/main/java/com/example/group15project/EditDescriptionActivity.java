package com.example.group15project;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.view.View;

public class EditDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_edit_description);
    }

    public void buttonClicked(View view){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("editDesc"); // ?

        EditText editText = findViewById(R.id.editDesciption);
        String editedText = editText.getText().toString();
        myRef.setValue(editedText);
    }

}
