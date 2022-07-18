package com.example.restaurantorg.orgrestaurant.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.restaurantorg.orgrestaurant.Models.Item;
import com.example.restaurantorg.orgrestaurant.R;

import java.util.ArrayList;

public class BillAdapter extends ArrayAdapter<Item> {

    // invoke the suitable constructor of the ArrayAdapter class
    public BillAdapter(@NonNull Context context, ArrayList<Item> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_bill, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        Item currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView textView1 = currentItemView.findViewById(R.id.tvName);
        textView1.setText(currentNumberPosition.getName());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView textView2 = currentItemView.findViewById(R.id.tvPrice);
        textView2.setText(currentNumberPosition.getPrice());

        // then return the recyclable view
        return currentItemView;
    }
}

