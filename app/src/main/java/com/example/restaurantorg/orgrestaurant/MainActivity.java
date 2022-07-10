package com.example.restaurantorg.orgrestaurant;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;


public class MainActivity extends Activity implements View.OnClickListener{


    private Button btnStaff, btnOrder;
    private TextView viewtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStaff =  findViewById(R.id.btnStaff);
        btnOrder =  findViewById(R.id.btnOrder);
        btnStaff.setOnClickListener(this);
        btnOrder.setOnClickListener(this);
        viewtitle=findViewById(R.id.tv_title);

        Typeface typeface = ResourcesCompat.getFont(
                this,
                R.font.lobster_regular);
        viewtitle.setTypeface(typeface);


    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnStaff)){



            startActivity(new Intent(this, StaffLoginActivity.class));
            //salvarea in fisierul de preferinta



        }
            //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        else if (v.equals(btnOrder)){
            Intent ıntent = new Intent(this, RestaurantMenuActivity.class);
            startActivity(ıntent);
            //startActivity(new Intent(getApplicationContext(), UserActivity.class));
            //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
           // SharedPreferences sharedpreferences = getSharedPreferences("userBasket", Context.MODE_PRIVATE);
            //SharedPreferences.Editor editor = sharedpreferences.edit();
            //editor.putBoolean("user", true);
            //editor.commit();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //TODO:App is finish
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
