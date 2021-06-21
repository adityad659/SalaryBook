package com.example.payroll.ProfileDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.payroll.R;

public class YourNameEdit extends AppCompatActivity {
    private EditText textInputYourName;

   private  String ownerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_name_edit);
        ownerName=getIntent().getStringExtra("ownerNm");
        EditText yname=findViewById(R.id.editText);
        yname.setText(ownerName);

        textInputYourName=findViewById(R.id.editText);

            }
    private boolean validateYourname() {
        String yournameInput = textInputYourName.getText().toString().trim();
        if (yournameInput.isEmpty()) {
            textInputYourName.setError("This Field is required");
            return false;
        }
        else {
            textInputYourName.setError(null);
            return true;
        }
    }
    public void saveYourName(View v) {

        if (!validateYourname()  ) {
            return;
        }
          ownerName= textInputYourName.getText().toString().trim();


                Intent resultIntent = new Intent();
                resultIntent.putExtra("ownerName", ownerName);
                setResult(1, resultIntent);


                finish();



    }


}