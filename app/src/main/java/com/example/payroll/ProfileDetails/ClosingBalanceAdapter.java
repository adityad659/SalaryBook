package com.example.payroll.ProfileDetails;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payroll.ClosingBalanceDetails;
import com.example.payroll.GlobalVariable;
import com.example.payroll.R;

import java.util.ArrayList;

public class ClosingBalanceAdapter extends RecyclerView.Adapter<ClosingBalanceAdapter.myviewholder> {
    ArrayList<dataClosingBalance> dataholder;
    Context context;

    public ClosingBalanceAdapter(ArrayList<dataClosingBalance > dataholder, Context context) {
        this.dataholder = dataholder;
        this.context=context;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.monthly_closing_balance_row_design,parent,false);
        return new myviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.closingBalance.setText(dataholder.get(position).getDate()+" Clos Bal");

        if(dataholder.get(position).getAmount()>=0) {
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.green));
        }
        else{
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.red));
        }
        holder.amount.setText(GlobalVariable.numToCurr(dataholder.get(position).getAmount()));
          holder.closingBalance1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ClosingBalanceDetails.class);
                intent.putExtra("mob",dataholder.get(position).mobno);
                intent.putExtra("month",dataholder.get(position).date1);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView closingBalance,amount;
LinearLayout closingBalance1;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            closingBalance = itemView.findViewById(R.id.closing_balance);
            closingBalance1 = itemView.findViewById(R.id.closing_balance1);
            amount = itemView.findViewById(R.id.amount);


        }
    }

}
