package varadraj.quotesalarm;

import android.app.AlarmManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button startAlarm, launchTimePicker;
    private final int secondsInDay = 86400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startAlarm = (Button)findViewById(R.id.bt_setalarm);
        startAlarm.setOnClickListener(this);
        launchTimePicker = (Button)findViewById(R.id.bt_launchTimePicker);
        launchTimePicker.setOnClickListener(this);
    }


    public void setAlarm(int duration){
        Intent intent = new Intent(this, AlarmBroadcastReceiver.class);
        PendingIntent pendingIntent =  PendingIntent.getBroadcast(this.getApplicationContext(),23081992,intent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + (duration * 1000 ),pendingIntent);
        Toast.makeText(this,"Alarm set for "+duration+" seconds",Toast.LENGTH_LONG).show();
        Log.d("debug","Alarm Set");
    }

    public void setAlarm(int alarmHour,int alarmMinute){
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        int currentTimeInSeconds = getSecondsOfDay(currentHour,currentMinute);
        int alarmTimeInSeconds = getSecondsOfDay(alarmHour,alarmMinute);
        if (currentTimeInSeconds < alarmTimeInSeconds){
            setAlarm(alarmTimeInSeconds - currentTimeInSeconds);
        }
        else {
            setAlarm(secondsInDay - currentTimeInSeconds + alarmTimeInSeconds);
        }
    }

    private int getSecondsOfDay(int hour, int minute){
        return ( hour * 60 + minute ) * 60;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_setalarm:{
                setAlarm(5);
                break;
            }
            case R.id.bt_launchTimePicker:{
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                timePickerFragment.show(getFragmentManager(),"TimePickerFragment");
                break;
            }
        }
    }
}
