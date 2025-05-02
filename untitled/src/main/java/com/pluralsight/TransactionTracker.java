package com.pluralsight;
import java.util.Scanner;
import java.util.ArrayList;

public class TransactionTracker {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TransactionManager manager = new TransactionManager();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== HOME MENU ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    addTransaction(true);
                    break;
                case "P":
                    addTransaction(false);
                    break;
                case "L":
                    showLedger();
                    break;
                case "X":
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addTransaction(boolean isDeposit) {
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter time (HH:MM:SS): ");
        String time = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (!isDeposit) {
            amount = -Math.abs(amount); // Ensure payment is negative
        }

        Transactions t = new Transactions(date, time, description, vendor, amount);
        manager.addTransaction(t);
        System.out.println("Transaction saved!");
    }

    private static void showLedger() {
        boolean viewing = true;

        while (viewing) {
            System.out.println("\n=== LEDGER MENU ===");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    displayTransactions(manager.getAllTransactions());
                    break;
                case "D":
                    displayTransactions(manager.getDeposits());
                    break;
                case "P":
                    displayTransactions(manager.getPayments());
                    break;
                case "R":
                    showReports();
                    break;
                case "H":
                    viewing = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void showReports() {
        boolean inReports = true;

        while (inReports) {
            System.out.println("\n=== REPORTS MENU ===");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back to Ledger");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "5":
                    System.out.print("Enter vendor name to search: ");
                    String vendor = scanner.nextLine();
                    ArrayList<Transactions> results = manager.searchByVendor(vendor);
                    displayTransactions(results);
                    break;
                case "0":
                    inReports = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void displayTransactions(ArrayList<Transactions> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("\n--- Transactions ---");
            for (int i = transactions.size() - 1; i >= 0; i--) { // Show newest first
                System.out.println(transactions.get(i));
            }
        }
    }
}