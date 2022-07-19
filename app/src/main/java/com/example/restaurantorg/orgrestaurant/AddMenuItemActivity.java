package com.example.restaurantorg.orgrestaurant;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantorg.orgrestaurant.Models.Item;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class AddMenuItemActivity extends AppCompatActivity {

    private EditText foodName, foodPrice, foodDesc;
    private RadioGroup cuisineGroup;
    private RadioButton selectedCuisineRadioBtn;
    private Button addItemBtn;
    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_item);
        foodName = findViewById(R.id.add_item_food_name_et);
        foodPrice = findViewById(R.id.add_item_food_price_et);
        foodDesc = findViewById(R.id.add_item_food_desc_et);
        cuisineGroup = findViewById(R.id.cuisine_group);
        firebaseDatabase = FirebaseDatabase.getInstance("https://restaurant-organizer-7518e-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseItem = firebaseDatabase.getReference().child("Items");
        addItemBtn = (Button) findViewById(R.id.add_item_button);
        addItemBtn.setOnClickListener(this::OnClick);
        //lvItems = findViewById(R.id.main_lv_item);

    }

    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.add_item_button:
                addItem();
                break;
        }

    }


    public void addItem() {
        int selectedId = cuisineGroup.getCheckedRadioButtonId();
        selectedCuisineRadioBtn = findViewById(selectedId);
        String cod = getFoodCuisineCode(selectedCuisineRadioBtn.getText().toString());
        String name = foodName.getText().toString().trim();
        String price = foodPrice.getText().toString().trim();
        String description = foodDesc.getText().toString().trim();
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setCatid(cod);


        Map<String, Object> foodMap = new HashMap<>();
        foodMap.put("foodName",foodName);
        foodMap.put("foodDesc", foodDesc);
        foodMap.put("foodPrice", foodPrice);
        foodMap.put("foodCode", cod);

        databaseItem.push().setValue(item);
        Toast.makeText(AddMenuItemActivity.this, "Item added to menu", Toast.LENGTH_SHORT).show();
        finish();
    }




    private String getFoodCuisineCode(String cuisineSelected) {

        String res = "";

        switch (cuisineSelected){
            case "Appetizers":res="101";
                break;
            case "Drinks":res="102";
                break;
            case "Food":res="103";
                break;
            case "Desserts":res="104";
                break;
            case "Wine":res="105";
                break;
        }

        return res;

    }


}


