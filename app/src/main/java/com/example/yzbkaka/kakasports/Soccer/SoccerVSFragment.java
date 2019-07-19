package com.example.yzbkaka.kakasports;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.yzbkaka.kakasports.SoccerActivity.nation;

/**
 * Created by yzbkaka on 19-7-18.
 */

public class SoccerVSFragment extends Fragment {
    private ListView englandVSListView;  //显示赛程的listView
    private List<Match> englandVSList;  //显示赛程的列表
    private SoccerVSAdapter englandSoccerVSAdapter;  //ListView的适配器
    private String responseData;
    private String league;  //联赛名称


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.england_vs,container,false);
        englandVSListView = (ListView)view.findViewById(R.id.england_vs_view);
        englandVSList = new ArrayList<>();

        Log.d("nation", String.valueOf(nation));

        if(nation == 0){
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


        new Thread(new Runnable() {  //开启子线程，请求API
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://api.avatardata.cn/Football/Query?key=83892d2ed3f14309bdf2d81ac6b0e9be&league=" + league)
                            .build();
                    Response response = client.newCall(request).execute();
                    responseData = response.body().string();
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

        englandSoccerVSAdapter = new SoccerVSAdapter(getContext(),R.layout.england_vs_item,englandVSList);  //设置子项布局
        englandVSListView.setAdapter(englandSoccerVSAdapter);  //设置ListView的适配器
        return view;
    }


    public void parseJSONWithJSONObject(String responseData)  {
            try{
                Log.d("parse_responseData",responseData);
                JSONObject jsonObject = new JSONObject(responseData);  //得到解析出来的json
                JSONObject result = jsonObject.getJSONObject("result"); //要对标签里面的内容一层一层进行解析
                JSONObject views = result.getJSONObject("views");
                JSONArray jsonArray = views.getJSONArray("saicheng1");  //得到"saicheng1"数组里面的内容
                for(int i = 0;i<jsonArray.length();i++){
                    Match match = new Match();
                    JSONObject myJsonObject = jsonArray.getJSONObject(i);  //开始遍历
                    match.setStartTime(myJsonObject.getString("c2"));
                    match.setFirstTeam(myJsonObject.getString("c4T1"));
                    match.setSecondTeam(myJsonObject.getString("c4T2"));
                    match.setVsGrade(myJsonObject.getString("c4R"));
                    englandVSList.add(match);
                    englandSoccerVSAdapter.notifyDataSetChanged();  //一定到对适配器进行更新
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }
}
