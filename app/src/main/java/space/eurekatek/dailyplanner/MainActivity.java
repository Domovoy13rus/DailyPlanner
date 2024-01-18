package space.eurekatek.dailyplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        long date_start = calendar.get(Calendar.HOUR_OF_DAY);
        long date_finish = calendar.get(Calendar.MINUTE);

        String text = getString(R.string.time_task, date_start, date_finish);
        TextView textView = new TextView(this);
        //textView.setText(text);
        //textView.setTextSize(28);
        //setContentView(textView);
    }
}