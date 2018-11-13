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

    private static boolean multiOn = false;

    public static boolean getMultiOption() {
        return multiOn;
    }

    public static void setMultiOption(boolean b) {
        multiOn = b;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        String q = getIntent().getStringExtra("quest");
        String a = getIntent().getStringExtra("ans");


        TextView quest = findViewById(R.id.q_field);
        TextView ans = findViewById(R.id.a_field);

        quest.setText(q);

        Log.i("HELPPP","ONCREATE");

        if( multiOn ){
            Log.i("HELPPP","MULTION IS TRUE");

            String a1 = getIntent().getStringExtra("ans1");
            String a2 = getIntent().getStringExtra("ans2");
            String a3 = getIntent().getStringExtra("ans3");

            TextView ans2 = findViewById(R.id.multichoice_field2);
            TextView ans3 = findViewById(R.id.multichoice_field3);

            ans.setText(a1);
            ans2.setText(a2);
            ans3.setText(a3);

            ans2.setVisibility(View.VISIBLE);
            ans3.setVisibility(View.VISIBLE);


        } else {

            ans.setText(a);
        }

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

                if (multiOn){

                    String ans2 = ((EditText) findViewById(R.id.multichoice_field2)).getText().toString();
                    String ans3 = ((EditText) findViewById(R.id.multichoice_field3)).getText().toString();

                    intent.putExtra("ans2", ans2);
                    intent.putExtra("ans3", ans3);
                }

                setResult( RESULT_OK, intent);
                finish();

            }
        });

        findViewById(R.id.multichoice_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if( !multiOn ){
                    findViewById(R.id.multichoice_field2).setVisibility(View.VISIBLE);
                    findViewById(R.id.multichoice_field3).setVisibility(View.VISIBLE);
                    multiOn = true;
                } else {
                    findViewById(R.id.multichoice_field2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.multichoice_field3).setVisibility(View.INVISIBLE);
                    multiOn = false;
                }


            }
        });
    }

}
