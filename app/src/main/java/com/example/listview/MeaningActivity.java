package com.example.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MeaningActivity extends AppCompatActivity {
    private TextView tvCapital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meaning);

        tvCapital = findViewById(R.id.tvCapital);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String capital = bundle.getString("capital");
            tvCapital.setText(capital);
        }else {
            Toast.makeText(this, "No message found", Toast.LENGTH_SHORT).show();
        }
    }
}
