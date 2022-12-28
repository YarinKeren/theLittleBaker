package com.example.thelittlebaker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class paymentActivity extends AppCompatActivity {

    EditText creditNum,ccvNum;
    Button payBtn;
    Spinner monthSpinner,yearSpinner;
    ArrayList<Integer> monthList,yearList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        creditNum=findViewById(R.id.creditCardNumber);
        ccvNum=findViewById(R.id.ccvNumber);
        monthSpinner=findViewById(R.id.monthSpinner);
        yearSpinner=findViewById(R.id.yearSpinner);
        payBtn=findViewById(R.id.payBtn);
        monthList=new ArrayList<>();
        yearList=new ArrayList<>();



        int month,year;

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        month = cal.get(Calendar.MONTH);
        year=cal.get(Calendar.YEAR);


        final int[] selectedYear = new int[1];
        final int[] selectedMonth = new int[1];


        for (int i=0;i<12;i++)
            monthList.add(i+1);
        for (int j = 0; j < 8; j++)
            yearList.add(2022+j);

        ArrayAdapter<Integer> monthAdapter
                =new ArrayAdapter<Integer>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                monthList);

        ArrayAdapter<Integer> yearAdapter
                =new ArrayAdapter<Integer>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                yearList);


        monthAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        yearAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        monthSpinner.setAdapter(monthAdapter);
        yearSpinner.setAdapter(yearAdapter);



        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMonth[0] =Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedYear[0] =Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ccv=ccvNum.getText().toString();
                Long credit= Long.valueOf(creditNum.getText().toString());

                boolean isCreditNumValid=isValid(credit);
                boolean isCcvNumValid=isValidCVVNumber(ccv);

                //Checking the card number
                if(!isCreditNumValid){
                    Toast.makeText(paymentActivity.this, "credit number is invalid ", Toast.LENGTH_SHORT).show();
                }
                //Checking the ccv number
                else if(!isCcvNumValid){
                    Toast.makeText(paymentActivity.this, "ccv number is invalid ", Toast.LENGTH_SHORT).show();
                }
                //Checking the year of the card
                else if(year>selectedYear[0]){
                    Toast.makeText(paymentActivity.this, "The card has expired", Toast.LENGTH_SHORT).show();
                }
                //Checking if the year is equal and checking the month
                else if(year==selectedYear[0]){
                    if(month>=selectedMonth[0]){
                        Toast.makeText(paymentActivity.this, "The card month has expired", Toast.LENGTH_SHORT).show();
                    }
                }
               else//if everything went good
                Toast.makeText(paymentActivity.this, "The payment went through successfully", Toast.LENGTH_SHORT).show();



            }
        });


    }

    public static boolean isValid(long number)
    {
        return (getSize(number) >= 13 &&
                getSize(number) <= 16) &&
                (prefixMatched(number, 4) ||
                        prefixMatched(number, 5) ||
                        prefixMatched(number, 37) ||
                        prefixMatched(number, 6)) &&
                ((sumOfDoubleEvenPlace(number) +
                        sumOfOddPlace(number)) % 10 == 0);
    }

    // Get the result from Step 2
    public static int sumOfDoubleEvenPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

        return sum;
    }

    // Return this number if it is a single digit, otherwise,
    // return the sum of the two digits
    public static int getDigit(int number)
    {
        if (number < 9)
            return number;
        return number / 10 + number % 10;
    }

    // Return sum of odd-place digits in number
    public static int sumOfOddPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }

    // Return true if the digit d is a prefix for number
    public static boolean prefixMatched(long number, int d)
    {
        return getPrefix(number, getSize(d)) == d;
    }

    // Return the number of digits in d
    public static int getSize(long d)
    {
        String num = d + "";
        return num.length();
    }

    // Return the first k number of digits from
    // number. If the number of digits in number
    // is less than k, return number.
    public static long getPrefix(long number, int k)
    {
        if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }

    public static boolean isValidCVVNumber(String str)
    {
        // Regex to check valid CVV number.
        String regex = "^[0-9]{3,4}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (str == null)
        {
            return false;
        }

        // Find match between given string
        // and regular expression
        // using Pattern.matcher()

        Matcher m = p.matcher(str);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }
}
