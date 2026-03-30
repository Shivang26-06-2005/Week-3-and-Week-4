import java.util.*;

public class RiskThresholdLookup {

    // ================= LINEAR SEARCH =================
    public static int linearSearch(int[] arr, int target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear Search -> Found at index: " + i + ", Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear Search -> Not Found, Comparisons: " + comparisons);
        return -1;
    }

    // ================= BINARY SEARCH (EXACT) =================
    public static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] == target) {
                System.out.println("Binary Search -> Found at index: " + mid + ", Comparisons: " + comparisons);
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Search -> Not Found, Comparisons: " + comparisons);
        return -1;
    }

    // ================= LOWER BOUND (Insertion Point) =================
    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        int comparisons = 0;

        while (low < high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        System.out.println("Lower Bound (Insertion Index): " + low + ", Comparisons: " + comparisons);
        return low;
    }

    // ================= FLOOR (largest ≤ target) =================
    public static Integer floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer result = null;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] == target) {
                result = arr[mid];
                break;
            } else if (arr[mid] < target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Floor: " + result + ", Comparisons: " + comparisons);
        return result;
    }

    // ================= CEILING (smallest ≥ target) =================
    public static Integer ceiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer result = null;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] == target) {
                result = arr[mid];
                break;
            } else if (arr[mid] > target) {
                result = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println("Ceiling: " + result + ", Comparisons: " + comparisons);
        return result;
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        int[] unsorted = {50, 10, 100, 25};
        int target = 30;

        System.out.println("Unsorted Risk Bands:");
        System.out.println(Arrays.toString(unsorted));

        // Linear search (unsorted)
        System.out.println("\n--- Linear Search ---");
        linearSearch(unsorted, target);

        // Sort for binary operations
        Arrays.sort(unsorted);

        System.out.println("\nSorted Risk Bands:");
        System.out.println(Arrays.toString(unsorted));

        // Binary search
        System.out.println("\n--- Binary Search ---");
        binarySearch(unsorted, target);

        // Lower bound (insertion point)
        System.out.println("\n--- Lower Bound ---");
        int insertIndex = lowerBound(unsorted, target);

        // Floor and Ceiling
        System.out.println("\n--- Floor & Ceiling ---");
        floor(unsorted, target);
        ceiling(unsorted, target);

        System.out.println("\nInsert " + target + " at index: " + insertIndex);
    }
}
