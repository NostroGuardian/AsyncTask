package com.nostrodev.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button startButton;
    ProgressBar progressBar;
    ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        startButton = findViewById(R.id.startButton);
        progressBar = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);

        progressBar.setVisibility(View.INVISIBLE);
        progressBar2.setVisibility(View.INVISIBLE);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ATask().execute();
            }
        });
    }

    class ATask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            progressBar2.setVisibility(View.VISIBLE);
            textView.setText("Started!");
            startButton.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... integers) {
            textView.setText("Progress: " + integers[0] + "%");
            progressBar2.setProgress(integers[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                int counter = 0;

                for (int i = 0; i < 100; i++) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    publishProgress(counter++);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            textView.setText("Done!");
            progressBar.setVisibility(View.INVISIBLE);
            progressBar2.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.VISIBLE);
            progressBar2.setProgress(0);
        }
    }

}