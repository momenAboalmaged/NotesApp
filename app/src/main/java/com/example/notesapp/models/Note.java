package com.example.notesapp.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    int id;
    int isFavourite;
    String category;
    String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Note(int isFavourite, String category, String description) {
        this.isFavourite = isFavourite;
        this.category = category;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", isFav=" + isFavourite +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
