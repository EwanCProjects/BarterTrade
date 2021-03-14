package com.example.group15project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

public class FilterActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView textView;

    private void setupSeekBar(){
        textView = findViewById(R.id.distance_text);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(HomeActivity.MAX_LOCAL_DISTANCE/1000);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                textView.setText(progress + "km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    /**
     * runs when Apply button is clicked
     */
    public void applyFilterOnButtonClick(View v){
        goToHome();
    }

    private void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void loadAndApplyUserFilterPreferences() {
        FilterPreferences filterPrefs = UserStatusData.getUserFilterPrefs(this);
        if(filterPrefs == null){
            return;
        }
        textView.setText(filterPrefs.maxDistance + " km");
        seekBar.setProgress(filterPrefs.maxDistance);

    }

    private FilterPreferences getUserPreferences() {
        FilterPreferences prefs = UserStatusData.getUserFilterPrefs(this);

        if(prefs == null){
            prefs = new FilterPreferences();
        }
        prefs.setMaxDistance(seekBar.getProgress());
        return prefs;
    }

    /**
     * runs when cancel button is clicked
     */
    public void cancel(View v){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        textView = (TextView) findViewById(R.id.distance_text);
        //homepage view of the tasks will go here
        setupSeekBar();
        loadAndApplyUserFilterPreferences();
    }
}
