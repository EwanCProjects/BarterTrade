package testActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.group15project.HomeActivity;
import com.example.group15project.R;
import com.example.group15project.Trade;
import com.google.firebase.database.DatabaseReference;

import java.util.UUID;

public class TestTradeRequestActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_request);

        Button sendRequest = findViewById(R.id.sendRequestButton);
        sendRequest.setOnClickListener(this);
        Button cancelRequest = findViewById(R.id.cancelRequestButton);
        cancelRequest.setOnClickListener(this);
    }

    protected String generateTradeID(){ return UUID.randomUUID().toString();}

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.tradeRequestStatusLabel);
        statusLabel.setText(message);
    }

    protected String getItemTitle(){
        EditText title = findViewById(R.id.itemTitle);
        return title.getText().toString().trim();
    }

    protected String getItemDescription(){
        EditText description = findViewById(R.id.itemDescription);
        return description.getText().toString().trim();

    }

    public boolean isTitleEmpty(String title){ return title.isEmpty();}

    public boolean isDescriptionEmpty(String description){ return description.isEmpty();}

    protected Trade createTrade(String tradeID, String title, String description){
        return new Trade(tradeID, title, description);
    }
    protected void addTradeToDatabase(DatabaseReference mDatabase, Trade trade, String tradeID){
        mDatabase.child("Trades").child(tradeID).setValue(trade);
    }

    protected void switchToHomeWindow(){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        String tradeID = generateTradeID();
        String title = getItemTitle();
        String description = getItemDescription();
        String errorMessage = "";

        if(v.getId() == R.id.sendRequestButton){
            if(isTitleEmpty(title)){
                errorMessage = "Title is Empty";
            }

            if(isDescriptionEmpty(description)){
                errorMessage = "Description is Empty";
            }

            if(errorMessage.isEmpty()){
                Trade trade = createTrade(tradeID, title, description);
                //addTradeToDatabase(realTimeDatabase, trade, tradeID);
                switchToHomeWindow();

            }else{
                setStatusMessage(errorMessage);
            }

        }
        else if (v.getId() == R.id.cancelRequestButton){
            switchToHomeWindow();
        }
    }
}
