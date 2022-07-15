package com.example.restaurantorg.orgrestaurant.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantorg.orgrestaurant.Models.Item;
import com.example.restaurantorg.orgrestaurant.R;

import java.util.ArrayList;

import Menu.WineMenuActivity;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private Context context;
    private ArrayList<Item> items;

    public ItemAdapter(WineMenuActivity context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.menu_layout_item,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemHolder holder, int position) {
        Item item=items.get(position);
        holder.setDetails(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder{
    private TextView txtName, txtDescription,txtPrice;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtName);
            txtDescription=itemView.findViewById(R.id.txtDescription);
            txtPrice=itemView.findViewById(R.id.txtPrice);
        }

        void setDetails(Item item)
        {
            txtName.setText(item.getName());
            txtDescription.setText(item.getDescription());
            txtPrice.setText(item.getPrice());

        }
    }
}
