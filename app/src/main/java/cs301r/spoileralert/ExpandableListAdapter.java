package cs301r.spoileralert;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kcwillmore on 10/25/17.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;

    public ExpandableListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return FoodData.getFoodCount();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return FoodData.getFoodAt(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return FoodData.getFoodAt(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = ((FoodData) getGroup(groupPosition)).getName();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_parent, null);
        }

        TextView foodName = (TextView) convertView.findViewById(R.id.lblListHeader);
        foodName.setTypeface(null, Typeface.BOLD);
        foodName.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String foodAcquiry = ((FoodData) getChild(groupPosition, childPosition)).getAcquiry();
        String foodExpiry = ((FoodData) getChild(groupPosition, childPosition)).getExpiry();
        String foodNote = ((FoodData) getChild(groupPosition, childPosition)).getNote();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_child, null);
        }

        TextView acquiryText = (TextView) convertView.findViewById(R.id.foodList_acquiry);
        TextView expiryText = (TextView) convertView.findViewById(R.id.foodList_expiry);
        TextView foodNoteText = (TextView) convertView.findViewById(R.id.foodList_note);

        acquiryText.setText(foodAcquiry);
        expiryText.setText(foodExpiry);
        foodNoteText.setText(foodNote);

        ImageButton removeFoodButton = (ImageButton) convertView.findViewById(R.id.foodList_removeItemButton);
        removeFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodData.removeAt(groupPosition);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
