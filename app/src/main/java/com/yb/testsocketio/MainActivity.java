package com.yb.testsocketio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private Button   mButton;
    private Intent   mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.editText);
        mButton   = (Button) findViewById(R.id.btnLogin);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mIntent = new Intent(MainActivity.this,MessageActivity.class);
                    String username = mEditText.getText().toString();
                    mIntent.putExtra("uname",username);
                    startActivity(mIntent);
            }
        });
    }

}
