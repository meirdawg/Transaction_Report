package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reports {

    public static ArrayList<Transactions> monthToDate(ArrayList<Transactions> list) {
        ArrayList<Transactions> results = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Transactions t : list) {
            LocalDate tDate = LocalDate.parse(t.date());
            if (tDate.getMonth() == today.getMonth() && tDate.getYear() == today.getYear()) {
                results.add(t);
            }
        }
        return results;
    }

    public static ArrayList<Transactions> previousMonth(ArrayList<Transactions> list) {
        ArrayList<Transactions> results = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate previousMonth = today.minusMonths(1);

        for (Transactions t : list) {
            LocalDate tDate = LocalDate.parse(t.date());
            if (tDate.getMonth() == previousMonth.getMonth() && tDate.getYear() == previousMonth.getYear()) {
                results.add(t);
            }
        }
        return results;
    }

    public static ArrayList<Transactions> yearToDate(ArrayList<Transactions> list) {
        ArrayList<Transactions> results = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Transactions t : list) {
            LocalDate tDate = LocalDate.parse(t.date());
            if (tDate.getYear() == today.getYear()) {
                results.add(t);
            }
        }
        return results;
    }

    public static ArrayList<Transactions> previousYear(ArrayList<Transactions> list) {
        ArrayList<Transactions> results = new ArrayList<>();
        LocalDate today = LocalDate.now();
        int previousYear = today.getYear() - 1;

        for (Transactions t : list) {
            LocalDate tDate = LocalDate.parse(t.date());
            if (tDate.getYear() == previousYear) {
                results.add(t);
            }
        }
        return results;
    }

    public static ArrayList<Transactions> searchByVendor(ArrayList<Transactions> list, String vendor) {
        ArrayList<Transactions> results = new ArrayList<>();
        for (Transactions t : list) {
            if (t.vendor().equalsIgnoreCase(vendor)) {
                results.add(t);
            }
        }
        return results;
    }
}