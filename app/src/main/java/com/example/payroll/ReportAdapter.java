package com.example.payroll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payroll.data.EmpMonthlyClosBal;
import com.example.payroll.databinding.ReportRowDesignBinding;
import com.example.payroll.databinding.StaffRowDesignBinding;

import java.time.LocalDate;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.myviewholder>{
    List<EmpMonthlyClosBal> dataholder;
    Context context;
    public ReportAdapter(List<EmpMonthlyClosBal> dataholder, Context context) {
        this.dataholder = dataholder;
        this.context=context;

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ReportRowDesignBinding reportRowDesignBinding = ReportRowDesignBinding.inflate(layoutInflater, parent, false);
        return new myviewholder(reportRowDesignBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        EmpMonthlyClosBal staff = dataholder.get(position);
        holder.reportRowDesignBinding.setStaff(staff);
        holder.reportRowDesignBinding.executePendingBindings();
        holder.reportRowDesignBinding.setMonthValues(GlobalVariable.monthYearConv(LocalDate.now()));


    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {
ReportRowDesignBinding reportRowDesignBinding;

        public myviewholder(@NonNull ReportRowDesignBinding reportRowDesignBinding) {
            super(reportRowDesignBinding.getRoot());
            this.reportRowDesignBinding=reportRowDesignBinding;

        }
    }
}
