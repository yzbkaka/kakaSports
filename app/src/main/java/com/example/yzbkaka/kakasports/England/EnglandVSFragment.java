package com.example.yzbkaka.kakasports.England;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yzbkaka.kakasports.Match;
import com.example.yzbkaka.kakasports.R;
import com.example.yzbkaka.kakasports.VSAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yzbkaka on 19-7-18.
 */

public class EnglandVSFragment extends Fragment {
    private ListView englandVSListView;  //显示赛程的listView
    private List<Match> englandVSList;  //显示赛程的列表
    private VSAdapter englandVSAdapter;  //ListView的适配器


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.england_vs,container,false);
        englandVSListView = (ListView)view.findViewById(R.id.england_vs_view);
        englandVSList = new ArrayList<>();


        new Thread(new Runnable() {  //开启子线程，请求API
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://api.avatardata.cn/Football/Query?key=83892d2ed3f14309bdf2d81ac6b0e9be&league=英超")
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

        Match match1 = new Match();

        match1.setStartTime("1");
        match1.setFirstTeam("2");
        match1.setSecondTeam("3");
        match1.setVsGrade("4");
        englandVSList.add(match1);

        englandVSAdapter = new VSAdapter(getContext(),R.layout.england_vs_item,englandVSList);  //设置子项布局
        englandVSListView.setAdapter(englandVSAdapter);  //设置ListView的适配器
        return view;
    }


    public void parseJSONWithJSONObject(String responseData)  {
        try{
            JSONObject jsonObject = new JSONObject(responseData);  //得到解析出来的json
            JSONArray jsonArray = jsonObject.getJSONArray("saicheng1");  //得到"saicheng1"数组里面的内容
            for(int i = 0;i<jsonArray.length();i++){
                Match match = new Match();
                JSONObject myJsonObject = jsonArray.getJSONObject(i);  //开始遍历
                match.setStartTime(myJsonObject.getString("c2"));
                match.setFirstTeam(myJsonObject.getString("c4T1"));
                match.setSecondTeam(myJsonObject.getString("c4T2"));
                match.setVsGrade(myJsonObject.getString("c4R"));
                englandVSList.add(match);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
