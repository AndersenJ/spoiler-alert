package cs301r.spoileralert;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cs301r.spoileralert.recyclerSimplified.Parent;

/**
 * Created by kcwillmore on 10/12/17.
 */

public class FoodData implements Serializable, Parent<FoodData> {
    private static List<FoodData> allFoods = load();
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
        if (allFoods.add(food)) {
            save();
            return true;
        } else {
            return false;
        }
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
        save();
    }

    public static boolean save() {
        try {
            String filename = "data/cs301r.spoileralert/foods.txt";
            File file = new File(Environment.getDataDirectory(), filename);
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(allFoods);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in foods.txt");
            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        }
    }

    public static ArrayList<FoodData> load() {
        try {
            FileInputStream fileIn = new FileInputStream(new File(Environment.getDataDirectory(), "data/cs301r.spoileralert/foods.txt"));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList loadedData = (ArrayList) in.readObject();
            in.close();
            fileIn.close();
            return loadedData;
        } catch (IOException i) {
            i.printStackTrace();
            return new ArrayList<>();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<FoodData> getChildList() {
        List<FoodData> childList = new ArrayList<>();
        childList.add(this);
        return childList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public static List<FoodData> getAllFoods() {
        return allFoods;
    }
}
