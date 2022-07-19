package com.example.restaurantorg.orgrestaurant;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantorg.orgrestaurant.Adapters.OrderAdapter;
import com.example.restaurantorg.orgrestaurant.Models.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    private OrderAdapter adapter;
    private Button button;
    int total;
    TextView tv;

    private ArrayList<Item> itemsList;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseItem;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        recyclerView = findViewById(R.id.recyclerview_order);
        firebaseDatabase = FirebaseDatabase.getInstance("https://restaurant-organizer-7518e-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseItem = firebaseDatabase.getReference().child("Bills");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        itemsList = new ArrayList<>();
        adapter = new OrderAdapter(this, itemsList);
        recyclerView.setAdapter(adapter);
        tv=findViewById(R.id.tv_total_earn);
        createListData();


    }



    private void createListData() {
        databaseReference = FirebaseDatabase.getInstance("https://restaurant-organizer-7518e-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Orders");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Item item = ds.getValue(Item.class);
                    if (item != null) {
                        Item item1 = new Item(item.getId(), item.getName(), item.getPrice(), item.getDescription(), item.getCatid());
                           itemsList.add(item1);


                    }

                }


                    for (int i = 0; i < itemsList.size(); i++)
                    {
                        total= total+Integer.parseInt(itemsList.get(i).getPrice());
                    }
                    tv.setText(String.valueOf(total)+"  lei ");

                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onClick(View v) {

    }


}
