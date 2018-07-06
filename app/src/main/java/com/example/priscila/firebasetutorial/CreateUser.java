package com.example.priscila.firebasetutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateUser extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference dbRef;

    Button btnSaveUser;
    EditText etName, etEmail, etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference("Users");

        etName = findViewById(R.id.etName);
        etEmail= findViewById(R.id.etEmail);
        etAge= findViewById(R.id.etAge);

        btnSaveUser = findViewById(R.id.btnSaveUser);
        btnSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                int age = Integer.valueOf(etAge.getText().toString());

                UserSave userSave = new UserSave();
                userSave.setName(name);
                userSave.setEmail(email);
                userSave.setAge(age);

                String key =dbRef.push().getKey();
                dbRef.child(key).push().setValue(userSave);


                Toast.makeText(CreateUser.this, "Usuario Creado", Toast.LENGTH_SHORT).show();
                Intent gotoMainIntent = new Intent(CreateUser.this, MainActivity.class);
                startActivity(gotoMainIntent);
            }
        });
    }
}
