package cs301r.spoileralert.recyclerSimplified;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import cs301r.spoileralert.FoodData;
import cs301r.spoileralert.R;

/**
 * Created by kcwillmore on 10/26/17.
 */

public class FoodChildViewHolder extends ChildViewHolder {

    private TextView acquiryTextView;
    private TextView expiryTextView;
    private TextView noteTextView;
    private ImageButton deleteFoodButton;

    public FoodChildViewHolder(@NonNull View itemView) {
        super(itemView);
        acquiryTextView = (TextView) itemView.findViewById(R.id.recyclerList_item_acquiry);
        expiryTextView = (TextView) itemView.findViewById(R.id.recyclerList_item_expiry);
        noteTextView = (TextView) itemView.findViewById(R.id.recyclerList_item_note);
        deleteFoodButton = (ImageButton) itemView.findViewById(R.id.recyclerList_removeItemButton);
    }

    public void bind(FoodData food) {
        acquiryTextView.setText(food.getAcquiry());
        expiryTextView.setText(food.getExpiry());
        noteTextView.setText(food.getNote());

        deleteFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = getParentAdapterPosition();
                FoodData.removeAt(i);
                mExpandableAdapter.notifyParentRemoved(i);

            }
        });
    }
}
