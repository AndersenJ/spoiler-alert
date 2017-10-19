package cs301r.spoileralert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by kcwillmore on 10/12/17.
 */

public class FoodListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;

    public FoodListAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return FoodData.getFoodCount();
    }

    @Override
    public Object getItem(int position) {
        return FoodData.getFoodAt(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.food_list_item, parent, false);

        TextView itemName = (TextView) v.findViewById(R.id.textView_foodListName);
        TextView acquiryDate = (TextView) v.findViewById(R.id.textView_foodListAcq);
        TextView expiryDate = (TextView) v.findViewById(R.id.textView_foodListExp);
        TextView note = (TextView) v.findViewById(R.id.textView_foodListNote);

        itemName.setText(FoodData.getFoodAt(position).getName());
        acquiryDate.setText(FoodData.getFoodAt(position).getAcquiry());
        expiryDate.setText(FoodData.getFoodAt(position).getExpiry());
        note.setText(FoodData.getFoodAt(position).getNote());

        return v;
    }
}
