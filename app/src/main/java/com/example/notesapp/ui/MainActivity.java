package com.example.notesapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.example.notesapp.R;
import com.example.notesapp.databinding.ActivityMainBinding;
import com.example.notesapp.databinding.ActivityRegisterBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_container);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);

        NavigationUI.setupWithNavController(navigationView, navController);



    }

}