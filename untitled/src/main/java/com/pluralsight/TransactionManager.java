package com.pluralsight;
import java.io.*;
import java.util.ArrayList;

public class TransactionManager {private final String fileName = "transactions.csv";
    private final ArrayList<Transactions> transactions;

    public TransactionManager() {
        transactions = new ArrayList<>();
        loadTransactions(); // Load existing transactions from the file
    }

    // Load transactions from the CSV file
    private void loadTransactions() {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                // Create the file if it doesn't exist yet
                boolean newFile = file.createNewFile();
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                Transactions transaction = Transactions.fromCSV(line);
                transactions.add(transaction);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }

    // Save a new transaction to the file and list
    public void addTransaction(Transactions transaction) {
        transactions.add(transaction);
        try {
            FileWriter writer = new FileWriter(fileName, true); // Append mode
            writer.write(transaction.toCSV() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing transaction: " + e.getMessage());
        }
    }

    // Return all transactions
    public ArrayList<Transactions> getAllTransactions() {
        return transactions;
    }

    // Get only deposits (amount >= 0)
    public ArrayList<Transactions> getDeposits() {
        ArrayList<Transactions> deposits = new ArrayList<>();
        for (Transactions t : transactions) {
            if (t.amount() >= 0) {
                deposits.add(t);
            }
        }
        return deposits;
    }

    // Get only payments (amount < 0)
    public ArrayList<Transactions> getPayments() {
        ArrayList<Transactions> payments = new ArrayList<>();
        for (Transactions t : transactions) {
            if (t.amount() < 0) {
                payments.add(t);
            }
        }
        return payments;
    }

    // Search by vendor name (case-insensitive)
    public ArrayList<Transactions> searchByVendor(String vendorName) {
        ArrayList<Transactions> results = new ArrayList<>();
        for (Transactions t : transactions) {
            if (t.vendor().equalsIgnoreCase(vendorName)) {
                results.add(t);
            }
        }
        return results;
    }
}


