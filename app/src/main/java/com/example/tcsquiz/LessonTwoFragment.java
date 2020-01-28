package com.example.tcsquiz;


import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LessonTwoFragment extends Fragment {


    private ImageButton homeButton;
    private ImageButton quizButton;
    private TextView firstLessonButton;
    private TextView firstLessonBottomButton;
    private TextView thirdLessonButton;
    private TextView thirdLessonBottomButton;

    private MediaPlayer mp;
    private ImageButton playPauseButton;
    private ImageButton stopButton;

    private boolean isPlaying = false;

    public LessonTwoFragment() {
        // Required empty public constructor
    }

    public static LessonTwoFragment newInstance() {
        return new LessonTwoFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_lesson_two, container, false);

        homeButton = view.findViewById(R.id.homeButton);
        quizButton = view.findViewById(R.id.startQuizButton);
        firstLessonButton = view.findViewById(R.id.quizLessonOnePointer);
        firstLessonBottomButton = view.findViewById(R.id.quizLessonOneBottomPointer);
        thirdLessonButton = view.findViewById(R.id.quizLessonThreePointer);
        thirdLessonBottomButton = view.findViewById(R.id.quizLessonThreeBottomPointer);


        playPauseButton = view.findViewById(R.id.play_pause_mp3_btn);
        stopButton = view.findViewById(R.id.stop_mp3_btn);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();

                Fragment fragment = MainMenuFragment.newInstance();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_fl_, fragment)
                        .commit();
            }
        });

        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                String transactionTag = "lesson_one";
                Fragment fragment = QuizOneFragment.newInstance(transactionTag);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_fl_, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        firstLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLessonOne();
            }
        });

        firstLessonBottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLessonOne();
            }
        });

        thirdLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLessonThree();
            }
        });

        thirdLessonBottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLessonThree();
            }
        });




        mp = MediaPlayer.create(getContext(), R.raw.felieton);

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying){
                    mp.pause();
                    isPlaying = false;

                    Drawable img = getContext().getResources().getDrawable( R.drawable.ic_play_arrow_black_24dp );
                    playPauseButton.setImageDrawable(img);
                }else{
                    mp.start();
                    isPlaying = true;

                    Drawable img = getContext().getResources().getDrawable( R.drawable.ic_pause_black_24dp );
                    playPauseButton.setImageDrawable(img);
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp = MediaPlayer.create(getContext(), R.raw.felieton);
                isPlaying = false;

                Drawable img = getContext().getResources().getDrawable( R.drawable.ic_play_arrow_black_24dp );
                playPauseButton.setImageDrawable(img);


            }
        });

        handleBackPress(view);
        return view;

    }

    private void switchToLessonOne() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        Fragment fragment = LessonOneFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_fl_, fragment)
                .commit();
    }

    private void switchToLessonThree() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        Fragment fragment = LessonThreeFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_fl_, fragment)
                .commit();
    }


    private void handleBackPress(View view) {

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener((v, keyCode, event) -> {
            if( keyCode == KeyEvent.KEYCODE_BACK )
            {
                stopPlaying();
                getActivity().getSupportFragmentManager().popBackStack();
                return true;
            }
            return false;
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        stopPlaying();
    }

    private void stopPlaying() {
        if (isPlaying){
            mp.stop();
        }
    }

}
