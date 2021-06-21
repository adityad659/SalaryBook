package com.example.payroll;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.payroll.databinding.StaffRowDesignBinding;

import java.util.ArrayList;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.myviewholder> {
    ArrayList<DataStaff> dataholder;
    Context context;

    public StaffAdapter(ArrayList<DataStaff> dataholder, Context context) {
        this.dataholder = dataholder;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        StaffRowDesignBinding staffRowDesignBinding = StaffRowDesignBinding.inflate(layoutInflater, parent, false);
        return new myviewholder(staffRowDesignBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
         DataStaff staff = dataholder.get(position);
        holder.staffRowDesignBinding.setStaff(staff);

        holder.staffRowDesignBinding.executePendingBindings();
        holder.staffRowDesignBinding.staffclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Staff_salary_details.class);
                intent.putExtra("mob", dataholder.get(position).mobno);
                intent.putExtra("name", dataholder.get(position).name);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {
        StaffRowDesignBinding staffRowDesignBinding;

        public myviewholder(@NonNull StaffRowDesignBinding staffRowDesignBinding) {
            super(staffRowDesignBinding.getRoot());
            this.staffRowDesignBinding = staffRowDesignBinding;


        }


    }

}
