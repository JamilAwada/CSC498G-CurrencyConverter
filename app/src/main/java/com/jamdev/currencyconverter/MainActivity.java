package com.jamdev.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Declaring the global variables used in the conversion function
    Spinner sp1, sp2;
    TextView tv, result;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // These 3 lines hide the title and action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        // Initializing the variables by linking them to UI components
        tv = findViewById(R.id.pt_amount);
        sp1 = findViewById(R.id.spinner_currency1);
        sp2 = findViewById(R.id.spinner_currency2);
        btn = findViewById(R.id.btn_convert);
        result = findViewById(R.id.num_result);
    }

    public void convert(View view){

        double converted;
        int usdValue = 22000;
        if (tv.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Empty field.", Toast.LENGTH_LONG).show();
        }
        else {
            double amountEntered = Double.parseDouble(tv.getText().toString());

            // Retrieving the currencies selected
            String from = sp1.getSelectedItem().toString();
            String to = sp2.getSelectedItem().toString();



            // Checking for different scenarios and applying the correct protocol for each
            if (from.equals("USD") && to.equals("LBP")){
                converted = amountEntered * usdValue;
                DecimalFormat df = new DecimalFormat("#.###");
                double roundedConversion = Double.valueOf(df.format(converted));
                result.setText(roundedConversion + " LBP");
            }
            else if (from.equals("LBP") && to.equals("USD")){
                converted = amountEntered / usdValue;
                DecimalFormat df = new DecimalFormat("#.###");
                double roundedConversion = Double.valueOf(df.format(converted));
                result.setText(roundedConversion + " USD");
            }
            else {
                Toast.makeText(getApplicationContext(), "Cannot converted to and from the same currency.", Toast.LENGTH_LONG).show();
            }
        }


    }
}