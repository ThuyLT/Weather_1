package com.example.lethuy.weathersimpleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * Created by Le Thuy on 30/03/2016.
 */
public class NotificationView extends AppCompatActivity {
    TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtContent = (TextView) findViewById(R.id.text_Content);

        Intent intent = getIntent();
        String msg = intent.getStringExtra("content");
        txtContent.setText(msg);

    }
}
