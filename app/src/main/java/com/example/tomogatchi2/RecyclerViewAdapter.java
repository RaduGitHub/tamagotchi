package com.example.tomogatchi2;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

    String data1[], data2[], data3[];
    int images[];
    Activity thisActivity;
    Context context;
    SharedPreferences sharedPreferences;

    public RecyclerViewAdapter(Context ct, String s1[], String s2[], String s3[], int images[], Activity thisActivity){
        this.context = ct;
        this.data1 = s1;
        this.data2 = s2;
        this.data3 = s3;
        this.images = images;
        sharedPreferences = context.getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);
        thisActivity = thisActivity;

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
        holder.name_txt.setText(data1[position]);
        holder.price.setText(data2[position]);
        holder.owned_txt.setText(data3[position]);
        holder.image.setImageResource(images[position]);
        holder.useButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences.Editor editor = sharedPreferences.edit();
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
                    editor.putInt(data1[position] + "Key", Integer.parseInt(holder.owned_txt.
                            getText().toString()));

                    int happiness = sharedPreferences.getInt(Data.Happiness, 0);
                    int happinessToAdd = sharedPreferences.getInt(data1[position] + "HappyKey", 0);
                    Log.d("xxx", "onClick: " + happinessToAdd);
                    if(happiness < 100)
                    {
                        happiness  = happiness + happinessToAdd;
                        editor.putInt(Data.Happiness, happiness);
                    }
                    editor.commit();


                    if(Data.foodActive == true && data1[position].equals("Bandage") == false && data1[position].equals("Pills") == false)
                    {

                        final Dialog dialog = new Dialog(context); // Context, this, etc.
                        dialog.setContentView(R.layout.dialog2);
                        dialog.show();

                        Data.feeding = true;
                    }
                    else if(Data.sickActive && (data1[position].equals("Bandage") || data1[position].equals("Pills")))
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
                //holder.food_name_txt.setText("M-am schimbat bro");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int currentMoney = sharedPreferences.getInt(Data.Money, 0);
                int objectPrice = Integer.parseInt(holder.price.getText().toString());

                if(currentMoney >= objectPrice)
                {

                    holder.owned_txt.setText(String.valueOf(Integer.parseInt(holder.owned_txt.
                            getText().toString()) + 1));

                    editor.putInt(data1[position] + "Key", Integer.parseInt(holder.owned_txt.
                            getText().toString()));

                    editor.putInt(Data.Money, currentMoney - Integer.parseInt(holder.price.
                            getText().toString()));
                    editor.commit();
                }
                else
                {
                    openDialog();
                }
            }
        });
        Log.d("status", "onBindViewHolder: " + data1[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
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
