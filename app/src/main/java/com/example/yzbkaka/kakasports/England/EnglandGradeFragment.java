package com.example.yzbkaka.kakasports.England;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.yzbkaka.kakasports.GradeAdapter;
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

/**
 * Created by yzbkaka on 19-7-18.
 */

public class EnglandGradeFragment extends Fragment {
    private ListView englandGradeListView;
    private List<Match> englandGradeList;
    private GradeAdapter englandGradeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.england_grade,container,false);
        englandGradeListView = (ListView)view.findViewById(R.id.england_grade_view);
        englandGradeList = new ArrayList<>();



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

        englandGradeAdapter = new GradeAdapter(getContext(),R.layout.england_grade_item,englandGradeList);
        englandGradeListView.setAdapter(englandGradeAdapter);

        return view;
    }


    public void parseJSONWithJSONObject(String responseData)  {
        try{
            JSONObject jsonObject = new JSONObject(responseData);  //得到解析出来的json
            JSONArray jsonArray = jsonObject.getJSONArray("jifenbang");  //得到jifenbang数组里面的内容
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
                englandGradeList.add(match);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
