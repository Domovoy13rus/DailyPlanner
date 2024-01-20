package space.eurekatek.dailyplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            Timestamp date_start, date_finish;

            json = new String(buffer, StandardCharsets.UTF_8);

            JSONArray jsonArray = new JSONArray(json);
            max = jsonArray.length();

            for (int i = 0; i < max; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id = jsonObject.getInt("id");
                date_start = jsonObject.getJSONObject("date_start");
                date_finish = jsonObject.getJSONObject("date_finish");
                name = jsonObject.getString("name");
                description = jsonObject.getString("description");

                Log.e("TAG", "loadJson:  id: " + id + " date start: " + date_start + " date finish: " + date_finish + " name: " + name + " description: " + description);

            }
        }
        catch (Exception e)
        {
            Log.e("TAG", "loadJson: error "+e);
        }
    }
}