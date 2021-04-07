package com.example.group15project;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import currentUserProperties.CurrentUser;

public class HistTradeActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView histTradeView;
    HistTradeAdapter histTradeAdapter;

    public static String currUser = CurrentUser.getInstance().currUserString;
    DatabaseReference realTimeDatabase = HomeActivity.realTimeDatabase;
    List<Trade> extractedTrades = new ArrayList<>();
    List<String> tradeReceivers = new ArrayList<>();
    List<String> tradeProviders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_history);

        histTradeView = findViewById(R.id.histTradeView);

        histTradeAdapter = new HistTradeAdapter(this, extractedTrades, tradeReceivers, tradeProviders);
        histTradeView.setAdapter(histTradeAdapter);
        histTradeView.setLayoutManager(new LinearLayoutManager(this));

        databaseRead(realTimeDatabase);

    }

    public void databaseRead(DatabaseReference db) {
        //code for database initialization and grabbing all Posts
        ValueEventListener chatListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot tradeSnapshot : dataSnapshot.child("Trades").getChildren()) {
                    Trade extractedTrade = tradeSnapshot.getValue(Trade.class);
                    if (extractedTrade.getReceiver().equals(currUser) || extractedTrade.getProvider().equals(currUser)) {
                        extractedTrades.add(extractedTrade);
                        tradeReceivers.add(extractedTrade.getReceiver());
                        tradeProviders.add(extractedTrade.getProvider());
                        histTradeAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        };
        db.addValueEventListener(chatListener);
    }

    @Override
    public void onClick(View v) {

    }
}
