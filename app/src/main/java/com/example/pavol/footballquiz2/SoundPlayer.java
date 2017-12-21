package com.example.pavol.footballquiz2;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by pavol on 21. 12. 2017.
 */

public class SoundPlayer {
    private static SoundPool soundPool;
    private static int correctAnswer;
    private static int wrongAnswer;

    public SoundPlayer(Context context)
    {
        //SoundPool(int maxStreams, int streamType, int srcQuality)
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        correctAnswer = soundPool.load(context, R.raw.correct,1);
        wrongAnswer = soundPool.load(context, R.raw.wrong,1);
    }

    public void playCorrectAnswerSound()
    {
        soundPool.play(correctAnswer, 1.0f, 1.0f,1,0,1.0f);
    }

    public void playwrongAnswerSound()
    {
        soundPool.play(wrongAnswer, 1.0f, 1.0f,1,0,1.0f);
    }
}
