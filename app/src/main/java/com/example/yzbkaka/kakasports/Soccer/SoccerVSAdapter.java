package com.example.yzbkaka.kakasports.Soccer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yzbkaka.kakasports.Match;
import com.example.yzbkaka.kakasports.R;


import java.util.List;





/**
 * Created by yzbkaka on 19-7-17.
 */

public class SoccerVSAdapter extends ArrayAdapter<Match> {
    private int resourceId;

    public SoccerVSAdapter(Context context, int id, List<Match> objetcs){
        super(context,id,objetcs);
        resourceId = id;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        final Match match = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView startTime = (TextView)view.findViewById(R.id.start_time);  //开始时间
        TextView firstTeam = (TextView)view.findViewById(R.id.first_team);  //主队
        TextView secondTeam = (TextView)view.findViewById(R.id.second_team);  //客队
        TextView vsGrade = (TextView)view.findViewById(R.id.match_grade);  //比分
        //WebView webView = (WebView)view.findViewById(R.id.webview);  //浏览器

        startTime.setText(match.getStartTime());
        firstTeam.setText(match.getFirstTeam());
        secondTeam.setText(match.getSecondTeam());
        vsGrade.setText(match.getVsGrade());

        return view;
    }

}
