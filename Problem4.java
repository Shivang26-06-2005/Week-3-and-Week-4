import java.util.*;

public class PortfolioSorting {

    // Asset class
    static class Asset {
        String name;
        double returnRate;
        double volatility;

        public Asset(String name, double returnRate, double volatility) {
            this.name = name;
            this.returnRate = returnRate;
            this.volatility = volatility;
        }

        @Override
        public String toString() {
            return name + ":" + returnRate + "% (Vol:" + volatility + ")";
        }
    }

    // ================= MERGE SORT (ASCENDING, STABLE) =================
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private static void merge(Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            // <= ensures stability
            if (L[i].returnRate <= R[j].returnRate) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // ================= QUICK SORT (DESC + VOL ASC) =================
    static Random rand = new Random();

    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {

            // Hybrid: use insertion sort for small partitions
            if (high - low < 10) {
                insertionSort(arr, low, high);
                return;
            }

            int pivotIndex = medianOfThree(arr, low, high);
            swap(arr, pivotIndex, high);

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Partition logic
    private static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // Comparator: DESC return, ASC volatility
    private static int compare(Asset a, Asset b) {
        if (a.returnRate != b.returnRate) {
            return Double.compare(b.returnRate, a.returnRate); // DESC
        }
        return Double.compare(a.volatility, b.volatility); // ASC
    }

    // Median-of-3 pivot selection
    private static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        Asset a = arr[low];
        Asset b = arr[mid];
        Asset c = arr[high];

        if (compare(a, b) < 0) {
            if (compare(b, c) < 0) return mid;
            else if (compare(a, c) < 0) return high;
            else return low;
        } else {
            if (compare(a, c) < 0) return low;
            else if (compare(b, c) < 0) return high;
            else return mid;
        }
    }

    // Random pivot (optional alternative)
    private static int randomPivot(int low, int high) {
        return low + rand.nextInt(high - low + 1);
    }

    // Insertion sort (for small partitions)
    private static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = arr[i];
            int j = i - 1;

            while (j >= low && compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Swap helper
    private static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Print
    public static void printAssets(Asset[] arr) {
        for (Asset a : arr) {
            System.out.print(a + " | ");
        }
        System.out.println();
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        Asset[] assets = {
            new Asset("AAPL", 12, 5),
            new Asset("TSLA", 8, 9),
            new Asset("GOOG", 15, 4)
        };

        System.out.println("Original:");
        printAssets(assets);

        // Merge Sort (Ascending)
        mergeSort(assets, 0, assets.length - 1);
        System.out.println("\nMerge Sort (Ascending Return):");
        printAssets(assets);

        // Quick Sort (Descending + Volatility)
        quickSort(assets, 0, assets.length - 1);
        System.out.println("\nQuick Sort (DESC Return + ASC Volatility):");
        printAssets(assets);
    }
}
