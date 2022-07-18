package com.example.restaurantorg.orgrestaurant;

import android.os.Bundle;
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

public class TableActivity extends AppCompatActivity {
   ListView lv;
    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseItem;

    DatabaseReference databaseReference;
    private String tablenr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        lv=findViewById(R.id.lv_table);

        String str;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            str= extras.getString("key");
            tablenr=str;
            }

        ArrayList<Item> arrayList=new ArrayList<>();
        BillAdapter billAdapter = new BillAdapter(this,(ArrayList<Item>) arrayList);
        databaseReference = FirebaseDatabase.getInstance("https://restaurant-organizer-7518e-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Bills");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Item item = ds.getValue(Item.class);
                    if (item != null) {

                        Item item1 = new Item(item.getId(), item.getName(), item.getPrice(), item.getDescription(), item.getCatid());
                        if(tablenr.matches(item.getTableNumber()))
                        arrayList.add(item1);



                    }

                }


                lv.setAdapter(billAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });






    }



}