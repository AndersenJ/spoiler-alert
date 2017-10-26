package cs301r.spoileralert;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import cs301r.spoileralert.recyclerSimplified.FoodListRecycleAdapter;


public class MainActivity extends AppCompatActivity {

    private RadioButton sortByExpiryButton;
    private RadioButton sortByNameButton;
    private FloatingActionButton addFoodButton;
    private RecyclerView foodRecyclerView;
    private FoodListRecycleAdapter foodAdapter;

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
                    foodAdapter.notifyParentDataSetChanged(false);
                }
            }
        });
        sortByNameButton = (RadioButton) findViewById(R.id.sortByNameRadioButton);
        sortByNameButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FoodData.sortByName();
                    foodAdapter.notifyParentDataSetChanged(false);
                }
            }
        });

        addFoodButton = (FloatingActionButton) findViewById(R.id.addFoodFloatingButton);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent i = new Intent(context, AddFoodActivity.class);
                context.startActivity(i);
            }
        });
//
//        foodListView = (ListView) findViewById(R.id.foodListView);
//        FoodListAdapter adapter = new FoodListAdapter(this);
//        foodListView.setAdapter(adapter);
//
//        foodListView = (ExpandableListView) findViewById(R.id.foodListView);
//        ExpandableListAdapter adapter = new ExpandableListAdapter(this);
//        foodListView.setAdapter(adapter);

        foodRecyclerView = (RecyclerView) findViewById(R.id.foodRecyclerView);
        foodAdapter = new FoodListRecycleAdapter(this, FoodData.getAllFoods());
        foodRecyclerView.setAdapter(foodAdapter);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        foodAdapter.notifyParentDataSetChanged(false);
    }
}
