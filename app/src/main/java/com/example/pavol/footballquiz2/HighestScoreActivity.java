package com.example.pavol.footballquiz2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HighestScoreActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private int score;
    private int questionCount;
    private Long seconds;
    private EditText name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);

        TextView txtScore = (TextView) findViewById(R.id.textScore);
        TextView txtHighScore = (TextView) findViewById(R.id.textHighScore);
        mPrefs = getSharedPreferences("app", MODE_PRIVATE);
        name = (EditText) findViewById(R.id.editName);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        questionCount = intent.getIntExtra("allQuestionCount", 0);
        seconds = intent.getLongExtra("time", 0L);
        txtScore.setText("Vaše skóre: " + score + "\n Váš čas:\t" + TimeHelper.getTimeFromSeconds(seconds));


        int highscore = mPrefs.getInt("highscore",0);
        if(highscore>= score)
            txtHighScore.setText("Najvyššie skóre: "+highscore);
        else
        {
            txtHighScore.setText("Nové najvyššie skóre: "+score);
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putInt("highscore", score);
            editor.commit();
        }
    }


    public void onClick(View view) {
        Intent myIntent = new Intent(HighestScoreActivity.this, QuizActivity.class);
        myIntent.putExtra("typeOfGame", "new"); //Optional parameters
        startActivity(myIntent);
        finish();

    }

    public void clearCurretnGame() {
        SharedPreferences.Editor prefs = mPrefs.edit();
        prefs.remove("CURRENT_GAME");
        prefs.commit();
    }

    private void addScore(ScoreRecord sr) {
        SharedPreferences.Editor prefs = mPrefs.edit();
        Gson gson = new Gson();
        List<ScoreRecord> scr = getScores();
        if(scr == null) {
            scr = new ArrayList<ScoreRecord>();
        }
        scr.add(sr);
        String cgString = gson.toJson(scr);
        prefs.putString("HIGH_SCORES", cgString);
        prefs.commit();
        finish();
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


    public void saveScore(View v){
        Float percentage =  (float)score / (float)questionCount *100;
        addScore(new ScoreRecord(name.getText().toString(),seconds,percentage, questionCount,score));
    }
}
