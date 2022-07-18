package Menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantorg.orgrestaurant.Adapters.ItemAdapterFood;
import com.example.restaurantorg.orgrestaurant.BillActivity;
import com.example.restaurantorg.orgrestaurant.Models.Bill;
import com.example.restaurantorg.orgrestaurant.Models.Item;
import com.example.restaurantorg.orgrestaurant.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoodMenuActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    private ItemAdapterFood adapter;
    private Button button;
    private ArrayList<Item> bill;
    private ArrayList<Item> itemsList;
    private Bill billList;
    private String tableNumber;
    public int orderNumber=0;
    ImageButton img;
    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseItem;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);
        recyclerView = findViewById(R.id.recyclerviewFood);
        firebaseDatabase = FirebaseDatabase.getInstance("https://restaurant-organizer-7518e-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseItem = firebaseDatabase.getReference().child("Bills");
        button = findViewById(R.id.btn_add_tocart);

        img = findViewById(R.id.imageButton4);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        itemsList = new ArrayList<>();
        bill = new ArrayList<>();
        adapter = new ItemAdapterFood(this, itemsList);
        recyclerView.setAdapter(adapter);
        createListData();
        button.setOnClickListener(this);
        img.setOnClickListener(this);


        Intent intent = getIntent();
        String str;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             str= extras.getString("key");
             tableNumber=str;
            //The key argument here must match that used in the other activity
        }



    }



    private void ShowToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void createListData() {

        databaseReference = FirebaseDatabase.getInstance("https://restaurant-organizer-7518e-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Items");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Item item = ds.getValue(Item.class);
                    if (item != null) {


                        Item item1 = new Item(item.getId(), item.getName(), item.getPrice(), item.getDescription(), item.getCatid());
                        item1.setTableNumber(tableNumber);
                         if (item.getCatid().matches("103"))
                            itemsList.add(item1);


                    }

                }
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_tocart: {
                {addToCart();
                   }
                break;
            }
            case R.id.imageButton4: {
                String str = tableNumber;

                Intent i = new Intent(FoodMenuActivity.this, BillActivity.class);
                i.putExtra("key",str);
                startActivity(i);
            }break;

        }
    }

    private void addToCart() {
        if(adapter.getSelected()!=null)
        {

            ShowToast(adapter.getSelected().getName());

            databaseItem.push().setValue(adapter.getSelected());


        }
        else
            ShowToast("No selection");
    }
}