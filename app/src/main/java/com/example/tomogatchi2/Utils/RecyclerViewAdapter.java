package com.example.tomogatchi2.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tomogatchi2.Controllers.RecyclerViewController;
import com.example.tomogatchi2.Models.Data;
import com.example.tomogatchi2.Models.ShopItem;
import com.example.tomogatchi2.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

    ShopItem shopItems[];
    Context context;
    RecyclerViewController recyclerViewController;

    public RecyclerViewAdapter(Context ct, ShopItem[] shopItem, Activity thisActivity){
        this.context = ct;
        this.shopItems = shopItem;
        this.recyclerViewController = new RecyclerViewController();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.shop_row, parent,  false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position)
    {
        holder.name_txt.setText(shopItems[position].name);
        holder.price.setText(shopItems[position].price);
        holder.owned_txt.setText(shopItems[position].owned);
        holder.image.setImageResource(shopItems[position].image);
        holder.useButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(Integer.parseInt(holder.owned_txt.getText().toString()) == 0){
                    //Implement eroare
                    AlertDialog alertDialog = new AlertDialog.Builder(context)
                            .setTitle("Can't use this")
                            .setMessage("You dont have any item of this type")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                }else{
                    holder.owned_txt.setText(String.valueOf(Integer.parseInt(holder.owned_txt.
                            getText().toString()) - 1));

                    recyclerViewController.setOwned(shopItems[position].name, holder.owned_txt.getText().toString());
                    recyclerViewController.addHappiness(shopItems[position].name);


                    if(Data.foodActive == true && shopItems[position].name.equals("Bandage") == false &&
                            shopItems[position].name.equals("Pills") == false)
                    {

                        final Dialog dialog = new Dialog(context); // Context, this, etc.
                        dialog.setContentView(R.layout.dialog2);
                        dialog.show();

                        Data.feeding = true;
                    }
                    else if(Data.sickActive && (shopItems[position].name.equals("Bandage") ||
                            shopItems[position].name.equals("Pills")))
                    {
                        final Dialog dialog = new Dialog(context); // Context, this, etc.
                        dialog.setContentView(R.layout.dialog3);;
                        dialog.show();

                        Data.caring = true;
                    }
                }
            }
        });
        holder.buyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                int currentMoney = recyclerViewController.getMoney();
                int objectPrice = Integer.parseInt(holder.price.getText().toString());

                if(currentMoney >= objectPrice)
                {

                    holder.owned_txt.setText(String.valueOf(Integer.parseInt(holder.owned_txt.
                            getText().toString()) + 1));

                    recyclerViewController.addItem(shopItems[position].name, holder.owned_txt.getText().toString());

                    recyclerViewController.deductMoney(currentMoney, holder.price.getText().toString());
                }
                else
                {
                    openDialog();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopItems.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {

        TextView name_txt, price, owned_txt;
        ImageView image;
        Button buyButton;
        Button useButton;
        View iview;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            iview = itemView;
            name_txt = itemView.findViewById(R.id.food_name_txt);
            price = itemView.findViewById(R.id.food_price);
            image = itemView.findViewById(R.id.food_image);
            buyButton = itemView.findViewById(R.id.buyButton);
            useButton = itemView.findViewById(R.id.useButton);
            owned_txt = itemView.findViewById(R.id.owned_food_count);
        }

    }

    public void openDialog() {
        final Dialog dialog = new Dialog(context); // Context, this, etc.
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle(R.string.dialog);
        dialog.show();
    }
}
