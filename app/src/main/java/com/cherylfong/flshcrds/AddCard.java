package com.cherylfong.flshcrds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        String q = getIntent().getStringExtra("quest");
        String a = getIntent().getStringExtra("ans");

        TextView quest = findViewById(R.id.q_field);
        TextView ans = findViewById(R.id.a_field);

        quest.setText(q);
        ans.setText(a);


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

                if( quest.matches("") && ans.matches("")){
                    Toast.makeText(getApplicationContext(), "Edit fields are empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (quest.matches("")){
                    Toast.makeText(getApplicationContext(), "Question field is empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (ans.matches("")){
                    Toast.makeText(getApplicationContext(), "Answer field is empty!", Toast.LENGTH_SHORT).show();
                    return;
                }


                Intent intent = new Intent();

                intent.putExtra("quest", quest);
                intent.putExtra("ans", ans);
                setResult( RESULT_OK, intent);
                finish();

            }
        });
    }

}
