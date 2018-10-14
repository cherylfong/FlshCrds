package com.cherylfong.flshcrds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


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

        findViewById(R.id.qfill).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(findViewById(R.id.qfill).getVisibility() == View.VISIBLE){

                    findViewById(R.id.qfill).setVisibility(View.INVISIBLE);

                }else{

                    findViewById(R.id.qfill).setVisibility(View.VISIBLE);
                }
            }
        });

    }



}
