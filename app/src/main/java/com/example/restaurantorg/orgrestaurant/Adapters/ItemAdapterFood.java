package com.example.restaurantorg.orgrestaurant.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantorg.orgrestaurant.Models.Item;
import com.example.restaurantorg.orgrestaurant.R;

import java.util.ArrayList;

import Menu.FoodMenuActivity;

public class ItemAdapterFood extends RecyclerView.Adapter<ItemAdapterFood.ItemHolderFood> {

    private Context context;
    private ArrayList<Item> items;
    private int checkedPosition=0;

    public ItemAdapterFood(FoodMenuActivity context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }
    public void setItems(ArrayList<Item>items){
        this.items= new ArrayList<Item>();
        this.items=items;
        notifyDataSetChanged();


    }
    @NonNull
    @Override
    public ItemHolderFood onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_layout_item, parent, false);
        return new ItemHolderFood(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemHolderFood holder, int position) {
        Item item = items.get(position);
        holder.setDetails(item);

    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemHolderFood extends RecyclerView.ViewHolder{
        private TextView txtName, txtDescription,txtPrice;
        private ImageView imageView;

        public ItemHolderFood(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtName);
            txtDescription=itemView.findViewById(R.id.txtDescription);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            imageView=itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View v) {
                    imageView.setVisibility(View.VISIBLE);
                    if(checkedPosition!=getBindingAdapterPosition())
                    {
                        notifyItemChanged(checkedPosition);
                      checkedPosition=getBindingAdapterPosition();}

                }
            });
        }
        void bind(final Item item){
          if(checkedPosition==-1)
          {imageView.setVisibility(View.GONE);
          }
          else
              if(checkedPosition==getBindingAdapterPosition()){
                  imageView.setVisibility(View.VISIBLE);
              }
              else
              {imageView.setVisibility(View.GONE);
              }

        }

        void setDetails(Item item)
        {
            txtName.setText(item.getName());
            txtDescription.setText(item.getDescription());
            txtPrice.setText(item.getPrice());

        }

    }
    public Item getSelected(){

        if(checkedPosition!=-1)
        {
            return  items.get(checkedPosition);
        }
        return null;
    }
}
