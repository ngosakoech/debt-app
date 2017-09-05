package com.madeni.niqoz.debtapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madeni.niqoz.debtapplication.R;

import java.util.List;

/**
 * Created by niqoz on 9/5/2017.
 */

public class DebtorAdapter extends RecyclerView.Adapter<DebtorAdapter.MyViewHolder> {

    private List<getDebtors> DebtorList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, amount, remaining_amount;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.rvName);
            remaining_amount = (TextView) view.findViewById(R.id.rvRemainngAmount);
            amount = (TextView) view.findViewById(R.id.rvLoanAmount);
        }
    }
    @Override
    public  MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_content_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        getDebtors movie = DebtorList.get(position);
        holder.name.setText(movie.getName());
        holder.remaining_amount.setText(movie.getRemaining_amount());
        holder.amount.setText(movie.getAmount());
    }

    public DebtorAdapter(List< getDebtors> DebtorList) {
        this.DebtorList = DebtorList;
    }



    @Override
    public int getItemCount() {
        return DebtorList.size();
    }
}
