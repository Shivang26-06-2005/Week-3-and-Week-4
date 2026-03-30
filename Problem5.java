import java.util.*;

public class AccountSearchSystem {

    // ================= LINEAR SEARCH =================

    // Find first occurrence
    public static int linearFirst(String[] arr, String target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear First -> Index: " + i + ", Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear First -> Not Found, Comparisons: " + comparisons);
        return -1;
    }

    // Find last occurrence
    public static int linearLast(String[] arr, String target) {
        int comparisons = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear Last -> Index: " + i + ", Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear Last -> Not Found, Comparisons: " + comparisons);
        return -1;
    }

    // ================= BINARY SEARCH =================

    // Find first occurrence using binary search
    public static int binaryFirst(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1; // continue left
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary First -> Index: " + result + ", Comparisons: " + comparisons);
        return result;
    }

    // Find last occurrence using binary search
    public static int binaryLast(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1; // continue right
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Last -> Index: " + result + ", Comparisons: " + comparisons);
        return result;
    }

    // Count occurrences using binary search
    public static int countOccurrences(String[] arr, String target) {
        int first = binaryFirst(arr, target);
        int last = binaryLast(arr, target);

        if (first == -1) return 0;
        return last - first + 1;
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        System.out.println("Original Logs:");
        System.out.println(Arrays.toString(logs));

        // Linear Search (unsorted)
        System.out.println("\n--- Linear Search ---");
        linearFirst(logs, "accB");
        linearLast(logs, "accB");

        // Sort for binary search
        Arrays.sort(logs);

        System.out.println("\nSorted Logs:");
        System.out.println(Arrays.toString(logs));

        // Binary Search
        System.out.println("\n--- Binary Search ---");
        int first = binaryFirst(logs, "accB");
        int last = binaryLast(logs, "accB");

        int count = (first == -1) ? 0 : (last - first + 1);
        System.out.println("Total Occurrences of accB: " + count);
    }
}
