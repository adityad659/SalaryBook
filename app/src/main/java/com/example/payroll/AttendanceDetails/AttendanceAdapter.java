package com.example.payroll.AttendanceDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payroll.R;
import com.example.payroll.ViewModel.MainActivityViewModel;
import com.example.payroll.data.Attendance;

import java.util.List;

public class AttendanceAdapter extends  RecyclerView.Adapter<AttendanceAdapter.myviewholder>{
    private MainActivityViewModel mainActivityViewModel;

  List<Attendance>dataholder;
  Context context;
TextView present1,absent1,halfday1;
    Integer pres=0,abs=0,halfda=0;
    public AttendanceAdapter(List<Attendance> dataholder, Context context, TextView present1, TextView absent1, TextView halfday1) {
        this.dataholder = dataholder;
        this.context=context;

        mainActivityViewModel = ViewModelProviders.of((FragmentActivity) context).get(MainActivityViewModel.class);

        this.present1=present1;
this.absent1=absent1;
this.halfday1=halfday1;
        pres=0;abs=0;halfda=0;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_row_design,parent,false);
    return new myviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
Attendance temp=dataholder.get(position);
 String att=dataholder.get(position).getAttendance();
 holder.name.setText(dataholder.get(position).getEmpName());
        switch (att)
        {
            case "present":
                pres++;
                holder.present.setChecked(true);
                break;
            case "halfday": halfda++;
                holder.halfday.setChecked(true);
                break;
            case "absent": abs++;
                holder.absent.setChecked(true);
                break;
        }
        holder.present.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String att1=dataholder.get(position).getAttendance();
        checkattend(holder, att1);
        pres++;
        temp.setAttendance("present");
        dataholder.set(position,temp);
mainActivityViewModel.updateAttendance(temp.getAttendance(),temp.getDate(),temp.getMobno());

count();
    }
});
        holder.halfday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String att1=dataholder.get(position).getAttendance();
                checkattend(holder, att1);
                halfda++;
                temp.setAttendance("halfday");
                dataholder.set(position,temp);
                mainActivityViewModel.updateAttendance(temp.getAttendance(),temp.getDate(),temp.getMobno());

                count();

            }
        });
        holder.absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String att1=dataholder.get(position).getAttendance();
                checkattend(holder, att1);
                abs++;
                temp.setAttendance("absent");
                dataholder.set(position,temp);
                mainActivityViewModel.updateAttendance(temp.getAttendance(),temp.getDate(),temp.getMobno());


                count();

            }
        });
      count();
    }

    private void checkattend(@NonNull myviewholder holder, String att) {
        switch (att)
        {
            case "present":
                pres--;
              //  holder.present.setChecked(true);
                break;
            case "halfday": halfda--;
               // holder.halfday.setChecked(true);
                break;
            case "absent": abs--;
                //holder.absent.setChecked(true);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder  {
        TextView name;
        RadioGroup rado;
        RadioButton present;
        RadioButton halfday;
        RadioButton absent;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nam1);
            present=itemView.findViewById(R.id.present);
            halfday=itemView.findViewById(R.id.half_day);
            absent=itemView.findViewById(R.id.absent);
            rado=itemView.findViewById(R.id.radioGroup1);


        }


    }
    public  void  count() {
        present1.setText("" + pres);
        absent1.setText("" + abs);
        halfday1.setText("" + halfda);

    }

}
