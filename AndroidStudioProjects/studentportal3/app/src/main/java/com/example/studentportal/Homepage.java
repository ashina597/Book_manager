package com.example.studentportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {
    TextView details;
    GridView gridView;
    ArrayList itemsArrayList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        gridView = findViewById(R.id.gridview);
        details = findViewById(R.id.name);
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference student = root.child("student details");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    String name = ds.child("name").getValue(String.class);
                    details.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Homepage.this, "failed to get data", Toast.LENGTH_SHORT).show();
            }
        };
        student.addValueEventListener(eventListener);

        itemsArrayList.add(new Items("COURSE", R.drawable.course));
        itemsArrayList.add(new Items("EXAM", R.drawable.exam));
        itemsArrayList.add(new Items("RESULTS", R.drawable.results));
        itemsArrayList.add(new Items("FEES", R.drawable.fees));
        itemsArrayList.add(new Items("HOSTEL", R.drawable.hostel));
        itemsArrayList.add(new Items("MY PROFILE", R.drawable.profile));

        Homepage_adapter adapter = new Homepage_adapter(getApplicationContext(), itemsArrayList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                {
                    Intent intent = new Intent(Homepage.this, course.class);
                    startActivity(intent);
                }
                if (i == 1)
                {
                    Intent intent = new Intent(Homepage.this, exam.class);
                    startActivity(intent);
                }
                if (i == 2)
                {
                    Intent intent = new Intent(Homepage.this, results.class);
                    startActivity(intent);
                }
                if (i == 3)
                {
                    Intent intent = new Intent(Homepage.this,fees.class);
                    startActivity(intent);
                }
                if (i == 4)
                {
                    Intent intent = new Intent(Homepage.this, hostel.class);
                    startActivity(intent);
                }
                if (i == 5)
                {
                    Intent intent = new Intent(Homepage.this, myprofile.class);
                    startActivity(intent);
                }
            }
        });
    }
}