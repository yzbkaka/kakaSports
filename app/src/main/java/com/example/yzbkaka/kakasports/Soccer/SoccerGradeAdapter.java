package com.example.yzbkaka.kakasports;

import android.content.Context;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by yzbkaka on 19-7-18.
 */

public class SoccerGradeAdapter extends ArrayAdapter<Match> {
    private int resourceId;

    public SoccerGradeAdapter(Context context, int id, List<Match> objetcs){
        super(context,id,objetcs);
        resourceId = id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Match match = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView rank = (TextView)view.findViewById(R.id.rank);
        TextView team = (TextView)view.findViewById(R.id.team);
        TextView sumMatch = (TextView)view.findViewById(R.id.sum_match);
        TextView win = (TextView)view.findViewById(R.id.win);
        TextView equal = (TextView)view.findViewById(R.id.equal);
        TextView lose = (TextView)view.findViewById(R.id.lose);
        TextView sumGrade = (TextView)view.findViewById(R.id.sum_grade);

        rank.setText(match.getRank());
        team.setText(match.getName());
        sumMatch.setText(match.getSumMatch());
        win.setText(match.getWin());
        equal.setText(match.getEqual());
        lose.setText(match.getLose());
        sumGrade.setText(match.getSumGrade());

        return view;
    }

}
