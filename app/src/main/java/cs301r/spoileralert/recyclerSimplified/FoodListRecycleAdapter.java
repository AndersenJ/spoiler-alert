package cs301r.spoileralert.recyclerSimplified;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cs301r.spoileralert.FoodData;
import cs301r.spoileralert.R;

/**
 * Created by kcwillmore on 10/26/17.
 */

public class FoodListRecycleAdapter extends ExpandableRecyclerAdapter<FoodData, FoodData, FoodParentViewHolder, FoodChildViewHolder> {

    private LayoutInflater inflater;

    public FoodListRecycleAdapter(Context context, @NonNull List<FoodData> parentList) {
        super(parentList);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FoodParentViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View foodParentView = inflater.inflate(R.layout.recycler_list_parent, parentViewGroup, false);
        return new FoodParentViewHolder(foodParentView);
    }

    @NonNull
    @Override
    public FoodChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View foodChildView = inflater.inflate(R.layout.recycler_list_child, childViewGroup, false);
        return new FoodChildViewHolder(foodChildView);
    }

    @Override
    public void onBindParentViewHolder(@NonNull FoodParentViewHolder parentViewHolder, int parentPosition, @NonNull FoodData parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull FoodChildViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull FoodData child) {
        childViewHolder.bind(child);
    }
}
