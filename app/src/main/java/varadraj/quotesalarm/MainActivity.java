package varadraj.quotesalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button startAlarm, pauseAlarm, stopAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startAlarm = (Button)findViewById(R.id.bt_setalarm);
        startAlarm.setOnClickListener(this);
        pauseAlarm = (Button)findViewById(R.id.bt_pauseresume);
        pauseAlarm.setOnClickListener(this);
        stopAlarm = (Button)findViewById(R.id.bt_stop);
        stopAlarm.setOnClickListener(this);
    }


    public void setAlarm(int duration){
        Intent intent = new Intent(this, AlarmBroadcastReceiver.class);
        PendingIntent pendingIntent =  PendingIntent.getBroadcast(this.getApplicationContext(),23081992,intent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + (duration * 1000 ),pendingIntent);
        Toast.makeText(this,"Alarm set for "+duration+" seconds",Toast.LENGTH_LONG).show();
        Log.d("debug","Alarm Set");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_setalarm:{
                setAlarm(5); //TODO Date Time Picker
                break;
            }
            case R.id.bt_pauseresume:{
                AlarmTone.getDefaultInstance().pauseAlarmTone();
                break;
            }
            case R.id.bt_stop:{
                AlarmTone.getDefaultInstance().stopAlarmTone();
                break;
            }
        }
    }
}
