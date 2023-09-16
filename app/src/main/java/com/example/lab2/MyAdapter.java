package com.example.lab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
implements MyInterface {
    private final MyInterface myInterface;

    Context context;
    ArrayList<RowModel> rowModels;

    public MyAdapter(Context context, ArrayList<RowModel> rowModels, MyInterface myInterface) {
        this.context = context;
        this.rowModels = rowModels;
        this.myInterface = myInterface;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rec_view_row, parent, false);

        return new MyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(rowModels.get(position).getRowName());
        holder.imageView.setImageResource(rowModels.get(position).getImage());

        holder.itemView.setOnClickListener(view -> {
            if (myInterface != null) {
                int pos = holder.getAdapterPosition();

                if (pos != RecyclerView.NO_POSITION) {
                    myInterface.onItemClick(pos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rowModels.size();
    }

    @Override
    public void onItemClick(int pos) {

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.textView);
        }
    }
}
