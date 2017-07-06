package varadraj.quotesalarm;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by varad on 5/7/17.
 */

public class AlarmTone {

    private static AlarmTone instance = new AlarmTone();
    private MediaPlayer mp;

    private AlarmTone(){}

    public static AlarmTone getDefaultInstance(){
        return instance;
    }

    public void startAlarmTone(Context context){
        mp = MediaPlayer.create(context, R.raw.swatkats);
        mp.setLooping(true);
        mp.start();
    }

    public void pauseAlarmTone(){
        if (mp.isPlaying()){
            mp.pause();
        }
    }

    public void stopAlarmTone(){
        mp.stop();
    }

}
