package com.example.payroll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payroll.Database.DBPayment;
import com.example.payroll.data.Payments;

import java.util.List;

import static com.example.payroll.GlobalVariable.numToCurr;

public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsAdapter.myviewholder> {
    List<Payments> dataholder;
    Context context;
    DBPayment DB;
    public PaymentsAdapter(List<Payments> dataholder, Context context) {
        this.dataholder = dataholder;
        this.context=context;
        DB=new DBPayment(context);

    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.payments_row_design,parent,false);
        return new myviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.date.setText(dataholder.get(position).getDate());
        holder.desc.setText(dataholder.get(position).getDescription());
        holder.amount.setText(""+numToCurr((dataholder.get(position).getAdvance()+dataholder.get(position).getPending())));

    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView desc;
        TextView date;
        TextView amount;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            desc = itemView.findViewById(R.id.desc);
            amount = itemView.findViewById(R.id.amount);


        }
    }
}
