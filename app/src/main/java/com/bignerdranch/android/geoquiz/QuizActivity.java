package com.bignerdranch.android.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    // Listing 3.1 - Adding a TAG constant
    private static final String TAG = "QuizActivity";
    // Listing 3.5 - Adding a key for the value
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;

    // Listing 2.6 - Adding variables and a Question array
    private Button mNextButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;
    // End

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Listing 3.2 - Adding a log statement to onCreate(Bundle)
        Log.d(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_quiz);

        // Listing 3.7 - Checking bundle in onCreate(Bundle)
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        // Listing 2.7 - Wiring up the TextView
        mQuestionTextView = findViewById(R.id.question_text_view);

        // Listing 2.9 - Encapsulating with updateQuestion()
        // int question = mQuestionBank[mCurrentIndex].getTextResId();
        // mQuestionTextView.setText(question);
        // End Listing 2.7
        // End Listing 2.9

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Listing 2.11 - Calling checkAnswer(boolean)
                // Toast.makeText(QuizActivity.this,
                //       R.string.correct_toast,
                //       Toast.LENGTH_SHORT).show();
                checkAnswer(true);
                // End
            }
        });

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Listing 2.11 - Calling checkAnswer(boolean)
                // Toast.makeText(QuizActivity.this,
                //       R.string.incorrect_toast,
                //       Toast.LENGTH_SHORT).show();
                checkAnswer(false);
                // End
            }
        });

        // Listing 2.8 - Wiring up the new button
        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;

                // Listing 2.9 - Encapsulating with updateQuestion()
                // int question = mQuestionBank[mCurrentIndex].getTextResId();
                // mQuestionTextView.setText(question);
                updateQuestion();
            }
        });

        updateQuestion();
        // End

    }

    // Listing 3.3 - Overriding more lifecycle methods
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    // Listing 3.6 - Overriding onSaveInstanceState(…)
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
    // End

    // Listing 2.9 - Encapsulating with updateQuestion()
    private void updateQuestion() {
        // Listing 4.3 - Exception for fun and profit
        // Log.d(TAG, "Updating question text", new Exception());
        //
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    // Listing 2.10 - Adding checkAnswer(boolean)
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show();
    }
    // End


}
