package com.example.payroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.payroll.ViewModel.MainActivityViewModel;
import com.example.payroll.data.Payments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.example.payroll.GlobalVariable.numToCurr;

public class MonthlyPaymentDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    PaymentsAdapter adapter;
    String mob1;
    private MainActivityViewModel mainActivityViewModel;

    TextView tot1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_payment_details);
        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mob1 = getIntent().getStringExtra("mob");

        Calendar rightNow = Calendar.getInstance();
        tot1=findViewById(R.id.tot);

        String ym = new SimpleDateFormat("M-yyyy").format(rightNow.getTime());

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);


        List<Payments> payments= mainActivityViewModel.getMonthPaymentsList(ym,mob1);
        Float totalMonthPaymentCalc=(mainActivityViewModel.getMonthTotalPayments(ym,mob1)==null)?0F:mainActivityViewModel.getMonthTotalPayments(ym,mob1);

        adapter=new PaymentsAdapter(payments,getApplicationContext());
        recyclerView.setAdapter(adapter);

        tot1.setText(""+numToCurr(totalMonthPaymentCalc));
    }
}