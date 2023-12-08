package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRCode_generate extends AppCompatActivity {

    private EditText etURL;
    private Button btnGen;
    private Button btnBackGen;
    private ImageView ivKod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_generate);
        init();
        btnBackGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QRCode_generate.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    if (etURL.getText().toString().trim().isEmpty()) {
                        Toast.makeText(QRCode_generate.this, "Nem lehet üres a cucc", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    BitMatrix bitMatrix = multiFormatWriter.encode(etURL.getText().toString(), BarcodeFormat.QR_CODE, 600, 600);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    ivKod.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void init() {
        etURL = findViewById(R.id.etURL);
        btnGen = findViewById(R.id.btnGen);
        btnBackGen = findViewById(R.id.btnBackGen);
        ivKod = findViewById(R.id.ivKod);
    }
}