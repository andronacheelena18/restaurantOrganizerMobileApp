package com.example.restaurantorg.orgrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import Menu.DrinksMenuActivity;
import Menu.FoodMenuActivity;
import Menu.WineMenuActivity;

public class RestaurantMenuActivity extends AppCompatActivity implements View.OnClickListener {

  private ImageButton btn_foodMenu;
  private ImageButton btn_drinksMenu;
  private ImageButton btn_WineMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        btn_foodMenu=findViewById(R.id.btn_local_food);
        btn_foodMenu.setOnClickListener(this);
        btn_drinksMenu=findViewById(R.id.btn_drinks);
        btn_drinksMenu.setOnClickListener(this);
        btn_WineMenu=findViewById(R.id.btn_wine);
        btn_WineMenu.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_drinks:
            { startActivity(new Intent(this, DrinksMenuActivity.class));
                break;}
            case R.id.btn_local_food:
            { startActivity(new Intent(this, FoodMenuActivity.class));
                break;}
            case R.id.btn_wine:
            { startActivity(new Intent(this, WineMenuActivity.class));
                break;}

        }}
}