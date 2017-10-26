package cs301r.spoileralert;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddFoodActivity extends AppCompatActivity {
    private enum DateType {ACQUIRY, EXPIRY};
    private static DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    public static EditText foodName;
    private TextView acquiryTextView;
    private TextView expiryTextView;
    private EditText foodNoteEditText;
    private Button addFoodButton;
    private Calendar acquiryDate;
    private Calendar expiryDate;
    private DateType lastSelected;
    private FloatingActionButton barcodeScannerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfoodform);

        acquiryDate = Calendar.getInstance();
        expiryDate = Calendar.getInstance();

        acquiryTextView = (TextView) findViewById(R.id.textView_acquisitionDate);
        acquiryTextView.setText(formatter.format(acquiryDate.getTime()));
        acquiryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelected = DateType.ACQUIRY;
                showDialog(998);
            }
        });

        expiryTextView = (TextView) findViewById(R.id.textView_expiryDate);
        expiryTextView.setText("");
        expiryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelected = DateType.EXPIRY;
                showDialog(999);
            }
        });

        foodNoteEditText = (EditText) findViewById(R.id.editText_notes);

        addFoodButton = (Button) findViewById(R.id.button_add);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = foodName.getText().toString();
                String note = foodNoteEditText.getText().toString();

                FoodData.addFood(new FoodData(name, acquiryDate, expiryDate, note));

                //after adding food returns to scanner screen by finishing this activity
                finish();
            }
        });
        addFoodButton.setEnabled(false);


        foodName =(EditText) findViewById(R.id.editText_itemName);
        foodName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String foodName = s.toString();
                addFoodButton.setEnabled(foodName.length() > 0);
            }
        });
        foodName.setText(getIntent().getStringExtra("FOOD_NAME"));

        barcodeScannerButton = (FloatingActionButton) findViewById(R.id.barcodeScannerButton);
        barcodeScannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFoodActivity.this, BarcodeScannerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 998) {
            return new DatePickerDialog(this, myDateListener, acquiryDate.get(Calendar.YEAR), acquiryDate.get(Calendar.MONTH), acquiryDate.get(Calendar.DATE));

        } else if (id == 999) {
            return new DatePickerDialog(this, myDateListener, expiryDate.get(Calendar.YEAR), expiryDate.get(Calendar.MONTH), expiryDate.get(Calendar.DATE));
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // arg1 = year
            // arg2 = month
            // arg3 = day
            setDate(lastSelected, arg1, arg2, arg3);
        }
    };

    private void setDate(DateType field, int year, int month, int day) {
        switch (field) {
            case ACQUIRY:
                acquiryDate.set(year, month, day);
                acquiryTextView.setText(formatter.format(acquiryDate.getTime()));
                break;
            case EXPIRY:
                expiryDate.set(year, month, day);
                expiryTextView.setText(formatter.format(expiryDate.getTime()));
                break;
        }
    }
}
