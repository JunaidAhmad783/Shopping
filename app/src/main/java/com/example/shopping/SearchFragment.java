package com.example.shopping;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class SearchFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText editText,editText_time,inputmilli;
    ImageView imageView,imageView_time;
    int day,month,year;
    int hour,minute;
    String date,time;
    String format="";
    Button mbutton,result;
    TextView mtext;
    private String mParam1;
    private String mParam2;

    public SearchFragment() {

    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);
         editText=view.findViewById(R.id.date);
         imageView=view.findViewById(R.id.cal);
         editText_time=view.findViewById(R.id.edittext_time);
         imageView_time=view.findViewById(R.id.time);
         inputmilli=view.findViewById(R.id.input_milli);
         result=view.findViewById(R.id.convert);
         mtext=view.findViewById(R.id.Result);
         mbutton=view.findViewById(R.id.milli);
        Calendar calendar=Calendar.getInstance();
         imageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 year=calendar.get(Calendar.YEAR);
                 month=calendar.get(Calendar.MONTH);
                 day=calendar.get(Calendar.DAY_OF_MONTH);
                 DatePickerDialog datapicker=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                     @Override
                     public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                         i1=i1+1;
                      editText.setText(i2+"/"+i1+"/"+i);
                      date=editText.getText().toString();
                     }
                 },day,month,year);

                 datapicker.getDatePicker().setMinDate(System.currentTimeMillis());
                 datapicker.show();
             }
         });
         imageView_time.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                     @Override
                     public void onTimeSet(TimePicker timePicker, int i, int i1) {
                         hour=i;
                         minute=i1;
                         Calendar calendar1=Calendar.getInstance();
                         calendar1.set(0,0,0,hour,minute);
                         showTime(hour,minute);
                     }
                 },12,0,false);
                // timePickerDialog.updateTime(hour,minute);
                 timePickerDialog.show();
             }
         });
         mbutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 if(TextUtils.isEmpty(editText.getText())||TextUtils.isEmpty(editText_time.getText()))
                 {
                     Toast.makeText(getActivity(), "Empty Credentials", Toast.LENGTH_SHORT).show();
                 }
                 else {
                     String myDate =date+" "+time;
                     System.out.println(myDate);
                     LocalDateTime localDateTime = LocalDateTime.parse(myDate,
                             DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                     long millis = localDateTime
                             .atZone(ZoneId.of("America/Chicago")) //optional
                             .toInstant().toEpochMilli();

                     System.out.println(millis);
                 }
             }
         }); //1639477654098
         result.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(TextUtils.isEmpty(inputmilli.getText()))
                 {
                     Toast.makeText(getContext(),"Empty Credentials",Toast.LENGTH_SHORT).show();
                 }
                     else{
                 long mil=Long.parseLong(inputmilli.getText().toString());
                // SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm a",Locale.getDefault());
                 //SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("dd/MM/yy",Locale.getDefault());
                // String time=simpleDateFormat.format(mil);
                // String date=simpleDateFormat1.format(mil);
                 //mtext.setText(date+" "+time);
                 Calendar cl = Calendar.getInstance();
                 int month=cl.get(Calendar.MONTH);
                 month=month+1;
                 cl.setTimeInMillis(mil);  //here your time in miliseconds
                 String date = "Day:" + cl.get(Calendar.DAY_OF_MONTH) + "\n" +"Month:"+month+ "\n" +"Year:"+ cl.get(Calendar.YEAR)+"\n";
                 String time = "Hour:" + cl.get(Calendar.HOUR_OF_DAY) + "\n"+"Minutes:"+ cl.get(Calendar.MINUTE) + "\n"+"Seconds:"+ cl.get(Calendar.SECOND);
                 mtext.setText(date+" "+time);
             }}
         });




        return view;
    }
    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        if(hour<10&&min<10)
        {
            editText_time.setText(new StringBuilder().append(0).append(hour).append(":").append(0).append(min));
        }
      else if(hour<10)
      {
          editText_time.setText(new StringBuilder().append(0).append(hour).append(":").append(min));
      }
      else if(min<10)
      {
          editText_time.setText(new StringBuilder().append(hour).append(":").append(0).append(min));
      }

        time=editText_time.getText().toString();
    }
}