package com.example.pavol.footballquiz2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StatsActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private TextView stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        mPrefs = getSharedPreferences("app", MODE_PRIVATE);
        stats = (TextView) findViewById(R.id.statistics);
        List<ScoreRecord> list = getScores();


        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                ScoreRecord p1 = (ScoreRecord) o1;
                ScoreRecord p2 = (ScoreRecord) o2;
                return p1.getPercentage().compareTo(p2.getPercentage());
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\t"+"Meno"+"\t\t\t\t" + "Percentá" + "\t\t\t" + "Čas" + "\n");
        for (ScoreRecord obj : list) {
            sb.append("\t\t\t"+obj.getName() + "\t\t\t" + obj.getPercentage() + "\t\t\t\t\t\t" + TimeHelper.getTimeFromSeconds(obj.getTime()) + "\n");
        }

        stats.setText(sb.toString());
    }

    private List<ScoreRecord> getScores(){
        Gson gson = new Gson();
        String json = mPrefs.getString("HIGH_SCORES", "null");
        if(json == "null") {
            return null;
        }
        else {
            Type listType = new TypeToken<List<ScoreRecord>>(){}.getType();
            List<ScoreRecord>  cg = gson.fromJson(json,listType);
            return cg;
        }
    }
}
