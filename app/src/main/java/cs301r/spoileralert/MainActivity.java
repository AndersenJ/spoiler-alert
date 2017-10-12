package cs301r.spoileralert;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView sortOptionsTextView;
    private RadioButton sortByExpiryButton;
    private RadioButton sortByNameButton;
    private FloatingActionButton addFoodButton;
    private RecyclerView foodListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTextView = (TextView) findViewById(R.id.foodstuffsTitleTextView);
        sortOptionsTextView = (TextView) findViewById(R.id.sortOptionsTextView);
        sortByExpiryButton = (RadioButton) findViewById(R.id.sortByExpiryRadioButton);
        sortByNameButton = (RadioButton) findViewById(R.id.sortByNameRadioButton);

        addFoodButton = (FloatingActionButton) findViewById(R.id.addFoodFloatingButton);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        foodListRecyclerView = (RecyclerView) findViewById(R.id.foodListRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
