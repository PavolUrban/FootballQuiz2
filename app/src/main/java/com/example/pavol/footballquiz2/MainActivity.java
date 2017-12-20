package com.example.pavol.footballquiz2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



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
        Toast.makeText(MainActivity.this, "Zatiaľ nemáte uložené žiadne štatistiky!", Toast.LENGTH_SHORT).show();
    }

    public void goToSettings(View v){
        Toast.makeText(MainActivity.this, "Tu budú nastavenia!", Toast.LENGTH_SHORT).show();
    }

    }

