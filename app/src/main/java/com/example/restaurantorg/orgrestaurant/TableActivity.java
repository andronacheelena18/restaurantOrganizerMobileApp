package com.example.restaurantorg.orgrestaurant;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantorg.orgrestaurant.Adapters.BillAdapter;
import com.example.restaurantorg.orgrestaurant.Models.Item;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {
   ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        lv=findViewById(R.id.lv_table);
        ArrayList<Item> arrayList=new ArrayList<>();

        BillAdapter billAdapter = new BillAdapter(this,(ArrayList<Item>) arrayList);
        lv.setAdapter(billAdapter);

    }



}