package com.example.restaurantorg.orgrestaurant;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantorg.orgrestaurant.Adapters.BillAdapter;
import com.example.restaurantorg.orgrestaurant.Models.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BillActivity extends AppCompatActivity {
    ListView listview;
    Button confirmButton;
    private String bills;
    FirebaseDatabase firebaseDatabase;
    public int ordernum=0;

    DatabaseReference databaseItem;

    DatabaseReference databaseReference;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        listview = findViewById(R.id.lv_bill);
        confirmButton = findViewById(R.id.btn_confirm);
        firebaseDatabase = FirebaseDatabase.getInstance("https://restaurant-organizer-7518e-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseItem = firebaseDatabase.getReference().child("Bills");
        ArrayList<Item> arrayList = new ArrayList<Item>();
        BillAdapter billAdapter = new BillAdapter(this, arrayList);
        databaseReference = FirebaseDatabase.getInstance("https://restaurant-organizer-7518e-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Bills");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Item item = ds.getValue(Item.class);
                    if (item != null) {
                        Item item1 = new Item(item.getId(), item.getName(), item.getPrice(), item.getDescription(), item.getCatid());

                        arrayList.add(item1);



                    }

                }

                listview.setAdapter(billAdapter);

                      }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });




    }



}