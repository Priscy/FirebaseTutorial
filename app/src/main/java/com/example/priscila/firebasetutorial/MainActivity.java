package com.example.priscila.firebasetutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.DefaultItemAnimator;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference dbRef;

    Button btnCreateUser;
    List<UserShow> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.rvUsers);

        btnCreateUser = findViewById(R.id.btnCreateUser);
        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createUserIntent = new Intent(MainActivity.this, CreateUser.class);
                startActivity(createUserIntent);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        loadUsers();

    }
    public void loadUsers(){
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference("Users");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getValue());
                UserSave userSave = dataSnapshot.getValue(UserSave.class);
                usersList = new ArrayList<>();
                DataSnapshot userSnapshot = dataSnapshot.child("Users");
                Iterable<DataSnapshot> usersSaved = userSnapshot.getChildren();

                for(DataSnapshot data : usersSaved){
                    UserShow userShow = new UserShow();
                    String name = userSave.getName();
                    String email = userSave.getEmail();
                    int age = Integer.valueOf(userSave.getAge());

                    userShow.setName(name);
                    userShow.setEmail(email);
                    userShow.setAge(age);
                    usersList.add(userShow);

                    RecyclerviewAdapter recycler = new RecyclerviewAdapter(usersList);
                    RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(layoutmanager);
                    recyclerView.setItemAnimator( new DefaultItemAnimator());
                    recyclerView.setAdapter(recycler);



                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Priscila", "Failed to read value.");

            }
        });

    }
}
