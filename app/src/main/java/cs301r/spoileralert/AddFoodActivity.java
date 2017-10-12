package cs301r.spoileralert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddFoodActivity extends AppCompatActivity {

    private EditText foodName;
    private EditText acquiryDateEditText;
    private EditText expiryDateEditText;
    private EditText foodNoteEditText;
    private Button addFoodButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfoodform);

        foodName =(EditText) findViewById(R.id.editText_itemName);
        foodName.setText(getIntent().getStringExtra("FOOD_NAME"));

        acquiryDateEditText = (EditText) findViewById(R.id.editText_aquisitionDate);
        expiryDateEditText = (EditText) findViewById(R.id.editText_expiryDate);
        foodNoteEditText = (EditText) findViewById(R.id.editText_notes);

        addFoodButton = (Button) findViewById(R.id.button_add);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = foodName.getText().toString();
                String acquisition = acquiryDateEditText.getText().toString();
                String expiration = expiryDateEditText.getText().toString();
                String note = foodNoteEditText.getText().toString();

                FoodData.addFood(new FoodData(name, acquisition, expiration, note));

                //after adding food returns to scanner screen by finishing this activity
                finish();
            }
        });
        //TODO: only have add button enabled if fields are all filled in (maybe fill in with default info instead)


        backButton = (Button) findViewById(R.id.buttonback);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //returns to home, closing activities as it goes
                Intent intent = new Intent(AddFoodActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }
}
