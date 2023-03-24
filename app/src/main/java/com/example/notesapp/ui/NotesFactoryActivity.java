package com.example.notesapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.notesapp.R;
import com.example.notesapp.databinding.ActivityNotesFactoryBinding;
import com.example.notesapp.db.NoteDB;
import com.example.notesapp.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesFactoryActivity extends AppCompatActivity implements AddDialogCategory.ExampleDialogListener {
    ArrayAdapter<String> adapter;
    List<String> data;
    ActivityNotesFactoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotesFactoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Spinner spinner = (Spinner) findViewById(R.id.notes_spinner);


        data = new ArrayList<>();
        data.add("Android");
        data.add("iOS");
        data.add("Google");
        data.add("Apple");
        data.add("Java");
        data.add("Gym");
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, data);
        binding.notesSpinner.setAdapter(adapter);

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = binding.notesSpinner.getSelectedItem().toString();
                String description = binding.notesEt.getText().toString();
                if (!description.isEmpty()) {
                    addNote(category, description);
                }
            }
        });


    }


    private void addNote(String category, String description) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Note note = new Note(0, category, description);
                NoteDB noteBuilder = NoteDB.getInstance(NotesFactoryActivity.this);
                noteBuilder.getNoteDao().addNote(note);
            }
        }).start();
        Toast.makeText(NotesFactoryActivity.this, "data added successfully", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            //code
            openDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openDialog() {
        AddDialogCategory dialog = new AddDialogCategory();
        dialog.show(getSupportFragmentManager(), "dialog");
    }


    @Override
    public void applyTexts(String category) {
        if (!category.isEmpty()) {
            data.add(category);
            adapter.notifyDataSetChanged();
        }
    }



}