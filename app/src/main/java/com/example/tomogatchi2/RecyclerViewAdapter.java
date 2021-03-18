package com.example.tomogatchi2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

    String data1[], data2[], data3[];
    int images[];
    Context context;
    SharedPreferences sharedPreferences;

    public RecyclerViewAdapter(Context ct, String s1[], String s2[], String s3[], int images[]){
        this.context = ct;
        this.data1 = s1;
        this.data2 = s2;
        this.data3 = s3;
        this.images = images;
        sharedPreferences = context.getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);

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
        holder.food_name_txt.setText(data1[position]);
        holder.food_price.setText(data2[position]);
        holder.owned_txt.setText(data3[position]);
        holder.food_image.setImageResource(images[position]);
        holder.buyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //holder.food_name_txt.setText("M-am schimbat bro");
                holder.owned_txt.setText(String.valueOf(Integer.parseInt(holder.owned_txt.
                        getText().toString()) + 1));
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(data1[position] + "Key", Integer.parseInt(holder.owned_txt.getText().toString()));
                int currentMoney = sharedPreferences.getInt(Data.Money, 0);
                editor.putInt(Data.Money, currentMoney - Integer.parseInt(holder.food_price.getText().toString()));
                editor.commit();
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

        TextView food_name_txt, food_price, owned_txt;
        ImageView food_image;
        Button buyButton;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            food_name_txt = itemView.findViewById(R.id.food_name_txt);
            food_price = itemView.findViewById(R.id.food_price);
            food_image = itemView.findViewById(R.id.food_image);
            buyButton = itemView.findViewById((R.id.buyButton));
            owned_txt = itemView.findViewById((R.id.owned_food_count));

        }

    }
}
