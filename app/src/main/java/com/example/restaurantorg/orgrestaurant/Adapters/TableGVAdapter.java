package com.example.restaurantorg.orgrestaurant.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.restaurantorg.orgrestaurant.Models.Table;
import com.example.restaurantorg.orgrestaurant.R;

import java.util.ArrayList;

public class TableGVAdapter extends ArrayAdapter<Table> {
    public TableGVAdapter(@NonNull Context context, ArrayList<Table> tableArrayList) {
        super(context, 0, tableArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }
        Table table = getItem(position);
        TextView courseTV = listitemView.findViewById(R.id.idTVCourse);
        ImageView courseIV = listitemView.findViewById(R.id.idIVcourse);
        courseTV.setText(table.getCourse_name());
        courseIV.setImageResource(table.getImgid());
        return listitemView;
    }
}