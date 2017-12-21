package com.example.pavol.footballquiz2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

public class SettingsActivity extends AppCompatActivity {

    private String[] arraySpinner;
    private SharedPreferences mPrefs;


    private Spinner  count;
    private Switch soundsOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Spinner s = (Spinner) findViewById(R.id.spinnerQuestionCount);
        count = (Spinner) findViewById(R.id.spinnerQuestionCount);
        soundsOn = (Switch) findViewById(R.id.swSounds);
        mPrefs = getSharedPreferences("app", MODE_PRIVATE);

//osetrit ak je cg null
        ConfigurationModel cg = getConf();
        if(cg!=null)
        {
            if (cg.getSoundsOn() == true) {
                soundsOn.setChecked(true);
            } else
                soundsOn.setChecked(false);

            if (cg.getQuestionCount() == 10) {
                this.arraySpinner = new String[]{
                        "10", "15", "20"
                };
            } else if (cg.getQuestionCount() == 15) {
                this.arraySpinner = new String[]{
                        "15", "10", "20"
                };
            }
            else
            {
                this.arraySpinner = new String[] {
                        "20", "15", "10"
                };
            }
        }

        else
        {
            soundsOn.setChecked(true);
            this.arraySpinner = new String[] {
                    "20", "15", "10"
            };
        }



        //Spinner s = (Spinner) findViewById(R.id.spinnerQuestionCount);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
    }

    public void resetScore(View v){
        clearScoresGame();
        Toast.makeText(getApplicationContext(), "Skóre resetované.", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    public void saveSettings(View v){
        saveConf();
        Toast.makeText(getApplicationContext(), "Nastavenia uložené.", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    private void saveConf() {
        Integer _count = Integer.parseInt(count.getSelectedItem().toString());
        Boolean _soundsOn =  soundsOn.isChecked();
        SharedPreferences.Editor prefs = mPrefs.edit();
        Gson gson = new Gson();
        ConfigurationModel cg = new ConfigurationModel(_soundsOn, _count);
        String cgString = gson.toJson(cg);
        prefs.putString("CONFIGURATION", cgString);
        prefs.commit();
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



    public void clearScoresGame() {
        SharedPreferences.Editor prefs = mPrefs.edit();
        prefs.remove("HIGH_SCORES");
        prefs.commit();
        prefs.remove("highscore");
        prefs.commit();
    }
}
