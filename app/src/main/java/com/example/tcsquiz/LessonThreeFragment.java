package com.example.tcsquiz;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import bg.devlabs.fullscreenvideoview.FullscreenVideoView;
import bg.devlabs.fullscreenvideoview.listener.mediacontroller.MediaControllerListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class LessonThreeFragment extends Fragment {


    private ImageButton homeButton;
    private ImageButton quizButton;
    private TextView secondLessonButton;
    private TextView secondLessonBottomButton;
    private TextView firstLessonButton;
    private TextView firstLessonBottomButton;

    //private ExoVideoView videoView;

    private FullscreenVideoView videoView;
    private View wrapper;

    public LessonThreeFragment() {
        // Required empty public constructor
    }

    public static LessonThreeFragment newInstance() {
        return new LessonThreeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lesson_three, container, false);

        wrapper = view.findViewById(R.id.wrapper);
        homeButton = view.findViewById(R.id.homeButton);
        quizButton = view.findViewById(R.id.startQuizButton);
        firstLessonButton = view.findViewById(R.id.quizLessonOnePointer);
        firstLessonBottomButton = view.findViewById(R.id.quizLessonOneBottomPointer);
        secondLessonButton = view.findViewById(R.id.quizLessonTwoPointer);
        secondLessonBottomButton = view.findViewById(R.id.quizLessonTwoBottomPointer);


        videoView = view.findViewById(R.id.video);

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




        new LoadVideoTask().execute();

        videoView.mediaControllerListener(new MediaControllerListener() {
            @Override
            public void onPlayClicked() {
                wrapper.setKeepScreenOn(true);
            }

            @Override
            public void onPauseClicked() {
                wrapper.setKeepScreenOn(false);
            }

            @Override
            public void onRewindClicked() { }

            @Override
            public void onFastForwardClicked() { }

            @Override
            public void onFullscreenClicked() { }

            @Override
            public void onSeekBarProgressChanged(long progressMs) { }
        });

        handleBackPress(view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            wrapper.post(new Runnable() {
                @Override
                public void run() {
                    wrapper.scrollTo(0, 0);
                }
            });
        }
        super.onActivityCreated(savedInstanceState);
    }


    private class LoadVideoTask extends AsyncTask<Void, Void, File> {

        protected File doInBackground(Void... params) {
            InputStream ins = getResources().openRawResource(
                    R.raw.hannawald);
            File file = new File(Environment.getExternalStorageDirectory(), "Video");
            this.copyInputStreamToFile(ins, file);

            return file;
        }

        private void copyInputStreamToFile(InputStream in, File file) {
            OutputStream out = null;

            try {
                out = new FileOutputStream(file);
                byte[] buf = new byte[1024];
                int len;
                while((len=in.read(buf))>0){
                    out.write(buf,0,len);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                // Ensure that the InputStreams are closed even if there's an exception.
                try {
                    if ( out != null ) {
                        out.close();
                    }

                    in.close();
                }
                catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        }
        protected void onPostExecute(File file) {
            videoView.videoFile(file);
        }
    }

    private void switchToLessonOne() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        Fragment fragment = LessonOneFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_fl_, fragment)
                .commit();
    }

    private void switchToLessonTwo() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        Fragment fragment = LessonTwoFragment.newInstance();
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
                videoView.pause();
                getActivity().getSupportFragmentManager().popBackStack();
                return true;
            }
            return false;
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        videoView.pause();
    }

}
