package com.example.yzbkaka.kakasports;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;




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

        startTime.setText(match.getStartTime());
        firstTeam.setText(match.getFirstTeam());
        secondTeam.setText(match.getSecondTeam());
        vsGrade.setText(match.getVsGrade());

        return view;
    }

}
