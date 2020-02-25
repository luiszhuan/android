package com.luis.firstcodes.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.luis.firstcodes.R;

public class ServiceActivity extends AppCompatActivity {

    private Button startService;
    private Button stopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        startService = findViewById(R.id.btn_start_service);
        stopService = findViewById(R.id.btn_stop_service);
        startService.setOnClickListener(v -> {
            Intent startIntent = new Intent(this, MyService.class);
            startService(startIntent);
        });
        stopService.setOnClickListener(v -> {
            Intent stopIntent = new Intent(this, MyService.class);
            stopService(stopIntent);
        });
    }
}
