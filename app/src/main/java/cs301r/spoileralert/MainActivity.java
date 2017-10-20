package cs301r.spoileralert;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup sortRadioGroup;
    private RadioButton sortByExpiryButton;
    private RadioButton sortByNameButton;
    private FloatingActionButton addFoodButton;
    private ListView foodListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sortByExpiryButton = (RadioButton) findViewById(R.id.sortByExpiryRadioButton);
        sortByExpiryButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FoodData.sortByExpires();
                    foodListView.invalidateViews();
                }
            }
        });
        sortByNameButton = (RadioButton) findViewById(R.id.sortByNameRadioButton);
        sortByNameButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FoodData.sortByName();
                    foodListView.invalidateViews();
                }
            }
        });

        addFoodButton = (FloatingActionButton) findViewById(R.id.addFoodFloatingButton);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent i = new Intent(context, BarcodeScannerActivity.class);
                context.startActivity(i);
            }
        });

        foodListView = (ListView) findViewById(R.id.foodListView);
        FoodListAdapter adapter = new FoodListAdapter(this);
        foodListView.setAdapter(adapter);
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

    @Override
    protected void onResume() {
        super.onResume();

        if (sortByExpiryButton.isChecked()) {
            FoodData.sortByExpires();
        } else if (sortByNameButton.isChecked()) {
            FoodData.sortByName();
        }
        foodListView.invalidateViews();
    }
}
