package com.example.notesapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notesapp.models.Note;

@Database(entities = Note.class, version = 1)
public abstract class NoteDB extends RoomDatabase {
    private static NoteDB instance;

    public abstract NoteDao getNoteDao();

    public static synchronized NoteDB getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDB.class, "NoteDB")
                    .build();
        }
        return instance;
    }
}
