package com.example.yzbkaka.kakasports;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yzbkaka.kakasports.Basketball.BasketballActivity;
import com.example.yzbkaka.kakasports.Soccer.SoccerActivity;

public class MainActivity extends AppCompatActivity {
    private Button soccer;
    private Button basketball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLightMode();
        soccer = (Button)findViewById(R.id.soccer);
        basketball = (Button)findViewById(R.id.basketball);

        soccer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SoccerActivity.class);
                startActivity(intent);
            }
        });

        basketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,BasketballActivity.class);
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
