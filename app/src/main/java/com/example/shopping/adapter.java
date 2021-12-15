package com.example.shopping;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class adapter extends RecyclerView.Adapter<adapter.myviewholder> {

    ArrayList<Data> list;
    Context context;

    public adapter(ArrayList<Data> list, Context bottomHome) {
        this.list = list;
        this.context = bottomHome;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        Data data = list.get(position);
        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());
        holder.quantity.setText(list.get(position).getQuantity());
        byte[] foodImage = data.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.circleImageView.setImageBitmap(bitmap);
        //Picasso.get().load(list.get(position).getImage()).into(holder.circleImageView);


        /*holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(view.getContext());
                View vie = layoutInflaterAndroid.inflate(R.layout.cancel_dialog, null);
                Button update=vie.findViewById(R.id.update);
                Button delete=vie.findViewById(R.id.delete);
                builder.setView(vie);
                builder.setCancelable(false);
                final AlertDialog alertDialog = builder.create();*/

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, AddItemActivity.class);
                intent.putExtra("data", data);
                context.startActivity(intent);
                //alertDialog.dismiss();
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new dbmanager(context).deleteItem(data);
                list.remove(data);
                notifyDataSetChanged();
                // alertDialog.dismiss();
            }
        });
        // alertDialog.show();
        //return false;


    }
    public void filterlist(ArrayList<Data>filteredlist)
    {
        list=filteredlist;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView name, price, quantity;
        CircleImageView circleImageView;
        ImageView imageView, update;

        //Button update,delete;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantity);
            circleImageView = itemView.findViewById(R.id.profile);
            imageView = itemView.findViewById(R.id.delete);
            update = itemView.findViewById(R.id.edit_img);


        }

    }
}
