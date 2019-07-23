package com.example.yzbkaka.kakasports.Basketball;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yzbkaka.kakasports.Match;
import com.example.yzbkaka.kakasports.R;

import java.util.List;

/**
 * Created by yzbkaka on 19-7-19.
 */

public class BasketballVSAdapter extends ArrayAdapter<Match> {
    private int resourceId;

    public BasketballVSAdapter(Context context, int id, List<Match> objetcs){
        super(context,id,objetcs);
        resourceId = id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Match match = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView startTime = (TextView)view.findViewById(R.id.time);  //开始时间
        TextView firstPlayer = (TextView)view.findViewById(R.id.first_player);  //主队
        TextView secondPlayer = (TextView)view.findViewById(R.id.second_player);  //客队
        TextView score = (TextView)view.findViewById(R.id.score);  //比分

        startTime.setText(match.getStartTime());
        firstPlayer.setText(match.getFirstTeam());
        secondPlayer.setText(match.getSecondTeam());
        score.setText(match.getVsGrade());

        return view;
    }
}
