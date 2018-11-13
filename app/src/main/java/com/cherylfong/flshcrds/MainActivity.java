package com.cherylfong.flshcrds;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

                AddCard.setMultiOption(false);
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

                TextView a1 = findViewById(R.id.multi_choice1);
                TextView a2 = findViewById(R.id.multi_choice2);
                TextView a3 = findViewById(R.id.multi_choice3);

                String quest = q.getText().toString();


                intent.putExtra("quest", quest);


                if( AddCard.getMultiOption() ){

                    String ans1 = a1.getText().toString();
                    String ans2 = a2.getText().toString();
                    String ans3 = a3.getText().toString();

                    intent.putExtra("ans1", ans1);
                    intent.putExtra("ans2", ans2);
                    intent.putExtra("ans3", ans3);

                }else {

                    String ans = a.getText().toString();
                    intent.putExtra("ans",  ans);
                }

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

                    boolean toShow = AddCard.getMultiOption();

                    Log.i("HELPPP",  String.valueOf(toShow) );

                    if( toShow ){

                        String ans2 = intent.getExtras().getString("ans2");
                        String ans3 = intent.getExtras().getString("ans3");
                        aText.setText(""); // make similar to invisible
                        qText.setVisibility( View.VISIBLE );

                        TextView multiA1 = findViewById(R.id.multi_choice1);
                        TextView multiA2 = findViewById(R.id.multi_choice2);
                        TextView multiA3 = findViewById(R.id.multi_choice3);

                        multiA1.setText(ans);
                        multiA2.setText(ans2);
                        multiA3.setText(ans3);

                        multiA1.setVisibility( View.VISIBLE );
                        multiA2.setVisibility( View.VISIBLE );
                        multiA3.setVisibility( View.VISIBLE );

                        multiA1.setBackgroundResource(R.drawable.solid_color_shape);
                        multiA2.setBackgroundResource(R.drawable.solid_color_shape);
                        multiA3.setBackgroundResource(R.drawable.solid_color_shape);
                        qText.setBackgroundResource(R.drawable.radial_gradient_shape);
                        qText.setTextSize(30);
                    } else {

                        findViewById(R.id.multi_choice1).setVisibility( View.INVISIBLE );
                        findViewById(R.id.multi_choice2).setVisibility( View.INVISIBLE );
                        findViewById(R.id.multi_choice3).setVisibility( View.INVISIBLE );

                        qText.setBackgroundResource(0); // set to nothing
//                        qText.setTextSize(40);

                        qText.setVisibility( View.VISIBLE );
                        aText.setVisibility(View.INVISIBLE);
                    }


                }
            }
        }
    }


}
