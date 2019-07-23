package com.example.yzbkaka.kakasports.Soccer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yzbkaka.kakasports.Match;
import com.example.yzbkaka.kakasports.R;
import com.example.yzbkaka.kakasports.Soccer.SoccerVSAdapter;

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

public class SoccerVSFragment extends Fragment {
    private TextView number1;  //显示轮数1
    private ListView vsListView1;  //显示赛程的listView
    private TextView number2;  //显示的轮数2
    private ListView vsListView2;
    private List<Match> vsList1;  //显示赛程1的列表
    private List<Match> vsList2;  //显示赛程2的列表
    private SoccerVSAdapter soccerVSAdapter1;  //赛程1的适配器
    private SoccerVSAdapter soccerVSAdapter2;  //赛程2的适配器
    private String responseData;
    private String league;  //联赛名称
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.england_vs,container,false);
        number1 = (TextView)view.findViewById(R.id.number_1);
        vsListView1 = (ListView)view.findViewById(R.id.england_vs_view);
        number2 = (TextView)view.findViewById(R.id.number_2);
        vsListView2 = (ListView)view.findViewById(R.id.england_vs_view_2);
        vsList1 = new ArrayList<>();
        vsList2 = new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.soccer_vs_refresh);  //下拉刷新

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

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showProgressDialog();
            }
        });

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);  //设置刷新条的颜色
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {  //设置下拉刷新
                sendOkHttpToGetJSON();
            }
        });

        soccerVSAdapter1 = new SoccerVSAdapter(getContext(),R.layout.england_vs_item,vsList1);  //设置赛程1的子项布局
        vsListView1.setAdapter(soccerVSAdapter1);  //设置的适配器

        soccerVSAdapter2 = new SoccerVSAdapter(getContext(),R.layout.england_vs_item,vsList2);  //设置赛程2的子项布局
        vsListView2.setAdapter(soccerVSAdapter2);  //设置赛程2的适配器

        vsListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Match match = vsList1.get(i);
                Intent intent = new Intent(getActivity(),LinkActivity.class);
                intent.putExtra("link",match.getLink());
                startActivity(intent);
            }
        });

        vsListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Match match = new Match();
                Intent intent = new Intent(getActivity(),LinkActivity.class);
                intent.putExtra("link",match.getLink());
                startActivity(intent);
            }
        });

        return view;
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
                    responseData = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {  //要回到主线程进行UI操作
                            parseJSONWithJSONObject(responseData);
                        }
                    });
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }).start();
    }


    public void parseJSONWithJSONObject(String responseData)  {
            try{
                JSONObject jsonObject = new JSONObject(responseData);  //得到解析出来的json
                JSONObject result = jsonObject.getJSONObject("result"); //要对标签里面的内容一层一层进行解析
                JSONObject tabs = result.getJSONObject("tabs");

                number1.setText(tabs.getString("saicheng1"));  //写出1轮数
                number2.setText(tabs.getString("saicheng2"));  //写出2轮数

                JSONObject views = result.getJSONObject("views");
                JSONArray saicheng1 = views.getJSONArray("saicheng1");  //得到"saicheng1"数组里面的内容
                JSONArray saicheng2 = views.getJSONArray("saicheng2");

                for(int i = 0;i<saicheng1.length();i++){  //开始遍历赛程1
                    Match match = new Match();
                    JSONObject myJsonObject = saicheng1.getJSONObject(i);
                    match.setStartTime(myJsonObject.getString("c2"));
                    match.setFirstTeam(myJsonObject.getString("c4T1"));
                    match.setSecondTeam(myJsonObject.getString("c4T2"));
                    match.setVsGrade(myJsonObject.getString("c4R"));
                    match.setLink(myJsonObject.getString("c52Link"));
                    vsList1.add(match);
                    soccerVSAdapter1.notifyDataSetChanged();  //一定到对适配器进行更新
                }

                for(int i = 0;i<saicheng2.length();i++){  //开始遍历赛程2
                    Match match = new Match();
                    JSONObject myJsonObject = saicheng2.getJSONObject(i);
                    match.setStartTime(myJsonObject.getString("c2"));
                    match.setFirstTeam(myJsonObject.getString("c4T1"));
                    match.setSecondTeam(myJsonObject.getString("c4T2"));
                    match.setVsGrade(myJsonObject.getString("c4R"));
                    vsList2.add(match);
                    soccerVSAdapter2.notifyDataSetChanged();  //一定到对适配器进行更新
                }
                swipeRefreshLayout.setRefreshing(false);  //关闭刷新圈
                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                closeProgressDialog();  //关闭加载框
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    public void showProgressDialog(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("kakaSports");
            progressDialog.setCancelable(true);
            progressDialog.setMessage("loading...");
            progressDialog.show();
        }
    }


    public void closeProgressDialog(){
        progressDialog.dismiss();

    }
}
