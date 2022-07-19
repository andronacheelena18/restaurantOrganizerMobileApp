package com.example.restaurantorg.orgrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import Menu.AppetizerMenuActivity;
import Menu.DessertMenuActivity;
import Menu.DrinksMenuActivity;
import Menu.FoodMenuActivity;
import Menu.WineMenuActivity;

public class RestaurantMenuActivity extends AppCompatActivity implements View.OnClickListener {

  private ImageButton btn_foodMenu;
  private ImageButton btn_drinksMenu;
  private ImageButton btn_WineMenu;
  private ImageButton btn_dessertsMenu;
  private ImageButton btn_appetizersMenu;
  private EditText etTnumber;

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
        btn_dessertsMenu=findViewById(R.id.imageButtonDessert);
        btn_dessertsMenu.setOnClickListener(this);
        btn_appetizersMenu=findViewById(R.id.btn_snack);
        btn_appetizersMenu.setOnClickListener(this);
        etTnumber=(EditText) findViewById(R.id.et_tabnum);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_drinks:
            {  String str = etTnumber.getText().toString();

                Intent i = new Intent(RestaurantMenuActivity.this, DrinksMenuActivity.class);
                i.putExtra("key",str);
                startActivity(i);
            }
            break;
            case R.id.btn_local_food:
            { TableNumSet();}


                break;
            case R.id.btn_wine:
            {  String str = etTnumber.getText().toString();

                Intent i = new Intent(RestaurantMenuActivity.this, WineMenuActivity.class);
                i.putExtra("key",str);
                startActivity(i);
                }
                break;
            case R.id.imageButtonDessert:
            {  String str = etTnumber.getText().toString();

                Intent i = new Intent(RestaurantMenuActivity.this, DessertMenuActivity.class);
                i.putExtra("key",str);
                startActivity(i);
            }break;
            case R.id.btn_snack:
            {  String str = etTnumber.getText().toString();

                Intent i = new Intent(RestaurantMenuActivity.this, AppetizerMenuActivity.class);
                i.putExtra("key",str);
                startActivity(i);
            }break;


        }}

    private void TableNumSet() {
        String str = etTnumber.getText().toString();

        Intent i = new Intent(RestaurantMenuActivity.this, FoodMenuActivity.class);
        i.putExtra("key",str);
        startActivity(i);
    }
}