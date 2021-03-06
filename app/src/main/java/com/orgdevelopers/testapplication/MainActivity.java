package com.orgdevelopers.testapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<TradeSummeryModel> summeryModels;
    RecyclerView list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.List);

        //data jsonArray
        JSONArray data = null;
        try {
            data = new JSONArray("[{\"srno\":1,\"userid\":\"CMGHG01\",\"accountcode\":\"CODE ABC\",\"symbol\":\"SILVER\",\"expirydate\":\"11 JAN 2022\"},{\"srno\":2,\"userid\":\"USER1\",\"accountcode\":\"CODE XYZ\",\"symbol\":\"DINR\",\"expirydate\":\"24 MAR 2022\"},{\"srno\":3,\"userid\":\"USER2\",\"accountcode\":\"CODE XYZ\",\"symbol\":\"CL\",\"expirydate\":\"3 JUL 2022\"},{\"srno\":4,\"userid\":\"USER2\",\"accountcode\":\"CODE ABC\",\"symbol\":\"SILVER\",\"expirydate\":\"19 JUL 2022\"}]");
            formatData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void formatData(JSONArray data) {
        summeryModels = new ArrayList<>();
        Toast.makeText(this, ""+data.length(), Toast.LENGTH_SHORT).show();

        try {
            for (int i=0; i<data.length(); i++) {
                String userId = data.getJSONObject(i).getString("userid");
                int index = chekId(userId);
                if (index!=-1){
                    //in this case summery model is already in list
                    //we have to add single trade object to that list

                    JSONObject singleRow = data.getJSONObject(i);
                    TradeModel trade = new TradeModel();

                    trade.sr = singleRow.getString("srno");
                    trade.userId = singleRow.getString("userid");
                    trade.accountCode = singleRow.getString("accountcode");
                    trade.expiry = singleRow.getString("expirydate");
                    trade.symbol = singleRow.getString("symbol");

                    //get existing model from list
                    TradeSummeryModel tmp_model = summeryModels.get(index);

                    //add trade in tmp model
                    tmp_model.tradeList.add(trade);

                    //replace tmp model with old model in list
                    summeryModels.set(index,tmp_model);

                }else{
                    //single row of data
                    JSONObject singleRow = data.getJSONObject(i);

                    //trade summery model
                    TradeSummeryModel full_summery = new TradeSummeryModel();

                    //trade model
                    TradeModel trade = new TradeModel();
                    //fill data in trade model
                    trade.sr = singleRow.getString("srno");
                    trade.userId = singleRow.getString("userid");
                    trade.accountCode = singleRow.getString("accountcode");
                    trade.expiry = singleRow.getString("expirydate");
                    trade.symbol = singleRow.getString("symbol");

                    full_summery.userId = singleRow.getString("userid");
                    full_summery.tradeList = new ArrayList<>();
                    full_summery.tradeList.add(trade);

                    summeryModels.add(full_summery);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initRecyclers();
            }
        },100);

    }



    private int chekId(String userId) {
        for (int i = 0; i < summeryModels.size(); i++) {
            if (summeryModels.get(i).userId.equalsIgnoreCase(userId))
                return i;
        }

        return -1;
    }

    private void initRecyclers() {
        //after purifying data let's show it
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);

        MainAdapter adapter = new MainAdapter(this,summeryModels);

        list.setLayoutManager(manager);
        list.setAdapter(adapter);

    }
}