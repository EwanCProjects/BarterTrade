package com.example.group15project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.*;

public class Acceptance2 extends AppCompatActivity {
    ListView resultsListView;
    SimpleAdapter adapter;
    List<HashMap<String, String>> listItems = new ArrayList<>();

    Button accept_btn;
    Button decline_btn;
    Button message_btn;
    TextView was_declined;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //init
        setContentView(R.layout.accept_2);  // link xml
        //LayoutInflater inflater = LayoutInflater.from(getContext());
        //View view = inflater.inflate(R.layout.hifragment_main, container, false);

        accept_btn = (Button) findViewById(R.id.btn_1);
        decline_btn = (Button) findViewById(R.id.btn_2);
        message_btn = (Button)  findViewById(R.id.btn_3);
        was_declined = (TextView) findViewById(R.id.text3);

        resultsListView = (ListView) findViewById(R.id.results_listview); //init LV
        placePreExistingData();
        adapter = new SimpleAdapter(this, listItems, R.layout.list_item,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.text1, R.id.text2});//place id's here
        resultsListView.setAdapter(adapter);
        //resultsListView.setAdapter(new ListAdapter(this, R.layout.list_item, data));
        //resultsListView.setAdapter(new ListAdapter(this,R.layout.list_item,listItems));
    }
    public void accept(View view){
        accept_btn = (Button) findViewById(R.id.btn_1);
        decline_btn = (Button) findViewById(R.id.btn_2);
        message_btn = (Button)  findViewById(R.id.btn_3);
        was_declined = (TextView) findViewById(R.id.text3);
        //print current position or name ...
        //Toast.makeText(this, listItems(position), Toast.LENGTH_SHORT);
        accept_btn.setVisibility(View.GONE);
        decline_btn.setVisibility(View.GONE);
        message_btn.setVisibility(View.VISIBLE);
    }
    public void decline(View view){
        accept_btn.setVisibility(View.GONE);
        decline_btn.setVisibility(View.GONE);
        message_btn.setVisibility(View.GONE);
        was_declined.setVisibility(View.VISIBLE);
    }
    public void message(View view){

    }
    protected void placePreExistingData(){
        //initialize a hashmap fro a local List
        HashMap<String, String> nameAddresses = new HashMap<>();
        nameAddresses.put("Diana", "3214 Broadway Avenue");
        nameAddresses.put("Tyga", "343 Rack City Drive");
        nameAddresses.put("Rich Homie Quan", "111 Everything Gold Way");
        nameAddresses.put("Donna", "789 Escort St");
        nameAddresses.put("Bartholomew", "332 Dunkin St");
        nameAddresses.put("Eden", "421 Angelic Blvd");

        //iterate thru the hashmap
        Iterator it = nameAddresses.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            //map the first line to a key string and second line to the value string
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            //from the new hashmap, add to arraylist
            listItems.add(resultsMap);
        }
    }
    /*
    private class ListAdapter extends ArrayAdapter<String> {
        private int layout;
        public ListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
            layout=resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder mainViewHolder = null;
            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.line_1 = (TextView) convertView.findViewById(R.id.text1);
                viewHolder.line_2 = (TextView) convertView.findViewById(R.id.text2);
                viewHolder.button_1 = (Button) convertView.findViewById(R.id.btn_1);
                viewHolder.button_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Button was clicked for item" + position, Toast.LENGTH_SHORT);
                    }
                });
                convertView.setTag(viewHolder);

            }
            else{
                mainViewHolder = (ViewHolder) convertView.getTag();
                mainViewHolder.line_1.setText(getItem(position));
            }
            return convertView;
        }
    }
    public class ViewHolder{
        //ImageView imageView;
        TextView line_1;
        TextView line_2;
        Button button_1;
    }*/
}


