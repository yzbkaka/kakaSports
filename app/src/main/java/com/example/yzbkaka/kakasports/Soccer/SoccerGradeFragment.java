package com.example.yzbkaka.kakasports.Soccer;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


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

import static com.example.yzbkaka.kakasports.Soccer.SoccerActivity.nation;

/**
 * Created by yzbkaka on 19-7-18.
 */

public class SoccerGradeFragment extends Fragment {
    private ListView gradeListView;
    private List<Match> gradeList;
    private SoccerGradeAdapter soccerGradeAdapter;
    private String league;  //联赛名称
    private SwipeRefreshLayout swipeRefreshLayout;
    private int count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.england_grade,container,false);
        setLightMode();
        gradeListView = (ListView)view.findViewById(R.id.england_grade_view);
        gradeList = new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.soccer_grade_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        if(nation == 0){  //判断联赛
            league = "英超";
        }
        if(nation == 1){
            league = "意甲";
        }
        if(nation == 2){
            league = "西甲";
        }
        if(nation == 3){
            league = "德甲";
        }
        if(nation == 4){
            league = "法甲";
        }
        if(nation == 5){
            league = "中超";
        }

        sendOkHttpToGetJSON();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                gradeList.clear();
                gradeListView.removeAllViews();
                sendOkHttpToGetJSON();
            }
        });

        if(count == 0){
            soccerGradeAdapter = new SoccerGradeAdapter(getContext(),R.layout.england_grade_item,gradeList);
            gradeListView.setAdapter(soccerGradeAdapter);
        }

        return view;
    }


    public void parseJSONWithJSONObject(String responseData)  {
        try{
            JSONObject jsonObject = new JSONObject(responseData);  //得到解析出来的json
            JSONObject result = jsonObject.getJSONObject("result");
            JSONObject views = result.getJSONObject("views");
            JSONArray jsonArray = views.getJSONArray("jifenbangP");  //得到jifenbang数组里面的内容
            for(int i = 0;i<jsonArray.length();i++){
                Match match = new Match();
                JSONObject myJsonObject = jsonArray.getJSONObject(i);  //开始遍历
                match.setRank(myJsonObject.getString("c1"));
                match.setName(myJsonObject.getString("c2"));
                match.setSumMatch(myJsonObject.getString("c3"));
                match.setWin(myJsonObject.getString("c41"));
                match.setEqual(myJsonObject.getString("c42"));
                match.setSumGrade(myJsonObject.getString("c6"));
                match.setLose(myJsonObject.getString("c43"));
                gradeList.add(match);
                soccerGradeAdapter.notifyDataSetChanged();
            }
            swipeRefreshLayout.setRefreshing(false);  //关闭刷新圈
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void sendOkHttpToGetJSON(){
        new Thread(new Runnable() {  //开启子线程，请求API
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://api.avatardata.cn/Football/Query?key=83892d2ed3f14309bdf2d81ac6b0e9be&league=" + league)
                            .build();
                    Response response = client.newCall(request).execute();
                    final String responseData = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            parseJSONWithJSONObject(responseData);
                        }
                    });
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void setLightMode(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
