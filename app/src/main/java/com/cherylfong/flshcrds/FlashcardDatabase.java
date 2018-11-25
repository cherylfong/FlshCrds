package com.cherylfong.flshcrds;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class FlashcardDatabase {

    private static AppDatabase db;

    FlashcardDatabase(Context context) {
        if(db == null){

            db = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "flashcards")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

    }

    public List<Flashcard> getAllCards() {
        return db.flashcardDao().getAll();
    }

    public void insertCard(Flashcard flashcard) {
        Log.i("HELPPP", "insertAll() called!"); db.flashcardDao().insertAll(flashcard);
    }

    public void deleteCard(String flashcardQuestion) {
        List<Flashcard> allCards = db.flashcardDao().getAll();
        for (Flashcard f : allCards) {
            if (f.getQuestion().equals(flashcardQuestion)) {
                db.flashcardDao().delete(f);
            }
        }
    }

    public void updateCard(Flashcard flashcard) {
        db.flashcardDao().update(flashcard);
    }
}
