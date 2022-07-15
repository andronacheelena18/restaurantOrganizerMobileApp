package Menu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantorg.orgrestaurant.Adapters.ItemAdapter;
import com.example.restaurantorg.orgrestaurant.Models.Item;
import com.example.restaurantorg.orgrestaurant.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WineMenuActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private ItemAdapter adapter;
    private ArrayList<Item> itemsList;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_menu);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemsList= new ArrayList<>();
        adapter=new ItemAdapter(this,itemsList);
        recyclerView.setAdapter(adapter);
        createListData();


    }
    private void createListData() {
        databaseReference = FirebaseDatabase.getInstance("https://restaurant-organizer-7518e-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Items");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()) {
                    Item item = ds.getValue(Item.class);
                    if (item != null) {

                     Item item1=new Item(item.getId(),item.getName(),item.getPrice(),item.getDescription(),item.getCatid());
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
}