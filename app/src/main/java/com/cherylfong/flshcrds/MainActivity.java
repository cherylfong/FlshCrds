package com.cherylfong.flshcrds;

import android.animation.Animator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    FlashcardDatabase fcDB; // db holder
    List<Flashcard> allFC; // all flash card objects

    int currCardIndex = 0;

    public static final int ADD_CARD_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize db
        fcDB = new FlashcardDatabase(this.getApplicationContext());
        allFC =fcDB.getAllCards();

        // if there are cards in the db
        // show the first item during app relaunch (not the default)
        if(allFC != null && allFC.size() > 0){

            TextView q = findViewById(R.id.flashc_question);
            q.setText(allFC.get(0).getQuestion());

            TextView a = findViewById(R.id.flashc_answer);
            a.setText(allFC.get(0).getAnswer());

        }

        findViewById(R.id.flashc_question).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                View ansView = findViewById(R.id.flashc_answer);
                // get center for clipping circle

                int cx = ansView.getWidth()/2;
                int cy = ansView.getHeight()/2;

                // get radius for clipping circle
                float radius = (float) Math.hypot(cx,cy);

                // View Animator, transistion from 0 to View's full radius size
                Animator ani = ViewAnimationUtils.createCircularReveal(ansView, cx, cy, 0f, radius );

                findViewById(R.id.flashc_question).setVisibility(View.INVISIBLE);
                ansView.setVisibility(View.VISIBLE);

                ani.setDuration(3000);
                ani.start();
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

                // paramL: enter animation for launched activity
                // paramR: exit animation for closing activity i.e MainActivity
                overridePendingTransition(R.anim.right_slide, R.anim.left_slide);

            }
        });

        // next button

        findViewById(R.id.nextCardButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // do this if only there's something in database
                if(allFC.size() != 0){


                    TextView q = findViewById(R.id.flashc_question);
                    TextView a = findViewById(R.id.flashc_answer);

                    // when currCardIndex surpasses the last card,
                    // make it back to zero again
                    if(currCardIndex >= allFC.size() - 1){
                        currCardIndex = 0;
                    }else {
                        // increment the next card index when tapped

                        currCardIndex++;
                    }

                    q.setText(allFC.get(currCardIndex).getQuestion());
                    a.setText(allFC.get(currCardIndex).getAnswer());

                    q.setVisibility(View.VISIBLE);
                    a.setVisibility(View.INVISIBLE);

                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        // request code 100 is from AddCard.java activity!

        if (resultCode == RESULT_OK) {

            if (intent != null) {

                if (requestCode == ADD_CARD_REQUEST_CODE) {

                    String quest = intent.getExtras().getString("quest");
                    String ans = intent.getExtras().getString("ans");

                    TextView qText = findViewById(R.id.flashc_question);
                    TextView aText = findViewById(R.id.flashc_answer);

                    qText.setText(quest);
                    aText.setText(ans);

                    fcDB.insertCard(new Flashcard(quest, ans));
                    allFC = fcDB.getAllCards(); // updates the list of flashcard objects
                }
            }
        }
    }


}
