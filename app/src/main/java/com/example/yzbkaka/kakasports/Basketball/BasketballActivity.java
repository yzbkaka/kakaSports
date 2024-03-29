package com.example.yzbkaka.kakasports.Basketball;

import android.app.ProgressDialog;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.yzbkaka.kakasports.Match;
import com.example.yzbkaka.kakasports.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BasketballActivity extends AppCompatActivity {
    private ListView basketballVSListView;
    private List<Match> basketballVSList;
    private BasketballVSAdapter basketballVSAdapter;  //ListView的适配器
    private String responseData;
    private SwipeRefreshLayout basketballRefresh;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball);
        setLightMode();

        basketballVSListView = (ListView)findViewById(R.id.nba_listview);
        basketballVSList = new ArrayList<>();
        basketballRefresh = (SwipeRefreshLayout)findViewById(R.id.basketball_refresh);
        basketballRefresh.setColorSchemeResources(R.color.colorPrimary);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showProgressDialog();
            }
        });

        sendOkHttpToGetJSON();

        basketballRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sendOkHttpToGetJSON();
            }
        });


        basketballVSAdapter = new BasketballVSAdapter(this,R.layout.basketball_vs_item,basketballVSList);
        basketballVSListView.setAdapter(basketballVSAdapter);
    }


    public void sendOkHttpToGetJSON(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://api.avatardata.cn/Nba/NomalRace?key=01569abf1f024581a4d3871d19cf1d4f")
                            .build();
                    Response response = client.newCall(request).execute();
                    responseData = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            parseJSONWithJSONObject(responseData);
                        }
                    });
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void parseJSONWithJSONObject(String responseData){
        try{
            JSONObject jsonObject = new JSONObject(responseData);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray list = result.getJSONArray("list");  //得到list这个数组
            JSONObject tr = list.getJSONObject(1);  //得到list数组中的第一个元素
            JSONArray jsonArray = tr.getJSONArray("tr");  //得到tr数组中的每一个元素

            for(int i = 0;i<jsonArray.length();i++){
                Match match = new Match();
                JSONObject myJsonObject = jsonArray.getJSONObject(i);
                match.setStartTime(myJsonObject.getString("time"));
                match.setFirstTeam(myJsonObject.getString("player1"));
                match.setSecondTeam(myJsonObject.getString("player2"));
                match.setVsGrade(myJsonObject.getString("score"));
                basketballVSList.add(match);
                basketballVSAdapter.notifyDataSetChanged();
            }
            closeProgressDialog();
            basketballRefresh.setRefreshing(false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void showProgressDialog(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("loading...");
            progressDialog.setTitle("kakaSports");
            progressDialog.show();
        }
    }


    public void closeProgressDialog(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }


    private void setLightMode(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
