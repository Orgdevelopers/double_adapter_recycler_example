package com.orgdevelopers.testapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    ArrayList<TradeSummeryModel> dataList;
    Context context;

    public MainAdapter(Context context,ArrayList<TradeSummeryModel> dataList){
        this.dataList = dataList;
        this.context = context;

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_summery_list,parent,false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.idTxt.setText(dataList.get(position).userId);

        bind(holder,position);

    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }


    protected class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView idTxt;
        RecyclerView recyclerView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            idTxt  = itemView.findViewById(R.id.idTxt);
            recyclerView = itemView.findViewById(R.id.userTradeList);
        }
    }

    private void bind(CustomViewHolder holder, int position) {

        LinearLayoutManager manager = new LinearLayoutManager(context);
        holder.recyclerView.setLayoutManager(manager);
        holder.recyclerView.setAdapter(new SupportAdapter(context,dataList.get(position).tradeList));

    }


}
