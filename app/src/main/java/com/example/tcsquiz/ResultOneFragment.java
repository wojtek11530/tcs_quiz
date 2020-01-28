package com.example.tcsquiz;


import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultOneFragment extends Fragment {


    private ImageButton homeButton;
    private ImageButton lessonButton;
    private ImageButton nextResultButton;

    private ImageView innsbruckImageView;
    private ImageView oberstdorfImageView;
    private ImageView bischofshofenImageView;
    private ImageView gapaImageView;

    private TextView resultTextView;

    private RadioButton userAnswerRadioButton;
    private RadioButton correctAnswerRadioButton;

    public ResultOneFragment() {
        // Required empty public constructor
    }

    public static ResultOneFragment newInstance() {
        return new ResultOneFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_result_one, container, false);

        homeButton = view.findViewById(R.id.homeButton);
        lessonButton = view.findViewById(R.id.lessonButton);
        nextResultButton = view.findViewById(R.id.nextResultButton);

        innsbruckImageView = view.findViewById (R.id.innsbruckImageView);
        oberstdorfImageView = view.findViewById (R.id.oberstdorfImageView);
        bischofshofenImageView = view.findViewById (R.id.bischofshofenImageView);
        gapaImageView = view.findViewById (R.id.gapaImageView);
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


                Fragment fragment = LessonOneFragment.newInstance();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_fl_, fragment)
                        .commit();
            }
        });


        nextResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                Fragment fragment = ResultTwoFragment.newInstance();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_fl_, fragment)
                        .commit();
            }
        });

        Glide.with(this)
                .load(R.drawable.innsbruck)
                .centerCrop()
                .into(innsbruckImageView);

        Glide.with(this)
                .load(R.drawable.oberstdorf)
                .centerCrop()
                .into(oberstdorfImageView);

        Glide.with(this)
                .load(R.drawable.gapa)
                .centerCrop()
                .into(gapaImageView);

        Glide.with(this)
                .load(R.drawable.bischofshofen)
                .centerCrop()
                .into(bischofshofenImageView);

        final MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;

        int[] correctAnswers = mainActivity.getCorrectAnswers();
        int[] userAnswers = mainActivity.getUserAnswers();

        if(userAnswers[0] == 1) {
            userAnswerRadioButton = view.findViewById(R.id.answer1);
        } else if(userAnswers[0] == 2) {
            userAnswerRadioButton = view.findViewById(R.id.answer2);
        } else if(userAnswers[0] == 3) {
            userAnswerRadioButton = view.findViewById(R.id.answer3);
        } else if(userAnswers[0] == 4) {
            userAnswerRadioButton = view.findViewById(R.id.answer4);
        }

        if(correctAnswers[0] == 1) {
            correctAnswerRadioButton = view.findViewById(R.id.answer1);
        } else if(correctAnswers[0] == 2) {
            correctAnswerRadioButton = view.findViewById(R.id.answer2);
        } else if(correctAnswers[0] == 3) {
            correctAnswerRadioButton = view.findViewById(R.id.answer3);
        } else if(correctAnswers[0] == 4) {
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
