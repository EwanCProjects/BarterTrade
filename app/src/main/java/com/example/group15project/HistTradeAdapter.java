package com.example.group15project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistTradeAdapter extends RecyclerView.Adapter<HistTradeAdapter.HistTradeViewHolder> {
    public static Trade currTrade;
    List<Trade> trades;
    List<String> tradeReceivers;
    List<String> tradeProviders;
    Context context;

    public HistTradeAdapter(Context ct, List<Trade> trades, List<String> receivers, List<String> providers) {
        context = ct;
        this.trades = trades;
        tradeReceivers = receivers;
        tradeProviders = providers;
    }

    @NonNull
    @Override
    public HistTradeAdapter.HistTradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.hist_trade_cardview, parent, false);
        return new HistTradeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistTradeAdapter.HistTradeViewHolder holder, int position) {
        holder.tradeItem.setText(trades.get(position).getTitle());
        holder.receiver.setText(tradeReceivers.get(position));
        holder.provider.setText(tradeProviders.get(position));
        /*
        holder.tradeLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, ViewTradeActivity.class);
            currTrade = trades.get(position);
            context.startActivity(intent);

        });*/
    }

    @Override
    public int getItemCount() {
        return trades.size();
    }

    public class HistTradeViewHolder extends RecyclerView.ViewHolder {
        TextView tradeItem, provider, receiver;
        ConstraintLayout tradeLayout;

        public HistTradeViewHolder(@NonNull View itemView) {
            super(itemView);
            tradeItem = itemView.findViewById(R.id.tradeItemField);
            receiver = itemView.findViewById(R.id.tradeReceiverField);
            provider = itemView.findViewById(R.id.tradeProviderField);
            tradeLayout = itemView.findViewById(R.id.tradeCardLayout);
        }
    }
}
