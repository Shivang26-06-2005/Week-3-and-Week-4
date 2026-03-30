// Bubble Sort (by fee ASC)
static void bubbleSort(List<Transaction> list) {
    int swaps = 0, passes = 0;

    for (int i = 0; i < list.size() - 1; i++) {
        boolean swapped = false;
        passes++;

        for (int j = 0; j < list.size() - i - 1; j++) {
            if (list.get(j).fee > list.get(j + 1).fee) {
                Collections.swap(list, j, j + 1);
                swaps++;
                swapped = true;
            }
        }
        if (!swapped) break;
    }
    System.out.println("Passes=" + passes + ", Swaps=" + swaps);
}

// Insertion Sort (fee + timestamp)
static void insertionSort(List<Transaction> list) {
    for (int i = 1; i < list.size(); i++) {
        Transaction key = list.get(i);
        int j = i - 1;

        while (j >= 0 &&
              (list.get(j).fee > key.fee ||
              (list.get(j).fee == key.fee &&
               list.get(j).ts.isAfter(key.ts)))) {
            list.set(j + 1, list.get(j));
            j--;
        }
        list.set(j + 1, key);
    }
}

// Outliers
static void outliers(List<Transaction> list) {
    list.stream().filter(t -> t.fee > 50).forEach(System.out::println);
}
