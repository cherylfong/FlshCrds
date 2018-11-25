package com.cherylfong.flshcrds;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

@Entity
public class Flashcard {

    /*
     * VARIABLES
     */

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uuid")
    private String uuid;

    @NonNull
    @ColumnInfo(name = "question")
    private String question;

    @ColumnInfo(name = "answer")
    private String answer;

    @Nullable
    @ColumnInfo(name = "answer_2")
    private String answer2;

    @Nullable
    @ColumnInfo(name = "answer_3")
    private String answer3;

    /*
     * Constructors
     */
    @Ignore
    Flashcard(String question, String answer){
        this.uuid = UUID.randomUUID().toString();
        this.question = question;
        this.answer = answer;
    }

    Flashcard(String question, String answer, String answer2, String answer3){
        this.uuid = UUID.randomUUID().toString();
        this.question = question;
        this.answer = answer;
        this.answer2 = answer2;
        this.answer3 = answer3;
    }


    /*
     * Getter and Setters
     */

    // NOTE: Do not change getUuid and setUuid to get/setUUID -- otherwise compiler error!
    @NonNull
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    // question
    public String getQuestion(){
        return this.question;
    }
    public void setQuestion(String question){
        this.question = question;
    }

    // answer
    public String getAnswer(){
        return this.answer;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }

    // answer2
    public String getAnswer2(){
        return this.answer2;
    }
    public void setAnswer2(String answer){
        this.answer2 = answer;
    }

    // answer3
    public String getAnswer3(){
        return this.answer3;
    }
    public void setAnswer3(String answer){
        this.answer = answer;
    }
}
