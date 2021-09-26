package com.example.thegolfclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class golfClubs extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_golf_clubs);

        //create object of listview
        ListView listView=(ListView)findViewById(R.id.listview);

        //create ArrayList of String
        final ArrayList<String> arrayList=new ArrayList<>();


//Add elements to arraylist
        arrayList.add("Maputo Golf club");
        arrayList.add("Harare Golf Club");
        arrayList.add("Nairobi Golf Club");
        arrayList.add("Mombasa Golf Club");
        arrayList.add("Railway Golf Club");
        arrayList.add("Kigali Golf Club");
        arrayList.add("Mombasa(k) Golf Club");
        arrayList.add("Kampala Golf Club");
        arrayList.add("Cape Town Golf Club");
        arrayList.add("Bujumbura Golf Club");
        arrayList.add("Nyeri Golf Club");
        arrayList.add("Nanyuki Golf Club");
        arrayList.add("Dodoma Golf Club");


//Create Adapter
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

//assign adapter to listview
        listView.setAdapter(arrayAdapter);

//add listener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(golfClubs.this,getResources().getString(R.string.clicked)+ ": " +arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(golfClubs.this, services_activites.class);
                startActivity(intent);
            }
        });
    }
}