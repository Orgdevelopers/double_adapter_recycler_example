package com.orgdevelopers.testapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SupportAdapter extends RecyclerView.Adapter<SupportAdapter.MyHolder> {

    ArrayList<TradeModel> trades;
    Context context;

    public SupportAdapter(Context context,ArrayList<TradeModel> trades){
        this.trades = trades;
        this.context = context;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_trad_list,parent,false);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        TradeModel item = trades.get(position);
        holder.accountCodeTxt.setText(item.accountCode);
        holder.symbolTxt.setText(item.symbol);
        holder.dateTxt.setText(item.expiry);

    }

    @Override
    public int getItemCount() {
        return trades.size();
    }

    protected class MyHolder extends RecyclerView.ViewHolder{

        TextView accountCodeTxt,symbolTxt,dateTxt;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            accountCodeTxt = itemView.findViewById(R.id.accountcodeTxt);
            symbolTxt = itemView.findViewById(R.id.symbolTxt);
            dateTxt = itemView.findViewById(R.id.dateTxt);

        }
    }
}
