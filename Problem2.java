import java.util.*;

public class ClientRiskRanking {

    // Client class
    static class Client {
        String name;
        int riskScore;
        double accountBalance;

        public Client(String name, int riskScore, double accountBalance) {
            this.name = name;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        @Override
        public String toString() {
            return name + ":" + riskScore + " (Bal:" + accountBalance + ")";
        }
    }

    // Bubble Sort (Ascending by Risk Score)
    public static void bubbleSortAscending(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    // swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;

                    // visualize swap
                    System.out.println("Swapped: " + arr[j] + " <-> " + arr[j + 1]);
                }
            }

            if (!swapped) break; // optimization
        }

        System.out.println("Total swaps: " + swaps);
    }

    // Insertion Sort (Descending by Risk Score + Account Balance)
    public static void insertionSortDescending(Client[] arr) {
        int shifts = 0;

        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 &&
                  (arr[j].riskScore < key.riskScore ||
                  (arr[j].riskScore == key.riskScore &&
                   arr[j].accountBalance < key.accountBalance))) {

                arr[j + 1] = arr[j]; // shift
                j--;
                shifts++;
            }

            arr[j + 1] = key;
        }

        System.out.println("Total shifts: " + shifts);
    }

    // Print array
    public static void printClients(Client[] arr) {
        for (Client c : arr) {
            System.out.println(c);
        }
    }

    // Top N highest risk clients
    public static void topRiskClients(Client[] arr, int topN) {
        System.out.println("\nTop " + topN + " High-Risk Clients:");

        for (int i = 0; i < Math.min(topN, arr.length); i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {

        Client[] clients = {
            new Client("clientC", 80, 5000),
            new Client("clientA", 20, 2000),
            new Client("clientB", 50, 3000)
        };

        System.out.println("Original Data:");
        printClients(clients);

        // Bubble Sort (Ascending)
        System.out.println("\nBubble Sort (Ascending Risk):");
        bubbleSortAscending(clients);
        printClients(clients);

        // Insertion Sort (Descending)
        System.out.println("\nInsertion Sort (Descending Risk + Balance):");
        insertionSortDescending(clients);
        printClients(clients);

        // Top 10 high-risk clients
        topRiskClients(clients, 10);
    }
}
