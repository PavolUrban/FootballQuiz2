package com.example.pavol.footballquiz2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {


    private QuestionBank mQuestionLibrary = new QuestionBank();
    private TextView mScoreView;   // view for current total score
    private TextView timer;
    private TextView mQuestionView;  //current question to answer
    private Button mButtonChoice1; // multiple choice 1 for mQuestionView
    private Button mButtonChoice2; // multiple choice 2 for mQuestionView
    private Button mButtonChoice3; // multiple choice 3 for mQuestionView
    private Button mButtonChoice4; // multiple choice 4 for mQuestionView

    private String mAnswer;  // correct answer for question in mQuestionView
    private int mScore = 0;  // current total score
    private Long elapsedSeconds = 0L;


    private int mQuestionNumber = 0; // current question number
    private int mQuestionCount = 0;
    private int maxQuestionsInGame = 10;

    private SharedPreferences mPrefs;
    private TimerTask tt;

    //upravit tak, aby negenerovalo tie iste otazky
    private int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(mQuestionLibrary.getLength());


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        // setup screen for the first question with four alternative to answer
        mScoreView = (TextView) findViewById(R.id.score);
        mQuestionView = (TextView) findViewById(R.id.question);
        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        mButtonChoice4 = (Button) findViewById(R.id.choice4);
        timer = (TextView) findViewById(R.id.myTimer);



        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        new Timer().scheduleAtFixedRate( tt= new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        IncrementSeconds();
                    }
                });
            }

        }, 0, 1000);//put here time 1000 milliseconds=1 second

        if(b!=null)
        {
            String type =(String) b.get("typeOfGame");
            if(type.equals("continue")){
                CurrentGame cg = getCurrentGame();
                if(cg != null) {
                    mQuestionCount = cg.getCurrentQuestion();
                    maxQuestionsInGame = cg.getAllQuestionsCount();
                    mScore = cg.getCurrentScore();
                    elapsedSeconds = cg.getEllapsedSeconds();
                }
                else {

                    elapsedSeconds = 0L;
                }
            }
            else {
                elapsedSeconds = 0L;
            }
        }

        updateQuestion();
        // show current total score for the user
        updateScore(mScore);
    }

    @Override
    protected void onPause() {
        super.onPause();
        tt.cancel();
    }

    private void updateQuestion() {

        if (mQuestionNumber < mQuestionLibrary.getLength() && mQuestionCount < maxQuestionsInGame) {

            mQuestionNumber = generateRandomNumber();


            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
            mButtonChoice2.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
            mButtonChoice3.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
            mButtonChoice4.setText(mQuestionLibrary.getChoice(mQuestionNumber, 4));
            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionCount++;

        }

        else
        {
            Toast.makeText(QuizActivity.this, "To bola poslendá otázka!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuizActivity.this, HighestScoreActivity.class);
            intent.putExtra("score", mScore); // pass the current score to the second screen
            intent.putExtra("allQuestionCount", maxQuestionsInGame);
            intent.putExtra("time", elapsedSeconds);

            CurrentGame cg = getCurrentGame();
            if(cg != null) {
                mQuestionCount = 0;
                maxQuestionsInGame = cg.getAllQuestionsCount();
                mScore = 0;
                elapsedSeconds = 0L;
            }

            startActivity(intent);

            finish();
        }
    }


    // show current total score for the user
    private void updateScore(int point) {
        mScoreView.setText("" + mScore + "/" + maxQuestionsInGame);
    }

    public void onClick(View view) {
        //all logic for all answers buttons in one method
        Button answer = (Button) view;
        // if the answer is correct, increase the score
        if (answer.getText() == mAnswer) {
            mScore = mScore + 1;
            Toast.makeText(QuizActivity.this, "Správne!", Toast.LENGTH_SHORT).show();
        }

        else
        {
            Toast.makeText(QuizActivity.this, "Nesprávne!", Toast.LENGTH_SHORT).show();
        }

        // show current total score for the user
        updateScore(mScore);
        // once user answer the question, we move on to the next one, if any
        updateQuestion();
        saveGame();
    }

    private void saveGame() {
        SharedPreferences.Editor prefs = mPrefs.edit();
        Gson gson = new Gson();
        CurrentGame cg = new CurrentGame(mQuestionCount, mScore,maxQuestionsInGame, elapsedSeconds);
        String cgString = gson.toJson(cg);
        prefs.putString("CURRENT_GAME", cgString);
        prefs.commit();
    }

    private CurrentGame getCurrentGame(){
        Gson gson = new Gson();
        String json = mPrefs.getString("CURRENT_GAME", "null");
        if(json == "null") {
            return null;
        }
        else {
            CurrentGame cg = gson.fromJson(json,CurrentGame.class);
            return cg;
        }
    }

    public  void IncrementSeconds(){
        ++elapsedSeconds;
        timer.setText(TimeHelper.getTimeFromSeconds(elapsedSeconds));
        saveGame();
    }

}
