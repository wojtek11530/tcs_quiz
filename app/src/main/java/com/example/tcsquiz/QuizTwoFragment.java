package com.example.tcsquiz;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizTwoFragment extends Fragment {

    private ImageButton homeButton;
    private ImageButton nextQuestionButton;
    private RadioGroup answersRadioGroup;

    private String transactionToBackTag;

    private static final String TRANSACTION_TAG_KEY = "TRANSACTION_TAG_KEY";

    public QuizTwoFragment() {
        // Required empty public constructor
    }

    public static QuizTwoFragment newInstance(String transactionTag) {
        QuizTwoFragment fragment = new QuizTwoFragment();
        Bundle arguments = new Bundle();
        arguments.putString(TRANSACTION_TAG_KEY, transactionTag);
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_two, container, false);

        homeButton = view.findViewById (R.id.homeButton);
        nextQuestionButton = view.findViewById (R.id.nextQuestionButton);

        assert getArguments() != null;
        if (getArguments().containsKey(TRANSACTION_TAG_KEY)) {
            transactionToBackTag = (String) getArguments().getSerializable(TRANSACTION_TAG_KEY);
        }

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

        nextQuestionButton.setEnabled(false);
        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                Fragment fragment = QuizThreeFragment.newInstance(transactionToBackTag);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_fl_, fragment)
                        .commit();
            }
        });

        final MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;

        answersRadioGroup = view.findViewById(R.id.answersRadioGroup);
        answersRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int[] userAnswers = mainActivity.getUserAnswers();

                if(checkedId == R.id.radioAnswer1) {
                    userAnswers[1] = 1;
                } else if(checkedId == R.id.radioAnswer2) {
                    userAnswers[1] = 2;
                } else if(checkedId == R.id.radioAnswer3) {
                    userAnswers[1] = 3;
                } else if(checkedId == R.id.radioAnswer4) {
                    userAnswers[1] = 4;
                }
                mainActivity.setUserAnswers(userAnswers);
                nextQuestionButton.setEnabled(true);
            }

        });

        return view;
    }
}
