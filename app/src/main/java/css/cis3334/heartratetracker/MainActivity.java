package css.cis3334.heartratetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    HeartRateList heartRateList;        // The list of heart rate objects
    TextView tvSelected;
    ListView lvHeartRate;
    ArrayAdapter<HeartRate> hrAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSelected = (TextView) findViewById(R.id.TextViewSelected);
        lvHeartRate = (ListView) findViewById(R.id.ListViewHeartRate);

        heartRateList = new HeartRateList();
        heartRateList.InitRandomElderly();

        hrAdapter = new HeartRateAdapter(this, R.layout.heart_rate_row, R.id.textViewPulse, heartRateList);
        hrAdapter.setDropDownViewResource(R.layout.heart_rate_row);
        lvHeartRate.setAdapter(hrAdapter);

        lvHeartRate.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                HeartRate hr = (HeartRate) parent.getItemAtPosition(position);
                tvSelected.setText("You selected: " + hr.toString());
            }
        });
    }
}
