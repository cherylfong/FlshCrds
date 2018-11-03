package com.cherylfong.flshcrds;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.flashc_question).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                    findViewById(R.id.flashc_answer).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashc_question).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.flashc_answer).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                findViewById(R.id.flashc_answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashc_question).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.addCardButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCard.class);
                startActivityForResult(intent, 100);

            }
        });

        findViewById(R.id.editCardButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCard.class);

                TextView q = findViewById(R.id.flashc_question);
                TextView a = findViewById(R.id.flashc_answer);

                String ans = a.getText().toString();
                String quest = q.getText().toString();


                intent.putExtra("quest", quest);
                intent.putExtra("ans",  ans);

                startActivityForResult(intent, 100);

            }
        });


    }

    // ONLY called when child's finish() is called

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        // request code 100 is from AddCard.java activity!

        if (resultCode == RESULT_OK) {

            if (intent != null) {

                if (requestCode == 100) {

                    // context i.e. flashcard question textview should on the activity that it resides
                    Snackbar.make(findViewById(R.id.flashc_question),
                            "Card successful saved",
                            Snackbar.LENGTH_SHORT)
                            .show();

                    String quest = intent.getExtras().getString("quest");
                    String ans = intent.getExtras().getString("ans");

                    TextView qText = findViewById(R.id.flashc_question);
                    TextView aText = findViewById(R.id.flashc_answer);

                    qText.setText(quest);
                    aText.setText(ans);
                }
            }
        }
    }


}
