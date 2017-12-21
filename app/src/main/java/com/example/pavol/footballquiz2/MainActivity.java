package com.example.pavol.footballquiz2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu);
        }

    public void exitApp(View v){
        this.finish();
    }

    public void newGame(View v){
        Intent myIntent = new Intent(MainActivity.this, QuizActivity.class);
        myIntent.putExtra("typeOfGame", "new"); //Optional parameters
        MainActivity.this.startActivity(myIntent);
    }

    public void continueGame(View v){
        Intent myIntent = new Intent(MainActivity.this, QuizActivity.class);
        myIntent.putExtra("typeOfGame", "continue");
        MainActivity.this.startActivity(myIntent);
    }


    public void goToStats(View v){
        mPrefs = getSharedPreferences("app", MODE_PRIVATE);
        String json = mPrefs.getString("HIGH_SCORES", null);
        if(json !=null)
        {
            Intent myIntent = new Intent(MainActivity.this, StatsActivity.class);
            MainActivity.this.startActivity(myIntent);
        }

        else
        {
            Toast.makeText(MainActivity.this, "Zatiaľ nemáte uložené žiadne štatistiky!", Toast.LENGTH_SHORT).show();
        }
           }

    public void goToSettings(View v){
        Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    }

