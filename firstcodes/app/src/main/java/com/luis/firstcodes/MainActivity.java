package com.luis.firstcodes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.luis.firstcodes.databind.DataBindActivity;
import com.luis.firstcodes.service.ServiceActivity;
import com.luis.firstcodes.xml.XmlParserActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_xml_parse_main).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, XmlParserActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.btn_service_main).setOnClickListener(v -> {
            startActivity(new Intent(this, ServiceActivity.class));
        });
        findViewById(R.id.btn_databind_main).setOnClickListener(v -> {
            startActivity(new Intent(this, DataBindActivity.class));
        });
    }
}
