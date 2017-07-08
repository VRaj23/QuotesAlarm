package varadraj.quotesalarm;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by varad on 6/7/17.
 */

public class AlarmActivity extends Activity implements View.OnClickListener {

    private TextView tv_quote;
    private Button bt_check_quote_and_stop_alarm;
    private EditText et_quote;
    private final long delay = 1000;
    private long lastEditTime = 0;
    Handler handler;
    private  Runnable noTypingChecker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlarmTone.getDefaultInstance().startAlarmTone(this);
        setContentView(R.layout.activity_alarmactivity);
        tv_quote = (TextView)findViewById(R.id.tv_quote);
        tv_quote.setText(this.getText(R.string.good_morning));

        bt_check_quote_and_stop_alarm  = (Button)findViewById(R.id.bt_check_quote_and_stop_alarm);
        setButtonText(this.getString(R.string.good_morning)); //TODO browse add quotes
        bt_check_quote_and_stop_alarm.setOnClickListener(this);

        handler = new Handler();
        noTypingChecker = new Runnable() {
            @Override
            public void run() {
                if(System.currentTimeMillis() > (lastEditTime + delay - 500)){
                    AlarmTone.getDefaultInstance().resumeAlarmTone();
                }
            }
        };

        et_quote = (EditText)findViewById(R.id.et_type_quote);
        et_quote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                AlarmTone.getDefaultInstance().pauseAlarmTone();
                handler.removeCallbacks(noTypingChecker);

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() > 0){
                    lastEditTime = System.currentTimeMillis();
                    handler.postDelayed(noTypingChecker,delay);
                }
            }
        });
    }


    String getQuoteText(){
        return tv_quote.getText().toString().trim().toLowerCase();
    }

    void setButtonText(String buttonText){
        bt_check_quote_and_stop_alarm.setText(buttonText);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_check_quote_and_stop_alarm:{
                if (et_quote.getText().toString().trim().toLowerCase().equals(getQuoteText())) {
                    AlarmTone.getDefaultInstance().stopAlarmTone();
                    Toast.makeText(this, "Alarm Stopped", Toast.LENGTH_SHORT).show();
                }
                 else{
                        Toast.makeText(this,"Please type correct Quote.",Toast.LENGTH_SHORT).show();
                    }
                break;
            }
        }

    }
}

//TODO dark theme
//TODO highlight char to be typed