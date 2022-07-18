package com.example.restaurantorg.orgrestaurant;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantorg.orgrestaurant.Adapters.BillAdapter;
import com.example.restaurantorg.orgrestaurant.Models.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity implements View.OnClickListener {
     ListView lv;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseItem;
    DatabaseReference databaseReference;
    private String tablenr;
    private Button btnOrderDelivered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        lv=findViewById(R.id.lv_table);
        btnOrderDelivered=findViewById(R.id.btn_order_delivered);
        btnOrderDelivered.setOnClickListener(this);

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


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_order_delivered:
            {deleteBill();}
                break;

        }}


        private void deleteBill () {

            DatabaseReference ref = FirebaseDatabase.getInstance("https://restaurant-organizer-7518e-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
            Query itemsQuery = ref.child("Bills").orderByChild("tableNumber").equalTo(tablenr.toString());

            itemsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                        appleSnapshot.getRef().removeValue();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());
                }
            });
            lv.setAdapter(null);
            Toast.makeText(TableActivity.this, "Order has been delivered", Toast.LENGTH_SHORT).show();
            finish();


        }

    }
