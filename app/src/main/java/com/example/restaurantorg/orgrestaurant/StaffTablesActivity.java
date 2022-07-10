package com.example.restaurantorg.orgrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantorg.orgrestaurant.Adapters.TableGVAdapter;
import com.example.restaurantorg.orgrestaurant.Models.Table;

import java.util.ArrayList;

public class StaffTablesActivity extends AppCompatActivity implements View.OnClickListener {
    GridView tableGV;
    Button updateMenuButton;
    Button logoutButton;
    Button checkorderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_tables);
        tableGV = findViewById(R.id.idGVTables);
        updateMenuButton = (Button) findViewById(R.id.button_menu_item);
        updateMenuButton.setOnClickListener(this);




        ArrayList<Table> courseModelArrayList = new ArrayList<Table>();
        courseModelArrayList.add(new Table("#Table 1", R.drawable.restaurant_icon, 1));
        courseModelArrayList.add(new Table("#Table 2", R.drawable.restaurant_icon, 2));
        courseModelArrayList.add(new Table("#Table 3", R.drawable.restaurant_icon, 3));
        courseModelArrayList.add(new Table("#Table 4", R.drawable.restaurant_icon, 4));
        courseModelArrayList.add(new Table("#Table 5", R.drawable.restaurant_icon, 5));
        courseModelArrayList.add(new Table("#Table 6", R.drawable.restaurant_icon, 6));

        TableGVAdapter adapter = new TableGVAdapter(this, courseModelArrayList);
        tableGV.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_menu_item:
            { startActivity(new Intent(this, AddMenuItemActivity.class));
                break;}

        }
    }

}