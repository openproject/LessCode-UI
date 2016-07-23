package com.jayfeng.lesscode.ui.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jayfeng.lesscode.core.ToastLess;
import com.jayfeng.lesscode.core.ViewLess;
import com.jayfeng.lesscode.ui.LessImageView;

public class MainActivity extends AppCompatActivity {

    private LessImageView mTestView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTestView = ViewLess.$(this, R.id.test);

        mTestView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastLess.$(MainActivity.this, "focus the image");
            }
        });
    }
}
