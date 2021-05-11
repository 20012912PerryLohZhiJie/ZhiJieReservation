package sg.edu.rp.c346.id20012912.zhijiesl04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
{
    EditText name;
    EditText mobile;
    EditText grpsize;
    TextView details;

    Button displaytable;
    CheckBox smoke;
    CheckBox nosmoke;

    TextView booktable;
    TextView cigar;
    TextView nocigar;

    DatePicker dp;
    TimePicker tp;
    Button reserve;

    TextView date;
    TextView time;

    ToggleButton reset;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.Displayname);
        mobile =findViewById(R.id.Displaymobile);
        grpsize = findViewById(R.id.Displaygrpsize);
        details = findViewById(R.id.detailsdone);

        displaytable = findViewById(R.id.button1);
        booktable = findViewById(R.id.Mustbooktable);
        smoke = findViewById(R.id.Smoking);
        nosmoke = findViewById(R.id.NonSmokingArea);

        cigar = findViewById(R.id.Smokingyes);
        nocigar = findViewById(R.id.nosmokes);

        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        reserve = findViewById(R.id.ConfirmReservation);

        date = findViewById(R.id.DisplayDate);
        time = findViewById(R.id.DisplayTime);

        reset = findViewById(R.id.Resettgbtn);

        String namestr = name.getText().toString();
        String mobilestr = mobile.getText().toString();
        String grpsizestr = grpsize.getText().toString();
        String detailsstr = details.getText().toString();

        if(namestr.length() ==0 && mobilestr.length() >0 && grpsizestr.length() >0)
        {
            Toast.makeText(MainActivity.this, "Name field is compulsory", Toast.LENGTH_LONG).show();
        }

        else if(namestr.length() ==0 && mobilestr.length() ==0 && grpsizestr.length() >0)
        {
            Toast.makeText(MainActivity.this, "Name field is compulsory", Toast.LENGTH_LONG).show();
            Toast.makeText(MainActivity.this, "Mobile field is compulsory", Toast.LENGTH_LONG).show();
        }

        else if(namestr.length() ==0 && mobilestr.length() ==0 && grpsizestr.length() ==0)
        {
            Toast.makeText(MainActivity.this, "Name field is compulsory", Toast.LENGTH_LONG).show();
            Toast.makeText(MainActivity.this, "Mobile field is compulsory", Toast.LENGTH_LONG).show();
            Toast.makeText(MainActivity.this, "GroupSize field is compulsory", Toast.LENGTH_LONG).show();

        }
        else
        {
            detailsstr = "Details Completed!";
            details.setText(detailsstr);
        }


        displaytable.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String cigarstr = cigar.getText().toString();
                String nocigarstr = nocigar.getText().toString();
                String booktablstr = booktable.getText().toString();

                if(smoke.isChecked() == true && nosmoke.isChecked() ==false)
                {
                    cigarstr = " You have reserved  " + cigarstr;
                    cigar.setText(cigarstr);

                }

                if(smoke.isChecked() == false && nosmoke.isChecked() == true)
                {
                    nocigarstr= "You have reserved " + nocigarstr;
                    nocigar.setText(nocigarstr);
                }

                if(smoke.isChecked()== true && nosmoke.isChecked()==true)
                {
                    cigarstr = " You have reserved  " + cigarstr;
                    cigar.setText(cigarstr);

                    nocigarstr= "You have reserved " + nocigarstr;
                    nocigar.setText(nocigarstr);
                }

                else
                {
                    booktablstr = "You MUST book table type!";
                    booktable.setText(booktablstr);
                }


            }
        });

        tp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tp.setIs24HourView(true);
                if ( (tp.getCurrentHour() >= 19) || (tp.getCurrentMinute() >=30))
                {

                    String timeis = "Time is " + tp.getCurrentHour() + ":" +
                            String.format("%02d", tp.getCurrentMinute());

                    Toast.makeText(MainActivity.this,"our default time is 7.30PM", Toast.LENGTH_SHORT).show();
                    time.setText(timeis);

                }

            }

        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute)
            {
                if(tp.getCurrentHour() < 8)
                {
                    tp.setCurrentHour(8);
                    Toast.makeText(MainActivity.this, "we are opened at 8AM", Toast.LENGTH_SHORT).show();

                }
                if(tp.getCurrentHour() > 20)
                {
                    tp.setCurrentHour(20);
                    Toast.makeText(MainActivity.this, "we are closed at 8.59PM", Toast.LENGTH_SHORT)).show();
                }
            }
        });



        dp.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                dp.updateDate(2021,5,1);
                String dateis ="Date is "+ dp.getDayOfMonth() +"/" +
                        (dp.getMonth() + 1) +"/" + dp.getYear();

                date.setText(dateis);

            }
        });


        reserve.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(dp.isEnabled()==true)
                {
                    String dateis ="Date is "+ dp.getDayOfMonth() +"/" +
                            (dp.getMonth() + 1) +"/" + dp.getYear();

                    date.setText(dateis);


                    String timeis = "Time is " + tp.getCurrentHour() + ":" +
                            String.format("%02d", tp.getCurrentMinute());

                    Toast.makeText(MainActivity.this,"our booking app closed at 7.30PM", Toast.LENGTH_SHORT).show();
                    time.setText(timeis);
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(reset.isChecked())
                {
                    date.setEnabled(true);
                    time.setEnabled(true);
                    tp.setCurrentHour(19);
                    tp.setCurrentMinute(30);
                    date.setText(" ");
                    time.setText(" ");
                    dp.updateDate(2021, 5, 1);



                }
                else
                {
                    date.setEnabled(false);
                    time.setEnabled(false);

                }
            }
        });















    }
}