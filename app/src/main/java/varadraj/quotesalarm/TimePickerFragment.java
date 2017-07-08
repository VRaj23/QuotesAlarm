package varadraj.quotesalarm;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

/**
 * Created by varad on 8/7/17.
 */

public class TimePickerFragment extends DialogFragment implements View.OnClickListener{

    TimePicker timePicker;
    Button setAlarm;

    public TimePickerFragment(){ }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timepicker,container,true);
        timePicker = (TimePicker)view.findViewById(R.id.tp_timePicker);
        setAlarm = (Button)view.findViewById(R.id.bt_setAlarmTimePicker);
        setAlarm.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bt_setAlarmTimePicker:{
                Log.d("TAG", "onClick: "+timePicker.getHour()+":"+timePicker.getMinute());
                ((MainActivity)getActivity()).setAlarm(timePicker.getHour(),timePicker.getMinute());
                getActivity().getFragmentManager().beginTransaction().remove(this).commit();
            }
        }
    }
}
