package com.example.restaurantorg.orgrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantorg.orgrestaurant.Adapters.TableGVAdapter;
import com.example.restaurantorg.orgrestaurant.Models.Table;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class StaffTablesActivity extends AppCompatActivity implements View.OnClickListener {
    GridView tableGV;
    Button updateMenuButton;
    Button logoutButton;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_tables);
        tableGV = findViewById(R.id.idGVTables);
        updateMenuButton = (Button) findViewById(R.id.button_menu_item);
        updateMenuButton.setOnClickListener(this);
        logoutButton=findViewById(R.id.btn_log_out);
        logoutButton.setOnClickListener(this);
        button= findViewById(R.id.button2);
        button.setOnClickListener(this);





        ArrayList<Table> courseModelArrayList = new ArrayList<Table>();
        courseModelArrayList.add(new Table("#Table 1", R.drawable.restaurant_icon, 1));
        courseModelArrayList.add(new Table("#Table 2", R.drawable.restaurant_icon, 2));
        courseModelArrayList.add(new Table("#Table 3", R.drawable.restaurant_icon, 3));
        courseModelArrayList.add(new Table("#Table 4", R.drawable.restaurant_icon, 4));
        courseModelArrayList.add(new Table("#Table 5", R.drawable.restaurant_icon, 5));
        courseModelArrayList.add(new Table("#Table 6", R.drawable.restaurant_icon, 6));

        TableGVAdapter adapter = new TableGVAdapter(this, courseModelArrayList);
        tableGV.setAdapter(adapter);
        tableGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:{ Intent i = new Intent(StaffTablesActivity.this, TableActivity.class);
                         i.putExtra("key","1");
                        startActivity(i);}
                        break;
                    case 1:{ Intent i = new Intent(StaffTablesActivity.this, TableActivity.class);
                        i.putExtra("key","2");
                        startActivity(i);}
                         break;
                    case 2:{ Intent i = new Intent(StaffTablesActivity.this, TableActivity.class);
                        i.putExtra("key","3");
                        startActivity(i);}
                    break;
                    case 3:{ Intent i = new Intent(StaffTablesActivity.this, TableActivity.class);
                        i.putExtra("key","4");
                        startActivity(i);}
                    break;
                    case 4:{ Intent i = new Intent(StaffTablesActivity.this, TableActivity.class);
                        i.putExtra("key","5");
                        startActivity(i);}
                    break;
                    case 5:{ Intent i = new Intent(StaffTablesActivity.this, TableActivity.class);
                        i.putExtra("key","6");
                        startActivity(i);}
                    break;



                }
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_menu_item:
            { startActivity(new Intent(this, AddMenuItemActivity.class));
                break;}
            case R.id.btn_log_out:
            {  FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;}
            case R.id.button2:
            { startActivity(new Intent(this,OrderHistoryActivity.class));
                break;}

        }
    }

}