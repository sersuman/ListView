package com.example.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String countries[] = {
            "Nepal","Kathmandu",
            "India","New Delhi",
            "China","Bejing",
            "UK","London",
            "US","Washington DC",
            "Canada","Ottawa"
    };
    private ListView lstCountries;
    private Map<String, String> dictionary;
    private Button btnAddCapital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstCountries = findViewById(R.id.lstCountries);
        btnAddCapital = findViewById(R.id.btnAddCapital);
        btnAddCapital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCaptialActivity.class);
                startActivity(intent);
            }
        });

        dictionary = new HashMap<>();
        for (int i=0;i<countries.length; i+=2){
            dictionary.put(countries[i], countries[i+1]);
        }

        readFromFile();

        ArrayAdapter countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(dictionary.keySet()));
        lstCountries.setAdapter(countryAdapter);

        lstCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String country = parent.getItemAtPosition(position).toString(); //get the current position
                String capital = dictionary.get(country);

                Intent intent = new Intent(MainActivity.this, MeaningActivity.class);
                intent.putExtra("capital",capital);
                startActivity(intent);
            }
        });
    }

    private void readFromFile() {
        try {
            FileInputStream fos = openFileInput("capital.txt");
            InputStreamReader isr = new InputStreamReader(fos);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line=br.readLine()) != null){
                String[] parts = line.split("=");
                dictionary.put(parts[0],parts[1]);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
