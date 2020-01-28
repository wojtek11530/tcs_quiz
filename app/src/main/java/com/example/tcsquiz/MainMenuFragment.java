package com.example.tcsquiz;


import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends Fragment {


    private Button lessonsButton;
    private Button quizButton;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    public static MainMenuFragment newInstance() {
        return new MainMenuFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);


        lessonsButton = view.findViewById(R.id.lessons_button);
        quizButton = view.findViewById(R.id.start_quiz_button);


        lessonsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                Fragment fragment = LessonOneFragment.newInstance();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_fl_, fragment)
                        .addToBackStack("lessons")
                        .commit();
            }
        });

        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                String transactionTag = "main_menu";
                Fragment fragment = QuizOneFragment.newInstance(transactionTag);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_fl_, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

}
