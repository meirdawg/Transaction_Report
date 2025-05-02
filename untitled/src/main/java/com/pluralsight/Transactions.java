package com.pluralsight;

public record Transactions(String date, String time, String description, String vendor, double amount) {// Constructor

    // Create a transaction from a line in the CSV file
    public static Transactions fromCSV(String line) {
        String[] parts = line.split("\\|");
        String date = parts[0];
        String time = parts[1];
        String description = parts[2];
        String vendor = parts[3];
        double amount = Double.parseDouble(parts[4]);

        return new Transactions(date, time, description, vendor, amount);
    }

    // Convert the transaction to a line that can be saved to the CSV file
    public String toCSV() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
    }

    // Display the transaction nicely
    public String toString() {
        return date + " " + time + " | " + description + " | " + vendor + " | " + amount;
    }
}

