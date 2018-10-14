package com.cherylfong.flshcrds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.flashc_answer).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                // highlight right answer
                findViewById(R.id.flashc_answer).setBackgroundColor
                        (getResources().getColor(R.color.colorCorrect, null));

                // to give better contrast
                TextView tv = findViewById(R.id.flashc_answer);
                tv.setTextColor(getResources().getColor(R.color.colorYellow, null));

                // show yay!
            }
        });

        findViewById(R.id.flashc_answer2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                // hightlight right answer
                TextView tv = findViewById(R.id.flashc_answer2);
                tv.setTextColor(getResources().getColor(R.color.colorWrong, null));

                // to give better contrast
                TextView tv1 = findViewById(R.id.flashc_answer);
                tv1.setTextColor(getResources().getColor(R.color.colorYellow, null));


                // highlight right answer
                findViewById(R.id.flashc_answer).setBackgroundColor
                        (getResources().getColor(R.color.colorCorrect, null));
            }
        });

        findViewById(R.id.flashc_answer3).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                // highlight wrong answer
                TextView tv = findViewById(R.id.flashc_answer3);
                tv.setTextColor(getResources().getColor(R.color.colorWrong, null));

                // to give better contrast
                TextView tv1 = findViewById(R.id.flashc_answer);
                tv1.setTextColor(getResources().getColor(R.color.colorYellow, null));


                // highlight right answer
                findViewById(R.id.flashc_answer).setBackgroundColor
                        (getResources().getColor(R.color.colorCorrect, null));
            }
        });

    }



}
