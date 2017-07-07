package varadraj.quotesalarm;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmactivity);
        tv_quote = (TextView)findViewById(R.id.tv_quote);
        tv_quote.setText(this.getText(R.string.good_morning));

        bt_check_quote_and_stop_alarm  = (Button)findViewById(R.id.bt_check_quote_and_stop_alarm);
        setButtonText(this.getString(R.string.good_morning));
        bt_check_quote_and_stop_alarm.setOnClickListener(this);

        et_quote = (EditText)findViewById(R.id.et_type_quote);
        et_quote.setOnClickListener(this);
    }


    String getQuoteText(){
        return tv_quote.getText().toString();
    }

    void setButtonText(String buttonText){
        bt_check_quote_and_stop_alarm.setText(buttonText);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_check_quote_and_stop_alarm:{
                if (et_quote.getText().toString().equals(getQuoteText().trim())) {
                    AlarmTone.getDefaultInstance().stopAlarmTone();
                    Toast.makeText(this, "Alarm Stopped", Toast.LENGTH_SHORT).show();
                }
                 else{
                        Toast.makeText(this,"Please type correct Quote.",Toast.LENGTH_SHORT).show();
                    }
                break;
            }
            case R.id.et_type_quote:{
                AlarmTone.getDefaultInstance().pauseAlarmTone();
                //TODO resume alarm if typing stops
                break;
            }

        }

    }
}

