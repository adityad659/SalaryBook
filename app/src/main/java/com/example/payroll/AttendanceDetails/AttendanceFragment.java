package com.example.payroll.AttendanceDetails;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payroll.R;
import com.example.payroll.ViewModel.MainActivityViewModel;
import com.example.payroll.data.Attendance;
import com.example.payroll.data.empdata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class  AttendanceFragment extends Fragment  {
    RecyclerView recyclerView;
    private MainActivityViewModel mainActivityViewModel;
    AttendanceAdapter adapter;
    Button b,d;
    String s;
    TextView present1,absent1,halfday1, date2;
    DateTimeFormatter formatter3,formatter4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);
        recyclerView = view.findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        final ProgressBar progressBar = view.findViewById(R.id.progressBar);

        formatter3 = DateTimeFormatter.ofPattern("MMMM dd,yyyy");
        formatter4 = DateTimeFormatter.ofPattern("MMMM yyyy");


        List<empdata> emprecords = mainActivityViewModel.getAllEmpData();
        List<LocalDate> dateTimeList = new ArrayList<>();
        if(!emprecords.isEmpty()) {
            for (empdata i : emprecords) {
                dateTimeList.add(LocalDate.parse(i.getDate(), formatter3));
            }
            LocalDate start = Collections.min(dateTimeList);

            present1 = view.findViewById(R.id.present1);
            absent1 = view.findViewById(R.id.absent1);
            halfday1 = view.findViewById(R.id.half_day1);
            date2 = view.findViewById(R.id.date);
            b = view.findViewById(R.id.backdate);
            d = view.findViewById(R.id.nextdate);


            LocalDate date = LocalDate.now();
            String r = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));

            String m = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
            String end = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));


            s = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
            date2.setText(s);

            //insert employee attendance data in dataholder
            List<Attendance> attendance = mainActivityViewModel.getAttendanceList(s);

            adapter = new AttendanceAdapter(attendance, getActivity(), present1, absent1, halfday1);
            recyclerView.setAdapter(adapter);

            d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String a = date2.getText().toString().trim();

                    LocalDate date1 = LocalDate.parse(a, DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
                    date1 = date1.plusDays(1);
                    String s = date1.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
                    String m = start.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
                    while (!m.equals(s)) {

                        if (mainActivityViewModel.getAttendanceExist(s) == true) {
                            List<Attendance> attendance = mainActivityViewModel.getAttendanceList(s);
                            adapter = new AttendanceAdapter(attendance, getActivity(), present1, absent1, halfday1);
                            recyclerView.setAdapter(adapter);
                            date2.setText(s);
                            b.setEnabled(true);
                            break;
                        }

                        date1 = LocalDate.parse(s, DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
                        date1 = date1.plusDays(1);
                        s = date1.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
                    }
                    if (s.equals(end)) {
                        d.setEnabled(false);
                    }


                }
            });

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String a = date2.getText().toString().trim();

                    LocalDate date1 = LocalDate.parse(a, DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
                    LocalDate r = date1.minusDays(1);
                    String s = r.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
                    String m = start.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
                    while (!m.equals(s)) {
                        if (mainActivityViewModel.getAttendanceExist(s) == true) {
                            List<Attendance> attendance = mainActivityViewModel.getAttendanceList(s);
                            adapter = new AttendanceAdapter(attendance, getActivity(), present1, absent1, halfday1);
                            recyclerView.setAdapter(adapter);
                            date2.setText(s);
                            d.setEnabled(true);
                            break;
                        }
                        date1 = LocalDate.parse(s, DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
                        r = date1.minusDays(1);
                        s = r.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

                    }
                    if (m.equals(s)) {
                        List<Attendance> attendance = mainActivityViewModel.getAttendanceList(s);
                        adapter = new AttendanceAdapter(attendance, getActivity(), present1, absent1, halfday1);
                        recyclerView.setAdapter(adapter);
                        date2.setText(s);
                        d.setEnabled(true);
                        b.setEnabled(false);
                    }


                }

            });
        }
return  view;

        }

}