package com.example.qrcode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRCode_scanning extends AppCompatActivity {

    private Button btnScan;
    private Button btnBackScan;
    private TextView tvScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scanning);
        init();
        btnBackScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QRCode_scanning.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(QRCode_scanning.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("QR Code olvasó ByAlex");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.initiateScan();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null) {
            if (result.getContents() == null){
                Toast.makeText(QRCode_scanning.this, "Kiléptél a QRCode olvasóból", Toast.LENGTH_SHORT).show();
            }
            else {
                tvScan.setText(result.getContents());
                Uri uri = Uri.parse(result.getContents());
                if (URLUtil.isValidUrl(result.getContents())) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                }
            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
    private void init(){
        btnScan = findViewById(R.id.btnScan);
        btnBackScan = findViewById(R.id.btnBackScan);
        tvScan = findViewById(R.id.tvScan);
    }
}