package cs301r.spoileralert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kcwillmore on 10/12/17.
 */

public class FoodData {
    private static List<FoodData> allFoods = new ArrayList<>();

    private String name;
    //TODO: make these into dates, not strings, so that we can access that info (calendar?)
    private String addDate;
    private String expiryDate;
    private String note;

    public FoodData(String name, String addDate, String expiryDate, String note) {
        this.name = name;
        this.addDate = addDate;
        this.expiryDate = expiryDate;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public static boolean addFood(FoodData food) {
        return allFoods.add(food);
    }

    public static int getFoodCount() {
        return allFoods.size();
    }

    public static FoodData getFoodAt(int i) {
        return allFoods.get(i);
    }

    //todo removal not yet supported, consider adding a unique id to reach food and making the list into a map?
}
