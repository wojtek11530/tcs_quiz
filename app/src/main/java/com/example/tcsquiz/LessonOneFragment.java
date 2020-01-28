package com.example.tcsquiz;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LessonOneFragment extends Fragment {

    private ImageButton homeButton;
    private ImageButton quizButton;
    private TextView secondLessonButton;
    private TextView secondLessonBottomButton;
    private TextView thirdLessonButton;
    private TextView thirdLessonBottomButton;

    public LessonOneFragment() {
        // Required empty public constructor
    }

    public static LessonOneFragment newInstance() {
        return new LessonOneFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lesson_one, container, false);

        homeButton = view.findViewById(R.id.homeButton);
        quizButton = view.findViewById(R.id.startQuizButton);
        secondLessonButton = view.findViewById(R.id.quizLessonTwoPointer);
        secondLessonBottomButton = view.findViewById(R.id.quizLessonTwoBottomPointer);
        thirdLessonButton = view.findViewById(R.id.quizLessonThreePointer);
        thirdLessonBottomButton = view.findViewById(R.id.quizLessonThreeBottomPointer);

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

        secondLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLessonTwo();
            }
        });

        secondLessonBottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLessonTwo();
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

        return view;
    }

    private void switchToLessonTwo() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        Fragment fragment = LessonTwoFragment.newInstance();
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

}
