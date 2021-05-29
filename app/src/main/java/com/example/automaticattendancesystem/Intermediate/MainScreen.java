package com.example.automaticattendancesystem.Intermediate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.automaticattendancesystem.MainActivity;
import com.example.automaticattendancesystem.PythonFirebase.RetrivingData;
import com.example.automaticattendancesystem.R;

public class MainScreen extends AppCompatActivity {

    //variables
    Button btn_1, btn_2;
    TextView tvs_1,tvs_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        tvs_1 = findViewById(R.id.tvs_1);
        tvs_2 = findViewById(R.id.tvs_2);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(MainScreen.this, RetrivingData.class);
                startActivity(send);
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(MainScreen.this, MainActivity.class);
                startActivity(send);
            }
        });
    }
}