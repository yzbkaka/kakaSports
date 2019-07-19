package com.example.yzbkaka.kakasports;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoccerActivity extends AppCompatActivity {
    private Button england;
    private Button italy;
    private Button spain;
    private Button german;
    private Button france;
    private Button china;

    public static int nation;

    /*public static int ENGLAND = 0;
    public static int ITALY = 1;
    public static int SPAIN = 2;
    public static int GRMAN = 3;
    public static int FRANCE = 4;
    public static int CHINA = 5;*/

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
                nation = 0;
                startActivity(intent);
            }
        });

        italy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoccerActivity.this, ItalyActivity.class);
                nation = 1;
                startActivity(intent);
            }
        });

        spain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoccerActivity.this,SpainActivity.class);
                nation = 2;
                startActivity(intent);
            }
        });

        german.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoccerActivity.this,GermanActivity.class);
                nation = 3;
                startActivity(intent);
            }
        });

        france.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoccerActivity.this,FranceActivity.class);
                nation = 4;
                startActivity(intent);
            }
        });

        china.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoccerActivity.this,ChinaActivity.class);
                nation = 5;
                startActivity(intent);
            }
        });

    }
}
