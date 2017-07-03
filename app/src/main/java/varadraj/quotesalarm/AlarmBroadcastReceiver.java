package varadraj.quotesalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by varad on 3/7/17.
 */

public class AlarmBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Alarm Raised",Toast.LENGTH_LONG).show();
        Log.d("debug","onReceive");
    }
}
