import java.util.*;

public class TradeVolumeAnalysis {

    // Trade class
    static class Trade {
        String id;
        int volume;

        public Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        @Override
        public String toString() {
            return id + ":" + volume;
        }
    }

    // ================= MERGE SORT (ASCENDING, STABLE) =================
    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private static void merge(Trade[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) { // <= ensures stability
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // ================= QUICK SORT (DESCENDING) =================
    public static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);

            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(Trade[] arr, int low, int high) {
        // Lomuto partition
        int pivot = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) { // DESCENDING
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(Trade[] arr, int i, int j) {
        Trade temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ================= MERGE TWO SORTED LISTS =================
    public static Trade[] mergeTwoSorted(Trade[] a, Trade[] b) {
        Trade[] result = new Trade[a.length + b.length];

        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    // ================= TOTAL VOLUME =================
    public static int totalVolume(Trade[] arr) {
        int sum = 0;
        for (Trade t : arr) {
            sum += t.volume;
        }
        return sum;
    }

    // ================= PRINT =================
    public static void printTrades(Trade[] arr) {
        for (Trade t : arr) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        Trade[] trades = {
            new Trade("trade3", 500),
            new Trade("trade1", 100),
            new Trade("trade2", 300)
        };

        System.out.println("Original:");
        printTrades(trades);

        // Merge Sort (Ascending)
        mergeSort(trades, 0, trades.length - 1);
        System.out.println("\nMerge Sort (Ascending):");
        printTrades(trades);

        // Quick Sort (Descending)
        quickSort(trades, 0, trades.length - 1);
        System.out.println("\nQuick Sort (Descending):");
        printTrades(trades);

        // Example: merge two sorted lists (morning + afternoon)
        Trade[] morning = {
            new Trade("m1", 100),
            new Trade("m2", 300)
        };

        Trade[] afternoon = {
            new Trade("a1", 200),
            new Trade("a2", 400)
        };

        // Ensure both are sorted before merging
        mergeSort(morning, 0, morning.length - 1);
        mergeSort(afternoon, 0, afternoon.length - 1);

        Trade[] merged = mergeTwoSorted(morning, afternoon);

        System.out.println("\nMerged Trades:");
        printTrades(merged);

        // Total volume
        int total = totalVolume(merged);
        System.out.println("\nTotal Volume: " + total);
    }
}
