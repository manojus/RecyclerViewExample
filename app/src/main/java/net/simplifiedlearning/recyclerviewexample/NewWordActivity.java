package net.simplifiedlearning.recyclerviewexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_REPLY1 = "com.example.android.wordlistsql.REPLY1";
    public static final String EXTRA_REPLY2 = "com.example.android.wordlistsql.REPLY2";

    private EditText mEditWordView,mEditWordView1,mEditWordView2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);
        mEditWordView1 = findViewById(R.id.edit_word1);
        mEditWordView2 = findViewById(R.id.edit_word2);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())||TextUtils.isEmpty(mEditWordView1.getText())||TextUtils.isEmpty(mEditWordView2.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);

                } else {
                    String word = mEditWordView.getText().toString();
                    String word1 = mEditWordView1.getText().toString();
                    String word2 = mEditWordView2.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    replyIntent.putExtra(EXTRA_REPLY1, word1);
                    replyIntent.putExtra(EXTRA_REPLY2, word2);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}

