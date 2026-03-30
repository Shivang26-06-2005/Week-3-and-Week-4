import java.util.*;
import java.time.LocalTime;

public class TransactionSorter {

    // Transaction class
    static class Transaction {
        String id;
        double fee;
        LocalTime timestamp;

        public Transaction(String id, double fee, String timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = LocalTime.parse(timestamp);
        }

        @Override
        public String toString() {
            return id + ":" + fee + "@" + timestamp;
        }
    }

    // Bubble Sort (by fee)
    public static void bubbleSortByFee(ArrayList<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // swap
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);

                    swaps++;
                    swapped = true;
                }
            }

            // early termination
            if (!swapped) break;
        }

        System.out.println("Bubble Sort -> Passes: " + passes + ", Swaps: " + swaps);
    }

    // Insertion Sort (by fee + timestamp)
    public static void insertionSortByFeeAndTime(ArrayList<Transaction> list) {
        int shifts = 0;

        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                  (list.get(j).fee > key.fee ||
                  (list.get(j).fee == key.fee &&
                   list.get(j).timestamp.isAfter(key.timestamp)))) {

                list.set(j + 1, list.get(j)); // shift right
                j--;
                shifts++;
            }

            list.set(j + 1, key);
        }

        System.out.println("Insertion Sort -> Shifts: " + shifts);
    }

    // Detect high-fee outliers
    public static void detectOutliers(ArrayList<Transaction> list) {
        System.out.println("High-fee outliers (>50):");
        boolean found = false;

        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("None");
        }
    }

    // Print transactions
    public static void printTransactions(ArrayList<Transaction> list) {
        for (Transaction t : list) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {

        ArrayList<Transaction> transactions = new ArrayList<>();

        // Sample input
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        int size = transactions.size();

        // Choose sorting strategy
        if (size <= 100) {
            bubbleSortByFee(transactions);
        } else if (size <= 1000) {
            insertionSortByFeeAndTime(transactions);
        } else {
            System.out.println("Dataset too large: Use advanced sorting (Merge/Quick Sort)");
        }

        // Output sorted transactions
        System.out.println("\nSorted Transactions:");
        printTransactions(transactions);

        // Detect outliers
        System.out.println();
        detectOutliers(transactions);
    }
}
