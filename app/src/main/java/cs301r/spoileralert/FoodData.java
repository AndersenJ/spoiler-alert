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

    public void setName(String name) {
        this.name = name;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static boolean addFood(FoodData food) {
        return allFoods.add(food);
    }

    //todo removal not yet supported, consider adding a unique id to reach food and making the list into a map?
}
