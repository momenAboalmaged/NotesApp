package com.example.notesapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.R;
import com.example.notesapp.data.SharedPref;
import com.example.notesapp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
   private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);


        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=binding.editTextName.getText().toString();
                String phone=binding.editTextPhone.getText().toString();
                String email=binding.editTextEmail.getText().toString();
                if (checkUserData(name,email,phone)){
                    writeToSharedPref(name,email,phone);
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                }

               else
                Toast.makeText(RegisterActivity.this, "data invalid", Toast.LENGTH_SHORT).show();


            }
        });


    }

    private  boolean checkUserData(String name, String email , String phone){
      return !name.isEmpty()&&!email.isEmpty()&&!phone.isEmpty();

    }


    private void writeToSharedPref(String name, String email,String phone ) {
        SharedPref.addToSharedPref(RegisterActivity.this,name,email,phone);
    }

}