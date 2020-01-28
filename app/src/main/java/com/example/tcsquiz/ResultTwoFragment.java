package com.example.tcsquiz;


import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultTwoFragment extends Fragment {


    private ImageButton homeButton;
    private ImageButton lessonButton;
    private ImageButton nextResultButton;
    private ImageButton prevResultButton;

    private RadioButton userAnswerRadioButton;
    private RadioButton correctAnswerRadioButton;
    private TextView resultTextView;



    public ResultTwoFragment() {
        // Required empty public constructor
    }

    public static ResultTwoFragment newInstance() {
        return new ResultTwoFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_result_two, container, false);

        homeButton = view.findViewById(R.id.homeButton);
        lessonButton = view.findViewById(R.id.lessonButton);
        nextResultButton = view.findViewById(R.id.nextResultButton);
        prevResultButton = view.findViewById(R.id.prevResultButton);
        resultTextView = view.findViewById (R.id.resultTextView);

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

        lessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack("lessons", 0);


                Fragment fragment = LessonTwoFragment.newInstance();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_fl_, fragment)
                        .commit();
            }
        });

        prevResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                Fragment fragment = ResultOneFragment.newInstance();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_fl_, fragment)
                        .commit();
            }
        });


        nextResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                Fragment fragment = ResultThreeFragment.newInstance();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_fl_, fragment)
                        .commit();
            }
        });

        final MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;

        int[] correctAnswers = mainActivity.getCorrectAnswers();
        int[] userAnswers = mainActivity.getUserAnswers();

        if(userAnswers[1] == 1) {
            userAnswerRadioButton = view.findViewById(R.id.answer1);
        } else if(userAnswers[1] == 2) {
            userAnswerRadioButton = view.findViewById(R.id.answer2);
        } else if(userAnswers[1] == 3) {
            userAnswerRadioButton = view.findViewById(R.id.answer3);
        } else if(userAnswers[1] == 4) {
            userAnswerRadioButton = view.findViewById(R.id.answer4);
        }

        if(correctAnswers[1] == 1) {
            correctAnswerRadioButton = view.findViewById(R.id.answer1);
        } else if(correctAnswers[1] == 2) {
            correctAnswerRadioButton = view.findViewById(R.id.answer2);
        } else if(correctAnswers[1] == 3) {
            correctAnswerRadioButton = view.findViewById(R.id.answer3);
        } else if(correctAnswers[1] == 4) {
            correctAnswerRadioButton = view.findViewById(R.id.answer4);
        }

        ColorStateList correctColorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_enabled} //enabled
                },
                new int[] {getResources().getColor(R.color.colorCorrect) }
        );

        ColorStateList wrongColorStateList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_enabled} //enabled
                },
                new int[] {getResources().getColor(R.color.colorWrong) }
        );

        userAnswerRadioButton.setButtonTintList(wrongColorStateList);
        userAnswerRadioButton.setTextColor(wrongColorStateList);
        userAnswerRadioButton.setChecked(true);

        correctAnswerRadioButton.setButtonTintList(correctColorStateList);
        correctAnswerRadioButton.setTextColor(correctColorStateList);
        correctAnswerRadioButton.setChecked(true);

        if (userAnswerRadioButton == correctAnswerRadioButton){
            resultTextView.setText("Odpowiedziałeś dobrze!");
            resultTextView.setTextColor(correctColorStateList);

        }else{
            resultTextView.setText("Odpowiedziałeś niepoprawnie");
            resultTextView.setTextColor(wrongColorStateList);
        }


        return view;
    }

}
