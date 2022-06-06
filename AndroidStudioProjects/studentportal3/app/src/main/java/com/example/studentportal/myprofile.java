package com.example.studentportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class myprofile extends AppCompatActivity {
    TextView names,school,programme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        names = findViewById(R.id.name);
        school = findViewById(R.id.school);
        programme = findViewById(R.id.programme);
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference student = root.child("student details");
       ValueEventListener eventListener = new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for(DataSnapshot ds : snapshot.getChildren()) {
                   String name = ds.child("name").getValue(String.class);
                   String School = ds.child("school").getValue(String.class);
                   String programme1 = ds.child("programme").getValue(String.class);
                   names.setText("NAME: " +name);
                   school.setText("SCHOOL: " +School);
                   programme.setText("PROGRAMME: " +programme1);
                   Log.d("TAG", name + " / " + school);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(myprofile.this, "failed to get data", Toast.LENGTH_SHORT).show();
           }
       };
       student.addValueEventListener(eventListener);
    }
}