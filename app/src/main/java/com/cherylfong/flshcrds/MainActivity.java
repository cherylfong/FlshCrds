package com.cherylfong.flshcrds;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    FlashcardDatabase fcDB; // db holder
    List<Flashcard> allFC; // all flash card objects

    private int currCardIndex = 0;

    private static final int HAPPY_EMOJI_UNI_CODE = 0x1F604;

    public static final int ADD_CARD_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

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

//                    q.setText(allFC.get(currCardIndex).getQuestion());
//                    a.setText(allFC.get(currCardIndex).getAnswer());

                    int randIndex = getRandIndex();
                    q.setText(allFC.get(randIndex).getQuestion());
                    a.setText(allFC.get(randIndex).getAnswer());

                    q.setVisibility(View.VISIBLE);
                    a.setVisibility(View.INVISIBLE);
                }
            }
        });

        findViewById(R.id.deleteCardButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView q = findViewById(R.id.flashc_question);
                String q_string = q.getText().toString();

                TextView a = findViewById(R.id.flashc_answer);

                // delete question and answer
                fcDB.deleteCard(q_string);

                // update list
                allFC = fcDB.getAllCards();

                if(allFC.size() != 0){
                    q.setText(allFC.get(allFC.size()-1).getQuestion());
                    a.setText(allFC.get(allFC.size()-1).getAnswer());
                    q.setVisibility(View.VISIBLE);
                    a.setVisibility(View.INVISIBLE);

                } else {

                    String happyMoji = new String(Character.toChars(HAPPY_EMOJI_UNI_CODE));

                    q.setText("Only a Fish lives here... Time to add a new card " + happyMoji);
                    a.setText("Tap on the plus button to add a card.");
                    findViewById(R.id.just_fish_image).setVisibility(View.VISIBLE);

                    findViewById(R.id.bubble1).setVisibility(View.VISIBLE);
                    findViewById(R.id.bubble2).setVisibility(View.VISIBLE);
                    findViewById(R.id.bubble3).setVisibility(View.VISIBLE);
                }
            }
        });


    }

    // returns a random card index
    private int getRandIndex(){

        Random r = new Random();

        return r.nextInt(allFC.size()) + 0;
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

                    currCardIndex++;

                    findViewById(R.id.just_fish_image).setVisibility(View.INVISIBLE);
                    findViewById(R.id.bubble1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.bubble2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.bubble3).setVisibility(View.INVISIBLE);

                }
            }

            if(allFC.size() == 0){
                findViewById(R.id.just_fish_image).setVisibility(View.VISIBLE);
                findViewById(R.id.bubble1).setVisibility(View.VISIBLE);
                findViewById(R.id.bubble2).setVisibility(View.VISIBLE);
                findViewById(R.id.bubble3).setVisibility(View.VISIBLE);
            }
        }
    }


}
