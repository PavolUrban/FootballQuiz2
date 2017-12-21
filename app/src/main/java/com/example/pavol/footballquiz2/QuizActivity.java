package com.example.pavol.footballquiz2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {

    private SoundPlayer sound;
    Vibrator vibrator;
    private QuestionBank mQuestionLibrary = new QuestionBank();
    private TextView mScoreView;   // view for current total score
    private TextView timer;
    private TextView mQuestionView;  //current question to answer
    private Button mButtonChoice1; // multiple choice 1 for mQuestionView
    private Button mButtonChoice2; // multiple choice 2 for mQuestionView
    private Button mButtonChoice3; // multiple choice 3 for mQuestionView
    private Button mButtonChoice4; // multiple choice 4 for mQuestionView
    private ImageView imageView;
    private String mAnswer;  // correct answer for question in mQuestionView
    private int mScore = 0;  // current total score
    private Long elapsedSeconds = 0L;


    private int mQuestionNumber = 0; // current question number
    private int mQuestionCount = 0;
    private int maxQuestionsInGame = 10;
    private Boolean soundsOn =true;
    private SharedPreferences mPrefs;
    private TimerTask tt;


    Drawable[] images;

    Resources resources;

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
        sound = new SoundPlayer(this);
        mQuestionView = (TextView) findViewById(R.id.question);
        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        mButtonChoice4 = (Button) findViewById(R.id.choice4);
        timer = (TextView) findViewById(R.id.myTimer);
        mPrefs = getSharedPreferences("app", MODE_PRIVATE);

        imageView = (ImageView) findViewById(R.id.questionImage);

        resources = getResources();

        images = new Drawable[30];
        images[24] = resources.getDrawable(R.drawable.bazaly);
        images[25]= resources.getDrawable(R.drawable.wembley);
        images[26]= resources.getDrawable(R.drawable.ss);//ok
        images[27]= resources.getDrawable(R.drawable.diego);
        images[28]= resources.getDrawable(R.drawable.ronaldo);
        String isAnyConfiguration = mPrefs.getString("CONFIGURATION",null);

        if(isAnyConfiguration != null)
        {
            ConfigurationModel mode = getConf();
            maxQuestionsInGame = mode.getQuestionCount();
            soundsOn = mode.getSoundsOn();
        }
        else
        {
            maxQuestionsInGame =10;
            soundsOn = true;
        }


        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
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

            imageView.setImageDrawable(null);
            imageView.getLayoutParams().height = 0;

            mQuestionNumber = generateRandomNumber();

            if(mQuestionNumber>23)
            {
                imageView.setImageDrawable(images[mQuestionNumber]);
                imageView.getLayoutParams().height = 300;
                imageView.requestLayout();
            }

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
            if (soundsOn) {
             sound.playCorrectAnswerSound();
            }
            else
            {
                vibrator.vibrate(50);
            }
            Toast.makeText(QuizActivity.this, "Správne!", Toast.LENGTH_SHORT).show();
        }

        else
        {
            if (soundsOn) {
                sound.playwrongAnswerSound();
            }
            else
            {
                vibrator.vibrate(150);
            }

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

    private ConfigurationModel getConf(){
        Gson gson = new Gson();
        String json = mPrefs.getString("CONFIGURATION", "null");
        if(json == "null") {
            return null;
        }
        else {
            ConfigurationModel cf = gson.fromJson(json,ConfigurationModel.class);
            return cf;
        }
    }

}
