package varadraj.quotesalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setAlarm(10);
    }

    public void setAlarm(int duration){
        Intent intent = new Intent(this, AlarmBroadcastReceiver.class);
        PendingIntent pendingIntent =  PendingIntent.getBroadcast(this.getApplicationContext(),23081992,intent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + (duration * 1000 ),pendingIntent);
        Toast.makeText(this,"Alarm set for "+duration+" seconds",Toast.LENGTH_LONG).show();
        Log.d("debug","Alarm Set");
    }
}
