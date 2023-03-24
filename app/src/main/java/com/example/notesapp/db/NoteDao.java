package com.example.notesapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.notesapp.models.Note;

import java.util.List;

@Dao
public interface NoteDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNote(Note note);

    @Query("select * from notes")
    List<Note>getAllNotes();

    @Query("select * from notes where isFavourite = 1")
    List<Note>getFavNotes();

    @Query("delete from notes where id = :id")
    void deleteDevByID(int id);

    @Query("delete from notes")
    void deleteAll();



}


