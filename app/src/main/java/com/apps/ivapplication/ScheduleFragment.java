package com.apps.ivapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.net.URI;
import java.util.Calendar;

public class ScheduleFragment extends Fragment implements View.OnClickListener {

    private String[] types = {"Rehydrate", "Revive", "Myers Cocktail", "Royal Flush", "Myers Cocktail", "Mega Myers Cocktail", "NAD+"};
    private Spinner spinner;
    private EditText addy;
    private Button datePicker1, schedule;
    private DatePickerDialog datePickerDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        initDatePicker();
        datePicker1 = (Button) view.findViewById(R.id.datePicker);
        datePicker1.setOnClickListener(this);
        datePicker1.setText(getTodaysDate());

        addy = (EditText) view.findViewById(R.id.addy1);
        String sendAddy = addy.getText().toString();

        String[] phnNo = new String[1];
        phnNo[0] = "6238669114";

        schedule = (Button) view.findViewById(R.id.schedulebtn);
        schedule.setOnClickListener(this);

        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_dropdown_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return view;
    }
    
    private String getTodaysDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    @Override
    public void onClick(View view) {
    switch (view.getId()){
        case R.id.schedule:
            String[] phnNo = new String[1];
            phnNo[0] = "6025588084";
            String sendAddy = addy.getText().toString();
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phnNo[0],null, "HI", null , null);
            break;

        case R.id.datePicker:
            datePickerDialog.show();
    }
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                datePicker1.setText(date);
            }

        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(getActivity(), style,dateSetListener, year, month, day);

    }

    private String makeDateString(int day, int month, int year) {

        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        return "JAN";
    }



}
