package com.example.group15project;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewTradeActivity extends AppCompatActivity implements View.OnClickListener {
    public static Trade currTrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trade);

        getTrade();
        displayTrade(currTrade);
    }

    private void getTrade() { currTrade = HistTradeAdapter.currTrade; }

    public void displayTrade(Trade tradeToDisplay) {
        TextView tradeTitle = findViewById(R.id.tradeTitleField);
        TextView provider = findViewById(R.id.providerField);
        TextView receiver = findViewById(R.id.receiverField);
        TextView tradeDescription = findViewById(R.id.tradeDescriptionField);

        setTextField(tradeTitle, tradeToDisplay.getTitle());
        setTextField(provider, tradeToDisplay.getProvider());
        setTextField(receiver, tradeToDisplay.getReceiver());
        setTextField(tradeDescription, tradeToDisplay.getDescription());
    }

    protected void setTextField(TextView field, String strValue) {
        field.setText(strValue);
    }

    public void onClick(View view) {

    }
}
