package space.eurekatek.dailyplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayoutTask = (LinearLayout)findViewById(R.id.linearLayoutTask);
        linearLayoutTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, AboutTask.class);
                    startActivity(intent); finish();
                }catch (Exception e){

                }
            }
        });

        loadJson();
    }

    private void loadJson(){
        try {
            InputStream inputStream = getAssets().open("DBTask.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json;
            int max, id;
            String name, description;
            Date date_start, date_finish;

            json = new String(buffer, StandardCharsets.UTF_8);

            JSONArray jsonArray = new JSONArray(json);
            max = jsonArray.length();

            for (int i = 0; i < max; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id = jsonObject.getInt("id");
                //date_start = jsonObject.getJSONObject("date_start");
                //date_finish = jsonObject.getJSONObject("date_finish");
                name = jsonObject.getString("name");
                description = jsonObject.getString("description");

                Log.e("TAG", "loadJson:  id: " + id + " name: " + name + " description: " + description);
                //Log.e("TAG", "loadJson:  id: " + id + " date start: " + date_start + " date finish: " + date_finish + " name: " + name + " description: " + description);
            }
        }
        catch (Exception e)
        {
            Log.e("TAG", "loadJson: error "+e);
        }
    }



    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toast.makeText(getBaseContext(), "Нажмите еще раз, чтобы выйти", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}