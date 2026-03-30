// Bubble ASC
static void bubble(Client[] arr) {
    int swaps = 0;
    for (int i = 0; i < arr.length - 1; i++) {
        for (int j = 0; j < arr.length - i - 1; j++) {
            if (arr[j].risk > arr[j + 1].risk) {
                Client temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                swaps++;
            }
        }
    }
    System.out.println("Swaps=" + swaps);
}

// Insertion DESC + balance
static void insertion(Client[] arr) {
    for (int i = 1; i < arr.length; i++) {
        Client key = arr[i];
        int j = i - 1;

        while (j >= 0 &&
              (arr[j].risk < key.risk ||
              (arr[j].risk == key.risk &&
               arr[j].balance < key.balance))) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}

// Top 10
static void topN(Client[] arr, int n) {
    for (int i = 0; i < n; i++)
        System.out.println(arr[i]);
}
