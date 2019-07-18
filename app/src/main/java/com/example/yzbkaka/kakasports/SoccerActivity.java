package com.example.yzbkaka.kakasports;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yzbkaka.kakasports.England.EnglandActivity;

public class SoccerActivity extends AppCompatActivity {
    private Button england;
    private Button italy;
    private Button spain;
    private Button german;
    private Button france;
    private Button china;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soccer);
        england = (Button)findViewById(R.id.england);
        italy = (Button)findViewById(R.id.italy);
        spain = (Button) findViewById(R.id.spain);
        german = (Button)findViewById(R.id.german);
        france = (Button)findViewById(R.id.france);
        china = (Button)findViewById(R.id.china);

        england.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoccerActivity.this,EnglandActivity.class);
                startActivity(intent);
            }
        });

        /*italy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,);
                startActivity(intent);
            }
        });

        spain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,);
                startActivity(intent);
            }
        });

        german.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,);
                startActivity(intent);
            }
        });

        france.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,);
                startActivity(intent);
            }
        });

        china.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,);
                startActivity(intent);
            }
        });*/

    }
}
