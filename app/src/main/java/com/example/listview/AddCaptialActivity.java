package com.example.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;

public class AddCaptialActivity extends AppCompatActivity {
    private EditText etWord, etMeaning;
    private Button btnAdd,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        etWord = findViewById(R.id.etWord);
        etMeaning = findViewById(R.id.etMeaning);
        btnAdd = findViewById(R.id.btnAddCapital);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCaptialActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void save(){
        try {
            PrintStream printStream = new PrintStream(openFileOutput("capital.txt",MODE_PRIVATE| MODE_APPEND)); //create a txt file
            printStream.println(etWord.getText().toString()+"="+etMeaning.getText().toString()); //add to file
            Toast.makeText(this, "saved to "+getFilesDir(),Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Log.d("Dictionary app","Error"+e.toString());
            e.printStackTrace();
        }
    }
}
