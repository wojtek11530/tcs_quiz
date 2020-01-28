package com.example.tcsquiz;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizOneFragment extends Fragment implements View.OnClickListener {

    private ImageButton homeButton;
    private ImageButton nextQuestionButton;
    private RadioButton innsbruckRadioButton;
    private RadioButton oberstdorfRadioButton;
    private RadioButton bischofshofenRadioButton;
    private RadioButton gapaRadioButton;

    private ImageView innsbruckImageView;
    private ImageView oberstdorfImageView;
    private ImageView bischofshofenImageView;
    private ImageView gapaImageView;

    private String transactionToBackTag;

    private static final String TRANSACTION_TAG_KEY = "TRANSACTION_TAG_KEY";
    private MainActivity mainActivity;

    public QuizOneFragment() {
        // Required empty public constructor
    }

    public static QuizOneFragment newInstance(String transactionTag) {
        QuizOneFragment fragment = new QuizOneFragment();
        Bundle arguments = new Bundle();
        arguments.putString(TRANSACTION_TAG_KEY, transactionTag);
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_one, container, false);

        homeButton = view.findViewById (R.id.homeButton);
        nextQuestionButton = view.findViewById (R.id.nextQuestionButton);

        innsbruckImageView = view.findViewById (R.id.innsbruckImageView);
        oberstdorfImageView = view.findViewById (R.id.oberstdorfImageView);
        bischofshofenImageView = view.findViewById (R.id.bischofshofenImageView);
        gapaImageView = view.findViewById (R.id.gapaImageView);

        innsbruckRadioButton = view.findViewById (R.id.radioAnswerInnsbruck);
        oberstdorfRadioButton = view.findViewById (R.id.radioAnswerObersdorf);
        bischofshofenRadioButton = view.findViewById (R.id.radioAnswerBischofshofen);
        gapaRadioButton = view.findViewById (R.id.radioAnswerGapa);

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

                Fragment fragment = QuizTwoFragment.newInstance(transactionToBackTag);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_fl_, fragment)
                        .commit();
            }
        });

        mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;
        mainActivity.resetUserAnswers();



        innsbruckRadioButton.setOnClickListener(this);
        oberstdorfRadioButton.setOnClickListener(this);
        bischofshofenRadioButton.setOnClickListener(this);
        gapaRadioButton.setOnClickListener(this);
        innsbruckImageView.setOnClickListener(this);
        oberstdorfImageView.setOnClickListener(this);
        bischofshofenImageView.setOnClickListener(this);
        gapaImageView.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View v) {

        int[] userAnswers = mainActivity.getUserAnswers();

        switch (v.getId()) {
            case R.id.innsbruckImageView:
            case R.id.radioAnswerInnsbruck:
                userAnswers[0] = 1;
                clickedRadioButton(innsbruckRadioButton);
                break;
            case R.id.oberstdorfImageView:
            case R.id.radioAnswerObersdorf:
                userAnswers[0] = 2;
                clickedRadioButton(oberstdorfRadioButton);
                break;
            case R.id.bischofshofenImageView:
            case R.id.radioAnswerBischofshofen:
                userAnswers[0] = 3;
                clickedRadioButton(bischofshofenRadioButton);
                break;
            case R.id.gapaImageView:
            case R.id.radioAnswerGapa:
                userAnswers[0] = 4;
                clickedRadioButton(gapaRadioButton);
                break;
        }

        mainActivity.setUserAnswers(userAnswers);
        nextQuestionButton.setEnabled(true);
    }

    private void clickedRadioButton(RadioButton radioButton) {

        innsbruckRadioButton.setChecked(false);
        oberstdorfRadioButton.setChecked(false);
        bischofshofenRadioButton.setChecked(false);
        gapaRadioButton.setChecked(false);

        radioButton.setChecked(true);
    }
}
