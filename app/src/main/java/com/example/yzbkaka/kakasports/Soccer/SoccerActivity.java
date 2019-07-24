package com.example.yzbkaka.kakasports.Soccer;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yzbkaka.kakasports.R;

public class SoccerActivity extends AppCompatActivity {
    private Button back;
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
        setLightMode();
        back = (Button)findViewById(R.id.back);
        england = (Button)findViewById(R.id.england);
        italy = (Button)findViewById(R.id.italy);
        spain = (Button) findViewById(R.id.spain);
        german = (Button)findViewById(R.id.german);
        france = (Button)findViewById(R.id.france);
        china = (Button)findViewById(R.id.china);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

    private void setLightMode(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
