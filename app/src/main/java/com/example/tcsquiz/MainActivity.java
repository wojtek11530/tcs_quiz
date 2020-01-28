package com.example.tcsquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    FragmentManager fragmentManager;

    private int[] correctAnswers = {4, 2, 1};
    private int[] userAnswers = new int[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            startFragmentForLaunchedApp();
        }
    }

    private void startFragmentForLaunchedApp() {
        fragmentManager.popBackStack();
        MainMenuFragment songListFragment =
                MainMenuFragment.newInstance();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_fl_, songListFragment)
                .commit();
    }

    public int[] getCorrectAnswers() {
        return correctAnswers;
    }

    public int[] getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(int[] userAnswers) {
        this.userAnswers = userAnswers;
    }

    public void resetUserAnswers() {
        this.userAnswers = new int[3];
    }
}
