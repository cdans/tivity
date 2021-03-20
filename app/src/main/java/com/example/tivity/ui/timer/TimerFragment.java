package com.example.tivity.ui.timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tivity.R;

public class TimerFragment extends Fragment {

    private TimerViewModel timerViewModel;

    private SeekBar seekBar;
    private TextView textViewTime;

    private Button buttonStartTimer;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        timerViewModel =
                ViewModelProviders.of(this).get(TimerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_timer, container, false);
        final TextView textView = root.findViewById(R.id.text_timer);
        timerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        this.textViewTime = root.findViewById(R.id.textViewTime);
        seekBar = root.findViewById(R.id.seekBarDiscrete);
        buttonStartTimer = root.findViewById(R.id.buttonStartTimer);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                TimerFragment.this.textViewTime.setText((seekBar.getProgress()+1)*5 + ":00");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final int[] initalProgress = {seekBar.getProgress()};
        final int[] startOrCancel = {0};
        final CountDownTimer[] timer = new CountDownTimer[1];

        buttonStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(startOrCancel[0] == 0) {

                    buttonStartTimer.setText("cancel");

                    initalProgress[0] = seekBar.getProgress();
/*
                    seekBar.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return true;
                        }
                    });

 */                 final int time = (seekBar.getProgress() + 1) * 5 * 1000 * 60;

                    seekBar.setProgress(47*time/time);

                    timer[0] = new CountDownTimer(time, 1000) {

                        public void onTick(long millisUntilFinished) {
                            //TimerFragment.this.textViewTime.setText("seconds remaining: " + millisUntilFinished / 1000);

                            long restSec = millisUntilFinished / 1000;
                            long min = restSec / 60;
                            long sec = restSec % 60;
                            seekBar.setProgress(47*(int)millisUntilFinished/time);

                            if(sec>9) {
                                TimerFragment.this.textViewTime.setText(min + ":" + sec);
                            }
                            else{
                                TimerFragment.this.textViewTime.setText(min + ":0" + sec);
                            }
                        }

                        public void onFinish() {
                            TimerFragment.this.textViewTime.setText("done!");
                            buttonStartTimer.setText("start");
                            startOrCancel[0]--;
                            seekBar.setProgress(initalProgress[0]);

                        }
                    }.start();

                    startOrCancel[0]++;
                }


                else if(startOrCancel[0] == 1) {
                    timer[0].cancel();
                    buttonStartTimer.setText("start");
                    textViewTime.setText((initalProgress[0]+1)*5 + ":00");
                    startOrCancel[0]--;
                    seekBar.setProgress(initalProgress[0]);

                }


            }
        });

        return root;
    }


}