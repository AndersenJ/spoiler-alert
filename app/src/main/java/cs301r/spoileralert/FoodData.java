package cs301r.spoileralert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kcwillmore on 10/12/17.
 */

public class FoodData {
    private static List<FoodData> allFoods = new ArrayList<>();
    private static DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    private String name;
    private Calendar addDate;
    private Calendar expiryDate;
    private String note;

    public FoodData(String name, Calendar addDate, Calendar expiryDate, String note) {
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

    public String getAcquiry() {
        return formatter.format(addDate.getTime());
    }

    public String getExpiry() {
        return formatter.format(expiryDate.getTime());
    }

    public String getNote() {
        return note;
    }

    public static void sortByExpires() {
        Collections.sort(allFoods, FoodData.FoodExpiryComparator);
    }

    public static void sortByName() {
        Collections.sort(allFoods, FoodData.FoodNameComparator);
    }

    private static Comparator<FoodData> FoodExpiryComparator = new Comparator<FoodData>() {

        public int compare(FoodData f1, FoodData f2) {
            return f1.expiryDate.compareTo(f2.expiryDate);
        }};


    private static Comparator<FoodData> FoodNameComparator = new Comparator<FoodData>() {

        public int compare(FoodData f1, FoodData f2) {
            String foodName1 = f1.name.toUpperCase();
            String foodName2 = f2.name.toUpperCase();

            return foodName1.compareTo(foodName2);
        }};

    public static void removeAt(int i) {
        allFoods.remove(i);
    }
}
