package com.example.studentportal;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class fees extends AppCompatActivity {
    TableLayout table;
    TableRow data;
    TextView date,amount,method, balance;
    List<String> dt = new ArrayList<>();
    List<String>  am = new ArrayList<>();
    List<String> pm = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees);
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference fees = root.child("fees");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> snapshotIterator = snapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext())
                {
                    data = new TableRow(fees.this);
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    String d = (String) next.child("date paid").getValue();
                    String a = (String) next.child("amount paid").getValue();
                    String p = (String) next.child("payment method").getValue();

                   dt.add(d);
                   am.add(a);
                   pm.add(p);
                }

                for(int i=0; i < dt.size(); i++){

                    date.setText(date.getText() + " " + dt.get(i) + "\n");
                }

                for(int j=0; j < am.size(); j++){

                    amount.setText(amount.getText() + " " + am.get(j) + "\n");
                }

                for(int k=0; k < pm.size(); k++){

                    method.setText(method.getText() + " " + pm.get(k) + "\n");
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        fees.addValueEventListener(eventListener);
    }
}