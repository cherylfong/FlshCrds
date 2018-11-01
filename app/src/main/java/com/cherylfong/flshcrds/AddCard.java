package com.cherylfong.flshcrds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.closeAddCardButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.save_contents_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quest = ((EditText) findViewById(R.id.q_field)).getText().toString();
                String ans = ((EditText) findViewById(R.id.a_field)).getText().toString();

                Intent intent = new Intent();

                intent.putExtra("quest", quest);
                intent.putExtra("ans", ans);
                setResult( RESULT_OK, intent);
                finish();

            }
        });
    }
}
