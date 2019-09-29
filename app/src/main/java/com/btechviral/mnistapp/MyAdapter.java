package com.btechviral.mnistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Item> itemList;
    private Context context;

    public MyAdapter(List<Item> itemList, Context context){
        this.itemList = itemList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Item item = itemList.get(i);
        viewHolder.typeText.setText(item.getType());
        viewHolder.definitionText.setText(item.getDefinition());
        viewHolder.exampleText.setText(item.getExample());
        if(!item.getUrl().equals(""))
            Glide.with(context).load(item.getUrl()).into(viewHolder.imageView);
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView typeText, definitionText, exampleText;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            typeText = itemView.findViewById(R.id.type);
            definitionText = itemView.findViewById(R.id.definitaion);
            exampleText = itemView.findViewById(R.id.example);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
