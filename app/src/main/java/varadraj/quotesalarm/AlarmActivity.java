package varadraj.quotesalarm;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by varad on 6/7/17.
 */

public class AlarmActivity extends Activity{

    private TextView tv_quote, tv_quote2, tv_quote3;
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
        tv_quote2 = (TextView)findViewById(R.id.tv_quote2);
        tv_quote3 = (TextView)findViewById(R.id.tv_quote3);
        setQuoteText();
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
        et_quote.setLongClickable(false);
        et_quote.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        et_quote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                AlarmTone.getDefaultInstance().pauseAlarmTone();
                handler.removeCallbacks(noTypingChecker);
                if (et_quote.getText().toString().trim().toLowerCase().equals(getQuoteText())){
                    stopAlarm();
                }
                setQuoteText();
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

    private void stopAlarm(){
        AlarmTone.getDefaultInstance().stopAlarmTone();
        Toast.makeText(this, "Alarm Stopped", Toast.LENGTH_SHORT).show();
        finish();
    }

    String getQuoteText(){
        return this.getString(R.string.good_morning).toLowerCase().trim();
    }

    void setQuoteText(){
        String quoteText = getQuoteText();
        int typedLength = 0;
        if(et_quote == null || quoteText.length()==et_quote.getText().toString().trim().length()) {
            setTextViews(quoteText,0);
        }
        else{
            String typedText = et_quote.getText().toString().toLowerCase().trim();
            while (typedLength < typedText.length()
                    && typedText.substring(typedLength,typedLength+1).equals(quoteText.substring(typedLength,typedLength+1))){
                typedLength++;
            }
            setTextViews(quoteText,typedLength);
        }

    }

    private void setTextViews(String quoteText,int index){
        tv_quote.setText(quoteText.substring(0,index));
        tv_quote2.setText(quoteText.substring(index,index+1));
        tv_quote3.setText(quoteText.substring(index+1));
    }



}
