package com.example.pavol.footballquiz2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class HighestScore extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private int score;
    private int questionCount;
    private Long seconds;
    private EditText name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);
    }
}
