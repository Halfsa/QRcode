package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnGeneralas;
    private Button btnScanning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnGeneralas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QRCode_generate.class);
                startActivity(intent);

            }
        });
        btnScanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QRCode_scanning.class);
                startActivity(intent);

            }
        });
    }
    private void init(){
        btnGeneralas = findViewById(R.id.btnGeneralas);
        btnScanning = findViewById(R.id.btnScanning);
    }
}