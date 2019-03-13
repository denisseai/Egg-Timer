package com.example.timers_demo;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    SeekBar timerSeekBar;
    Boolean counterIsActive = false;
    Button startButton;
    CountDownTimer countDownTimer;

    public void resetTimer() {
        timerTextView.setText("0:10");
        timerSeekBar.setProgress(10);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        startButton.setText("Start");
        counterIsActive = false;
    }

    public void play(View view) {

        if (counterIsActive) {

            resetTimer();

        } else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            startButton.setText("Stop");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.marbles);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondString = "0" + secondString;
        }

        timerTextView.setText(Integer.toString(minutes)+ ":" + secondString);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.countdownTextView);
        startButton = findViewById(R.id.startButton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(10);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
