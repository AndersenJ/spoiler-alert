package cs301r.spoileralert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static cs301r.spoileralert.R.id.textView_itemName;

public class AddFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfoodform);

        TextView name =(TextView)findViewById(R.id.textView_itemName);
        name.setText(getIntent().getStringExtra("FOOD_NAME"));

    }
}
